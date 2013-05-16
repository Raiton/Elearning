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
@Table(name = "exam")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Exam.findAll", query = "SELECT e FROM Exam e"),
    @NamedQuery(name = "Exam.findByIdExam", query = "SELECT e FROM Exam e WHERE e.idExam = :idExam"),
    @NamedQuery(name = "Exam.findByExamContent", query = "SELECT e FROM Exam e WHERE e.examContent = :examContent"),
    @NamedQuery(name = "Exam.findByMark", query = "SELECT e FROM Exam e WHERE e.mark = :mark"),
    @NamedQuery(name = "Exam.findByResponse", query = "SELECT e FROM Exam e WHERE e.response = :response"),
    @NamedQuery(name = "Exam.findByDeadline", query = "SELECT e FROM Exam e WHERE e.deadline = :deadline"),
    @NamedQuery(name = "Exam.findByBeginingDate", query = "SELECT e FROM Exam e WHERE e.beginingDate = :beginingDate")})
public class Exam implements Serializable {
    @JoinColumn(name = "id_topic", referencedColumnName = "id_topic")
    @ManyToOne(optional = false)
    private Topic idTopic;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_exam")
    private Integer idExam;
    @Size(max = 2147483647)
    @Column(name = "exam_content")
    private String examContent;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "mark")
    private Double mark;
    @Size(max = 2147483647)
    @Column(name = "response")
    private String response;
    @Column(name = "deadline")
    @Temporal(TemporalType.DATE)
    private Date deadline;
    @Column(name = "begining_date")
    @Temporal(TemporalType.DATE)
    private Date beginingDate;
    @JoinColumn(name = "id_user_table", referencedColumnName = "id_user_table")
    @ManyToOne(optional = false)
    private UserTable idUserTable;

    public Exam() {
    }

    public Exam(Integer idExam) {
        this.idExam = idExam;
    }

    public Integer getIdExam() {
        return idExam;
    }

    public void setIdExam(Integer idExam) {
        this.idExam = idExam;
    }

    public String getExamContent() {
        return examContent;
    }

    public void setExamContent(String examContent) {
        this.examContent = examContent;
    }

    public Double getMark() {
        return mark;
    }

    public void setMark(Double mark) {
        this.mark = mark;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Date getBeginingDate() {
        return beginingDate;
    }

    public void setBeginingDate(Date beginingDate) {
        this.beginingDate = beginingDate;
    }

    public UserTable getIdUserTable() {
        return idUserTable;
    }

    public void setIdUserTable(UserTable idUserTable) {
        this.idUserTable = idUserTable;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idExam != null ? idExam.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Exam)) {
            return false;
        }
        Exam other = (Exam) object;
        if ((this.idExam == null && other.idExam != null) || (this.idExam != null && !this.idExam.equals(other.idExam))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.elearningproject.entities.Exam[ idExam=" + idExam + " ]";
    }

    public Topic getIdTopic() {
        return idTopic;
    }

    public void setIdTopic(Topic idTopic) {
        this.idTopic = idTopic;
    }
    
}
