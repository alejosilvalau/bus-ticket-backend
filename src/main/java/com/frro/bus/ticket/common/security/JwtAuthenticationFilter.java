package com.frro.bus.ticket.common.security;

import java.io.IOException;
import java.io.PrintWriter;

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

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
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

        // Allow swagger and related static endpoints to bypass this filter so Swagger
        // UI can fetch remote configuration without authentication.
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
                Claims claims = jwtUtil.validateAndExtractClaims(token);
                request.setAttribute("userId", Integer.parseInt(claims.getSubject()));
                request.setAttribute("email", claims.get("email", String.class));
                request.setAttribute("isAdmin", claims.get("isAdmin", Boolean.class));
            } catch (ExpiredJwtException e) {
                log.warn("JWT token expired for request {}: {}", path, e.getMessage());
                sendError(response, HttpServletResponse.SC_UNAUTHORIZED, "Token has expired");
                return;
            } catch (MalformedJwtException | SignatureException e) {
                log.warn("JWT token is invalid for request {}: {}", path, e.getMessage());
                sendError(response, HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
                return;
            } catch (IllegalArgumentException e) {
                log.warn("JWT token rejected for request {}: {}", path, e.getMessage());
                sendError(response, HttpServletResponse.SC_UNAUTHORIZED, "Token rejected");
                return;
            } catch (Exception e) {
                log.error("Unexpected error validating JWT for request {}: {}", path, e.getMessage(), e);
                sendError(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal server error");
                return;
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
            } else {
                sendError(response, HttpServletResponse.SC_NOT_FOUND, "Endpoint not found");
                return;
            }
        } catch (Exception e) {
            log.error("Unexpected error during endpoint access level resolution: {}", e.getMessage(), e);
            sendError(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal server error");
            return;
        }

        filterChain.doFilter(request, response);
    }

    private HandlerMethod getHandlerMethod(HttpServletRequest request) throws Exception {
        Object handler = handlerMapping.getHandler(request);
        if (handler instanceof HandlerMethod handlerMethod) {
            return handlerMethod;
        }
        if (handler instanceof HandlerExecutionChain chain) {
            Object inner = chain.getHandler();
            if (inner instanceof HandlerMethod innerHandlerMethod) {
                return innerHandlerMethod;
            }
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
        if (response.isCommitted()) {
            log.warn("Response already committed, cannot send error: {}", message);
            return;
        }
        response.setStatus(status);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        ApiResponse<Void> apiResponse = ApiResponse.error(message);
        PrintWriter writer = response.getWriter();
        writer.write(objectMapper.writeValueAsString(apiResponse));
        writer.flush();
    }
}
