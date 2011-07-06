/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import Conexao.Aluno;
import Conexao.AlunoJpaController;
import Conexao.exceptions.NonexistentEntityException;
import Conexao.exceptions.PreexistingEntityException;

/**
 *
 * @author Guilherme
 */
@ManagedBean (name="cadastroBean")
@RequestScoped
public class CadastroBean {

    
    private Aluno cadastro;

    public Aluno getCadastro() {
        return cadastro;
    }

    public void setCadastro(Aluno cadastro) {
        this.cadastro = cadastro;
    }
      
      public CadastroBean(){
    
      cadastro = new Aluno();
    }
      
    /** Creates a new instance of CadastroBean */
       
    public void salvar() throws NonexistentEntityException, Exception, PreexistingEntityException{
    
     //FAZER UM TRY CATCH PARA TRATAR O ERRO DE MATRICULAS IGUAIS.
        
        try {
            
        } catch (Exception e) {
        }
     
     AlunoJpaController jpa = new AlunoJpaController();
     jpa.create(cadastro);
    
    
    }
    
    
}
