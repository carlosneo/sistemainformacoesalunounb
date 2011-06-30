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
@ManagedBean (name="questionarioBean")
@RequestScoped
public class questionarioBean {
    
    private Questionario questionario;
    /** Creates a new instance of questionarioBean */
    public questionarioBean() {
        questionario = new Questionario();
    }
    
 public String ValidadorQuestionario() {
        if (questionario.getQuestão2Item1().equalsIgnoreCase("") && questionario.getQuestão2Item2().equalsIgnoreCase("")) {
            
            return "questao2Item1ACEITO";
           
        } else {
            FacesContext.getCurrentInstance().addMessage(null , new FacesMessage(FacesMessage.SEVERITY_ERROR,"O número da matricula ou a senha estão incorretos. Tente novamente.", "Login invalido!"));
           
            return "questao2Item1ERRO";
        } 
    }
    
    
}
