/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elearningproject.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author raiton
 */
@Entity
@Table(name = "message")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Message.findAll", query = "SELECT m FROM Message m"),
    @NamedQuery(name = "Message.findByIdMessage", query = "SELECT m FROM Message m WHERE m.idMessage = :idMessage"),
    @NamedQuery(name = "Message.findByIdUserReceiver", query = "SELECT m FROM Message m WHERE m.id_user_receiver = :id_user_receiver"),
    @NamedQuery(name = "Message.findByIdUserTable", query = "SELECT m FROM Message m WHERE m.idUserTable.idUserTable= :idUserTable"),
    @NamedQuery(name = "Message.findByIdUserTableIdUserReceiver", query = "SELECT m FROM Message m WHERE (m.idUserTable.idUserTable= :idUserTable AND m.id_user_receiver = :id_user_receiver)"),
    @NamedQuery(name = "Message.findByDate", query = "SELECT m FROM Message m WHERE m.date = :date"),
    @NamedQuery(name = "Message.findByMessageContent", query = "SELECT m FROM Message m WHERE m.messageContent = :messageContent")})
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_message")
    private Integer idMessage;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Size(max = 255)
    @Column(name = "message_content")
    private String messageContent;
    @Column(name = "id_user_receiver")
    private int id_user_receiver;
    @JoinColumn(name = "id_user_table", referencedColumnName = "id_user_table")
    @ManyToOne
    private UserTable idUserTable;

    public Message() {
    }

    public Message(Integer idMessage) {
        this.idMessage = idMessage;
    }

    public Integer getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(Integer idMessage) {
        this.idMessage = idMessage;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public UserTable getIdUserTable() {
        return idUserTable;
    }

    public void setIdUserTable(UserTable idUserTable) {
        this.idUserTable = idUserTable;
    }

    public int getId_user_receiver() {
        return id_user_receiver;
    }

    public void setId_user_receiver(int id_user_receiver) {
        this.id_user_receiver = id_user_receiver;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMessage != null ? idMessage.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Message)) {
            return false;
        }
        Message other = (Message) object;
        if ((this.idMessage == null && other.idMessage != null) || (this.idMessage != null && !this.idMessage.equals(other.idMessage))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.elearningproject.entities.Message[ idMessage=" + idMessage + " ]";
    }
}
