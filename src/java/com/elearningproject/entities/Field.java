/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elearningproject.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author raiton
 */
@Entity
@Table(name = "field")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Field.findAll", query = "SELECT f FROM Field f"),
    @NamedQuery(name = "Field.findByIdField", query = "SELECT f FROM Field f WHERE f.idField = :idField"),
    @NamedQuery(name = "Field.findByFieldCreationDate", query = "SELECT f FROM Field f WHERE f.fieldCreationDate = :fieldCreationDate"),
    @NamedQuery(name = "Field.findByFieldName", query = "SELECT f FROM Field f WHERE f.fieldName = :fieldName")})
public class Field implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_field")
    private Integer idField;
    @Column(name = "field_creation_date")
    @Temporal(TemporalType.DATE)
    private Date fieldCreationDate;
    @Size(max = 255)
    @Column(name = "field_name")
    private String fieldName;
    @OneToMany(mappedBy = "idField")
    private List<Course> courseList;

    public Field() {
    }

    public Field(Integer idField) {
        this.idField = idField;
    }

    public Integer getIdField() {
        return idField;
    }

    public void setIdField(Integer idField) {
        this.idField = idField;
    }

    public Date getFieldCreationDate() {
        return fieldCreationDate;
    }

    public void setFieldCreationDate(Date fieldCreationDate) {
        this.fieldCreationDate = fieldCreationDate;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    @XmlTransient
    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idField != null ? idField.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Field)) {
            return false;
        }
        Field other = (Field) object;
        if ((this.idField == null && other.idField != null) || (this.idField != null && !this.idField.equals(other.idField))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.elearningproject.entities.Field[ idField=" + idField + " ]";
    }
    
}
