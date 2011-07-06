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
@Table(name = "itens_respondidos", catalog = "questionario", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItensRespondidos.findAll", query = "SELECT i FROM ItensRespondidos i"),
    @NamedQuery(name = "ItensRespondidos.findById", query = "SELECT i FROM ItensRespondidos i WHERE i.id = :id")})
public class ItensRespondidos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkItem")
    private List<Resposta> respostaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkPai")
    private List<SubItem> subItemList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkPai")
    private List<EscolhaTipo> escolhaTipoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkPai")
    private List<Livre> livreList;

    public ItensRespondidos() {
    }

    public ItensRespondidos(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @XmlTransient
    public List<Resposta> getRespostaList() {
        return respostaList;
    }

    public void setRespostaList(List<Resposta> respostaList) {
        this.respostaList = respostaList;
    }

    @XmlTransient
    public List<SubItem> getSubItemList() {
        return subItemList;
    }

    public void setSubItemList(List<SubItem> subItemList) {
        this.subItemList = subItemList;
    }

    @XmlTransient
    public List<EscolhaTipo> getEscolhaTipoList() {
        return escolhaTipoList;
    }

    public void setEscolhaTipoList(List<EscolhaTipo> escolhaTipoList) {
        this.escolhaTipoList = escolhaTipoList;
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
        if (!(object instanceof ItensRespondidos)) {
            return false;
        }
        ItensRespondidos other = (ItensRespondidos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Conexao.ItensRespondidos[ id=" + id + " ]";
    }
    
}
