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
@Table(name = "escolha", catalog = "questionario", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Escolha.findAll", query = "SELECT e FROM Escolha e"),
    @NamedQuery(name = "Escolha.findById", query = "SELECT e FROM Escolha e WHERE e.id = :id")})
public class Escolha implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "fk_itens", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Itens fkItens;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkEscolha")
    private List<EscolhaTipo> escolhaTipoList;

    public Escolha() {
    }

    public Escolha(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Itens getFkItens() {
        return fkItens;
    }

    public void setFkItens(Itens fkItens) {
        this.fkItens = fkItens;
    }

    @XmlTransient
    public List<EscolhaTipo> getEscolhaTipoList() {
        return escolhaTipoList;
    }

    public void setEscolhaTipoList(List<EscolhaTipo> escolhaTipoList) {
        this.escolhaTipoList = escolhaTipoList;
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
        if (!(object instanceof Escolha)) {
            return false;
        }
        Escolha other = (Escolha) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Conexao.Escolha[ id=" + id + " ]";
    }
    
}
