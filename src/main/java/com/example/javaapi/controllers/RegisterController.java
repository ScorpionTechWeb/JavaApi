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
public class RegisterController {

    private final Userdao userdao;

    public RegisterController(Userdao userdao) {
        this.userdao = userdao;
    }

    //http://localhost:8080/register
    @PostMapping("/register")
    Userbean register(@RequestBody Userbean user) {

        System.out.println("/register user = " + user);
        try {
            System.out.println(user.getLogin());
            if (user.getLogin().isEmpty() || user.getLogin().isBlank()) {
                throw new Exception("Le pseudo est vide ou manquant");
            }
            System.out.println(user.getPassword());
            if (user.getPassword().isEmpty() || user.getPassword().isBlank()) {
                throw new Exception("Le password est vide ou manquant");
            }
            System.out.println(user.getName());
            List<Userbean> users = userdao.findAllByLogin(user.getLogin());
            System.out.println(users);
            if (!users.isEmpty()) {
                throw new Exception("Il y a déjà un utilisateur s'appelant ${user.getLogin()}");
            } else {
                // Hashage d'un mot de passe
                // Il est possible d'augmenter la complexité (et donc le temps
                // de traitement) en passant le "workfactor" en paramètre
                // ici : 12 La valeur par défaut est 10
                // Il est possible d'augmenter la complexité (et donc le temps
                // de traitement) en passant le "workfactor" en paramètre
                // ici : 12 La valeur par défaut est 10
                String passHashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12));
                String login = user.getLogin();
                String name = user.getName();
                int idRole = 2;
                Userbean userToSave = new Userbean(login, passHashed, name, idRole);
                userdao.save(userToSave);
                return userToSave;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}