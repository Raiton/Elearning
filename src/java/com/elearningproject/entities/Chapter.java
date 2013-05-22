/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elearningproject.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "chapter")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Chapter.findAll", query = "SELECT c FROM Chapter c"),
    @NamedQuery(name = "Chapter.findByIdChapter", query = "SELECT c FROM Chapter c WHERE c.idChapter = :idChapter"),
    @NamedQuery(name = "Chapter.findByChapterName", query = "SELECT c FROM Chapter c WHERE c.chapterName = :chapterName"),
    @NamedQuery(name = "Chapter.findByChapterRank", query = "SELECT c FROM Chapter c WHERE c.chapterRank = :chapterRank")})
public class Chapter implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_chapter")
    private Integer idChapter;
    @Size(max = 2147483647)
    @Column(name = "chapter_name")
    private String chapterName;
    @Column(name = "chapter_rank")
    private BigInteger chapterRank;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idChapter")
    private List<Content> contentList;
    @JoinColumn(name = "id_topic", referencedColumnName = "id_topic")
    @ManyToOne(optional = false)
    private Topic idTopic;

    public Chapter() {
    }

    public Chapter(Integer idChapter) {
        this.idChapter = idChapter;
    }

    public Integer getIdChapter() {
        return idChapter;
    }

    public void setIdChapter(Integer idChapter) {
        this.idChapter = idChapter;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public BigInteger getChapterRank() {
        return chapterRank;
    }

    public void setChapterRank(BigInteger chapterRank) {
        this.chapterRank = chapterRank;
    }

    @XmlTransient
    public List<Content> getContentList() {
        return contentList;
    }

    public void setContentList(List<Content> contentList) {
        this.contentList = contentList;
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
        hash += (idChapter != null ? idChapter.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Chapter)) {
            return false;
        }
        Chapter other = (Chapter) object;
        if ((this.idChapter == null && other.idChapter != null) || (this.idChapter != null && !this.idChapter.equals(other.idChapter))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return chapterName;
    }
    
}
