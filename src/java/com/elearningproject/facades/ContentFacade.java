/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elearningproject.facades;

import com.elearningproject.entities.Content;
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
public class ContentFacade extends AbstractFacade<Content> {
    @PersistenceContext(unitName = "E-learning_projectPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ContentFacade() {
        super(Content.class);
    }
    
    public List<Content> findByIdChapter(int idChapter) {
        List<Content> listContent = null;
        try {
            listContent = getEntityManager().createNamedQuery("Content.findByIdChapter").setParameter("idChapter", idChapter).getResultList();
        } catch (NoResultException e) {
            return null;
        } catch (NonUniqueResultException e) {
            return null;
        }
        return listContent;
    }
    
}
