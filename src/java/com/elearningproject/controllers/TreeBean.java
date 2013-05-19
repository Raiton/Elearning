/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elearningproject.controllers;

import com.elearningproject.facades.CourseFacade;
import com.elearningproject.personalisedclasses.PersonalisedNode;
import java.io.Serializable;
import java.math.BigInteger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
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
    

    private CourseController courseController = new CourseController();
    

    public CourseController getCourseController() {
        return courseController;
    }

    public void setCourseController(CourseController courseController) {
        this.courseController = courseController;
    }

    public String getPageToInclude() {
        return pageToInclude;
    }

    public void setPageToInclude(String pageToInclude) {
        this.pageToInclude = pageToInclude;
    }

    public TreeBean() {

        root = new DefaultTreeNode("Root", null);

        node0 = new PersonalisedNode("Course", root, "Course");

    }

    public void addNode() {
        System.out.println("here in addnode");
        System.out.println(selectedNode.getNodetype());

        if (selectedNode.getNodetype() == "Course") {
            System.out.println(selectedNode.getNodetype());
            new PersonalisedNode("Topic", selectedNode, "Topic");
        } else if (selectedNode.getNodetype() == "Topic") {
            new PersonalisedNode("Chapter", selectedNode, "Chapter");
        } else if (selectedNode.getNodetype() == "Chapter") {
            new PersonalisedNode("Content", selectedNode, "Content");
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
        setPageToInclude("../course/");
    }

    public int getNumberWeeks() {
        int returned = 0;
        if (this.node0 != null) {
            returned = this.node0.getChildCount();
        }
        return returned;

    }

    public String createCourse() {
        this.courseController.setNumberWeeks(this.getNumberWeeks());
        return this.courseController.create();
    }
}
