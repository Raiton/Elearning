/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elearningproject.facades;

import com.elearningproject.entities.Message;
import com.elearningproject.entities.UserTable;
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
public class UserTableFacade extends AbstractFacade<UserTable> {
    @PersistenceContext(unitName = "E-learning_projectPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserTableFacade() {
        super(UserTable.class);
    }
    
         public UserTable findByUserName (String username) {
       UserTable userTable;
        try {
            userTable = (UserTable) getEntityManager().createNamedQuery("UserTable.findByName").setParameter("name", username).getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (NonUniqueResultException e) {
            return null;
        }
        return userTable;
    }
    
    
}
