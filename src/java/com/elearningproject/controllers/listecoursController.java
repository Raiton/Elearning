/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elearningproject.controllers;

import com.elearningproject.entities.Course;
import com.elearningproject.entities.UserHasCourse;
import com.elearningproject.entities.UserTable;
import com.elearningproject.facades.CourseFacade;
import com.elearningproject.facades.UserHasCourseFacade;
import com.elearningproject.personalisedclasses.ManagedBeanRetriever;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.el.ELContext;
import javax.faces.FacesException;

import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class listecoursController implements Serializable {

    private int idField;

    public int getIdField() {
        return idField;
    }

    public void setIdField(int idField) {
        this.idField = idField;
    }
    private UserTable user;

    public UserTable getUser() {
        return user;
    }

    public void setUser(UserTable user) {
        this.user = user;
    }
    private List<Course> cours;

    public List<Course> getCours() {
        return cours;
    }

    public void setCours(List<Course> cours) {
        this.cours = cours;
    }
    @EJB
    private CourseFacade courseFacade;

    public CourseFacade getCourseFacade() {
        return courseFacade;
    }
    @EJB
    private UserHasCourseFacade userHasCourseFacade;

    public UserHasCourseFacade getUserHasCourseFacade() {
        return userHasCourseFacade;
    }

    public void setUserHasCourseFacade(UserHasCourseFacade userHasCourseFacade) {
        this.userHasCourseFacade = userHasCourseFacade;
    }

    public void setCourseFacade(CourseFacade courseFacade) {
        this.courseFacade = courseFacade;
    }

    public List<Course> getListCourseByIdField() {
        cours = courseFacade.findByIdField(idField);


        return cours;
    }

    /* public void inLoad() {
     field = ((listefieldController) (getManagedBean("listefieldController"))).getField();
     System.out.println("the field is" + field);
     }*/
    public static Object getManagedBean(final String beanName) {
        FacesContext fc = FacesContext.getCurrentInstance();
        Object bean;

        try {
            ELContext elContext = fc.getELContext();
            bean = elContext.getELResolver().getValue(elContext, null, beanName);
        } catch (RuntimeException e) {
            throw new FacesException(e.getMessage(), e);
        }

        if (bean == null) {
            throw new FacesException("Managed bean with name '" + beanName
                    + "' was not found. Check your faces-config.xml or @ManagedBean annotation.");
        }

        return bean;
    }

    public String redirect() throws IOException {

        String result = "../testAB/testB.xhtml?faces-redirect=true&includeViewParams=true";

        return result;
    }

    public String subscribe(Course course) {
        LoginController loginController = (LoginController) ManagedBeanRetriever.getManagedBean("loginController");
        if (loginController.getUserdata() != null) {
            if ("Subscribe".equals(nameSubscribe(course))) {
                UserHasCourse userHasCourse = new UserHasCourse();
                userHasCourse.setIdCourse(course);
                userHasCourse.setIdUserTable(loginController.getUsertable());
                getUserHasCourseFacade().create(userHasCourse);
            } else {
                UserHasCourse userHasCourse = getUserHasCourseFacade().findCourseByIdCourseAndIdUserTable(course, loginController.getUsertable());
                getUserHasCourseFacade().remove(userHasCourse);
            }
            return "listecours.xhtml?faces-redirect=true";
        } else {
            return "../login/login.xhtml?faces-redirect=true";

        }


    }

    public String nameSubscribe(Course course) {
        String name = "Subscribe";
        LoginController loginController = (LoginController) ManagedBeanRetriever.getManagedBean("loginController");
        try {
            UserHasCourse userHasCourse = getUserHasCourseFacade().findCourseByIdCourseAndIdUserTable(course, loginController.getUsertable());

            name = "Unsubscribe";
        } catch (Exception e) {
        }
        return name;
    }
}