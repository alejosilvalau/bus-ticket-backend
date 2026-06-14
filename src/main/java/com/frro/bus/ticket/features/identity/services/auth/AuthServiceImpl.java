package com.frro.bus.ticket.features.identity.services.auth;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.frro.bus.ticket.common.exceptions.InvalidCredentialsException;
import com.frro.bus.ticket.common.security.JwtUtil;
import com.frro.bus.ticket.features.identity.dtos.user.ChangePasswordUserDTO;
import com.frro.bus.ticket.features.identity.dtos.user.LoginResponseDTO;
import com.frro.bus.ticket.features.identity.dtos.user.LoginUserDTO;
import com.frro.bus.ticket.features.identity.dtos.user.UserDTO;
import com.frro.bus.ticket.features.identity.entities.User;
import com.frro.bus.ticket.features.identity.mappers.UserMapper;
import com.frro.bus.ticket.features.identity.repositories.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final HttpServletRequest request;

    @Override
    public LoginResponseDTO login(LoginUserDTO userRequest) {
        User user = userRepository.findByEmail(userRequest.email())
                .filter(userFound -> passwordEncoder.matches(userRequest.password(), userFound.getPassword()))
                .orElseThrow(InvalidCredentialsException::new);

        UserDTO userDTO = userMapper.toUserDTO(user);
        String token = jwtUtil.generateToken(user.getId(), user.getEmail(), user.isAdmin());
        return new LoginResponseDTO(token, userDTO);
    }

    @Override
    public boolean logout() {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            jwtUtil.blacklistToken(token);
        }
        return true;
    }

    @Override
    public boolean changePassword(ChangePasswordUserDTO userRequest) {
        User user = userRepository.findByEmail(userRequest.email())
                .filter(userFound -> passwordEncoder.matches(userRequest.password(), userFound.getPassword()))
                .orElseThrow(() -> new InvalidCredentialsException("Invalid current credentials"));

        String hashedPassword = passwordEncoder.encode(userRequest.newPassword());
        user.setPassword(hashedPassword);
        userRepository.save(user);
        return true;
    }
}
