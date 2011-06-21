/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexao;

import Conexao.exceptions.IllegalOrphanException;
import Conexao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Guilherme
 */
public class ItensJpaController implements Serializable {

    public ItensJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Itens itens) {
        if (itens.getItemList() == null) {
            itens.setItemList(new ArrayList<Item>());
        }
        if (itens.getEscolhaList() == null) {
            itens.setEscolhaList(new ArrayList<Escolha>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Questao fkQuestao = itens.getFkQuestao();
            if (fkQuestao != null) {
                fkQuestao = em.getReference(fkQuestao.getClass(), fkQuestao.getId());
                itens.setFkQuestao(fkQuestao);
            }
            List<Item> attachedItemList = new ArrayList<Item>();
            for (Item itemListItemToAttach : itens.getItemList()) {
                itemListItemToAttach = em.getReference(itemListItemToAttach.getClass(), itemListItemToAttach.getId());
                attachedItemList.add(itemListItemToAttach);
            }
            itens.setItemList(attachedItemList);
            List<Escolha> attachedEscolhaList = new ArrayList<Escolha>();
            for (Escolha escolhaListEscolhaToAttach : itens.getEscolhaList()) {
                escolhaListEscolhaToAttach = em.getReference(escolhaListEscolhaToAttach.getClass(), escolhaListEscolhaToAttach.getId());
                attachedEscolhaList.add(escolhaListEscolhaToAttach);
            }
            itens.setEscolhaList(attachedEscolhaList);
            em.persist(itens);
            if (fkQuestao != null) {
                fkQuestao.getItensList().add(itens);
                fkQuestao = em.merge(fkQuestao);
            }
            for (Item itemListItem : itens.getItemList()) {
                Itens oldFkItensOfItemListItem = itemListItem.getFkItens();
                itemListItem.setFkItens(itens);
                itemListItem = em.merge(itemListItem);
                if (oldFkItensOfItemListItem != null) {
                    oldFkItensOfItemListItem.getItemList().remove(itemListItem);
                    oldFkItensOfItemListItem = em.merge(oldFkItensOfItemListItem);
                }
            }
            for (Escolha escolhaListEscolha : itens.getEscolhaList()) {
                Itens oldFkItensOfEscolhaListEscolha = escolhaListEscolha.getFkItens();
                escolhaListEscolha.setFkItens(itens);
                escolhaListEscolha = em.merge(escolhaListEscolha);
                if (oldFkItensOfEscolhaListEscolha != null) {
                    oldFkItensOfEscolhaListEscolha.getEscolhaList().remove(escolhaListEscolha);
                    oldFkItensOfEscolhaListEscolha = em.merge(oldFkItensOfEscolhaListEscolha);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Itens itens) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Itens persistentItens = em.find(Itens.class, itens.getId());
            Questao fkQuestaoOld = persistentItens.getFkQuestao();
            Questao fkQuestaoNew = itens.getFkQuestao();
            List<Item> itemListOld = persistentItens.getItemList();
            List<Item> itemListNew = itens.getItemList();
            List<Escolha> escolhaListOld = persistentItens.getEscolhaList();
            List<Escolha> escolhaListNew = itens.getEscolhaList();
            List<String> illegalOrphanMessages = null;
            for (Item itemListOldItem : itemListOld) {
                if (!itemListNew.contains(itemListOldItem)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Item " + itemListOldItem + " since its fkItens field is not nullable.");
                }
            }
            for (Escolha escolhaListOldEscolha : escolhaListOld) {
                if (!escolhaListNew.contains(escolhaListOldEscolha)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Escolha " + escolhaListOldEscolha + " since its fkItens field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (fkQuestaoNew != null) {
                fkQuestaoNew = em.getReference(fkQuestaoNew.getClass(), fkQuestaoNew.getId());
                itens.setFkQuestao(fkQuestaoNew);
            }
            List<Item> attachedItemListNew = new ArrayList<Item>();
            for (Item itemListNewItemToAttach : itemListNew) {
                itemListNewItemToAttach = em.getReference(itemListNewItemToAttach.getClass(), itemListNewItemToAttach.getId());
                attachedItemListNew.add(itemListNewItemToAttach);
            }
            itemListNew = attachedItemListNew;
            itens.setItemList(itemListNew);
            List<Escolha> attachedEscolhaListNew = new ArrayList<Escolha>();
            for (Escolha escolhaListNewEscolhaToAttach : escolhaListNew) {
                escolhaListNewEscolhaToAttach = em.getReference(escolhaListNewEscolhaToAttach.getClass(), escolhaListNewEscolhaToAttach.getId());
                attachedEscolhaListNew.add(escolhaListNewEscolhaToAttach);
            }
            escolhaListNew = attachedEscolhaListNew;
            itens.setEscolhaList(escolhaListNew);
            itens = em.merge(itens);
            if (fkQuestaoOld != null && !fkQuestaoOld.equals(fkQuestaoNew)) {
                fkQuestaoOld.getItensList().remove(itens);
                fkQuestaoOld = em.merge(fkQuestaoOld);
            }
            if (fkQuestaoNew != null && !fkQuestaoNew.equals(fkQuestaoOld)) {
                fkQuestaoNew.getItensList().add(itens);
                fkQuestaoNew = em.merge(fkQuestaoNew);
            }
            for (Item itemListNewItem : itemListNew) {
                if (!itemListOld.contains(itemListNewItem)) {
                    Itens oldFkItensOfItemListNewItem = itemListNewItem.getFkItens();
                    itemListNewItem.setFkItens(itens);
                    itemListNewItem = em.merge(itemListNewItem);
                    if (oldFkItensOfItemListNewItem != null && !oldFkItensOfItemListNewItem.equals(itens)) {
                        oldFkItensOfItemListNewItem.getItemList().remove(itemListNewItem);
                        oldFkItensOfItemListNewItem = em.merge(oldFkItensOfItemListNewItem);
                    }
                }
            }
            for (Escolha escolhaListNewEscolha : escolhaListNew) {
                if (!escolhaListOld.contains(escolhaListNewEscolha)) {
                    Itens oldFkItensOfEscolhaListNewEscolha = escolhaListNewEscolha.getFkItens();
                    escolhaListNewEscolha.setFkItens(itens);
                    escolhaListNewEscolha = em.merge(escolhaListNewEscolha);
                    if (oldFkItensOfEscolhaListNewEscolha != null && !oldFkItensOfEscolhaListNewEscolha.equals(itens)) {
                        oldFkItensOfEscolhaListNewEscolha.getEscolhaList().remove(escolhaListNewEscolha);
                        oldFkItensOfEscolhaListNewEscolha = em.merge(oldFkItensOfEscolhaListNewEscolha);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = itens.getId();
                if (findItens(id) == null) {
                    throw new NonexistentEntityException("The itens with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Itens itens;
            try {
                itens = em.getReference(Itens.class, id);
                itens.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The itens with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Item> itemListOrphanCheck = itens.getItemList();
            for (Item itemListOrphanCheckItem : itemListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Itens (" + itens + ") cannot be destroyed since the Item " + itemListOrphanCheckItem + " in its itemList field has a non-nullable fkItens field.");
            }
            List<Escolha> escolhaListOrphanCheck = itens.getEscolhaList();
            for (Escolha escolhaListOrphanCheckEscolha : escolhaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Itens (" + itens + ") cannot be destroyed since the Escolha " + escolhaListOrphanCheckEscolha + " in its escolhaList field has a non-nullable fkItens field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Questao fkQuestao = itens.getFkQuestao();
            if (fkQuestao != null) {
                fkQuestao.getItensList().remove(itens);
                fkQuestao = em.merge(fkQuestao);
            }
            em.remove(itens);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Itens> findItensEntities() {
        return findItensEntities(true, -1, -1);
    }

    public List<Itens> findItensEntities(int maxResults, int firstResult) {
        return findItensEntities(false, maxResults, firstResult);
    }

    private List<Itens> findItensEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Itens.class));
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

    public Itens findItens(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Itens.class, id);
        } finally {
            em.close();
        }
    }

    public int getItensCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Itens> rt = cq.from(Itens.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
