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
import javax.persistence.Persistence;

/**
 *
 * @author Guilherme
 */
public class AlunoJpaController implements Serializable {

    public AlunoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public AlunoJpaController() {
       if (emf == null)
            this.emf = Persistence.createEntityManagerFactory("Projeto_Final_TP_PU");

    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Aluno aluno) {
        if (aluno.getRespostaList() == null) {
            aluno.setRespostaList(new ArrayList<Resposta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Resposta> attachedRespostaList = new ArrayList<Resposta>();
            for (Resposta respostaListRespostaToAttach : aluno.getRespostaList()) {
                respostaListRespostaToAttach = em.getReference(respostaListRespostaToAttach.getClass(), respostaListRespostaToAttach.getId());
                attachedRespostaList.add(respostaListRespostaToAttach);
            }
            aluno.setRespostaList(attachedRespostaList);
            em.persist(aluno);
            for (Resposta respostaListResposta : aluno.getRespostaList()) {
                Aluno oldFkAlunoOfRespostaListResposta = respostaListResposta.getFkAluno();
                respostaListResposta.setFkAluno(aluno);
                respostaListResposta = em.merge(respostaListResposta);
                if (oldFkAlunoOfRespostaListResposta != null) {
                    oldFkAlunoOfRespostaListResposta.getRespostaList().remove(respostaListResposta);
                    oldFkAlunoOfRespostaListResposta = em.merge(oldFkAlunoOfRespostaListResposta);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Aluno aluno) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Aluno persistentAluno = em.find(Aluno.class, aluno.getId());
            List<Resposta> respostaListOld = persistentAluno.getRespostaList();
            List<Resposta> respostaListNew = aluno.getRespostaList();
            List<String> illegalOrphanMessages = null;
            for (Resposta respostaListOldResposta : respostaListOld) {
                if (!respostaListNew.contains(respostaListOldResposta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Resposta " + respostaListOldResposta + " since its fkAluno field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Resposta> attachedRespostaListNew = new ArrayList<Resposta>();
            for (Resposta respostaListNewRespostaToAttach : respostaListNew) {
                respostaListNewRespostaToAttach = em.getReference(respostaListNewRespostaToAttach.getClass(), respostaListNewRespostaToAttach.getId());
                attachedRespostaListNew.add(respostaListNewRespostaToAttach);
            }
            respostaListNew = attachedRespostaListNew;
            aluno.setRespostaList(respostaListNew);
            aluno = em.merge(aluno);
            for (Resposta respostaListNewResposta : respostaListNew) {
                if (!respostaListOld.contains(respostaListNewResposta)) {
                    Aluno oldFkAlunoOfRespostaListNewResposta = respostaListNewResposta.getFkAluno();
                    respostaListNewResposta.setFkAluno(aluno);
                    respostaListNewResposta = em.merge(respostaListNewResposta);
                    if (oldFkAlunoOfRespostaListNewResposta != null && !oldFkAlunoOfRespostaListNewResposta.equals(aluno)) {
                        oldFkAlunoOfRespostaListNewResposta.getRespostaList().remove(respostaListNewResposta);
                        oldFkAlunoOfRespostaListNewResposta = em.merge(oldFkAlunoOfRespostaListNewResposta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = aluno.getId();
                if (findAluno(id) == null) {
                    throw new NonexistentEntityException("The aluno with id " + id + " no longer exists.");
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
            Aluno aluno;
            try {
                aluno = em.getReference(Aluno.class, id);
                aluno.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The aluno with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Resposta> respostaListOrphanCheck = aluno.getRespostaList();
            for (Resposta respostaListOrphanCheckResposta : respostaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Aluno (" + aluno + ") cannot be destroyed since the Resposta " + respostaListOrphanCheckResposta + " in its respostaList field has a non-nullable fkAluno field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(aluno);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Aluno> findAlunoEntities() {
        return findAlunoEntities(true, -1, -1);
    }

    public List<Aluno> findAlunoEntities(int maxResults, int firstResult) {
        return findAlunoEntities(false, maxResults, firstResult);
    }

    private List<Aluno> findAlunoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Aluno.class));
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

    public Aluno findAluno(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Aluno.class, id);
        } finally {
            em.close();
        }
    }

    public int getAlunoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Aluno> rt = cq.from(Aluno.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
