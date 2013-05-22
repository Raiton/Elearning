/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elearningproject.controllers;

import com.elearningproject.controllers.util.JsfUtil;
import com.elearningproject.entities.Chapter;
import com.elearningproject.entities.Content;
import com.elearningproject.entities.Course;
import com.elearningproject.entities.Topic;
import com.elearningproject.facades.ChapterFacade;
import com.elearningproject.facades.ContentFacade;
import com.elearningproject.facades.CourseFacade;
import com.elearningproject.facades.TopicFacade;
import com.elearningproject.personalisedclasses.PersonalisedNode;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author raiton
 */
@ManagedBean
@SessionScoped
public class TreeBean implements Serializable {

    private TreeNode root;
    private PersonalisedNode node0;
    private PersonalisedNode selectedNode;
    private String pageToInclude = "../course/";
    private Course course;
    private Topic topic;
    private Chapter chapter;
    private Content content;
    @EJB
    private CourseFacade coursefacade;
    @EJB
    private TopicFacade topicfacade;
    @EJB
    private ChapterFacade chapterfacade;
    @EJB
    private ContentFacade contentfacade;

    public String getPageToInclude() {
        return pageToInclude;
    }

    public void setPageToInclude(String pageToInclude) {
        this.pageToInclude = pageToInclude;
    }

    @PostConstruct
    public void initialize() {
        root = buildTree();
    }

    public TreeNode buildTree() {

        root = new DefaultTreeNode("Root", null);

        node0 = new PersonalisedNode("Course", root, "Course", new Course());

        return root;
    }

    public TreeBean() {
    }

    public void addNode() {

        if ("Course".equals(selectedNode.getNodetype())) {
            new PersonalisedNode("Topic", selectedNode, "Topic", new Topic());

        } else if ("Topic".equals(selectedNode.getNodetype())) {
            new PersonalisedNode("Chapter", selectedNode, "Chapter", new Chapter());
        } else if ("Chapter".equals(selectedNode.getNodetype())) {
            new PersonalisedNode("Content", selectedNode, "Content", new Content());
        }
        selectedNode.setExpanded(true);
    }

    public TreeNode getRoot() {
        return root;
    }

    public PersonalisedNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(PersonalisedNode selectedNode) {
        this.selectedNode = selectedNode;
    }

    public void displaySelectedSingle() {
        if (selectedNode != null) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected", selectedNode.getData().toString());

            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void deleteNode() {
        selectedNode.getChildren().clear();
        selectedNode.getParent().getChildren().remove(selectedNode);
        selectedNode.setParent(null);

        selectedNode = null;
    }

    public void onNodeSelect(NodeSelectEvent event) {
        PersonalisedNode selectedEntitiy = (PersonalisedNode) event.getTreeNode();
        if ("Course".equals(selectedEntitiy.getNodetype())) {
            pageToInclude = "../course/";
        } else if ("Topic".equals(selectedEntitiy.getNodetype())) {
            pageToInclude = "../topic/";
        } else if ("Chapter".equals(selectedEntitiy.getNodetype())) {
            pageToInclude = "../chapter/";
        } else if ("Content".equals(selectedEntitiy.getNodetype())) {
            pageToInclude = "../content/";
        }
    }

    public int getNumberWeeks() {
        int returned = 0;
        if (this.node0 != null) {
            returned = this.node0.getChildCount();
        }
        return returned;

    }

    private CourseFacade getCourseFacade() {
        return coursefacade;
    }

    private TopicFacade getTopicFacade() {
        return topicfacade;
    }

    private ChapterFacade getChapterFacade() {
        return chapterfacade;
    }

    private ContentFacade getContentFacade() {
        return contentfacade;
    }

    public void coursePreparation() {
        BigInteger bi = BigInteger.valueOf(getNumberWeeks());
        course = (Course) selectedNode.getEntity();
        course.setNbreWeeks(bi);
        Date currentDate = new Date();
        course.setCourseCreationDate(currentDate);
        course.setUpdateDate(currentDate);
    }

    public void topicPreparation() {
        topic = (Topic) selectedNode.getEntity();

    }

    public void chapterPreparation() {
        chapter = (Chapter) selectedNode.getEntity();

    }

    public void contentPreparation() {
        content = (Content) selectedNode.getEntity();

    }

    public String createcourse() {
        try {
            coursePreparation();
            if (course.getIdCourse() == null) {
                getCourseFacade().create(course);
            } else {
                getCourseFacade().edit(course);
            }
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("CourseCreated"));
            return null;
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String createtopic() {
        topicPreparation();

        try {
            if (topic.getIdTopic() == null) {
                getTopicFacade().create(topic);
            } else {
                getTopicFacade().edit(topic);
            }
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("CourseCreated"));
            return null;
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }

    }

    public String createchapter() {
        try {
            chapterPreparation();
            if (chapter.getChapterName() == null) {
                getChapterFacade().create(chapter);
            } else {
                getChapterFacade().edit(chapter);
            }
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ChapterCreated"));
            return null;
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String createcontent() {
        contentPreparation();

        try {
            if (content.getContentName() == null) {
                getContentFacade().create(content);
            } else {
                getContentFacade().edit(content);
            }
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ContentCreated"));
            return null;
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }

    }
}
