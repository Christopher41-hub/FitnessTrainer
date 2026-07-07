package de.htw_berlin.FitnessTrainer;

import de.htw_berlin.FitnessTrainer.dto.AuthResponse;
import de.htw_berlin.FitnessTrainer.dto.LoginRequest;
import de.htw_berlin.FitnessTrainer.dto.RegisterRequest;
import de.htw_berlin.FitnessTrainer.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fitnesstrainer/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            return ResponseEntity.status(409).body("Diese E-Mail wird bereits verwendet.");
        }

        User user = new User(
                request.email(),
                passwordEncoder.encode(request.password()),
                request.name()
        );
        userRepository.save(user);

        String token = jwtUtil.generateToken(user.getEmail(), user.getId());
        return ResponseEntity.ok(new AuthResponse(token, user.getId(), user.getEmail(), user.getName()));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        User user = userRepository.findByEmail(request.email()).orElse(null);

        if (user == null || !passwordEncoder.matches(request.password(), user.getPassword())) {
            return ResponseEntity.status(401).body("E-Mail oder Passwort ist falsch.");
        }

        String token = jwtUtil.generateToken(user.getEmail(), user.getId());
        return ResponseEntity.ok(new AuthResponse(token, user.getId(), user.getEmail(), user.getName()));
    }

    @GetMapping("/me")
    public ResponseEntity<?> me(@AuthenticationPrincipal User user) {
        if (user == null) {
            return ResponseEntity.status(401).build();
        }
        return ResponseEntity.ok(new AuthResponse(null, user.getId(), user.getEmail(), user.getName()));
    }
}