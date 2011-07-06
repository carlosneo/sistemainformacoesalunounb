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
public class LivreJpaController implements Serializable {

    public LivreJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Livre livre) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Questao fkItens = livre.getFkItens();
            if (fkItens != null) {
                fkItens = em.getReference(fkItens.getClass(), fkItens.getId());
                livre.setFkItens(fkItens);
            }
            ItensRespondidos fkPai = livre.getFkPai();
            if (fkPai != null) {
                fkPai = em.getReference(fkPai.getClass(), fkPai.getId());
                livre.setFkPai(fkPai);
            }
            em.persist(livre);
            if (fkItens != null) {
                fkItens.getLivreList().add(livre);
                fkItens = em.merge(fkItens);
            }
            if (fkPai != null) {
                fkPai.getLivreList().add(livre);
                fkPai = em.merge(fkPai);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Livre livre) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Livre persistentLivre = em.find(Livre.class, livre.getId());
            Questao fkItensOld = persistentLivre.getFkItens();
            Questao fkItensNew = livre.getFkItens();
            ItensRespondidos fkPaiOld = persistentLivre.getFkPai();
            ItensRespondidos fkPaiNew = livre.getFkPai();
            if (fkItensNew != null) {
                fkItensNew = em.getReference(fkItensNew.getClass(), fkItensNew.getId());
                livre.setFkItens(fkItensNew);
            }
            if (fkPaiNew != null) {
                fkPaiNew = em.getReference(fkPaiNew.getClass(), fkPaiNew.getId());
                livre.setFkPai(fkPaiNew);
            }
            livre = em.merge(livre);
            if (fkItensOld != null && !fkItensOld.equals(fkItensNew)) {
                fkItensOld.getLivreList().remove(livre);
                fkItensOld = em.merge(fkItensOld);
            }
            if (fkItensNew != null && !fkItensNew.equals(fkItensOld)) {
                fkItensNew.getLivreList().add(livre);
                fkItensNew = em.merge(fkItensNew);
            }
            if (fkPaiOld != null && !fkPaiOld.equals(fkPaiNew)) {
                fkPaiOld.getLivreList().remove(livre);
                fkPaiOld = em.merge(fkPaiOld);
            }
            if (fkPaiNew != null && !fkPaiNew.equals(fkPaiOld)) {
                fkPaiNew.getLivreList().add(livre);
                fkPaiNew = em.merge(fkPaiNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = livre.getId();
                if (findLivre(id) == null) {
                    throw new NonexistentEntityException("The livre with id " + id + " no longer exists.");
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
            Livre livre;
            try {
                livre = em.getReference(Livre.class, id);
                livre.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The livre with id " + id + " no longer exists.", enfe);
            }
            Questao fkItens = livre.getFkItens();
            if (fkItens != null) {
                fkItens.getLivreList().remove(livre);
                fkItens = em.merge(fkItens);
            }
            ItensRespondidos fkPai = livre.getFkPai();
            if (fkPai != null) {
                fkPai.getLivreList().remove(livre);
                fkPai = em.merge(fkPai);
            }
            em.remove(livre);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Livre> findLivreEntities() {
        return findLivreEntities(true, -1, -1);
    }

    public List<Livre> findLivreEntities(int maxResults, int firstResult) {
        return findLivreEntities(false, maxResults, firstResult);
    }

    private List<Livre> findLivreEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Livre.class));
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

    public Livre findLivre(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Livre.class, id);
        } finally {
            em.close();
        }
    }

    public int getLivreCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Livre> rt = cq.from(Livre.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
