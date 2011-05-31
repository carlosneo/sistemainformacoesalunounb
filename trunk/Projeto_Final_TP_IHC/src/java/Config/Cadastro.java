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
@Table(name = "Cadastro", catalog = "Projeto_Final_TP_IHC", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cadastro.findAll", query = "SELECT c FROM Cadastro c"),
    @NamedQuery(name = "Cadastro.findByNome", query = "SELECT c FROM Cadastro c WHERE c.nome = :nome"),
    @NamedQuery(name = "Cadastro.findByMatAluno", query = "SELECT c FROM Cadastro c WHERE c.matAluno = :matAluno"),
    @NamedQuery(name = "Cadastro.findBySexo", query = "SELECT c FROM Cadastro c WHERE c.sexo = :sexo"),
    @NamedQuery(name = "Cadastro.findById", query = "SELECT c FROM Cadastro c WHERE c.id = :id"),
    @NamedQuery(name = "Cadastro.findBySenha", query = "SELECT c FROM Cadastro c WHERE c.senha = :senha")})
public class Cadastro implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @Column(name = "matAluno")
    private int matAluno;
    @Basic(optional = false)
    @Column(name = "sexo")
    private String sexo;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "senha")
    private String senha;

    public Cadastro() {
    }

    public Cadastro(Integer id) {
        this.id = id;
    }

    public Cadastro(Integer id, String nome, int matAluno, String sexo, String senha) {
        this.id = id;
        this.nome = nome;
        this.matAluno = matAluno;
        this.sexo = sexo;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getMatAluno() {
        return matAluno;
    }

    public void setMatAluno(int matAluno) {
        this.matAluno = matAluno;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cadastro)) {
            return false;
        }
        Cadastro other = (Cadastro) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Confi.Cadastro[ id=" + id + " ]";
    }
    
}
