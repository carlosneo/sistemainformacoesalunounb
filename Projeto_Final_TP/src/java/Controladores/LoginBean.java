/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Logger;
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
    
    private static Logger log = Logger.getLogger(LoginBean.class.getName());
    
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
    
    public String login() {
        ResourceBundle myResource = ResourceBundle.getBundle("idiomas.idioma", FacesContext.getCurrentInstance().getViewRoot().getLocale());
        if (login.getLogin().equalsIgnoreCase("1045547") && login.getSenha().equals("1234")){
           log.info("Login Aluno efetuado com sucesso");
            return "SUCESSOaluno" ;
        } else {
            FacesContext.getCurrentInstance().addMessage(null , new FacesMessage(FacesMessage.SEVERITY_ERROR, myResource.getString("promptloginalunomenu7"), myResource.getString("promptloginalunomenu8")));
            log.info("Erro ao logar aluno");
            return "ERROaluno";
        } 
    }
    
       public String loginProf() {
        ResourceBundle myResource = ResourceBundle.getBundle("idiomas.idioma", FacesContext.getCurrentInstance().getViewRoot().getLocale());
        if (login.getLogin().equalsIgnoreCase("1234") && login.getSenha().equals("1234")){
            log.info("Login Aluno efetuado com sucesso");
            return "SUCESSOprof" ;
        } else {
            FacesContext.getCurrentInstance().addMessage(null , new FacesMessage(FacesMessage.SEVERITY_ERROR, myResource.getString("promptloginprof10"), myResource.getString("promptloginprof11")));
            log.info("Erro ao logar professor");
            return "ERROprof";
        } 
    }


}

