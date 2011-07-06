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
public class QuestionarioJpaController implements Serializable {

    public QuestionarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Questionario questionario) {
        if (questionario.getQuestaoList() == null) {
            questionario.setQuestaoList(new ArrayList<Questao>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Questao> attachedQuestaoList = new ArrayList<Questao>();
            for (Questao questaoListQuestaoToAttach : questionario.getQuestaoList()) {
                questaoListQuestaoToAttach = em.getReference(questaoListQuestaoToAttach.getClass(), questaoListQuestaoToAttach.getId());
                attachedQuestaoList.add(questaoListQuestaoToAttach);
            }
            questionario.setQuestaoList(attachedQuestaoList);
            em.persist(questionario);
            for (Questao questaoListQuestao : questionario.getQuestaoList()) {
                Questionario oldFkQuestionarioOfQuestaoListQuestao = questaoListQuestao.getFkQuestionario();
                questaoListQuestao.setFkQuestionario(questionario);
                questaoListQuestao = em.merge(questaoListQuestao);
                if (oldFkQuestionarioOfQuestaoListQuestao != null) {
                    oldFkQuestionarioOfQuestaoListQuestao.getQuestaoList().remove(questaoListQuestao);
                    oldFkQuestionarioOfQuestaoListQuestao = em.merge(oldFkQuestionarioOfQuestaoListQuestao);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Questionario questionario) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Questionario persistentQuestionario = em.find(Questionario.class, questionario.getId());
            List<Questao> questaoListOld = persistentQuestionario.getQuestaoList();
            List<Questao> questaoListNew = questionario.getQuestaoList();
            List<String> illegalOrphanMessages = null;
            for (Questao questaoListOldQuestao : questaoListOld) {
                if (!questaoListNew.contains(questaoListOldQuestao)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Questao " + questaoListOldQuestao + " since its fkQuestionario field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Questao> attachedQuestaoListNew = new ArrayList<Questao>();
            for (Questao questaoListNewQuestaoToAttach : questaoListNew) {
                questaoListNewQuestaoToAttach = em.getReference(questaoListNewQuestaoToAttach.getClass(), questaoListNewQuestaoToAttach.getId());
                attachedQuestaoListNew.add(questaoListNewQuestaoToAttach);
            }
            questaoListNew = attachedQuestaoListNew;
            questionario.setQuestaoList(questaoListNew);
            questionario = em.merge(questionario);
            for (Questao questaoListNewQuestao : questaoListNew) {
                if (!questaoListOld.contains(questaoListNewQuestao)) {
                    Questionario oldFkQuestionarioOfQuestaoListNewQuestao = questaoListNewQuestao.getFkQuestionario();
                    questaoListNewQuestao.setFkQuestionario(questionario);
                    questaoListNewQuestao = em.merge(questaoListNewQuestao);
                    if (oldFkQuestionarioOfQuestaoListNewQuestao != null && !oldFkQuestionarioOfQuestaoListNewQuestao.equals(questionario)) {
                        oldFkQuestionarioOfQuestaoListNewQuestao.getQuestaoList().remove(questaoListNewQuestao);
                        oldFkQuestionarioOfQuestaoListNewQuestao = em.merge(oldFkQuestionarioOfQuestaoListNewQuestao);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = questionario.getId();
                if (findQuestionario(id) == null) {
                    throw new NonexistentEntityException("The questionario with id " + id + " no longer exists.");
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
            Questionario questionario;
            try {
                questionario = em.getReference(Questionario.class, id);
                questionario.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The questionario with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Questao> questaoListOrphanCheck = questionario.getQuestaoList();
            for (Questao questaoListOrphanCheckQuestao : questaoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Questionario (" + questionario + ") cannot be destroyed since the Questao " + questaoListOrphanCheckQuestao + " in its questaoList field has a non-nullable fkQuestionario field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(questionario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Questionario> findQuestionarioEntities() {
        return findQuestionarioEntities(true, -1, -1);
    }

    public List<Questionario> findQuestionarioEntities(int maxResults, int firstResult) {
        return findQuestionarioEntities(false, maxResults, firstResult);
    }

    private List<Questionario> findQuestionarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Questionario.class));
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

    public Questionario findQuestionario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Questionario.class, id);
        } finally {
            em.close();
        }
    }

    public int getQuestionarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Questionario> rt = cq.from(Questionario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
