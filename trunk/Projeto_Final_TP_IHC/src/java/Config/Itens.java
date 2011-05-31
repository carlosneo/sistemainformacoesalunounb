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
@Table(name = "Itens", catalog = "Projeto_Final_TP_IHC", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Itens.findAll", query = "SELECT i FROM Itens i"),
    @NamedQuery(name = "Itens.findByNumQuestao", query = "SELECT i FROM Itens i WHERE i.numQuestao = :numQuestao"),
    @NamedQuery(name = "Itens.findByEnunciadoItem", query = "SELECT i FROM Itens i WHERE i.enunciadoItem = :enunciadoItem"),
    @NamedQuery(name = "Itens.findByNumItem", query = "SELECT i FROM Itens i WHERE i.numItem = :numItem")})
public class Itens implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "numQuestao")
    private int numQuestao;
    @Basic(optional = false)
    @Column(name = "enunciadoItem")
    private String enunciadoItem;
    @Id
    @Basic(optional = false)
    @Column(name = "numItem")
    private Integer numItem;

    public Itens() {
    }

    public Itens(Integer numItem) {
        this.numItem = numItem;
    }

    public Itens(Integer numItem, int numQuestao, String enunciadoItem) {
        this.numItem = numItem;
        this.numQuestao = numQuestao;
        this.enunciadoItem = enunciadoItem;
    }

    public int getNumQuestao() {
        return numQuestao;
    }

    public void setNumQuestao(int numQuestao) {
        this.numQuestao = numQuestao;
    }

    public String getEnunciadoItem() {
        return enunciadoItem;
    }

    public void setEnunciadoItem(String enunciadoItem) {
        this.enunciadoItem = enunciadoItem;
    }

    public Integer getNumItem() {
        return numItem;
    }

    public void setNumItem(Integer numItem) {
        this.numItem = numItem;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numItem != null ? numItem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Itens)) {
            return false;
        }
        Itens other = (Itens) object;
        if ((this.numItem == null && other.numItem != null) || (this.numItem != null && !this.numItem.equals(other.numItem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Confi.Itens[ numItem=" + numItem + " ]";
    }
    
}
