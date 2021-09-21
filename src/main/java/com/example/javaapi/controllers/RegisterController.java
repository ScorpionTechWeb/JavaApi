package com.example.javaapi.controllers;

import com.example.javaapi.models.Message;
import com.example.javaapi.models.Rolebean;
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
    Object register(@RequestBody Userbean user) {

        Message message = new Message();
        System.out.println("/register user = " + user);
        try {
            if (user.getLogin().isEmpty() || user.getLogin().isBlank()) {
                message.setMessage("Le pseudo est vide ou manquant");
                message.setCode(411);
                throw new Exception(message.getMessage());
            }
            if (user.getPassword().isEmpty() || user.getPassword().isBlank()) {
                message.setMessage("Le password est vide ou manquant");
                message.setCode(412);
                throw new Exception(message.getMessage());
            }
            List<Userbean> users = userdao.findAllByLogin(user.getLogin());
            if (!users.isEmpty()) {
                message.setMessage("Il y a déjà un utilisateur s'appelant " + user.getLogin());
                message.setCode(415);
                throw new Exception(message.getMessage());
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
                Rolebean role = new Rolebean();
                role.setIdRole(2);
                Userbean userToSave = new Userbean(login, passHashed, name, role);
                userdao.save(userToSave);
                return userToSave;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return message;
        }
    }
}
