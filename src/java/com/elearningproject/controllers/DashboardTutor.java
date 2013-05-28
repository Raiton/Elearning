/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elearningproject.controllers;

import com.elearningproject.entities.Course;
import com.elearningproject.facades.CourseFacade;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import com.elearningproject.facades.UserHasCourseFacade;
import com.elearningproject.personalisedclasses.ManagedBeanRetriever;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author raiton
 */
@ManagedBean
@SessionScoped
public class DashboardTutor implements Serializable {

    @EJB
    private UserHasCourseFacade userHasCourseFacade;
    private List<Course> listcourse;
    private Course selectedCourse;
    @EJB
    private CourseFacade courseFacade;

    public CourseFacade getCourseFacade() {
        return courseFacade;
    }

    public void setCourseFacade(CourseFacade courseFacade) {
        this.courseFacade = courseFacade;
    }

    public List<Course> getListcourse() {
        LoginController loginController = (LoginController) ManagedBeanRetriever.getManagedBean("loginController");
        listcourse = userHasCourseFacade.findCourseByUserTable(loginController.getUsertable());

        return listcourse;
    }

    public void setListcourse(List<Course> listcourse) {
        this.listcourse = listcourse;
    }

    public UserHasCourseFacade getUserHasCourseFacade() {
        return userHasCourseFacade;
    }

    public void setUserHasCourseFacade(UserHasCourseFacade userHasCourseFacade) {
        this.userHasCourseFacade = userHasCourseFacade;
    }

    public Course getSelectedCourse() {
        return selectedCourse;
    }

    public void setSelectedCourse(Course selectedCourse) {
        this.selectedCourse = selectedCourse;
    }

    public DashboardTutor() {
    }

    public String redirect() {
        System.out.println("dsq");
        return "dashboarduser.xhtml?faces-redirect=true";
    }

    public String gotoEditCourse(Course course) {
        this.selectedCourse = course;
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("treeBeanModified");

        return "../course/bb.xhtml?faces-redirect=true";

    }

    public void publish(Course course) {
        this.selectedCourse = course;
        selectedCourse.setStatus("Published");
        Date currentDate = new Date();
        selectedCourse.setLaunchDate(currentDate);
        getCourseFacade().edit(selectedCourse);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, selectedCourse.getCourseName(), "Your course has been published");

        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    public boolean isNotPublished(Course course){
        boolean result = true;
        if ("Published".equals(course.getStatus())){
            result=false;
        }
        return result;
    }
}
