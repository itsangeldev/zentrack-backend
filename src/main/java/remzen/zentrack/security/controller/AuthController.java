package remzen.zentrack.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import remzen.zentrack.security.controller.dto.AuthResponse;
import remzen.zentrack.security.controller.dto.LoginRequest;
import remzen.zentrack.security.controller.dto.ResetPasswordRequest;
import lombok.RequiredArgsConstructor;
import remzen.zentrack.security.service.AuthService;

@CrossOrigin(origins = {"http://localhost:8081", "http://192.168.1.88:8081"})
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<AuthResponse> resetPassword(@RequestBody ResetPasswordRequest request) {
        return ResponseEntity.ok(authService.resetPassword(request));
    }
}