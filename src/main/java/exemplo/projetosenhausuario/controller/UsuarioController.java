package exemplo.projetosenhausuario.controller;

import exemplo.projetosenhausuario.model.UsuarioModel;
import exemplo.projetosenhausuario.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder encoder;

    public UsuarioController(UsuarioRepository usuarioRepository, PasswordEncoder encoder) {
        this.usuarioRepository = usuarioRepository;
        this.encoder = encoder;
    }

    @GetMapping("/listarTodos")
    public ResponseEntity<List<UsuarioModel>> listarTodos(){
        return ResponseEntity.ok(usuarioRepository.findAll());
    }

    @PostMapping("/salvar")
    public ResponseEntity<UsuarioModel> salvar(@RequestBody UsuarioModel usuarioModel){
        usuarioModel.setPassword(encoder.encode(usuarioModel.getPassword()));
        return ResponseEntity.ok(usuarioRepository.save(usuarioModel));
    }

    @GetMapping("/validarSenha")
    public ResponseEntity<Boolean> validarSenha(@RequestParam String login,
                                                @RequestParam String password){

        Optional<UsuarioModel> optUsuario = usuarioRepository.findByLogin(login);
    if(optUsuario.isEmpty()){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
    }

    UsuarioModel usuario = optUsuario.get();
    boolean valid = encoder.matches(password, usuario.getPassword());

    HttpStatus status = (valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
    return ResponseEntity.status(status).body(valid);

    }
}
