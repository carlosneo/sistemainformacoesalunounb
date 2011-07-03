/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

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
        if (login.getLogin().equalsIgnoreCase("1045547") && login.getSenha().equals("1234")){
           log.info("Login Aluno efetuado com sucesso");
            return "SUCESSOaluno" ;
        } else {
            FacesContext.getCurrentInstance().addMessage(null , new FacesMessage(FacesMessage.SEVERITY_ERROR,"#{msg.promptloginalunomenu7}", "#{msg.promptloginalunomenu8}"));
            log.info("Erro ao logar aluno");
            return "ERROaluno";
        } 
    }
    
       public String loginProf() {
        if (login.getLogin().equalsIgnoreCase("1234") && login.getSenha().equals("1234")){
            log.info("Login Aluno efetuado com sucesso");
            return "SUCESSOprof" ;
        } else {
            FacesContext.getCurrentInstance().addMessage(null , new FacesMessage(FacesMessage.SEVERITY_ERROR,"O número da matricula ou a senha estão incorretos. Tente novamente.", "Login invalido!"));
            log.info("Erro ao logar professor");
            return "ERROprof";
        } 
    }


}

