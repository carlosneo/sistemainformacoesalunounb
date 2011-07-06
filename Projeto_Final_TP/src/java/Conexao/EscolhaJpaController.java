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
public class EscolhaJpaController implements Serializable {

    public EscolhaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Escolha escolha) {
        if (escolha.getEscolhaTipoList() == null) {
            escolha.setEscolhaTipoList(new ArrayList<EscolhaTipo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Itens fkItens = escolha.getFkItens();
            if (fkItens != null) {
                fkItens = em.getReference(fkItens.getClass(), fkItens.getId());
                escolha.setFkItens(fkItens);
            }
            List<EscolhaTipo> attachedEscolhaTipoList = new ArrayList<EscolhaTipo>();
            for (EscolhaTipo escolhaTipoListEscolhaTipoToAttach : escolha.getEscolhaTipoList()) {
                escolhaTipoListEscolhaTipoToAttach = em.getReference(escolhaTipoListEscolhaTipoToAttach.getClass(), escolhaTipoListEscolhaTipoToAttach.getId());
                attachedEscolhaTipoList.add(escolhaTipoListEscolhaTipoToAttach);
            }
            escolha.setEscolhaTipoList(attachedEscolhaTipoList);
            em.persist(escolha);
            if (fkItens != null) {
                fkItens.getEscolhaList().add(escolha);
                fkItens = em.merge(fkItens);
            }
            for (EscolhaTipo escolhaTipoListEscolhaTipo : escolha.getEscolhaTipoList()) {
                Escolha oldFkEscolhaOfEscolhaTipoListEscolhaTipo = escolhaTipoListEscolhaTipo.getFkEscolha();
                escolhaTipoListEscolhaTipo.setFkEscolha(escolha);
                escolhaTipoListEscolhaTipo = em.merge(escolhaTipoListEscolhaTipo);
                if (oldFkEscolhaOfEscolhaTipoListEscolhaTipo != null) {
                    oldFkEscolhaOfEscolhaTipoListEscolhaTipo.getEscolhaTipoList().remove(escolhaTipoListEscolhaTipo);
                    oldFkEscolhaOfEscolhaTipoListEscolhaTipo = em.merge(oldFkEscolhaOfEscolhaTipoListEscolhaTipo);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Escolha escolha) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Escolha persistentEscolha = em.find(Escolha.class, escolha.getId());
            Itens fkItensOld = persistentEscolha.getFkItens();
            Itens fkItensNew = escolha.getFkItens();
            List<EscolhaTipo> escolhaTipoListOld = persistentEscolha.getEscolhaTipoList();
            List<EscolhaTipo> escolhaTipoListNew = escolha.getEscolhaTipoList();
            List<String> illegalOrphanMessages = null;
            for (EscolhaTipo escolhaTipoListOldEscolhaTipo : escolhaTipoListOld) {
                if (!escolhaTipoListNew.contains(escolhaTipoListOldEscolhaTipo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain EscolhaTipo " + escolhaTipoListOldEscolhaTipo + " since its fkEscolha field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (fkItensNew != null) {
                fkItensNew = em.getReference(fkItensNew.getClass(), fkItensNew.getId());
                escolha.setFkItens(fkItensNew);
            }
            List<EscolhaTipo> attachedEscolhaTipoListNew = new ArrayList<EscolhaTipo>();
            for (EscolhaTipo escolhaTipoListNewEscolhaTipoToAttach : escolhaTipoListNew) {
                escolhaTipoListNewEscolhaTipoToAttach = em.getReference(escolhaTipoListNewEscolhaTipoToAttach.getClass(), escolhaTipoListNewEscolhaTipoToAttach.getId());
                attachedEscolhaTipoListNew.add(escolhaTipoListNewEscolhaTipoToAttach);
            }
            escolhaTipoListNew = attachedEscolhaTipoListNew;
            escolha.setEscolhaTipoList(escolhaTipoListNew);
            escolha = em.merge(escolha);
            if (fkItensOld != null && !fkItensOld.equals(fkItensNew)) {
                fkItensOld.getEscolhaList().remove(escolha);
                fkItensOld = em.merge(fkItensOld);
            }
            if (fkItensNew != null && !fkItensNew.equals(fkItensOld)) {
                fkItensNew.getEscolhaList().add(escolha);
                fkItensNew = em.merge(fkItensNew);
            }
            for (EscolhaTipo escolhaTipoListNewEscolhaTipo : escolhaTipoListNew) {
                if (!escolhaTipoListOld.contains(escolhaTipoListNewEscolhaTipo)) {
                    Escolha oldFkEscolhaOfEscolhaTipoListNewEscolhaTipo = escolhaTipoListNewEscolhaTipo.getFkEscolha();
                    escolhaTipoListNewEscolhaTipo.setFkEscolha(escolha);
                    escolhaTipoListNewEscolhaTipo = em.merge(escolhaTipoListNewEscolhaTipo);
                    if (oldFkEscolhaOfEscolhaTipoListNewEscolhaTipo != null && !oldFkEscolhaOfEscolhaTipoListNewEscolhaTipo.equals(escolha)) {
                        oldFkEscolhaOfEscolhaTipoListNewEscolhaTipo.getEscolhaTipoList().remove(escolhaTipoListNewEscolhaTipo);
                        oldFkEscolhaOfEscolhaTipoListNewEscolhaTipo = em.merge(oldFkEscolhaOfEscolhaTipoListNewEscolhaTipo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = escolha.getId();
                if (findEscolha(id) == null) {
                    throw new NonexistentEntityException("The escolha with id " + id + " no longer exists.");
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
            Escolha escolha;
            try {
                escolha = em.getReference(Escolha.class, id);
                escolha.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The escolha with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<EscolhaTipo> escolhaTipoListOrphanCheck = escolha.getEscolhaTipoList();
            for (EscolhaTipo escolhaTipoListOrphanCheckEscolhaTipo : escolhaTipoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Escolha (" + escolha + ") cannot be destroyed since the EscolhaTipo " + escolhaTipoListOrphanCheckEscolhaTipo + " in its escolhaTipoList field has a non-nullable fkEscolha field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Itens fkItens = escolha.getFkItens();
            if (fkItens != null) {
                fkItens.getEscolhaList().remove(escolha);
                fkItens = em.merge(fkItens);
            }
            em.remove(escolha);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Escolha> findEscolhaEntities() {
        return findEscolhaEntities(true, -1, -1);
    }

    public List<Escolha> findEscolhaEntities(int maxResults, int firstResult) {
        return findEscolhaEntities(false, maxResults, firstResult);
    }

    private List<Escolha> findEscolhaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Escolha.class));
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

    public Escolha findEscolha(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Escolha.class, id);
        } finally {
            em.close();
        }
    }

    public int getEscolhaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Escolha> rt = cq.from(Escolha.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
