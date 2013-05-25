/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elearningproject.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
    @NamedQuery(name = "UserHasCourse.findByIdUserTable", query = "SELECT u FROM UserHasCourse u WHERE u.userHasCoursePK.idUserTable = :idUserTable"),
    @NamedQuery(name = "UserHasCourse.findByIdCourse", query = "SELECT u FROM UserHasCourse u WHERE u.userHasCoursePK.idCourse = :idCourse"),
    @NamedQuery(name = "UserHasCourse.findByIdUserHasCourse", query = "SELECT u FROM UserHasCourse u WHERE u.idUserHasCourse = :idUserHasCourse")})
public class UserHasCourse implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UserHasCoursePK userHasCoursePK;
    @Column(name = "id_user_has_course")
    private Integer idUserHasCourse;
    @JoinColumn(name = "id_user_table", referencedColumnName = "id_user_table", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private UserTable userTable;
    @JoinColumn(name = "id_course", referencedColumnName = "id_course", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Course course;

    public UserHasCourse() {
    }

    public UserHasCourse(UserHasCoursePK userHasCoursePK) {
        this.userHasCoursePK = userHasCoursePK;
    }

    public UserHasCourse(int idUserTable, int idCourse) {
        this.userHasCoursePK = new UserHasCoursePK(idUserTable, idCourse);
    }

    public UserHasCoursePK getUserHasCoursePK() {
        return userHasCoursePK;
    }

    public void setUserHasCoursePK(UserHasCoursePK userHasCoursePK) {
        this.userHasCoursePK = userHasCoursePK;
    }

    public Integer getIdUserHasCourse() {
        return idUserHasCourse;
    }

    public void setIdUserHasCourse(Integer idUserHasCourse) {
        this.idUserHasCourse = idUserHasCourse;
    }

    public UserTable getUserTable() {
        return userTable;
    }

    public void setUserTable(UserTable userTable) {
        this.userTable = userTable;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userHasCoursePK != null ? userHasCoursePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserHasCourse)) {
            return false;
        }
        UserHasCourse other = (UserHasCourse) object;
        if ((this.userHasCoursePK == null && other.userHasCoursePK != null) || (this.userHasCoursePK != null && !this.userHasCoursePK.equals(other.userHasCoursePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.elearningproject.entities.UserHasCourse[ userHasCoursePK=" + userHasCoursePK + " ]";
    }
    
}
