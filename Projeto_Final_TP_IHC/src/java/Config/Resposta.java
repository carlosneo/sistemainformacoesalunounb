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
@Table(name = "Resposta", catalog = "Projeto_Final_TP_IHC", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Resposta.findAll", query = "SELECT r FROM Resposta r"),
    @NamedQuery(name = "Resposta.findByMatAluno", query = "SELECT r FROM Resposta r WHERE r.matAluno = :matAluno"),
    @NamedQuery(name = "Resposta.findByNumQuestao", query = "SELECT r FROM Resposta r WHERE r.numQuestao = :numQuestao"),
    @NamedQuery(name = "Resposta.findByNumItem", query = "SELECT r FROM Resposta r WHERE r.numItem = :numItem"),
    @NamedQuery(name = "Resposta.findByResposta", query = "SELECT r FROM Resposta r WHERE r.resposta = :resposta")})
public class Resposta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "matAluno")
    private int matAluno;
    @Basic(optional = false)
    @Column(name = "numQuestao")
    private int numQuestao;
    @Basic(optional = false)
    @Column(name = "numItem")
    private int numItem;
    @Id
    @Basic(optional = false)
    @Column(name = "resposta")
    private String resposta;

    public Resposta() {
    }

    public Resposta(String resposta) {
        this.resposta = resposta;
    }

    public Resposta(String resposta, int matAluno, int numQuestao, int numItem) {
        this.resposta = resposta;
        this.matAluno = matAluno;
        this.numQuestao = numQuestao;
        this.numItem = numItem;
    }

    public int getMatAluno() {
        return matAluno;
    }

    public void setMatAluno(int matAluno) {
        this.matAluno = matAluno;
    }

    public int getNumQuestao() {
        return numQuestao;
    }

    public void setNumQuestao(int numQuestao) {
        this.numQuestao = numQuestao;
    }

    public int getNumItem() {
        return numItem;
    }

    public void setNumItem(int numItem) {
        this.numItem = numItem;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (resposta != null ? resposta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Resposta)) {
            return false;
        }
        Resposta other = (Resposta) object;
        if ((this.resposta == null && other.resposta != null) || (this.resposta != null && !this.resposta.equals(other.resposta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Confi.Resposta[ resposta=" + resposta + " ]";
    }
    
}
