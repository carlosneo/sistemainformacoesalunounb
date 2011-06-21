
import Conexao.Aluno;
import Conexao.AlunoJpaController;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Guilherme
 */
public class Teste {
    
    public static void main (String args[]) {
        Aluno aluno = new Aluno("Guilherme Fay");
        AlunoJpaController jpa = new AlunoJpaController();
        jpa.create(aluno);
    }
    
}
