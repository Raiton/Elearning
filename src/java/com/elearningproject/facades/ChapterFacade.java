/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elearningproject.facades;

import com.elearningproject.entities.Chapter;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author raiton
 */
@Stateless
public class ChapterFacade extends AbstractFacade<Chapter> {

    @PersistenceContext(unitName = "E-learning_projectPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ChapterFacade() {
        super(Chapter.class);
    }

    public List<Chapter> findByIdTopic(int idTopic) {
        List<Chapter> listChapter = null;
        try {
            listChapter = getEntityManager().createNamedQuery("Chapter.findByIdTopic").setParameter("idTopic", idTopic).getResultList();
        } catch (NoResultException e) {
            return null;
        } catch (NonUniqueResultException e) {
            return null;
        }
        return listChapter;
    }
}
