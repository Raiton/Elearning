/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elearningproject.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
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
@Table(name = "topic")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Topic.findAll", query = "SELECT t FROM Topic t"),
    @NamedQuery(name = "Topic.findByIdTopic", query = "SELECT t FROM Topic t WHERE t.idTopic = :idTopic"),
    @NamedQuery(name = "Topic.findByNameTopic", query = "SELECT t FROM Topic t WHERE t.nameTopic = :nameTopic"),
    @NamedQuery(name = "Topic.findByWeekNumber", query = "SELECT t FROM Topic t WHERE t.weekNumber = :weekNumber")})
public class Topic implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_topic")
    private Integer idTopic;
    @Size(max = 255)
    @Column(name = "name_topic")
    private String nameTopic;
    @Column(name = "week_number")
    private BigInteger weekNumber;
    @JoinColumn(name = "id_course", referencedColumnName = "id_course")
    @ManyToOne
    private Course idCourse;
    @OneToMany(mappedBy = "idTopic")
    private List<Test> testList;
    @OneToMany(mappedBy = "idTopic")
    private List<Chapter> chapterList;
    @OneToMany(mappedBy = "idTopic")
    private List<Exam> examList;

    public Topic() {
    }

    public Topic(Integer idTopic) {
        this.idTopic = idTopic;
    }

    public Integer getIdTopic() {
        return idTopic;
    }

    public void setIdTopic(Integer idTopic) {
        this.idTopic = idTopic;
    }

    public String getNameTopic() {
        return nameTopic;
    }

    public void setNameTopic(String nameTopic) {
        this.nameTopic = nameTopic;
    }

    public BigInteger getWeekNumber() {
        return weekNumber;
    }

    public void setWeekNumber(BigInteger weekNumber) {
        this.weekNumber = weekNumber;
    }

    public Course getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(Course idCourse) {
        this.idCourse = idCourse;
    }

    @XmlTransient
    public List<Test> getTestList() {
        return testList;
    }

    public void setTestList(List<Test> testList) {
        this.testList = testList;
    }

    @XmlTransient
    public List<Chapter> getChapterList() {
        return chapterList;
    }

    public void setChapterList(List<Chapter> chapterList) {
        this.chapterList = chapterList;
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
        hash += (idTopic != null ? idTopic.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Topic)) {
            return false;
        }
        Topic other = (Topic) object;
        if ((this.idTopic == null && other.idTopic != null) || (this.idTopic != null && !this.idTopic.equals(other.idTopic))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.elearningproject.entities.Topic[ idTopic=" + idTopic + " ]";
    }
    
}
