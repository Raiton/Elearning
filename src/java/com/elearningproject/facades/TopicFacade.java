/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elearningproject.facades;

import com.elearningproject.entities.Topic;
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
public class TopicFacade extends AbstractFacade<Topic> {
    @PersistenceContext(unitName = "E-learning_projectPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TopicFacade() {
        super(Topic.class);
    }
       public List<Topic> findByIdCourse(int idCourse) {
        List<Topic> listTopic =  null;
        try {
            listTopic = getEntityManager().createNamedQuery("Topic.findByIdCourse").setParameter("idCourse", idCourse).getResultList();
        } catch (NoResultException e) {
            return null;
        } catch (NonUniqueResultException e) {
            return null;
        }
        return listTopic;
    }
    
}
