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
import com.elearningproject.entities.UserHasCourse;
import com.elearningproject.facades.ChapterFacade;
import com.elearningproject.facades.ContentFacade;
import com.elearningproject.facades.CourseFacade;
import com.elearningproject.facades.TopicFacade;
import com.elearningproject.personalisedclasses.PersonalisedNode;
import java.io.Serializable;
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
import com.elearningproject.facades.UserHasCourseFacade;
import com.elearningproject.personalisedclasses.ManagedBeanRetriever;
import javax.faces.event.ActionListener;

/**
 *
 * @author raiton
 */
@ManagedBean
@SessionScoped
public class TreeBeanModified implements Serializable {

    private TreeNode root;
    private PersonalisedNode node0;
    private PersonalisedNode selectedNode;
    private String pageToInclude = "../course/";
    private Course course;
    private Topic topic;
    private Chapter chapter;
    private Content content;
    private PersonalisedNode lastOneExpanded;
    @EJB
    private CourseFacade coursefacade;
    @EJB
    private TopicFacade topicfacade;
    @EJB
    private ChapterFacade chapterfacade;
    @EJB
    private ContentFacade contentfacade;
    @EJB
    private UserHasCourseFacade userHasCourseFacade;

    public String getPageToInclude() {
        return pageToInclude;
    }

    public void setPageToInclude(String pageToInclude) {
        this.pageToInclude = pageToInclude;
    }

    public void onload() {
        DashboardTutor dashboardTutor = (DashboardTutor) ManagedBeanRetriever.getManagedBean("dashboardTutor");
        course = dashboardTutor.getSelectedCourse();

        if (root == null) {
            initialize();
        }
    }

    public void initialize() {
        root = buildTree();
        if (selectedNode == null) {
            selectedNode = node0;

        }
        selectedNode.setSelected(true);


    }

    public TreeNode buildTree() {

        root = new DefaultTreeNode("Root", null);
        node0 = new PersonalisedNode(course.getCourseName(), root, "Course", course);
        node0.setExpanded(true);
        for (Topic child : course.getTopicList()) {
            PersonalisedNode tnChild = new PersonalisedNode(child.getNameTopic(), node0, "Topic", child, node0);
            buildTreeRecursively(tnChild);

        }

        return root;
    }

    void buildTreeRecursively(PersonalisedNode personalisedNode) {
        personalisedNode.setExpanded(true);

        if (personalisedNode.getNodetype() == "Topic") {
            Topic topic = (Topic) (personalisedNode.getEntity());
            for (Chapter child : topic.getChapterList()) {
                PersonalisedNode tnChild = new PersonalisedNode(child.getChapterName(), personalisedNode, "Chapter", child, personalisedNode);
                lastOneExpanded = tnChild;
                buildTreeRecursively(tnChild);
            }
        } else if (personalisedNode.getNodetype() == "Chapter") {
            Chapter chapter = (Chapter) (personalisedNode.getEntity());
            for (Content child : chapter.getContentList()) {
                PersonalisedNode tnChild = new PersonalisedNode(child.getContentName(), personalisedNode, "Content", child, personalisedNode);
                lastOneExpanded = tnChild;
            }
        }
    }

    public TreeBeanModified() {
    }

    public void addNode() {

        if ("Course".equals(selectedNode.getNodetype())) {
            if (((Course) selectedNode.getEntity()).getIdCourse() != null) {

                if (getNumberWeeks() < course.getNbreWeeks().intValue()) {
                    new PersonalisedNode("Topic", selectedNode, "Topic", new Topic(), selectedNode);
                }
            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Selected", "Please save the course");

                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        } else if ("Topic".equals(selectedNode.getNodetype())) {
            if (((Topic) selectedNode.getEntity()).getIdCourse() != null) {
                new PersonalisedNode("Chapter", selectedNode, "Chapter", new Chapter(), selectedNode);
            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Selected", "Please save the topic");

                FacesContext.getCurrentInstance().addMessage(null, message);
            }


        } else if ("Chapter".equals(selectedNode.getNodetype())) {
            if (((Chapter) selectedNode.getEntity()).getIdChapter() != null) {
                PersonalisedNode temp = new PersonalisedNode("Content", selectedNode, "Content", new Content(), selectedNode);
                ((Content) temp.getEntity()).setContentUrl("default.jpg");
            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Selected", "Please save the chapter");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
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

    public UserHasCourseFacade getUserHasCourseFacade() {
        return userHasCourseFacade;
    }

    public void setUserHasCourseFacade(UserHasCourseFacade userHasCourseFacade) {
        this.userHasCourseFacade = userHasCourseFacade;
    }

    public void coursePreparation() {
        // BigInteger bi = BigInteger.valueOf(getNumberWeeks());
        course = (Course) selectedNode.getEntity();
        // course.setNbreWeeks(bi);
        Date currentDate = new Date();
        course.setCourseCreationDate(currentDate);
        course.setUpdateDate(currentDate);
        course.setStatus("Not published");
    }

    public void topicPreparation() {
        topic = (Topic) selectedNode.getEntity();
        topic.setIdCourse(course);

    }

    public void chapterPreparation() {
        chapter = (Chapter) selectedNode.getEntity();
        chapter.setIdTopic((Topic) selectedNode.getParentPerso().getEntity());
    }

    public void contentPreparation() {
        content = (Content) selectedNode.getEntity();
        content.setIdChapter((Chapter) selectedNode.getParentPerso().getEntity());


    }

    public String createcourse() {
        try {
            if (course.getIdCourse() == null) {
                getCourseFacade().create(course);
                UserHasCourse userHasCourse = new UserHasCourse();
                userHasCourse.setIdCourse(course);
                LoginController loginController = (LoginController) ManagedBeanRetriever.getManagedBean("loginController");
                userHasCourse.setIdUserTable(loginController.getUsertable());
                getUserHasCourseFacade().create(userHasCourse);
                selectedNode.setData(course.getCourseName());


            } else {
                getCourseFacade().edit(course);
                Date currentDate = new Date();
                course.setUpdateDate(currentDate);
                selectedNode.setData(course.getCourseName());

            }
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("CourseUpdated"));
            return null;
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String createtopic() {
        try {
            topicPreparation();
            if (topic.getIdTopic() == null) {
                getTopicFacade().create(topic);
                selectedNode.setData(topic.getNameTopic());

            } else {
                getTopicFacade().edit(topic);
                selectedNode.setData(topic.getNameTopic());

            }
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TopicUpdated"));
            return null;
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }

    }

    public String createchapter() {
        try {
            chapterPreparation();
            if (chapter.getIdChapter() == null) {
                getChapterFacade().create(chapter);
                selectedNode.setData(chapter.getChapterName());

            } else {
                getChapterFacade().edit(chapter);
                selectedNode.setData(chapter.getChapterName());
            }
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ChapterUpdated"));
            return null;
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String createcontent() {
        contentPreparation();
        try {
            if (content.getIdContent() == null) {
                getContentFacade().create(content);
                selectedNode.setData(content.getContentName());

            } else {
                getContentFacade().edit(content);
                selectedNode.setData(content.getContentName());

            }
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ContentUpdated"));
            return null;
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }

    }

    public String clear() {

        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("treeBeanModified");
        return "aa.xhtml?faces-redirect=true;";
    }
}
