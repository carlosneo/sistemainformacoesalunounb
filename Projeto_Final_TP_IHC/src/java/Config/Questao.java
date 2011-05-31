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
@Table(name = "Questao", catalog = "Projeto_Final_TP_IHC", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Questao.findAll", query = "SELECT q FROM Questao q"),
    @NamedQuery(name = "Questao.findByTipoQuestao", query = "SELECT q FROM Questao q WHERE q.tipoQuestao = :tipoQuestao"),
    @NamedQuery(name = "Questao.findByEnunciado", query = "SELECT q FROM Questao q WHERE q.enunciado = :enunciado"),
    @NamedQuery(name = "Questao.findByNumQuestao", query = "SELECT q FROM Questao q WHERE q.numQuestao = :numQuestao")})
public class Questao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "tipoQuestao")
    private int tipoQuestao;
    @Basic(optional = false)
    @Column(name = "enunciado")
    private String enunciado;
    @Id
    @Basic(optional = false)
    @Column(name = "numQuestao")
    private Integer numQuestao;

    public Questao() {
    }

    public Questao(Integer numQuestao) {
        this.numQuestao = numQuestao;
    }

    public Questao(Integer numQuestao, int tipoQuestao, String enunciado) {
        this.numQuestao = numQuestao;
        this.tipoQuestao = tipoQuestao;
        this.enunciado = enunciado;
    }

    public int getTipoQuestao() {
        return tipoQuestao;
    }

    public void setTipoQuestao(int tipoQuestao) {
        this.tipoQuestao = tipoQuestao;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public Integer getNumQuestao() {
        return numQuestao;
    }

    public void setNumQuestao(Integer numQuestao) {
        this.numQuestao = numQuestao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numQuestao != null ? numQuestao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Questao)) {
            return false;
        }
        Questao other = (Questao) object;
        if ((this.numQuestao == null && other.numQuestao != null) || (this.numQuestao != null && !this.numQuestao.equals(other.numQuestao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Confi.Questao[ numQuestao=" + numQuestao + " ]";
    }
    
}
