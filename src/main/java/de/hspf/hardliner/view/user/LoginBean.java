package de.hspf.hardliner.view.user;

import de.hspf.hardliner.view.util.UserManager;
import de.hspf.hardliner.logic.UserController;
import de.hspf.hardliner.view.util.ViewContextUtil;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

/**
 *
 * @author thomas
 */
@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    private boolean loggedIn;
    private String userLoggedIn;
    private String userName;
    private String password;

    @Inject
    UserBean userBean;
    @EJB
    UserController userController;

    public LoginBean() {
        setLoggedIn(false);
    }

    /**
     * *
     * This method will do a authentication. If the authentication is
     * successful, the user will be redirected to a personal deshboard page. If
     * the authentication is not successful the user will remain on the login
     * page.
     *
     * @return
     */
    public String authenticate() {
        if (userController.validateUser(userName, password)) {
            userBean.setUser(userController.getUser(userName, password));
            setLoggedIn(true);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", userBean.getUser());
            ViewContextUtil.getFacesContext().addMessage(null, new FacesMessage("Login sucessfull."));
            return "home";
        } else {
            ViewContextUtil.getFacesContext().addMessage(null, new FacesMessage("Login failed, either username or password are wrong. You provided:" + getUserName()));
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Wrong", "Login failed, either username or password are wrong. You provided:" + getUserName()));
            return "login";
        }
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

}
