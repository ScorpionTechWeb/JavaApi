package com.example.javaapi.controllers;

import com.example.javaapi.models.Userbean;
import com.example.javaapi.models.daos.Userdao;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class LoginController {

    private final Userdao userdao;

    public LoginController(Userdao userdao) {
        this.userdao = userdao;
    }

    //http://localhost:8080/login
    @PostMapping("/login")
    Userbean login(@RequestBody Userbean user) {

        System.out.println("/login user = $user");
        try {
            if (user.getLogin().isEmpty() || user.getLogin().isBlank()) {
                throw new Exception("Le pseudo est vide ou manquant");
            }
            if (user.getPassword().isEmpty() || user.getPassword().isBlank()) {
                throw new Exception("Le password est vide ou manquant");
            }
            List<Userbean> users = userdao.findAllByLogin(user.getLogin());
            if (users.isEmpty()) {
                throw new Exception("Il n'y a pas d'utilisateur s'appelant ${user.getLogin()}");
            } else if (BCrypt.checkpw(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12)), users.get(0).getPassword())) {
                throw new Exception("le mot de passe ne correspond pas à ${user.getLogin()}");
            }

            String name = users.get(0).getName();
            int idRole = users.get(0).getIdRole();
            String login = users.get(0).getLogin();
            return new Userbean(name, idRole, login);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}