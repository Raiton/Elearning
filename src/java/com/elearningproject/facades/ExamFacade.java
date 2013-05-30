/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elearningproject.facades;

import com.elearningproject.entities.Exam;
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
public class ExamFacade extends AbstractFacade<Exam> {
    @PersistenceContext(unitName = "E-learning_projectPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ExamFacade() {
        super(Exam.class);
    }
    
        public Exam findByIdTopic(int idTopic) {
        Exam exam = null;
        try {
            exam = (Exam) getEntityManager().createNamedQuery("Exam.findByIdTopic").setParameter("idTopic", idTopic).getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (NonUniqueResultException e) {
            return null;
        }
        return exam;
    }

    
}
