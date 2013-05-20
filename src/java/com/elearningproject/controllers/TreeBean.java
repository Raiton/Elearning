/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elearningproject.controllers;

import com.elearningproject.controllers.util.JsfUtil;
import com.elearningproject.controllers.util.PaginationHelper;
import com.elearningproject.entities.Course;
import com.elearningproject.entities.Topic;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.TreeNode;
import com.elearningproject.facades.TopicFacade;
import com.elearningproject.facades.CourseFacade;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.DefaultTreeNode;

/**
 *
 * @author raiton
 */
@ManagedBean
@ViewScoped
public class TreeBean implements Serializable {
    private int numberCourses=0;
    private TreeNode root;
    private TreeNode node0;
    private TreeNode selectedNode;
    private String pageToInclude = "../course/";
    @EJB
    private TopicFacade topicfacade;
    @EJB
    private CourseFacade coursefacade;
    private Topic topic;
    private Course course;
    @ManagedProperty(value="#{courseController}")
    CourseController courseController;

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

    @PostConstruct
    public void initialize() {
        root = buildTree();
        courseController=new CourseController();
    }
    public TreeNode buildTree(){
        
        root = new DefaultTreeNode("Root", null);
        course=new Course();
        course.setCourseName("Course");
        node0 = new DefaultTreeNode(course,root);
        return root;
    }
    public TreeBean() {

    }

      public void addNode() {
      numberCourses++;
       Course course0=new Course();
        course0.setCourseName("Course "+numberCourses);
            new DefaultTreeNode(course0, selectedNode);
    
        selectedNode.setExpanded(true);
    }

  /*  public void addNode() {
        System.out.println("here in addnode");
        System.out.println(selectedNode.getNodetype());

        if (selectedNode.getNodetype() == "Course") {
            System.out.println(selectedNode.getNodetype());
            new TreeNode(topic, selectedNode, "Topic");
        } else if (selectedNode.getNodetype() == "Topic") {
            new TreeNode("Chapter", selectedNode, "Chapter");
        } else if (selectedNode.getNodetype() == "Chapter") {
            new TreeNode("Content", selectedNode, "Content");
        }
        selectedNode.setExpanded(true);
    }
*/
    public TreeNode getRoot() {
        return root;
    }

    public TreeNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(TreeNode selectedNode) {
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
        TreeNode selectedEntitiy = (TreeNode) event.getTreeNode();
        /*if (selectedEntitiy.getNodetype() == "Course") {*/
            setPageToInclude("../course/");
       /* } else if (selectedEntitiy.getNodetype() == "Topic") {
            setPageToInclude("../topic/");
        }*/


    }

    public int getNumberWeeks() {
        int returned = 0;
        if (this.node0 != null) {
            returned = this.node0.getChildCount();
        }
        return returned;

    }
    
    /*
    private Course current;
    private DataModel items = null;
    @EJB
    public com.elearningproject.facades.CourseFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public Course getSelected() {
        if (current == null) {
            current = new Course();
            selectedItemIndex = -1;
        }
        return current;
    }

    private CourseFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {
                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (Course) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Course();
         selectedItemIndex = -1;
        return null;
    }

    public void coursePreparation() {
        BigInteger bi = BigInteger.valueOf(getNumberWeeks());
        current.setNbreWeeks(bi);
        Date currentDate = new Date();
        current.setCourseCreationDate(currentDate);
        current.setUpdateDate(currentDate);
    }
/*
    public String create() {
        try {
            coursePreparation();
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("CourseCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Course) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("CourseUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Course) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("CourseDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    @FacesConverter(forClass = Course.class)
    public static class CourseControllerConverter implements Converter {

        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CourseController controller = (CourseController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "courseController");
            return controller.ejbFacade.find(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuffer sb = new StringBuffer();
            sb.append(value);
            return sb.toString();
        }

        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Course) {
                Course o = (Course) object;
                return getStringKey(o.getIdCourse());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Course.class.getName());
            }
        }
    }*/
}
