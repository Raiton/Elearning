/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elearningproject.facades;

import com.elearningproject.entities.Course;
import com.elearningproject.entities.Message;
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
public class MessageFacade extends AbstractFacade<Message> {
    @PersistenceContext(unitName = "E-learning_projectPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MessageFacade() {
        super(Message.class);
    }
    public List<Message> findByIdreceiver(int id_user_receiver) {
        List<Message> listMessages = null;
        try {
            listMessages = getEntityManager().createNamedQuery("Message.findByIdUserReceiver").setParameter("id_user_receiver", id_user_receiver).getResultList();
        } catch (NoResultException e) {
            return null;
        } catch (NonUniqueResultException e) {
            return null;
        }
        return listMessages;
    }
    public List<Message> findByIduserTable(int IdUserTable) {
        List<Message> listMessage = null;
        try {
            listMessage = getEntityManager().createNamedQuery("Message.findByIdUserTable").setParameter("IdUserTable", IdUserTable).getResultList();
        } catch (NoResultException e) {
            return null;
        } catch (NonUniqueResultException e) {
            return null;
        }
        return listMessage;
    }
    
     public List<Message> findHistoriqueMessages (int IdUserTable,int id_user_receiver) {
        List<Message> listMessagerie = null;
        try {
            listMessagerie = getEntityManager().createNamedQuery("Message.findByIdUserTableIdUserReceiver").setParameter("IdUserTable", IdUserTable).setParameter("id_user_receiver", id_user_receiver).getResultList();
        } catch (NoResultException e) {
            return null;
        } catch (NonUniqueResultException e) {
            return null;
        }
        return listMessagerie;
    }
    
}
