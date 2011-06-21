/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Guilherme
 */
@ManagedBean(name="loginBean")
@RequestScoped
public class LoginBean {

    private Login login;

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }
    
    /** Creates a new instance of LoginBean */
    public LoginBean() {
        login = new Login();
    }
    
    public void login() {
        if (login.getLogin().equalsIgnoreCase("1045547") && login.getSenha().equals("1234")){
           
        } else {
            FacesContext.getCurrentInstance().addMessage(null , new FacesMessage(FacesMessage.SEVERITY_ERROR,"O número da matricula ou a senha estão incorretos. Tente novamente.", "Login invalido!"));
    }
}
}
