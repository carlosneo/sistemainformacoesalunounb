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
public class RespostaJpaController implements Serializable {

    public RespostaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Resposta resposta) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Aluno fkAluno = resposta.getFkAluno();
            if (fkAluno != null) {
                fkAluno = em.getReference(fkAluno.getClass(), fkAluno.getId());
                resposta.setFkAluno(fkAluno);
            }
            ItensRespondidos fkItem = resposta.getFkItem();
            if (fkItem != null) {
                fkItem = em.getReference(fkItem.getClass(), fkItem.getId());
                resposta.setFkItem(fkItem);
            }
            em.persist(resposta);
            if (fkAluno != null) {
                fkAluno.getRespostaList().add(resposta);
                fkAluno = em.merge(fkAluno);
            }
            if (fkItem != null) {
                fkItem.getRespostaList().add(resposta);
                fkItem = em.merge(fkItem);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Resposta resposta) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Resposta persistentResposta = em.find(Resposta.class, resposta.getId());
            Aluno fkAlunoOld = persistentResposta.getFkAluno();
            Aluno fkAlunoNew = resposta.getFkAluno();
            ItensRespondidos fkItemOld = persistentResposta.getFkItem();
            ItensRespondidos fkItemNew = resposta.getFkItem();
            if (fkAlunoNew != null) {
                fkAlunoNew = em.getReference(fkAlunoNew.getClass(), fkAlunoNew.getId());
                resposta.setFkAluno(fkAlunoNew);
            }
            if (fkItemNew != null) {
                fkItemNew = em.getReference(fkItemNew.getClass(), fkItemNew.getId());
                resposta.setFkItem(fkItemNew);
            }
            resposta = em.merge(resposta);
            if (fkAlunoOld != null && !fkAlunoOld.equals(fkAlunoNew)) {
                fkAlunoOld.getRespostaList().remove(resposta);
                fkAlunoOld = em.merge(fkAlunoOld);
            }
            if (fkAlunoNew != null && !fkAlunoNew.equals(fkAlunoOld)) {
                fkAlunoNew.getRespostaList().add(resposta);
                fkAlunoNew = em.merge(fkAlunoNew);
            }
            if (fkItemOld != null && !fkItemOld.equals(fkItemNew)) {
                fkItemOld.getRespostaList().remove(resposta);
                fkItemOld = em.merge(fkItemOld);
            }
            if (fkItemNew != null && !fkItemNew.equals(fkItemOld)) {
                fkItemNew.getRespostaList().add(resposta);
                fkItemNew = em.merge(fkItemNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = resposta.getId();
                if (findResposta(id) == null) {
                    throw new NonexistentEntityException("The resposta with id " + id + " no longer exists.");
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
            Resposta resposta;
            try {
                resposta = em.getReference(Resposta.class, id);
                resposta.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The resposta with id " + id + " no longer exists.", enfe);
            }
            Aluno fkAluno = resposta.getFkAluno();
            if (fkAluno != null) {
                fkAluno.getRespostaList().remove(resposta);
                fkAluno = em.merge(fkAluno);
            }
            ItensRespondidos fkItem = resposta.getFkItem();
            if (fkItem != null) {
                fkItem.getRespostaList().remove(resposta);
                fkItem = em.merge(fkItem);
            }
            em.remove(resposta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Resposta> findRespostaEntities() {
        return findRespostaEntities(true, -1, -1);
    }

    public List<Resposta> findRespostaEntities(int maxResults, int firstResult) {
        return findRespostaEntities(false, maxResults, firstResult);
    }

    private List<Resposta> findRespostaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Resposta.class));
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

    public Resposta findResposta(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Resposta.class, id);
        } finally {
            em.close();
        }
    }

    public int getRespostaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Resposta> rt = cq.from(Resposta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
