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
@Table(name = "group_table")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GroupTable.findAll", query = "SELECT g FROM GroupTable g"),
    @NamedQuery(name = "GroupTable.findByIdGroupTable", query = "SELECT g FROM GroupTable g WHERE g.idGroupTable = :idGroupTable"),
    @NamedQuery(name = "GroupTable.findByGroupName", query = "SELECT g FROM GroupTable g WHERE g.groupName = :groupName")})
public class GroupTable implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_group_table")
    private Integer idGroupTable;
    @Size(max = 2147483647)
    @Column(name = "group_name")
    private String groupName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idGroupTable")
    private List<UserTable> userTableList;

    public GroupTable() {
    }

    public GroupTable(Integer idGroupTable) {
        this.idGroupTable = idGroupTable;
    }

    public Integer getIdGroupTable() {
        return idGroupTable;
    }

    public void setIdGroupTable(Integer idGroupTable) {
        this.idGroupTable = idGroupTable;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @XmlTransient
    public List<UserTable> getUserTableList() {
        return userTableList;
    }

    public void setUserTableList(List<UserTable> userTableList) {
        this.userTableList = userTableList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGroupTable != null ? idGroupTable.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GroupTable)) {
            return false;
        }
        GroupTable other = (GroupTable) object;
        if ((this.idGroupTable == null && other.idGroupTable != null) || (this.idGroupTable != null && !this.idGroupTable.equals(other.idGroupTable))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.elearningproject.entities.GroupTable[ idGroupTable=" + idGroupTable + " ]";
    }
    
}
