package penta.sisPenta.gestaoFinanceira.Controller;

import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import penta.sisPenta.gestaoFinanceira.Model.Dto.Usuario.UsuarioDtoGET;
import penta.sisPenta.gestaoFinanceira.Service.UsuarioService;


@RestController
@RequestMapping("api/login")
public class LoginController {


    @Autowired
    UsuarioService usuarioService;


    @GetMapping
    public ResponseEntity<UsuarioDtoGET> verificar_login(){

        System.out.println("OK!!");

        return ResponseEntity.ok().body(new UsuarioDtoGET(usuarioService.listar_usuario(1L)));

    }


    /*@PostMapping
    public ResponseEntity<LoggedDtoLogin> verificar_login(@RequestBody @Valid UsuarioDtoLogin user){

        return ResponseEntity.ok().body(usuarioService.verificarLogin(user));

    }*/





}
