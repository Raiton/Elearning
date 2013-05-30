/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elearningproject.controllers;

import com.elearningproject.entities.Course;
import com.elearningproject.entities.UserHasCourse;
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
public class DashboardUser implements Serializable {

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

    public DashboardUser() {
    }

    public String redirect() {
        return "dashboarduser.xhtml?faces-redirect=true";
    }

    public String unsubscribe(Course course) {
         LoginController loginController = (LoginController) ManagedBeanRetriever.getManagedBean("loginController");
        UserHasCourse userHasCourse = getUserHasCourseFacade().findCourseByIdCourseAndIdUserTable(course, loginController.getUsertable());
       getUserHasCourseFacade().remove(userHasCourse);
        return "dashboarduser.xhtml?faces-redirect=true";

    }
}
