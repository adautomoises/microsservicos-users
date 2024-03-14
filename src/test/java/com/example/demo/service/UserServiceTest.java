package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.user.DTOConverter;
import com.example.demo.model.user.User;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    public static User getUser(Long id, String nome, String cpf) {
        User user = new User();
        user.setId(id);
        user.setNome(nome);
        user.setCpf(cpf);
        user.setEndereco("rua blablabla");
        user.setTelefone("85 99999999");
        return user;
    }

    @Test
    public void testListAllUsers() {
        List<User> users = new ArrayList<>();
        users.add(getUser(1L, "Adauto Moisés", "620"));
        users.add(getUser(2L, "Rafael Aires", "621"));

        Mockito.when(userRepository.findAll()).thenReturn(users);

        List<UserDTO> usersReturn = userService.getUsuarios();
        Assertions.assertEquals(2, usersReturn.size());
    }

    @Test
    public void testSaveUser() {
        User userMock = getUser(1L, "Adauto Moisés", "620");
        UserDTO userDTO = DTOConverter.convert(userMock);

        Mockito.when(userRepository.save(Mockito.any())).thenReturn(userMock);

        UserDTO user = userService.inserirUsuario(userDTO);
        Assertions.assertEquals("Adauto Moisés", user.getNome());
        Assertions.assertEquals("620", user.getCpf());
    }

    @Test
    public void testEditUser(){
        User userMock = getUser(1L, "Adauto Moisés", "620");

        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(userMock));
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(userMock);

        UserDTO userDTO = DTOConverter.convert(userMock);
        userDTO.setEndereco("Rua tal");
        userDTO.setTelefone("telefone");

        UserDTO user = userService.editarUsuario(1L, userDTO);
        Assertions.assertEquals("Rua tal", user.getEndereco());
        Assertions.assertEquals("telefone", user.getTelefone());
    }
}
