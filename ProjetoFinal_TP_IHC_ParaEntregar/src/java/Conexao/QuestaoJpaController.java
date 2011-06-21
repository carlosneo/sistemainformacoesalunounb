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
public class QuestaoJpaController implements Serializable {

    public QuestaoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Questao questao) {
        if (questao.getSubItemList() == null) {
            questao.setSubItemList(new ArrayList<SubItem>());
        }
        if (questao.getItensList() == null) {
            questao.setItensList(new ArrayList<Itens>());
        }
        if (questao.getLivreList() == null) {
            questao.setLivreList(new ArrayList<Livre>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Questionario fkQuestionario = questao.getFkQuestionario();
            if (fkQuestionario != null) {
                fkQuestionario = em.getReference(fkQuestionario.getClass(), fkQuestionario.getId());
                questao.setFkQuestionario(fkQuestionario);
            }
            List<SubItem> attachedSubItemList = new ArrayList<SubItem>();
            for (SubItem subItemListSubItemToAttach : questao.getSubItemList()) {
                subItemListSubItemToAttach = em.getReference(subItemListSubItemToAttach.getClass(), subItemListSubItemToAttach.getId());
                attachedSubItemList.add(subItemListSubItemToAttach);
            }
            questao.setSubItemList(attachedSubItemList);
            List<Itens> attachedItensList = new ArrayList<Itens>();
            for (Itens itensListItensToAttach : questao.getItensList()) {
                itensListItensToAttach = em.getReference(itensListItensToAttach.getClass(), itensListItensToAttach.getId());
                attachedItensList.add(itensListItensToAttach);
            }
            questao.setItensList(attachedItensList);
            List<Livre> attachedLivreList = new ArrayList<Livre>();
            for (Livre livreListLivreToAttach : questao.getLivreList()) {
                livreListLivreToAttach = em.getReference(livreListLivreToAttach.getClass(), livreListLivreToAttach.getId());
                attachedLivreList.add(livreListLivreToAttach);
            }
            questao.setLivreList(attachedLivreList);
            em.persist(questao);
            if (fkQuestionario != null) {
                fkQuestionario.getQuestaoList().add(questao);
                fkQuestionario = em.merge(fkQuestionario);
            }
            for (SubItem subItemListSubItem : questao.getSubItemList()) {
                Questao oldFkItemOfSubItemListSubItem = subItemListSubItem.getFkItem();
                subItemListSubItem.setFkItem(questao);
                subItemListSubItem = em.merge(subItemListSubItem);
                if (oldFkItemOfSubItemListSubItem != null) {
                    oldFkItemOfSubItemListSubItem.getSubItemList().remove(subItemListSubItem);
                    oldFkItemOfSubItemListSubItem = em.merge(oldFkItemOfSubItemListSubItem);
                }
            }
            for (Itens itensListItens : questao.getItensList()) {
                Questao oldFkQuestaoOfItensListItens = itensListItens.getFkQuestao();
                itensListItens.setFkQuestao(questao);
                itensListItens = em.merge(itensListItens);
                if (oldFkQuestaoOfItensListItens != null) {
                    oldFkQuestaoOfItensListItens.getItensList().remove(itensListItens);
                    oldFkQuestaoOfItensListItens = em.merge(oldFkQuestaoOfItensListItens);
                }
            }
            for (Livre livreListLivre : questao.getLivreList()) {
                Questao oldFkItensOfLivreListLivre = livreListLivre.getFkItens();
                livreListLivre.setFkItens(questao);
                livreListLivre = em.merge(livreListLivre);
                if (oldFkItensOfLivreListLivre != null) {
                    oldFkItensOfLivreListLivre.getLivreList().remove(livreListLivre);
                    oldFkItensOfLivreListLivre = em.merge(oldFkItensOfLivreListLivre);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Questao questao) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Questao persistentQuestao = em.find(Questao.class, questao.getId());
            Questionario fkQuestionarioOld = persistentQuestao.getFkQuestionario();
            Questionario fkQuestionarioNew = questao.getFkQuestionario();
            List<SubItem> subItemListOld = persistentQuestao.getSubItemList();
            List<SubItem> subItemListNew = questao.getSubItemList();
            List<Itens> itensListOld = persistentQuestao.getItensList();
            List<Itens> itensListNew = questao.getItensList();
            List<Livre> livreListOld = persistentQuestao.getLivreList();
            List<Livre> livreListNew = questao.getLivreList();
            List<String> illegalOrphanMessages = null;
            for (SubItem subItemListOldSubItem : subItemListOld) {
                if (!subItemListNew.contains(subItemListOldSubItem)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain SubItem " + subItemListOldSubItem + " since its fkItem field is not nullable.");
                }
            }
            for (Itens itensListOldItens : itensListOld) {
                if (!itensListNew.contains(itensListOldItens)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Itens " + itensListOldItens + " since its fkQuestao field is not nullable.");
                }
            }
            for (Livre livreListOldLivre : livreListOld) {
                if (!livreListNew.contains(livreListOldLivre)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Livre " + livreListOldLivre + " since its fkItens field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (fkQuestionarioNew != null) {
                fkQuestionarioNew = em.getReference(fkQuestionarioNew.getClass(), fkQuestionarioNew.getId());
                questao.setFkQuestionario(fkQuestionarioNew);
            }
            List<SubItem> attachedSubItemListNew = new ArrayList<SubItem>();
            for (SubItem subItemListNewSubItemToAttach : subItemListNew) {
                subItemListNewSubItemToAttach = em.getReference(subItemListNewSubItemToAttach.getClass(), subItemListNewSubItemToAttach.getId());
                attachedSubItemListNew.add(subItemListNewSubItemToAttach);
            }
            subItemListNew = attachedSubItemListNew;
            questao.setSubItemList(subItemListNew);
            List<Itens> attachedItensListNew = new ArrayList<Itens>();
            for (Itens itensListNewItensToAttach : itensListNew) {
                itensListNewItensToAttach = em.getReference(itensListNewItensToAttach.getClass(), itensListNewItensToAttach.getId());
                attachedItensListNew.add(itensListNewItensToAttach);
            }
            itensListNew = attachedItensListNew;
            questao.setItensList(itensListNew);
            List<Livre> attachedLivreListNew = new ArrayList<Livre>();
            for (Livre livreListNewLivreToAttach : livreListNew) {
                livreListNewLivreToAttach = em.getReference(livreListNewLivreToAttach.getClass(), livreListNewLivreToAttach.getId());
                attachedLivreListNew.add(livreListNewLivreToAttach);
            }
            livreListNew = attachedLivreListNew;
            questao.setLivreList(livreListNew);
            questao = em.merge(questao);
            if (fkQuestionarioOld != null && !fkQuestionarioOld.equals(fkQuestionarioNew)) {
                fkQuestionarioOld.getQuestaoList().remove(questao);
                fkQuestionarioOld = em.merge(fkQuestionarioOld);
            }
            if (fkQuestionarioNew != null && !fkQuestionarioNew.equals(fkQuestionarioOld)) {
                fkQuestionarioNew.getQuestaoList().add(questao);
                fkQuestionarioNew = em.merge(fkQuestionarioNew);
            }
            for (SubItem subItemListNewSubItem : subItemListNew) {
                if (!subItemListOld.contains(subItemListNewSubItem)) {
                    Questao oldFkItemOfSubItemListNewSubItem = subItemListNewSubItem.getFkItem();
                    subItemListNewSubItem.setFkItem(questao);
                    subItemListNewSubItem = em.merge(subItemListNewSubItem);
                    if (oldFkItemOfSubItemListNewSubItem != null && !oldFkItemOfSubItemListNewSubItem.equals(questao)) {
                        oldFkItemOfSubItemListNewSubItem.getSubItemList().remove(subItemListNewSubItem);
                        oldFkItemOfSubItemListNewSubItem = em.merge(oldFkItemOfSubItemListNewSubItem);
                    }
                }
            }
            for (Itens itensListNewItens : itensListNew) {
                if (!itensListOld.contains(itensListNewItens)) {
                    Questao oldFkQuestaoOfItensListNewItens = itensListNewItens.getFkQuestao();
                    itensListNewItens.setFkQuestao(questao);
                    itensListNewItens = em.merge(itensListNewItens);
                    if (oldFkQuestaoOfItensListNewItens != null && !oldFkQuestaoOfItensListNewItens.equals(questao)) {
                        oldFkQuestaoOfItensListNewItens.getItensList().remove(itensListNewItens);
                        oldFkQuestaoOfItensListNewItens = em.merge(oldFkQuestaoOfItensListNewItens);
                    }
                }
            }
            for (Livre livreListNewLivre : livreListNew) {
                if (!livreListOld.contains(livreListNewLivre)) {
                    Questao oldFkItensOfLivreListNewLivre = livreListNewLivre.getFkItens();
                    livreListNewLivre.setFkItens(questao);
                    livreListNewLivre = em.merge(livreListNewLivre);
                    if (oldFkItensOfLivreListNewLivre != null && !oldFkItensOfLivreListNewLivre.equals(questao)) {
                        oldFkItensOfLivreListNewLivre.getLivreList().remove(livreListNewLivre);
                        oldFkItensOfLivreListNewLivre = em.merge(oldFkItensOfLivreListNewLivre);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = questao.getId();
                if (findQuestao(id) == null) {
                    throw new NonexistentEntityException("The questao with id " + id + " no longer exists.");
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
            Questao questao;
            try {
                questao = em.getReference(Questao.class, id);
                questao.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The questao with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<SubItem> subItemListOrphanCheck = questao.getSubItemList();
            for (SubItem subItemListOrphanCheckSubItem : subItemListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Questao (" + questao + ") cannot be destroyed since the SubItem " + subItemListOrphanCheckSubItem + " in its subItemList field has a non-nullable fkItem field.");
            }
            List<Itens> itensListOrphanCheck = questao.getItensList();
            for (Itens itensListOrphanCheckItens : itensListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Questao (" + questao + ") cannot be destroyed since the Itens " + itensListOrphanCheckItens + " in its itensList field has a non-nullable fkQuestao field.");
            }
            List<Livre> livreListOrphanCheck = questao.getLivreList();
            for (Livre livreListOrphanCheckLivre : livreListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Questao (" + questao + ") cannot be destroyed since the Livre " + livreListOrphanCheckLivre + " in its livreList field has a non-nullable fkItens field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Questionario fkQuestionario = questao.getFkQuestionario();
            if (fkQuestionario != null) {
                fkQuestionario.getQuestaoList().remove(questao);
                fkQuestionario = em.merge(fkQuestionario);
            }
            em.remove(questao);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Questao> findQuestaoEntities() {
        return findQuestaoEntities(true, -1, -1);
    }

    public List<Questao> findQuestaoEntities(int maxResults, int firstResult) {
        return findQuestaoEntities(false, maxResults, firstResult);
    }

    private List<Questao> findQuestaoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Questao.class));
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

    public Questao findQuestao(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Questao.class, id);
        } finally {
            em.close();
        }
    }

    public int getQuestaoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Questao> rt = cq.from(Questao.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
