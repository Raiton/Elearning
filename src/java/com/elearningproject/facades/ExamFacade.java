/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elearningproject.facades;

import com.elearningproject.entities.Exam;
import java.util.ArrayList;
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
        List<Exam> exam = null;
        try {
            exam = getEntityManager().createNamedQuery("Exam.findByIdTopic").setParameter("idTopic", idTopic).getResultList();
        } catch (NoResultException e) {
            return null;
        } catch (NonUniqueResultException e) {
            return null;
        }
        return exam.get(0);
    }
        
         public Exam findByIdTopicAndIdUser(int idTopic, int idUserTable) {
        List<Exam> exam = new ArrayList<Exam>();
        try {
            exam = getEntityManager().createNamedQuery("Exam.findByIdTopicAndIdUser").setParameter("idTopic", idTopic).setParameter("idUserTable", idUserTable).getResultList();
        } catch (NoResultException e) {
            return null;
        } catch (NonUniqueResultException e) {
            return null;
        }
        return exam.get(exam.size()-1);
    } 

    
}
