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

    public Questionario getQuestionario() {
        return questionario;
    }

    public void setQuestionario(Questionario questionario) {
        this.questionario = questionario;
    }

  
    
  
    public questionarioBean() {
        questionario = new Questionario();
    }
    
    public String validadorQuestionario() {
        if (questionario.getQuestão2Item1().equalsIgnoreCase("marista")){
         
            return "questao2Item1ACEITO" ;
        } else {
       
            return "questao2Item1ERRO";
        } 
    }
    
     

}


