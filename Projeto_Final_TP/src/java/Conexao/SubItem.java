/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexao;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Guilherme
 */
@Entity
@Table(name = "sub_item", catalog = "questionario", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SubItem.findAll", query = "SELECT s FROM SubItem s"),
    @NamedQuery(name = "SubItem.findById", query = "SELECT s FROM SubItem s WHERE s.id = :id"),
    @NamedQuery(name = "SubItem.findByItemResp", query = "SELECT s FROM SubItem s WHERE s.itemResp = :itemResp")})
public class SubItem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "item_resp")
    private String itemResp;
    @JoinColumn(name = "fk_item", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Questao fkItem;
    @JoinColumn(name = "fk_pai", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ItensRespondidos fkPai;

    public SubItem() {
    }

    public SubItem(Integer id) {
        this.id = id;
    }

    public SubItem(Integer id, String itemResp) {
        this.id = id;
        this.itemResp = itemResp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItemResp() {
        return itemResp;
    }

    public void setItemResp(String itemResp) {
        this.itemResp = itemResp;
    }

    public Questao getFkItem() {
        return fkItem;
    }

    public void setFkItem(Questao fkItem) {
        this.fkItem = fkItem;
    }

    public ItensRespondidos getFkPai() {
        return fkPai;
    }

    public void setFkPai(ItensRespondidos fkPai) {
        this.fkPai = fkPai;
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
        if (!(object instanceof SubItem)) {
            return false;
        }
        SubItem other = (SubItem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Conexao.SubItem[ id=" + id + " ]";
    }
    
}
