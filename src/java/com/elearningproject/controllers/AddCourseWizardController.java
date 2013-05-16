/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elearningproject.controllers;

import com.elearningproject.entities.Course;
import com.sun.tools.xjc.reader.xmlschema.bindinfo.BIConversion.User;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.event.FlowEvent;

/**
 *
 * @author raiton
 */
@ManagedBean
@ViewScoped
public class AddCourseWizardController {

    private Course course = new Course();

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        AddCourseWizardController.logger = logger;
    }
    private boolean skip;
    private static Logger logger = Logger.getLogger(AddCourseWizardController.class.getName());

    public void save(ActionEvent actionEvent) {
        //Persist user

        FacesMessage msg = new FacesMessage("Successful", "Welcome :" + course.getCourseName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public String onFlowProcess(FlowEvent event) {
        logger.info("Current wizard step:" + event.getOldStep());
        logger.info("Next step:" + event.getNewStep());

        if (skip) {
            skip = false;	//reset in case user goes back
            return "confirm";
        } else {
            return event.getNewStep();
        }
    }

    public List<String> getProducts() {
        List<String> generatedlist = new ArrayList<String>();
        int i = 0;
        if (course.getNbreWeeks() != null){
        while (i < course.getNbreWeeks().intValue()) {
            generatedlist.add("lol[" + i + "]");
            i = i + 1;

        }    
        }
        
        return generatedlist;
    }
}
