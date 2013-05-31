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
    @EJB
    private UserHasCourseFacade userHasCourseFacade;

    public String getPageToInclude() {
        return pageToInclude;
    }

    public void setPageToInclude(String pageToInclude) {
        this.pageToInclude = pageToInclude;
    }

    @PostConstruct
    public void initialize() {
        root = buildTree();
        setSelectedNode(node0);
        node0.setSelected(true);
    }

    public TreeNode buildTree() {

        root = new DefaultTreeNode("Root", null);

        node0 = new PersonalisedNode("Course", root, "Course", new Course(), selectedNode);

        ((Course) node0.getEntity()).setPhoto("default.jpg");

        return root;
    }

    public TreeBean() {
    }

    public void addNode() {

        if ("Course".equals(selectedNode.getNodetype())) {
            if (((Course) selectedNode.getEntity()).getIdCourse() != null) {
                if (getNumberWeeks() < course.getNbreWeeks().intValue()) {
                    new PersonalisedNode("Topic", selectedNode, "Topic", new Topic(), selectedNode);
                } else {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Selected", "Maximum number of topics reached");

                    FacesContext.getCurrentInstance().addMessage(null, message);
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
                new PersonalisedNode("Content", selectedNode, "Content", new Content(), selectedNode);
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

    public void deleteNode() {
        if (!"Course".equals(selectedNode.getNodetype())) {
            selectedNode.getChildren().clear();
            // selectedNode.getParent().getChildren().remove(selectedNode);
            selectedNode.setParent(null);

            selectedNode = null;
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
        content.setContentUrl(pageToInclude);


    }

    public String createcourse() {
        try {
            coursePreparation();
            if (course.getIdCourse() == null) {
                getCourseFacade().create(course);
                UserHasCourse userHasCourse = new UserHasCourse();
                userHasCourse.setIdCourse(course);
                LoginController loginController = (LoginController) ManagedBeanRetriever.getManagedBean("loginController");
                userHasCourse.setIdUserTable(loginController.getUsertable());
                getUserHasCourseFacade().create(userHasCourse);

            } else {
                getCourseFacade().edit(course);
            }
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("CourseCreated"));
            return null;
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        } finally {
            selectedNode.setData(course.getCourseName());
            
        }
    }

    public String createtopic() {
        try {
            topicPreparation();
            if (topic.getIdTopic() == null) {
                getTopicFacade().create(topic);
            } else {
                getTopicFacade().edit(topic);
            }
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TopicCreated"));
            return null;
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        } finally {
            selectedNode.setData(topic.getIdTopic());
        }
    }

    public String createchapter() {
        try {
            chapterPreparation();
            if (chapter.getIdChapter() == null) {
                getChapterFacade().create(chapter);
            } else {
                getChapterFacade().edit(chapter);
            }
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ChapterCreated"));
            return null;
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        } finally {
            selectedNode.setData(chapter.getChapterName());
        }
    }

    public String createcontent() {
        contentPreparation();
        try {
            if (content.getIdContent() == null) {
                getContentFacade().create(content);
            } else {
                getContentFacade().edit(content);
            }
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ContentCreated"));
            return null;
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        } finally {
            selectedNode.setData(content.getContentName());
        }
    }

    public String clear() {

        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("treeBean");
        return "../course/aa.xhtml?faces-redirect=true";
    }
}
