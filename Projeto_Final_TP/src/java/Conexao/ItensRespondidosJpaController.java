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
public class ItensRespondidosJpaController implements Serializable {

    public ItensRespondidosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ItensRespondidos itensRespondidos) {
        if (itensRespondidos.getRespostaList() == null) {
            itensRespondidos.setRespostaList(new ArrayList<Resposta>());
        }
        if (itensRespondidos.getSubItemList() == null) {
            itensRespondidos.setSubItemList(new ArrayList<SubItem>());
        }
        if (itensRespondidos.getEscolhaTipoList() == null) {
            itensRespondidos.setEscolhaTipoList(new ArrayList<EscolhaTipo>());
        }
        if (itensRespondidos.getLivreList() == null) {
            itensRespondidos.setLivreList(new ArrayList<Livre>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Resposta> attachedRespostaList = new ArrayList<Resposta>();
            for (Resposta respostaListRespostaToAttach : itensRespondidos.getRespostaList()) {
                respostaListRespostaToAttach = em.getReference(respostaListRespostaToAttach.getClass(), respostaListRespostaToAttach.getId());
                attachedRespostaList.add(respostaListRespostaToAttach);
            }
            itensRespondidos.setRespostaList(attachedRespostaList);
            List<SubItem> attachedSubItemList = new ArrayList<SubItem>();
            for (SubItem subItemListSubItemToAttach : itensRespondidos.getSubItemList()) {
                subItemListSubItemToAttach = em.getReference(subItemListSubItemToAttach.getClass(), subItemListSubItemToAttach.getId());
                attachedSubItemList.add(subItemListSubItemToAttach);
            }
            itensRespondidos.setSubItemList(attachedSubItemList);
            List<EscolhaTipo> attachedEscolhaTipoList = new ArrayList<EscolhaTipo>();
            for (EscolhaTipo escolhaTipoListEscolhaTipoToAttach : itensRespondidos.getEscolhaTipoList()) {
                escolhaTipoListEscolhaTipoToAttach = em.getReference(escolhaTipoListEscolhaTipoToAttach.getClass(), escolhaTipoListEscolhaTipoToAttach.getId());
                attachedEscolhaTipoList.add(escolhaTipoListEscolhaTipoToAttach);
            }
            itensRespondidos.setEscolhaTipoList(attachedEscolhaTipoList);
            List<Livre> attachedLivreList = new ArrayList<Livre>();
            for (Livre livreListLivreToAttach : itensRespondidos.getLivreList()) {
                livreListLivreToAttach = em.getReference(livreListLivreToAttach.getClass(), livreListLivreToAttach.getId());
                attachedLivreList.add(livreListLivreToAttach);
            }
            itensRespondidos.setLivreList(attachedLivreList);
            em.persist(itensRespondidos);
            for (Resposta respostaListResposta : itensRespondidos.getRespostaList()) {
                ItensRespondidos oldFkItemOfRespostaListResposta = respostaListResposta.getFkItem();
                respostaListResposta.setFkItem(itensRespondidos);
                respostaListResposta = em.merge(respostaListResposta);
                if (oldFkItemOfRespostaListResposta != null) {
                    oldFkItemOfRespostaListResposta.getRespostaList().remove(respostaListResposta);
                    oldFkItemOfRespostaListResposta = em.merge(oldFkItemOfRespostaListResposta);
                }
            }
            for (SubItem subItemListSubItem : itensRespondidos.getSubItemList()) {
                ItensRespondidos oldFkPaiOfSubItemListSubItem = subItemListSubItem.getFkPai();
                subItemListSubItem.setFkPai(itensRespondidos);
                subItemListSubItem = em.merge(subItemListSubItem);
                if (oldFkPaiOfSubItemListSubItem != null) {
                    oldFkPaiOfSubItemListSubItem.getSubItemList().remove(subItemListSubItem);
                    oldFkPaiOfSubItemListSubItem = em.merge(oldFkPaiOfSubItemListSubItem);
                }
            }
            for (EscolhaTipo escolhaTipoListEscolhaTipo : itensRespondidos.getEscolhaTipoList()) {
                ItensRespondidos oldFkPaiOfEscolhaTipoListEscolhaTipo = escolhaTipoListEscolhaTipo.getFkPai();
                escolhaTipoListEscolhaTipo.setFkPai(itensRespondidos);
                escolhaTipoListEscolhaTipo = em.merge(escolhaTipoListEscolhaTipo);
                if (oldFkPaiOfEscolhaTipoListEscolhaTipo != null) {
                    oldFkPaiOfEscolhaTipoListEscolhaTipo.getEscolhaTipoList().remove(escolhaTipoListEscolhaTipo);
                    oldFkPaiOfEscolhaTipoListEscolhaTipo = em.merge(oldFkPaiOfEscolhaTipoListEscolhaTipo);
                }
            }
            for (Livre livreListLivre : itensRespondidos.getLivreList()) {
                ItensRespondidos oldFkPaiOfLivreListLivre = livreListLivre.getFkPai();
                livreListLivre.setFkPai(itensRespondidos);
                livreListLivre = em.merge(livreListLivre);
                if (oldFkPaiOfLivreListLivre != null) {
                    oldFkPaiOfLivreListLivre.getLivreList().remove(livreListLivre);
                    oldFkPaiOfLivreListLivre = em.merge(oldFkPaiOfLivreListLivre);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ItensRespondidos itensRespondidos) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ItensRespondidos persistentItensRespondidos = em.find(ItensRespondidos.class, itensRespondidos.getId());
            List<Resposta> respostaListOld = persistentItensRespondidos.getRespostaList();
            List<Resposta> respostaListNew = itensRespondidos.getRespostaList();
            List<SubItem> subItemListOld = persistentItensRespondidos.getSubItemList();
            List<SubItem> subItemListNew = itensRespondidos.getSubItemList();
            List<EscolhaTipo> escolhaTipoListOld = persistentItensRespondidos.getEscolhaTipoList();
            List<EscolhaTipo> escolhaTipoListNew = itensRespondidos.getEscolhaTipoList();
            List<Livre> livreListOld = persistentItensRespondidos.getLivreList();
            List<Livre> livreListNew = itensRespondidos.getLivreList();
            List<String> illegalOrphanMessages = null;
            for (Resposta respostaListOldResposta : respostaListOld) {
                if (!respostaListNew.contains(respostaListOldResposta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Resposta " + respostaListOldResposta + " since its fkItem field is not nullable.");
                }
            }
            for (SubItem subItemListOldSubItem : subItemListOld) {
                if (!subItemListNew.contains(subItemListOldSubItem)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain SubItem " + subItemListOldSubItem + " since its fkPai field is not nullable.");
                }
            }
            for (EscolhaTipo escolhaTipoListOldEscolhaTipo : escolhaTipoListOld) {
                if (!escolhaTipoListNew.contains(escolhaTipoListOldEscolhaTipo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain EscolhaTipo " + escolhaTipoListOldEscolhaTipo + " since its fkPai field is not nullable.");
                }
            }
            for (Livre livreListOldLivre : livreListOld) {
                if (!livreListNew.contains(livreListOldLivre)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Livre " + livreListOldLivre + " since its fkPai field is not nullable.");
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
            itensRespondidos.setRespostaList(respostaListNew);
            List<SubItem> attachedSubItemListNew = new ArrayList<SubItem>();
            for (SubItem subItemListNewSubItemToAttach : subItemListNew) {
                subItemListNewSubItemToAttach = em.getReference(subItemListNewSubItemToAttach.getClass(), subItemListNewSubItemToAttach.getId());
                attachedSubItemListNew.add(subItemListNewSubItemToAttach);
            }
            subItemListNew = attachedSubItemListNew;
            itensRespondidos.setSubItemList(subItemListNew);
            List<EscolhaTipo> attachedEscolhaTipoListNew = new ArrayList<EscolhaTipo>();
            for (EscolhaTipo escolhaTipoListNewEscolhaTipoToAttach : escolhaTipoListNew) {
                escolhaTipoListNewEscolhaTipoToAttach = em.getReference(escolhaTipoListNewEscolhaTipoToAttach.getClass(), escolhaTipoListNewEscolhaTipoToAttach.getId());
                attachedEscolhaTipoListNew.add(escolhaTipoListNewEscolhaTipoToAttach);
            }
            escolhaTipoListNew = attachedEscolhaTipoListNew;
            itensRespondidos.setEscolhaTipoList(escolhaTipoListNew);
            List<Livre> attachedLivreListNew = new ArrayList<Livre>();
            for (Livre livreListNewLivreToAttach : livreListNew) {
                livreListNewLivreToAttach = em.getReference(livreListNewLivreToAttach.getClass(), livreListNewLivreToAttach.getId());
                attachedLivreListNew.add(livreListNewLivreToAttach);
            }
            livreListNew = attachedLivreListNew;
            itensRespondidos.setLivreList(livreListNew);
            itensRespondidos = em.merge(itensRespondidos);
            for (Resposta respostaListNewResposta : respostaListNew) {
                if (!respostaListOld.contains(respostaListNewResposta)) {
                    ItensRespondidos oldFkItemOfRespostaListNewResposta = respostaListNewResposta.getFkItem();
                    respostaListNewResposta.setFkItem(itensRespondidos);
                    respostaListNewResposta = em.merge(respostaListNewResposta);
                    if (oldFkItemOfRespostaListNewResposta != null && !oldFkItemOfRespostaListNewResposta.equals(itensRespondidos)) {
                        oldFkItemOfRespostaListNewResposta.getRespostaList().remove(respostaListNewResposta);
                        oldFkItemOfRespostaListNewResposta = em.merge(oldFkItemOfRespostaListNewResposta);
                    }
                }
            }
            for (SubItem subItemListNewSubItem : subItemListNew) {
                if (!subItemListOld.contains(subItemListNewSubItem)) {
                    ItensRespondidos oldFkPaiOfSubItemListNewSubItem = subItemListNewSubItem.getFkPai();
                    subItemListNewSubItem.setFkPai(itensRespondidos);
                    subItemListNewSubItem = em.merge(subItemListNewSubItem);
                    if (oldFkPaiOfSubItemListNewSubItem != null && !oldFkPaiOfSubItemListNewSubItem.equals(itensRespondidos)) {
                        oldFkPaiOfSubItemListNewSubItem.getSubItemList().remove(subItemListNewSubItem);
                        oldFkPaiOfSubItemListNewSubItem = em.merge(oldFkPaiOfSubItemListNewSubItem);
                    }
                }
            }
            for (EscolhaTipo escolhaTipoListNewEscolhaTipo : escolhaTipoListNew) {
                if (!escolhaTipoListOld.contains(escolhaTipoListNewEscolhaTipo)) {
                    ItensRespondidos oldFkPaiOfEscolhaTipoListNewEscolhaTipo = escolhaTipoListNewEscolhaTipo.getFkPai();
                    escolhaTipoListNewEscolhaTipo.setFkPai(itensRespondidos);
                    escolhaTipoListNewEscolhaTipo = em.merge(escolhaTipoListNewEscolhaTipo);
                    if (oldFkPaiOfEscolhaTipoListNewEscolhaTipo != null && !oldFkPaiOfEscolhaTipoListNewEscolhaTipo.equals(itensRespondidos)) {
                        oldFkPaiOfEscolhaTipoListNewEscolhaTipo.getEscolhaTipoList().remove(escolhaTipoListNewEscolhaTipo);
                        oldFkPaiOfEscolhaTipoListNewEscolhaTipo = em.merge(oldFkPaiOfEscolhaTipoListNewEscolhaTipo);
                    }
                }
            }
            for (Livre livreListNewLivre : livreListNew) {
                if (!livreListOld.contains(livreListNewLivre)) {
                    ItensRespondidos oldFkPaiOfLivreListNewLivre = livreListNewLivre.getFkPai();
                    livreListNewLivre.setFkPai(itensRespondidos);
                    livreListNewLivre = em.merge(livreListNewLivre);
                    if (oldFkPaiOfLivreListNewLivre != null && !oldFkPaiOfLivreListNewLivre.equals(itensRespondidos)) {
                        oldFkPaiOfLivreListNewLivre.getLivreList().remove(livreListNewLivre);
                        oldFkPaiOfLivreListNewLivre = em.merge(oldFkPaiOfLivreListNewLivre);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = itensRespondidos.getId();
                if (findItensRespondidos(id) == null) {
                    throw new NonexistentEntityException("The itensRespondidos with id " + id + " no longer exists.");
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
            ItensRespondidos itensRespondidos;
            try {
                itensRespondidos = em.getReference(ItensRespondidos.class, id);
                itensRespondidos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The itensRespondidos with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Resposta> respostaListOrphanCheck = itensRespondidos.getRespostaList();
            for (Resposta respostaListOrphanCheckResposta : respostaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ItensRespondidos (" + itensRespondidos + ") cannot be destroyed since the Resposta " + respostaListOrphanCheckResposta + " in its respostaList field has a non-nullable fkItem field.");
            }
            List<SubItem> subItemListOrphanCheck = itensRespondidos.getSubItemList();
            for (SubItem subItemListOrphanCheckSubItem : subItemListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ItensRespondidos (" + itensRespondidos + ") cannot be destroyed since the SubItem " + subItemListOrphanCheckSubItem + " in its subItemList field has a non-nullable fkPai field.");
            }
            List<EscolhaTipo> escolhaTipoListOrphanCheck = itensRespondidos.getEscolhaTipoList();
            for (EscolhaTipo escolhaTipoListOrphanCheckEscolhaTipo : escolhaTipoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ItensRespondidos (" + itensRespondidos + ") cannot be destroyed since the EscolhaTipo " + escolhaTipoListOrphanCheckEscolhaTipo + " in its escolhaTipoList field has a non-nullable fkPai field.");
            }
            List<Livre> livreListOrphanCheck = itensRespondidos.getLivreList();
            for (Livre livreListOrphanCheckLivre : livreListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ItensRespondidos (" + itensRespondidos + ") cannot be destroyed since the Livre " + livreListOrphanCheckLivre + " in its livreList field has a non-nullable fkPai field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(itensRespondidos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ItensRespondidos> findItensRespondidosEntities() {
        return findItensRespondidosEntities(true, -1, -1);
    }

    public List<ItensRespondidos> findItensRespondidosEntities(int maxResults, int firstResult) {
        return findItensRespondidosEntities(false, maxResults, firstResult);
    }

    private List<ItensRespondidos> findItensRespondidosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ItensRespondidos.class));
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

    public ItensRespondidos findItensRespondidos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ItensRespondidos.class, id);
        } finally {
            em.close();
        }
    }

    public int getItensRespondidosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ItensRespondidos> rt = cq.from(ItensRespondidos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
