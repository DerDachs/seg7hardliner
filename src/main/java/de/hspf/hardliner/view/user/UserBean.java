package de.hspf.hardliner.view.user;

import de.hspf.hardliner.model.Users;
import de.hspf.hardliner.view.util.CreatorUtil;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.inject.Inject;

/**
 *
 * @author thomas
 */
@Named(value = "userBean")
@SessionScoped
public class UserBean implements Serializable {

    private Users user;
    @Inject
    private CreatorUtil util;
   
    public UserBean() {
    }
    
    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String getUserName() {
        return user.getUsername();
    }
    
    

}
