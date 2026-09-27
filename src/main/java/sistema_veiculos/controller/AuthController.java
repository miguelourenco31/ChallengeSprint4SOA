package sistema_veiculos.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import sistema_veiculos.dto.*;
import sistema_veiculos.model.Usuario;
import sistema_veiculos.repository.UsuarioRepository;
import sistema_veiculos.security.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authManager, JwtUtil jwtUtil,
                           UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody LoginRequest request) {
        Usuario usuario = new Usuario();
        usuario.setUsername(request.getUsername());
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        usuarioRepository.save(usuario);
        return ResponseEntity.ok("Usuário criado com sucesso");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        String token = jwtUtil.gerarToken(request.getUsername());
        return ResponseEntity.ok(new LoginResponse(token));
    }
}
