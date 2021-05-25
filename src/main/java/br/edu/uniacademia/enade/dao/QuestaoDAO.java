/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.uniacademia.enade.dao;

import br.edu.uniacademia.enade.model.Questao;
import br.edu.uniacademia.enade.util.PersistenceUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author tassi
 */
public class QuestaoDAO {

    public static QuestaoDAO questaoDAO;

    public static QuestaoDAO getInstance() {
        if (questaoDAO == null) {
            questaoDAO = new QuestaoDAO();
        }
        return questaoDAO;
    }

    public Questao buscar(int codigo) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select t from Questao t where t.idQuestao =:id ");
        query.setParameter("id", codigo);

        List<Questao> tipoQuestao = query.getResultList();
        if (tipoQuestao != null && tipoQuestao.size() > 0) {
            return tipoQuestao.get(0);
        }

        return null;
    }

    public List<Questao> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from Questao t");
        return query.getResultList();
    }

    public void remover(Questao tipoQuestao) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        em.remove(tipoQuestao);
        em.getTransaction().commit();
    }

    public Questao atualizar(Questao tipoQuestao) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            tipoQuestao = em.merge(tipoQuestao);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
        return tipoQuestao;
    }

    public void persistir(Questao tipoQuestao) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(tipoQuestao);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    public void removeAll() {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("delete from Questao");
        query.executeUpdate();
        em.getTransaction().commit();
    }

}
