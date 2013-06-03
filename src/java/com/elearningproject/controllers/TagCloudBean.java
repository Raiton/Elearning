package com.elearningproject.controllers;

import com.elearningproject.entities.Course;
import com.elearningproject.facades.CourseFacade;
import java.io.Serializable;
import java.util.List;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.tagcloud.DefaultTagCloudItem;
import org.primefaces.model.tagcloud.DefaultTagCloudModel;
import org.primefaces.model.tagcloud.TagCloudItem;
import org.primefaces.model.tagcloud.TagCloudModel;

@ManagedBean
@ViewScoped
public class TagCloudBean implements Serializable {

    @EJB
    private CourseFacade courseFacade;
    private TagCloudModel model;
    private List<Course> listeCourse;

    public List<Course> getListeCourse() {
        return listeCourse;
    }

    public void setListeCourse(List<Course> listeCourse) {
        this.listeCourse = listeCourse;
    }

    public CourseFacade getCourseFacade() {
        return courseFacade;
    }

    public void setCourseFacade(CourseFacade courseFacade) {
        this.courseFacade = courseFacade;
    }

    @PostConstruct
    public void init() {
        model = new DefaultTagCloudModel();
        listeCourse = courseFacade.findAll();
        for (Course course : listeCourse) {
            Random rd = new Random();
            int randomInt = rd.nextInt(10) + 1;


            model.addTag(new DefaultTagCloudItem(course.getCourseName(), randomInt));

        }
    }

    public TagCloudBean() {
    }

    public TagCloudModel getModel() {
        return model;
    }

    public void onSelect(SelectEvent event) {
        TagCloudItem item = (TagCloudItem) event.getObject();
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Selected", item.getLabel());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}