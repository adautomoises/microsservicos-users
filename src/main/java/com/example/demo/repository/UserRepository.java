package com.example.demo.repository;

import com.example.demo.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByCpf(String cpf);

    @Query(value = "SELECT * FROM users.user WHERE nome LIKE concat(:name, '%')", nativeQuery = true)
    List<User> buscarUsuario(@Param("name") String name);
}
