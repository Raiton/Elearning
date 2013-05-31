/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elearningproject.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "course")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Course.findAll", query = "SELECT c FROM Course c"),
    @NamedQuery(name = "Course.findByIdCourse", query = "SELECT c FROM Course c WHERE c.idCourse = :idCourse"),
    @NamedQuery(name = "Course.findByCourseCreationDate", query = "SELECT c FROM Course c WHERE c.courseCreationDate = :courseCreationDate"),
    @NamedQuery(name = "Course.findByCourseName", query = "SELECT c FROM Course c WHERE c.courseName = :courseName"),
    @NamedQuery(name = "Course.findByLaunchDate", query = "SELECT c FROM Course c WHERE c.launchDate = :launchDate"),
    @NamedQuery(name = "Course.findByNbreWeeks", query = "SELECT c FROM Course c WHERE c.nbreWeeks = :nbreWeeks"),
    @NamedQuery(name = "Course.findByPhoto", query = "SELECT c FROM Course c WHERE c.photo = :photo"),
    @NamedQuery(name = "Course.findByStatus", query = "SELECT c FROM Course c WHERE c.status = :status"),
    @NamedQuery(name = "Course.findByUpdateDate", query = "SELECT c FROM Course c WHERE c.updateDate = :updateDate"),
    @NamedQuery(name = "Course.findByIdField", query = "SELECT c FROM Course c WHERE c.idField.idField = :idField AND c.status = :published")})

public class Course implements Serializable {
    @Column(name = "course_description")
    private String courseDescription;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_course")
    private Integer idCourse;
    @Column(name = "course_creation_date")
    @Temporal(TemporalType.DATE)
    private Date courseCreationDate;
    @Size(max = 255)
    @Column(name = "course_name")
    private String courseName;
    @Column(name = "launch_date")
    @Temporal(TemporalType.DATE)
    private Date launchDate;
    @Column(name = "nbre_weeks")
    private BigInteger nbreWeeks;
    @Size(max = 255)
    @Column(name = "photo")
    private String photo;
    @Size(max = 255)
    @Column(name = "status")
    private String status;
    @Column(name = "update_date")
    @Temporal(TemporalType.DATE)
    private Date updateDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCourse",fetch=FetchType.EAGER)
    private List<Topic> topicList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCourse")
    private List<UserHasCourse> userHasCourseList;
    @JoinColumn(name = "id_field", referencedColumnName = "id_field")
    @ManyToOne
    private Field idField;

    public Course() {
    }

    public Course(Integer idCourse) {
        this.idCourse = idCourse;
    }

    public Integer getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(Integer idCourse) {
        this.idCourse = idCourse;
    }

    public Date getCourseCreationDate() {
        return courseCreationDate;
    }

    public void setCourseCreationDate(Date courseCreationDate) {
        this.courseCreationDate = courseCreationDate;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Date getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(Date launchDate) {
        this.launchDate = launchDate;
    }

    public BigInteger getNbreWeeks() {
        return nbreWeeks;
    }

    public void setNbreWeeks(BigInteger nbreWeeks) {
        this.nbreWeeks = nbreWeeks;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @XmlTransient
    public List<Topic> getTopicList() {
        return topicList;
    }

    public void setTopicList(List<Topic> topicList) {
        this.topicList = topicList;
    }

    @XmlTransient
    public List<UserHasCourse> getUserHasCourseList() {
        return userHasCourseList;
    }

    public void setUserHasCourseList(List<UserHasCourse> userHasCourseList) {
        this.userHasCourseList = userHasCourseList;
    }

    public Field getIdField() {
        return idField;
    }

    public void setIdField(Field idField) {
        this.idField = idField;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCourse != null ? idCourse.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Course)) {
            return false;
        }
        Course other = (Course) object;
        if ((this.idCourse == null && other.idCourse != null) || (this.idCourse != null && !this.idCourse.equals(other.idCourse))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return courseName;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }
    
}
