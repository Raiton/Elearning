/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elearningproject.facades;

import com.elearningproject.entities.Test;
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
public class TestFacade extends AbstractFacade<Test> {
    @PersistenceContext(unitName = "E-learning_projectPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TestFacade() {
        super(Test.class);
    }
    
    public List<Test> findByIdTopic(int idTopic) {
        List<Test> listTest = null;
        try {
            listTest = (List<Test>) getEntityManager().createNamedQuery("Test.findByIdTopic").setParameter("idTopic", idTopic).getResultList();
        } catch (NoResultException e) {
            return null;
        } catch (NonUniqueResultException e) {
            return null;
        }
        return listTest;
    } 
    
}
