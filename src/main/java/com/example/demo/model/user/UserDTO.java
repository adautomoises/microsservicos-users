package com.example.demo.model.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    @NotBlank(message="Nome é obrigatório")
    private	String	nome;
    @NotBlank(message="CPF é obrigatório")
    private	String	cpf;
    private	String	endereco;
    private	String	email;
    @NotBlank(message="E-mail é obrigatório")
    private	String	telefone;
    private LocalDateTime dataCadastro;

    public	static	UserDTO	convert(User	user)	{
        UserDTO	userDTO	=	new	UserDTO();
        userDTO.setNome(user.getNome());
        userDTO.setEndereco(user.getEndereco());
        userDTO.setCpf(user.getCpf());
        userDTO.setEmail(user.getEmail());
        userDTO.setTelefone(user.getTelefone());
        userDTO.setDataCadastro(user.getDataCadastro());
        return	userDTO;
    }
}
