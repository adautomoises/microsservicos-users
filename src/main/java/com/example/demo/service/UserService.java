package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.user.DTOConverter;
import com.example.demo.model.user.User;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<UserDTO> getUsuarios() {
        List<User> usuarios = userRepository.findAll();

        return usuarios
                .stream()
                .map(DTOConverter::convert)
                .collect(Collectors.toList());
    }

    public UserDTO getUsuarioById(long userId) {
        User usuario = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado")
                );
        return DTOConverter.convert(usuario);
    }

    public UserDTO inserirUsuario(UserDTO userDTO) {
        userDTO.setDataCadastro(LocalDateTime.now());
        User usuario = userRepository.save(DTOConverter.convert(userDTO));
        return DTOConverter.convert(usuario);
    }

    public UserDTO removerUsuario(long userId) {
        User user = userRepository
                .findById(userId).orElseThrow(() -> new RuntimeException(
                ));
        userRepository.delete(user);
        return DTOConverter.convert(user);
    }

    public UserDTO getUsuarioByCpf(String cpf) {
        User usuario = userRepository.findByCpf(cpf);
        if (usuario != null) {
            return DTOConverter.convert(usuario);
        }
        return null;
    }

    public List<UserDTO> queryByName(String name) {
        List<User> usuarios = userRepository.buscarUsuario(name);
        return usuarios
                .stream()
                .map(DTOConverter::convert)
                .collect(Collectors.toList());
    }

    public UserDTO editarUsuario(Long userId, UserDTO userDTO) {
        User usuario = userRepository
                .findById(userId).orElseThrow(RuntimeException::new);
        if (userDTO.getEmail() != null &&
                !usuario.getEmail().equals(userDTO.getEmail())) {
            usuario.setEmail(userDTO.getEmail());
        }
        if (userDTO.getTelefone() != null &&
                !usuario.getTelefone().equals(userDTO.getTelefone())) {
            usuario.setTelefone(userDTO.getTelefone());
        }
        if (userDTO.getEndereco() != null &&
                !usuario.getEndereco().equals(userDTO.getEndereco())) {
            usuario.setEndereco(userDTO.getEndereco());
        }
        usuario = userRepository.save(usuario);
        return DTOConverter.convert(usuario);
    }

    public Page<UserDTO> getUsuariosPaginados(Pageable page){
        Page<User> usuarios = userRepository.findAll(page);

        return usuarios.map(DTOConverter::convert);
    }
}
