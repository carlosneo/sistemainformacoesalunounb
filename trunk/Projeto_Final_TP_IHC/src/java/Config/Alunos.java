/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Config;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Neo
 */
@Entity
@Table(name = "Alunos", catalog = "Projeto_Final_TP_IHC", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Alunos.findAll", query = "SELECT a FROM Alunos a"),
    @NamedQuery(name = "Alunos.findByMatAluno", query = "SELECT a FROM Alunos a WHERE a.matAluno = :matAluno"),
    @NamedQuery(name = "Alunos.findByNome", query = "SELECT a FROM Alunos a WHERE a.nome = :nome")})
public class Alunos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "matAluno")
    private Integer matAluno;
    @Column(name = "nome")
    private String nome;

    public Alunos() {
    }

    public Alunos(Integer matAluno) {
        this.matAluno = matAluno;
    }

    public Integer getMatAluno() {
        return matAluno;
    }

    public void setMatAluno(Integer matAluno) {
        this.matAluno = matAluno;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (matAluno != null ? matAluno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Alunos)) {
            return false;
        }
        Alunos other = (Alunos) object;
        if ((this.matAluno == null && other.matAluno != null) || (this.matAluno != null && !this.matAluno.equals(other.matAluno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Confi.Alunos[ matAluno=" + matAluno + " ]";
    }
    
}
