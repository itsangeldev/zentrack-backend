package remzen.zentrack.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import remzen.zentrack.models.users.model.BeanUser;
import remzen.zentrack.models.users.model.UserRepository;
import remzen.zentrack.security.controller.dto.AuthResponse;
import remzen.zentrack.security.controller.dto.LoginRequest;
import remzen.zentrack.security.controller.dto.ResetPasswordRequest;
import remzen.zentrack.security.jwt.JwtService;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    public String variable;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        BeanUser user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String token = jwtService.generateToken(user);

        return new AuthResponse(token);
    }

    public AuthResponse resetPassword(ResetPasswordRequest request) {
        BeanUser user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (!passwordEncoder.matches(request.getSecretWord(), user.getSecretWord())) {
            throw new IllegalArgumentException("Invalid secret word");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);

        return new AuthResponse(jwtService.generateToken(user));
    }
}