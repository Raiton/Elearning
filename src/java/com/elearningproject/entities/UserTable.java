/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elearningproject.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author raiton
 */
@Entity
@Table(name = "user_table")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserTable.findAll", query = "SELECT u FROM UserTable u"),
    @NamedQuery(name = "UserTable.findByIdUserTable", query = "SELECT u FROM UserTable u WHERE u.idUserTable = :idUserTable"),
    @NamedQuery(name = "UserTable.findByEMail", query = "SELECT u FROM UserTable u WHERE u.eMail = :eMail"),
    @NamedQuery(name = "UserTable.findByName", query = "SELECT u FROM UserTable u WHERE u.name = :name")})
public class UserTable implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_user_table")
    private Integer idUserTable;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 255)
    @Column(name = "e_mail")
    private String eMail;
    @Size(max = 255)
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUserTable")
    private List<UserHasCourse> userHasCourseList;
    @JoinColumn(name = "id_group_table", referencedColumnName = "id_group_table")
    @ManyToOne
    private GroupTable idGroupTable;
    @OneToMany(mappedBy = "idUserTable")
    private List<Message> messageList;
    @OneToMany(mappedBy = "idUserTable")
    private List<Account> accountList;
    @OneToMany(mappedBy = "idUserTable")
    private List<Exam> examList;

    public UserTable() {
    }

    public UserTable(Integer idUserTable) {
        this.idUserTable = idUserTable;
    }

    public Integer getIdUserTable() {
        return idUserTable;
    }

    public void setIdUserTable(Integer idUserTable) {
        this.idUserTable = idUserTable;
    }

    public String getEMail() {
        return eMail;
    }

    public void setEMail(String eMail) {
        this.eMail = eMail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public List<UserHasCourse> getUserHasCourseList() {
        return userHasCourseList;
    }

    public void setUserHasCourseList(List<UserHasCourse> userHasCourseList) {
        this.userHasCourseList = userHasCourseList;
    }

    public GroupTable getIdGroupTable() {
        return idGroupTable;
    }

    public void setIdGroupTable(GroupTable idGroupTable) {
        this.idGroupTable = idGroupTable;
    }

    @XmlTransient
    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }

    @XmlTransient
    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

    @XmlTransient
    public List<Exam> getExamList() {
        return examList;
    }

    public void setExamList(List<Exam> examList) {
        this.examList = examList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUserTable != null ? idUserTable.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserTable)) {
            return false;
        }
        UserTable other = (UserTable) object;
        if ((this.idUserTable == null && other.idUserTable != null) || (this.idUserTable != null && !this.idUserTable.equals(other.idUserTable))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return name;
    }
    
}
