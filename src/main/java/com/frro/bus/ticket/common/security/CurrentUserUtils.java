package com.frro.bus.ticket.common.security;

import com.frro.bus.ticket.common.exceptions.BusinessException;

import jakarta.servlet.http.HttpServletRequest;

public abstract class CurrentUserUtils {

    public static int getAuthenticatedUserId(HttpServletRequest request) {
        Object userId = request.getAttribute("userId");
        if (userId == null) {
            throw new BusinessException("No authenticated user found");
        }
        return (int) userId;
    }

    public static boolean isAdmin(HttpServletRequest request) {
        Object isAdmin = request.getAttribute("isAdmin");
        return isAdmin instanceof Boolean && (Boolean) isAdmin;
    }
}
