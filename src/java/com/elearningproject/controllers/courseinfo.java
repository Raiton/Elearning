/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elearningproject.controllers;

import com.elearningproject.entities.Course;
import com.elearningproject.entities.UserTable;
import com.elearningproject.facades.CourseFacade;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.el.ELContext;
import javax.faces.FacesException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

    

 


/**
 *
 * @author mariem
 */

@ManagedBean
@SessionScoped
public class courseinfo implements Serializable {
    private int idCourse;

    public int getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(int idCourse) {
        this.idCourse = idCourse;
    }
    
    @EJB
    private CourseFacade courseFacade;

    public CourseFacade getCourseFacade() {
        return courseFacade;
    }

    public void setCourseFacade(CourseFacade courseFacade) {
        this.courseFacade = courseFacade;
    }
            
    private List<Course> cours;

    public List<Course> getCours() {
        return cours;
    }

    public void setCours(List<Course> cours) {
        this.cours = cours;
    }

    
    public List<Course> getCourseByIDCourse() {
        cours=courseFacade.findByIdCourse(idCourse);
        return cours;
    }
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
}
    
    

    

   