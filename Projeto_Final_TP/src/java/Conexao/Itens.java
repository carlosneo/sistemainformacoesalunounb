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
@Table(name = "itens", catalog = "questionario", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Itens.findAll", query = "SELECT i FROM Itens i"),
    @NamedQuery(name = "Itens.findById", query = "SELECT i FROM Itens i WHERE i.id = :id"),
    @NamedQuery(name = "Itens.findByItemEnunciado", query = "SELECT i FROM Itens i WHERE i.itemEnunciado = :itemEnunciado"),
    @NamedQuery(name = "Itens.findByNumItem", query = "SELECT i FROM Itens i WHERE i.numItem = :numItem")})
public class Itens implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "item_enunciado")
    private String itemEnunciado;
    @Basic(optional = false)
    @Column(name = "num_item")
    private int numItem;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkItens")
    private List<Item> itemList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkItens")
    private List<Escolha> escolhaList;
    @JoinColumn(name = "fk_questao", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Questao fkQuestao;

    public Itens() {
    }

    public Itens(Integer id) {
        this.id = id;
    }

    public Itens(Integer id, int numItem) {
        this.id = id;
        this.numItem = numItem;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItemEnunciado() {
        return itemEnunciado;
    }

    public void setItemEnunciado(String itemEnunciado) {
        this.itemEnunciado = itemEnunciado;
    }

    public int getNumItem() {
        return numItem;
    }

    public void setNumItem(int numItem) {
        this.numItem = numItem;
    }

    @XmlTransient
    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    @XmlTransient
    public List<Escolha> getEscolhaList() {
        return escolhaList;
    }

    public void setEscolhaList(List<Escolha> escolhaList) {
        this.escolhaList = escolhaList;
    }

    public Questao getFkQuestao() {
        return fkQuestao;
    }

    public void setFkQuestao(Questao fkQuestao) {
        this.fkQuestao = fkQuestao;
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
        if (!(object instanceof Itens)) {
            return false;
        }
        Itens other = (Itens) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Conexao.Itens[ id=" + id + " ]";
    }
    
}
