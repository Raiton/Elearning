/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elearningproject.entities;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author raiton
 */
@Entity
@Table(name = "user_has_course")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserHasCourse.findAll", query = "SELECT u FROM UserHasCourse u"),
    @NamedQuery(name = "UserHasCourse.findByIdUser", query = "SELECT u FROM UserHasCourse u where u.idUserTable= :idUserTable"),
    @NamedQuery(name = "UserHasCourse.findByIdUserHasCourse", query = "SELECT u FROM UserHasCourse u WHERE u.idUserHasCourse = :idUserHasCourse")})
public class UserHasCourse implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_user_has_course")
    private Integer idUserHasCourse;
    @JoinColumn(name = "id_user_table", referencedColumnName = "id_user_table")
    @ManyToOne(optional = false)
    private UserTable idUserTable;
    @JoinColumn(name = "id_course", referencedColumnName = "id_course")
    @ManyToOne(optional = false)
    private Course idCourse;

    public UserHasCourse() {
    }

    public UserHasCourse(Integer idUserHasCourse) {
        this.idUserHasCourse = idUserHasCourse;
    }

    public Integer getIdUserHasCourse() {
        return idUserHasCourse;
    }

    public void setIdUserHasCourse(Integer idUserHasCourse) {
        this.idUserHasCourse = idUserHasCourse;
    }

    public UserTable getIdUserTable() {
        return idUserTable;
    }

    public void setIdUserTable(UserTable idUserTable) {
        this.idUserTable = idUserTable;
    }

    public Course getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(Course idCourse) {
        this.idCourse = idCourse;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUserHasCourse != null ? idUserHasCourse.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserHasCourse)) {
            return false;
        }
        UserHasCourse other = (UserHasCourse) object;
        if ((this.idUserHasCourse == null && other.idUserHasCourse != null) || (this.idUserHasCourse != null && !this.idUserHasCourse.equals(other.idUserHasCourse))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.elearningproject.entities.UserHasCourse[ idUserHasCourse=" + idUserHasCourse + " ]";
    }
    
}
