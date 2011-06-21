/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Guilherme
 */
@Entity
@Table(name = "questao", catalog = "questionario", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Questao.findAll", query = "SELECT q FROM Questao q"),
    @NamedQuery(name = "Questao.findById", query = "SELECT q FROM Questao q WHERE q.id = :id"),
    @NamedQuery(name = "Questao.findByEnunciado", query = "SELECT q FROM Questao q WHERE q.enunciado = :enunciado"),
    @NamedQuery(name = "Questao.findByNumQuestao", query = "SELECT q FROM Questao q WHERE q.numQuestao = :numQuestao")})
public class Questao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "enunciado")
    private String enunciado;
    @Basic(optional = false)
    @Column(name = "num_questao")
    private int numQuestao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkItem")
    private List<SubItem> subItemList;
    @JoinColumn(name = "fk_questionario", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Questionario fkQuestionario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkQuestao")
    private List<Itens> itensList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkItens")
    private List<Livre> livreList;

    public Questao() {
    }

    public Questao(Integer id) {
        this.id = id;
    }

    public Questao(Integer id, String enunciado, int numQuestao) {
        this.id = id;
        this.enunciado = enunciado;
        this.numQuestao = numQuestao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public int getNumQuestao() {
        return numQuestao;
    }

    public void setNumQuestao(int numQuestao) {
        this.numQuestao = numQuestao;
    }

    @XmlTransient
    public List<SubItem> getSubItemList() {
        return subItemList;
    }

    public void setSubItemList(List<SubItem> subItemList) {
        this.subItemList = subItemList;
    }

    public Questionario getFkQuestionario() {
        return fkQuestionario;
    }

    public void setFkQuestionario(Questionario fkQuestionario) {
        this.fkQuestionario = fkQuestionario;
    }

    @XmlTransient
    public List<Itens> getItensList() {
        return itensList;
    }

    public void setItensList(List<Itens> itensList) {
        this.itensList = itensList;
    }

    @XmlTransient
    public List<Livre> getLivreList() {
        return livreList;
    }

    public void setLivreList(List<Livre> livreList) {
        this.livreList = livreList;
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
        if (!(object instanceof Questao)) {
            return false;
        }
        Questao other = (Questao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Conexao.Questao[ id=" + id + " ]";
    }
    
}
