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
@Table(name = "escolha_tipo", catalog = "questionario", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EscolhaTipo.findAll", query = "SELECT e FROM EscolhaTipo e"),
    @NamedQuery(name = "EscolhaTipo.findById", query = "SELECT e FROM EscolhaTipo e WHERE e.id = :id"),
    @NamedQuery(name = "EscolhaTipo.findByEscolhaResp", query = "SELECT e FROM EscolhaTipo e WHERE e.escolhaResp = :escolhaResp")})
public class EscolhaTipo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "escolha_resp")
    private String escolhaResp;
    @JoinColumn(name = "fk_escolha", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Escolha fkEscolha;
    @JoinColumn(name = "fk_pai", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ItensRespondidos fkPai;

    public EscolhaTipo() {
    }

    public EscolhaTipo(Integer id) {
        this.id = id;
    }

    public EscolhaTipo(Integer id, String escolhaResp) {
        this.id = id;
        this.escolhaResp = escolhaResp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEscolhaResp() {
        return escolhaResp;
    }

    public void setEscolhaResp(String escolhaResp) {
        this.escolhaResp = escolhaResp;
    }

    public Escolha getFkEscolha() {
        return fkEscolha;
    }

    public void setFkEscolha(Escolha fkEscolha) {
        this.fkEscolha = fkEscolha;
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
        if (!(object instanceof EscolhaTipo)) {
            return false;
        }
        EscolhaTipo other = (EscolhaTipo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Conexao.EscolhaTipo[ id=" + id + " ]";
    }
    
}
