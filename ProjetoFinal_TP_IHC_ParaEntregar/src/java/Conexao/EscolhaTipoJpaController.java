/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexao;

import Conexao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Guilherme
 */
public class EscolhaTipoJpaController implements Serializable {

    public EscolhaTipoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EscolhaTipo escolhaTipo) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Escolha fkEscolha = escolhaTipo.getFkEscolha();
            if (fkEscolha != null) {
                fkEscolha = em.getReference(fkEscolha.getClass(), fkEscolha.getId());
                escolhaTipo.setFkEscolha(fkEscolha);
            }
            ItensRespondidos fkPai = escolhaTipo.getFkPai();
            if (fkPai != null) {
                fkPai = em.getReference(fkPai.getClass(), fkPai.getId());
                escolhaTipo.setFkPai(fkPai);
            }
            em.persist(escolhaTipo);
            if (fkEscolha != null) {
                fkEscolha.getEscolhaTipoList().add(escolhaTipo);
                fkEscolha = em.merge(fkEscolha);
            }
            if (fkPai != null) {
                fkPai.getEscolhaTipoList().add(escolhaTipo);
                fkPai = em.merge(fkPai);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EscolhaTipo escolhaTipo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EscolhaTipo persistentEscolhaTipo = em.find(EscolhaTipo.class, escolhaTipo.getId());
            Escolha fkEscolhaOld = persistentEscolhaTipo.getFkEscolha();
            Escolha fkEscolhaNew = escolhaTipo.getFkEscolha();
            ItensRespondidos fkPaiOld = persistentEscolhaTipo.getFkPai();
            ItensRespondidos fkPaiNew = escolhaTipo.getFkPai();
            if (fkEscolhaNew != null) {
                fkEscolhaNew = em.getReference(fkEscolhaNew.getClass(), fkEscolhaNew.getId());
                escolhaTipo.setFkEscolha(fkEscolhaNew);
            }
            if (fkPaiNew != null) {
                fkPaiNew = em.getReference(fkPaiNew.getClass(), fkPaiNew.getId());
                escolhaTipo.setFkPai(fkPaiNew);
            }
            escolhaTipo = em.merge(escolhaTipo);
            if (fkEscolhaOld != null && !fkEscolhaOld.equals(fkEscolhaNew)) {
                fkEscolhaOld.getEscolhaTipoList().remove(escolhaTipo);
                fkEscolhaOld = em.merge(fkEscolhaOld);
            }
            if (fkEscolhaNew != null && !fkEscolhaNew.equals(fkEscolhaOld)) {
                fkEscolhaNew.getEscolhaTipoList().add(escolhaTipo);
                fkEscolhaNew = em.merge(fkEscolhaNew);
            }
            if (fkPaiOld != null && !fkPaiOld.equals(fkPaiNew)) {
                fkPaiOld.getEscolhaTipoList().remove(escolhaTipo);
                fkPaiOld = em.merge(fkPaiOld);
            }
            if (fkPaiNew != null && !fkPaiNew.equals(fkPaiOld)) {
                fkPaiNew.getEscolhaTipoList().add(escolhaTipo);
                fkPaiNew = em.merge(fkPaiNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = escolhaTipo.getId();
                if (findEscolhaTipo(id) == null) {
                    throw new NonexistentEntityException("The escolhaTipo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EscolhaTipo escolhaTipo;
            try {
                escolhaTipo = em.getReference(EscolhaTipo.class, id);
                escolhaTipo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The escolhaTipo with id " + id + " no longer exists.", enfe);
            }
            Escolha fkEscolha = escolhaTipo.getFkEscolha();
            if (fkEscolha != null) {
                fkEscolha.getEscolhaTipoList().remove(escolhaTipo);
                fkEscolha = em.merge(fkEscolha);
            }
            ItensRespondidos fkPai = escolhaTipo.getFkPai();
            if (fkPai != null) {
                fkPai.getEscolhaTipoList().remove(escolhaTipo);
                fkPai = em.merge(fkPai);
            }
            em.remove(escolhaTipo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EscolhaTipo> findEscolhaTipoEntities() {
        return findEscolhaTipoEntities(true, -1, -1);
    }

    public List<EscolhaTipo> findEscolhaTipoEntities(int maxResults, int firstResult) {
        return findEscolhaTipoEntities(false, maxResults, firstResult);
    }

    private List<EscolhaTipo> findEscolhaTipoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EscolhaTipo.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public EscolhaTipo findEscolhaTipo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EscolhaTipo.class, id);
        } finally {
            em.close();
        }
    }

    public int getEscolhaTipoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EscolhaTipo> rt = cq.from(EscolhaTipo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
