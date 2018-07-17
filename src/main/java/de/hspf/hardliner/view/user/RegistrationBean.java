package de.hspf.hardliner.view.user;

import de.hspf.hardliner.logic.UserController;
import de.hspf.hardliner.model.Users;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author thomas.schuster
 */
@Named(value = "registrationBean")
@SessionScoped
public class RegistrationBean implements Serializable {

    private static final Logger logger = LogManager.getLogger(RegistrationBean.class);
    
    @EJB
    private UserController controller;
    
    private String salutation;
    private String name;
    private String surName;
    private String email;
    private String username;
    private String password;
    private String repeatedpassword;
    
    public RegistrationBean() {
    }

    public String register() {
        logger.info("started to register user");
        if (password.equals(repeatedpassword)) {
            logger.info("call user manager");
            Users user2register = createUser();
//            controller.registerUser(user2register);
            logger.info("user registration successful");
            return "index";
        }
        return "registration";
    }

    private Users createUser() {
        Users user2register = new Users();
        user2register.setUsername(username);
        user2register.setPassword(password);
        user2register.setMail(email);
        user2register.setLastname(name);
        user2register.setSurname(surName);
        return user2register;
    }
    
    public String getSalutation() {
        return salutation;
    }

    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatedpassword() {
        return repeatedpassword;
    }

    public void setRepeatedpassword(String repeatedpassword) {
        this.repeatedpassword = repeatedpassword;
    }
    
}
