package com.frro.bus.ticket.common.security;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.HandlerExecutionChain;

import com.frro.bus.ticket.common.dto.ApiResponse;
import com.frro.bus.ticket.common.security.endpointhelpers.AdminEndpoint;
import com.frro.bus.ticket.common.security.endpointhelpers.AuthenticatedEndpoint;
import com.frro.bus.ticket.common.security.endpointhelpers.PublicEndpoint;

import tools.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private final JwtUtil jwtUtil;
    private final RequestMappingHandlerMapping handlerMapping;
    private final ObjectMapper objectMapper;

    public JwtAuthenticationFilter(JwtUtil jwtUtil, RequestMappingHandlerMapping handlerMapping,
            ObjectMapper objectMapper) {
        this.jwtUtil = jwtUtil;
        this.handlerMapping = handlerMapping;
        this.objectMapper = objectMapper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        // Allow swagger and related static endpoints to bypass this filter so Swagger UI
        // can fetch remote configuration without authentication.
        String path = request.getRequestURI();
        if (path != null && (path.startsWith("/v3/api-docs") || path.startsWith("/swagger-ui")
                || path.equals("/swagger-ui.html") || path.startsWith("/swagger-resources")
                || path.startsWith("/webjars"))) {
            filterChain.doFilter(request, response);
            return;
        }

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);

            try {
                if (jwtUtil.validateToken(token)) {
                    int userId = jwtUtil.extractUserId(token);
                    String email = jwtUtil.extractEmail(token);
                    boolean isAdmin = jwtUtil.extractIsAdmin(token);

                    request.setAttribute("userId", userId);
                    request.setAttribute("email", email);
                    request.setAttribute("isAdmin", isAdmin);
                }
            } catch (Exception e) {
                log.warn("Invalid JWT token: {}", e.getMessage());
            }
        }

        try {
            HandlerMethod handlerMethod = getHandlerMethod(request);
            if (handlerMethod != null) {
                if (handlerMethod.hasMethodAnnotation(AdminEndpoint.class)
                        || handlerMethod.getBeanType().isAnnotationPresent(AdminEndpoint.class)) {
                    if (!isAdmin(request)) {
                        sendError(response, HttpServletResponse.SC_FORBIDDEN, "Admin access required");
                        return;
                    }
                } else if (handlerMethod.hasMethodAnnotation(AuthenticatedEndpoint.class)
                        || handlerMethod.getBeanType().isAnnotationPresent(AuthenticatedEndpoint.class)) {
                    if (!hasToken(authHeader)) {
                        sendError(response, HttpServletResponse.SC_UNAUTHORIZED, "Authentication required");
                        return;
                    }
                } else if (handlerMethod.hasMethodAnnotation(PublicEndpoint.class)
                        || handlerMethod.getBeanType().isAnnotationPresent(PublicEndpoint.class)) {
                    // Public endpoint, no auth required
                } else {
                    if (!hasToken(authHeader)) {
                        sendError(response, HttpServletResponse.SC_UNAUTHORIZED, "Authentication required");
                        return;
                    }
                }
            }
        } catch (Exception e) {
            log.debug("Could not determine endpoint access level: {}", e.getMessage());
        }

        filterChain.doFilter(request, response);
    }

    private HandlerMethod getHandlerMethod(HttpServletRequest request) {
        try {
            Object handler = handlerMapping.getHandler(request);
            // RequestMappingHandlerMapping.getHandler(...) may return a HandlerExecutionChain
            // which wraps the actual handler (often a HandlerMethod). Unwrap when necessary.
            if (handler instanceof HandlerMethod handlerMethod) {
                return handlerMethod;
            }
            if (handler instanceof HandlerExecutionChain chain) {
                Object inner = chain.getHandler();
                if (inner instanceof HandlerMethod innerHandlerMethod) {
                    return innerHandlerMethod;
                }
            }
        } catch (Exception e) {
            log.debug("Could not resolve handler method: {}", e.getMessage());
        }
        return null;
    }

    private boolean hasToken(String authHeader) {
        return authHeader != null && authHeader.startsWith("Bearer ");
    }

    private boolean isAdmin(HttpServletRequest request) {
        Object isAdmin = request.getAttribute("isAdmin");
        return isAdmin instanceof Boolean && (Boolean) isAdmin;
    }

    private void sendError(HttpServletResponse response, int status, String message) throws IOException {
        response.setStatus(status);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        ApiResponse<Void> apiResponse = ApiResponse.error(message);
        response.getWriter().write(objectMapper.writeValueAsString(apiResponse));
        response.getWriter().flush();
    }
}
