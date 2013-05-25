/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elearningproject.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author raiton
 */
@Embeddable
public class UserHasCoursePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_user_table")
    private int idUserTable;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_course")
    private int idCourse;

    public UserHasCoursePK() {
    }

    public UserHasCoursePK(int idUserTable, int idCourse) {
        this.idUserTable = idUserTable;
        this.idCourse = idCourse;
    }

    public int getIdUserTable() {
        return idUserTable;
    }

    public void setIdUserTable(int idUserTable) {
        this.idUserTable = idUserTable;
    }

    public int getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(int idCourse) {
        this.idCourse = idCourse;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idUserTable;
        hash += (int) idCourse;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserHasCoursePK)) {
            return false;
        }
        UserHasCoursePK other = (UserHasCoursePK) object;
        if (this.idUserTable != other.idUserTable) {
            return false;
        }
        if (this.idCourse != other.idCourse) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.elearningproject.entities.UserHasCoursePK[ idUserTable=" + idUserTable + ", idCourse=" + idCourse + " ]";
    }
    
}
