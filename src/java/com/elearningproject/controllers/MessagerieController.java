/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elearningproject.controllers;

import com.elearningproject.entities.Message;
import com.elearningproject.entities.UserTable;
import com.elearningproject.facades.MessageFacade;
import com.elearningproject.facades.UserTableFacade;
import com.elearningproject.personalisedclasses.ManagedBeanRetriever;
import java.awt.Event;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author raiton
 */
@ManagedBean
@SessionScoped
public class MessagerieController implements Serializable {

    @EJB
    private UserTableFacade userTableFacade;

    public UserTableFacade getUserTableFacade() {
        return userTableFacade;
    }
    private int idUserSelected;

    public int getIdUserSelected() {
        return idUserSelected;
    }

    public void setIdUserSelected(int idUserSelected) {
        this.idUserSelected = idUserSelected;
    }

    public void setUserTableFacade(UserTableFacade userTableFacade) {
        this.userTableFacade = userTableFacade;
    }
    private List<UserTable> users;

    public List<UserTable> getUsers() {
        return users;
    }

    public void setUsers(List<UserTable> users) {
        this.users = users;
    }
    @EJB
    private MessageFacade messageFacade;

    public MessageFacade getMessageFacade() {
        return messageFacade;
    }

    public void setMessageFacade(MessageFacade messageFacade) {
        this.messageFacade = messageFacade;
    }
    private List<Message> messages;

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
    private int IdUserTable;

    public int getIdUserTable() {
        return IdUserTable;
    }

    public void setIdUserTable() {
        LoginController loginController = (LoginController) ManagedBeanRetriever.getManagedBean("loginController");

        this.IdUserTable = loginController.getUsertable().getIdUserTable();
    }
    private int id_user_receiver;

    public int getId_user_receiver() {
        return id_user_receiver;
    }

    public void setId_user_receiver(int id_user_receiver) {
        this.id_user_receiver = id_user_receiver;
    }
    private String selectedUser;

    public String getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(String selectedUser) {
        this.selectedUser = selectedUser;
    }

    public List<UserTable> findUsers() {
        users = userTableFacade.findAll();
        return users;
    }
    public void findIdUserSelected() {
        idUserSelected= userTableFacade.findByUserName(selectedUser).getIdUserTable();
    
    
    }

    public List<Message> findMessages() {
        messages = messageFacade.findHistoriqueMessages(IdUserTable, idUserSelected);
        return messages;
    }

    
}
