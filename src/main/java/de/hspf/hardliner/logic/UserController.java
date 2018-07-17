/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hspf.hardliner.logic;

import de.hspf.hardliner.model.Users;
import de.hspf.hardliner.view.util.UserManager;
import java.util.Collection;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author thomas.schuster
 */
@Stateless
public class UserController {

    @Inject
    private UserManager userManager;

    @PostConstruct
    private void init() {
    }

//    public boolean registerUser(Users user2register) {
//        return userManager.registerUser(user2register);
//    }

    public boolean validateUser(String username, String password) {
        return (userManager.validateUser(username, password) != null);
    }

    public Users getUser(String username, String password) {
        return userManager.validateUser(username, password);
    }

    
    public Users getUserByName(String username) {
        return userManager.getFacade().findByName(username);
        
    }

//    @GET
//    @Path("getuserlist")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Collection<Users> getListOfUsers() {
//        return userManager.getUsers();
//    }

}
