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
public class SubItemJpaController implements Serializable {

    public SubItemJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SubItem subItem) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Questao fkItem = subItem.getFkItem();
            if (fkItem != null) {
                fkItem = em.getReference(fkItem.getClass(), fkItem.getId());
                subItem.setFkItem(fkItem);
            }
            ItensRespondidos fkPai = subItem.getFkPai();
            if (fkPai != null) {
                fkPai = em.getReference(fkPai.getClass(), fkPai.getId());
                subItem.setFkPai(fkPai);
            }
            em.persist(subItem);
            if (fkItem != null) {
                fkItem.getSubItemList().add(subItem);
                fkItem = em.merge(fkItem);
            }
            if (fkPai != null) {
                fkPai.getSubItemList().add(subItem);
                fkPai = em.merge(fkPai);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SubItem subItem) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SubItem persistentSubItem = em.find(SubItem.class, subItem.getId());
            Questao fkItemOld = persistentSubItem.getFkItem();
            Questao fkItemNew = subItem.getFkItem();
            ItensRespondidos fkPaiOld = persistentSubItem.getFkPai();
            ItensRespondidos fkPaiNew = subItem.getFkPai();
            if (fkItemNew != null) {
                fkItemNew = em.getReference(fkItemNew.getClass(), fkItemNew.getId());
                subItem.setFkItem(fkItemNew);
            }
            if (fkPaiNew != null) {
                fkPaiNew = em.getReference(fkPaiNew.getClass(), fkPaiNew.getId());
                subItem.setFkPai(fkPaiNew);
            }
            subItem = em.merge(subItem);
            if (fkItemOld != null && !fkItemOld.equals(fkItemNew)) {
                fkItemOld.getSubItemList().remove(subItem);
                fkItemOld = em.merge(fkItemOld);
            }
            if (fkItemNew != null && !fkItemNew.equals(fkItemOld)) {
                fkItemNew.getSubItemList().add(subItem);
                fkItemNew = em.merge(fkItemNew);
            }
            if (fkPaiOld != null && !fkPaiOld.equals(fkPaiNew)) {
                fkPaiOld.getSubItemList().remove(subItem);
                fkPaiOld = em.merge(fkPaiOld);
            }
            if (fkPaiNew != null && !fkPaiNew.equals(fkPaiOld)) {
                fkPaiNew.getSubItemList().add(subItem);
                fkPaiNew = em.merge(fkPaiNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = subItem.getId();
                if (findSubItem(id) == null) {
                    throw new NonexistentEntityException("The subItem with id " + id + " no longer exists.");
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
            SubItem subItem;
            try {
                subItem = em.getReference(SubItem.class, id);
                subItem.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The subItem with id " + id + " no longer exists.", enfe);
            }
            Questao fkItem = subItem.getFkItem();
            if (fkItem != null) {
                fkItem.getSubItemList().remove(subItem);
                fkItem = em.merge(fkItem);
            }
            ItensRespondidos fkPai = subItem.getFkPai();
            if (fkPai != null) {
                fkPai.getSubItemList().remove(subItem);
                fkPai = em.merge(fkPai);
            }
            em.remove(subItem);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SubItem> findSubItemEntities() {
        return findSubItemEntities(true, -1, -1);
    }

    public List<SubItem> findSubItemEntities(int maxResults, int firstResult) {
        return findSubItemEntities(false, maxResults, firstResult);
    }

    private List<SubItem> findSubItemEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SubItem.class));
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

    public SubItem findSubItem(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SubItem.class, id);
        } finally {
            em.close();
        }
    }

    public int getSubItemCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SubItem> rt = cq.from(SubItem.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
