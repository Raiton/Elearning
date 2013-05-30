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
@Table(name = "content")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Content.findAll", query = "SELECT c FROM Content c"),
    @NamedQuery(name = "Content.findByIdContent", query = "SELECT c FROM Content c WHERE c.idContent = :idContent"),
    @NamedQuery(name = "Content.findByContentName", query = "SELECT c FROM Content c WHERE c.contentName = :contentName"),
    @NamedQuery(name = "Content.findByContentUrl", query = "SELECT c FROM Content c WHERE c.contentUrl = :contentUrl"),
    @NamedQuery(name = "Content.findByIdChapter", query = "SELECT c FROM Content c WHERE c.idChapter.idChapter = :idChapter")})
public class Content implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_content")
    private Integer idContent;
    @Size(max = 255)
    @Column(name = "content_name")
    private String contentName;
    @Size(max = 255)
    @Column(name = "content_url")
    private String contentUrl;
    @JoinColumn(name = "id_form", referencedColumnName = "id_form")
    @ManyToOne
    private Form idForm;
    @JoinColumn(name = "id_chapter", referencedColumnName = "id_chapter")
    @ManyToOne
    private Chapter idChapter;

    public Content() {
    }

    public Content(Integer idContent) {
        this.idContent = idContent;
    }

    public Integer getIdContent() {
        return idContent;
    }

    public void setIdContent(Integer idContent) {
        this.idContent = idContent;
    }

    public String getContentName() {
        return contentName;
    }

    public void setContentName(String contentName) {
        this.contentName = contentName;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }

    public Form getIdForm() {
        return idForm;
    }

    public void setIdForm(Form idForm) {
        this.idForm = idForm;
    }

    public Chapter getIdChapter() {
        return idChapter;
    }

    public void setIdChapter(Chapter idChapter) {
        this.idChapter = idChapter;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idContent != null ? idContent.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Content)) {
            return false;
        }
        Content other = (Content) object;
        if ((this.idContent == null && other.idContent != null) || (this.idContent != null && !this.idContent.equals(other.idContent))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.elearningproject.entities.Content[ idContent=" + idContent + " ]";
    }
    
}
