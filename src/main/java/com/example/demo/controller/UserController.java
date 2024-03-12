package com.example.demo.controller;

import com.example.demo.model.user.User;
import com.example.demo.model.user.UserDTO;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<UserDTO> getUsuarios() {
        return userService.getUsuarios();
    }

    @GetMapping("/{id}")
    public UserDTO getUsuario(@PathVariable Long id) {
        return userService.getUsuarioById(id);
    }

    @GetMapping("/{cpf}/cpf")
    public UserDTO getUsuario(@PathVariable String cpf) {
        return userService.getUsuarioByCpf(cpf);
    }

    @GetMapping("/search")
    public List<UserDTO> queryByName(@RequestParam(name = "nome") String nome) {
        return userService.queryByName(nome);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO inserirUsuario(@RequestBody @Valid UserDTO userDTO) {
        return userService.inserirUsuario(userDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public UserDTO removerUsuario(@PathVariable Long id) {
        return userService.removerUsuario(id);
    }

    @PatchMapping("/{id}")
    public UserDTO editarUsuario(@PathVariable Long id, @RequestBody UserDTO userDTO){
        return userService.editarUsuario(id, userDTO);
    }

    @GetMapping("/lista")
    public Page<UserDTO> getUsuariosPaginados(Pageable pageable){
        return userService.getUsuariosPaginados(pageable);
    }
}
