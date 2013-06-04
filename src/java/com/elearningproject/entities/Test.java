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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author raiton
 */
@Entity
        @Table(name = "test")
        @XmlRootElement
        @NamedQueries({
    @NamedQuery(name = "Test.findAll", query = "SELECT t FROM Test t"),
    @NamedQuery(name = "Test.findByIdTest", query = "SELECT t FROM Test t WHERE t.idTest = :idTest"),
    @NamedQuery(name = "Test.findByTestContent", query = "SELECT t FROM Test t WHERE t.testContent = :testContent"),
    @NamedQuery(name = "Test.findByIdTopic", query = "SELECT t FROM Test t WHERE t.idTopic.idTopic = :idTopic"),    
        @NamedQuery(name = "Test.findByTestName", query = "SELECT t FROM Test t WHERE t.testName = :testName")

})
public class Test implements Serializable {

    @Size(max = 2147483647)
    @Column(name = "r1")
    private String r1;
    @Size(max = 2147483647)
    @Column(name = "r2")
    private String r2;
    @Size(max = 2147483647)
    @Column(name = "r3")
    private String r3;
    @Column(name = "r")
    private Integer r;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_test")
    private Integer idTest;
    @Size(max = 255)
    @Column(name = "test_content")
    private String testContent;
    @Size(max = 255)
    @Column(name = "test_name")
    private String testName;
    @JoinColumn(name = "id_topic", referencedColumnName = "id_topic")
    @ManyToOne
    private Topic idTopic;

    public Test() {
    }

    public Test(Integer idTest) {
        this.idTest = idTest;
    }

    public Integer getIdTest() {
        return idTest;
    }

    public void setIdTest(Integer idTest) {
        this.idTest = idTest;
    }

    public String getTestContent() {
        return testContent;
    }

    public void setTestContent(String testContent) {
        this.testContent = testContent;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public Topic getIdTopic() {
        return idTopic;
    }

    public void setIdTopic(Topic idTopic) {
        this.idTopic = idTopic;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTest != null ? idTest.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Test)) {
            return false;
        }
        Test other = (Test) object;
        if ((this.idTest == null && other.idTest != null) || (this.idTest != null && !this.idTest.equals(other.idTest))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.elearningproject.entities.Test[ idTest=" + idTest + " ]";
    }

    public String getR1() {
        return r1;
    }

    public void setR1(String r1) {
        this.r1 = r1;
    }

    public String getR2() {
        return r2;
    }

    public void setR2(String r2) {
        this.r2 = r2;
    }

    public String getR3() {
        return r3;
    }

    public void setR3(String r3) {
        this.r3 = r3;
    }

    public Integer getR() {
        return r;
    }

    public void setR(Integer r) {
        this.r = r;
    }
}
