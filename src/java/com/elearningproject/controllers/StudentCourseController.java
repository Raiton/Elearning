/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elearningproject.controllers;

import com.elearningproject.entities.Chapter;
import com.elearningproject.entities.Content;
import com.elearningproject.entities.Course;
import com.elearningproject.entities.Exam;
import com.elearningproject.entities.Topic;
import com.elearningproject.entities.UserTable;
import com.elearningproject.facades.ChapterFacade;
import com.elearningproject.facades.ContentFacade;
import com.elearningproject.facades.CourseFacade;
import com.elearningproject.facades.ExamFacade;
import com.elearningproject.facades.TopicFacade;
import com.elearningproject.personalisedclasses.ManagedBeanRetriever;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.MenuModel;

/**
 *
 * @author macbook
 */
@ManagedBean
@ViewScoped
public class StudentCourseController implements Serializable {

    private UserTable user = new UserTable();

    public UserTable getUser() {
        return user;
    }

    public void setUser(UserTable user) {
        this.user = user;
    }
    private Course course = new Course();

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
    private Topic selectedTopic = new Topic();

    public Topic getSelectedTopic() {
        return selectedTopic;
    }

    public void setSelectedTopic(Topic selectedTopic) {
        this.selectedTopic = selectedTopic;
    }
    private Chapter selectedChapter;

    public Chapter getSelectedChapter() {
        return selectedChapter;
    }

    public void setSelectedChapter(Chapter selectedChapter) {
        this.selectedChapter = selectedChapter;
    }
    private Content selectedContent;

    public Content getSelectedContent() {
        return selectedContent;
    }

    public void setSelectedContent(Content selectedContent) {
        this.selectedContent = selectedContent;
    }
    private List<Topic> listTopicByCourse = null;

    public List<Topic> getListTopicByCourse() {
        return listTopicByCourse;
    }

    public void setListTopicByCourse(List<Topic> listTopicByCourse) {
        this.listTopicByCourse = listTopicByCourse;
    }
    private List<Exam> listExamByCourse = null;

    public List<Exam> getListExamByCourse() {
        return listExamByCourse;
    }

    public void setListExamByCourse(List<Exam> listExamByCourse) {
        this.listExamByCourse = listExamByCourse;
    }
    private MenuModel model;

    public MenuModel getModel() {
        return model;
    }
    private String courseIdPassed;

    public String getCourseIdPassed() {
        return courseIdPassed;
    }

    public void setCourseIdPassed(String courseIdPassed) {
        this.courseIdPassed = courseIdPassed;
    }
    @EJB
    private CourseFacade courseFacade;

    public CourseFacade getCourseFacade() {
        return courseFacade;
    }

    public void setCourseFacade(CourseFacade courseFacade) {
        this.courseFacade = courseFacade;
    }
    @EJB
    private TopicFacade topicFacade;

    public TopicFacade getTopicFacade() {
        return topicFacade;
    }

    public void setTopicFacade(TopicFacade topicFacade) {
        this.topicFacade = topicFacade;
    }
    @EJB
    private ExamFacade examFacade;

    public ExamFacade getExamFacade() {
        return examFacade;
    }

    public void setExamFacade(ExamFacade examFacade) {
        this.examFacade = examFacade;
    }
    @EJB
    private ChapterFacade chapterFacade;

    public ChapterFacade getChapterFacade() {
        return chapterFacade;
    }

    public void setChapterFacade(ChapterFacade chapterFacade) {
        this.chapterFacade = chapterFacade;
    }
    @EJB
    private ContentFacade contentFacade;

    public ContentFacade getContentFacade() {
        return contentFacade;
    }

    public void setContentFacade(ContentFacade contentFacade) {
        this.contentFacade = contentFacade;
    }

    public List<Topic> topicByCourse(Course course) {
        List<Topic> result = null;
        try {
            result = topicFacade.findByIdCourse(course.getIdCourse());
        } catch (NullPointerException e) {
            result = null;
        }
        return result;
    }

    public List<Exam> examByCourse(Course cours) {
        List<Exam> result = new ArrayList<Exam>();
        //listTopicByCourse = topicFacade.findByIdCourse(cours.getIdCourse());
        for (Topic iTopic : listTopicByCourse) {

            try {
                result.add(examFacade.findByIdTopic(iTopic.getIdTopic()));
            } catch (NullPointerException e) {
                result = (List<Exam>) examFacade.findByIdTopic(iTopic.getIdTopic());
            }

        }
        return result;
    }

    public Exam examByTopic(Topic topic) {
        Exam result = null;
        try {
            result = examFacade.findByIdTopic(topic.getIdTopic());
        } catch (NullPointerException e) {
            result = null;
        }
        return result;
    }

    public List<Chapter> chapterByTopic(Topic topic) {
        List<Chapter> result = null;
        try {
            result = chapterFacade.findByIdTopic(topic.getIdTopic());
        } catch (NullPointerException e) {
            result = null;
        }
        return result;
    }

    public List<Content> contentByChapter(Chapter chapter) {
        List<Content> result = null;
        try {
            result = contentFacade.findByIdChapter(chapter.getIdChapter());
        } catch (NullPointerException e) {
            result = null;
        }
        return result;
    }

    public void fullCourse() throws IOException {
        LoginController loginController = (LoginController) ManagedBeanRetriever.getManagedBean("loginController");
        loginController.security("coursedashboard");
        try{
            
        
            course = courseFacade.find(Integer.parseInt(courseIdPassed));
          //  selectedContent = contentFacade.find(1);

            listTopicByCourse = topicByCourse(course);

            selectedTopic = listTopicByCourse.get(0);

//        listExamByTopic = examFacade.findByIdTopic(4);
            //listExamByTopic = examByTopic(topic);

            listExamByCourse = examByCourse(course);



   } catch (Exception e) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("dashboarduser.xhtml");

        }
       

    }

    public void resetSelected() {
        selectedChapter = new Chapter();
        selectedContent = contentFacade.find(1);
        System.out.println("Here");
    }

    public String updateDate(Date date, int num) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 7 * (num - 1));  // number of days to add
        String dt = sdf.format(c.getTime());  // dt is now the new date

        return dt;

    }
}

/*
 * public void menuBean() {
 model = new DefaultMenuModel();

 for (Chapter iChapter : chapterByTopic(selectedTopic)) {
 Submenu submenu = new Submenu();
 submenu.setLabel(iChapter.getChapterName());           
 try {
 for (Content iContent : contentByChapter(iChapter)) {
 MenuItem item = new MenuItem();
 item.setValue(iContent.getContentName());
 item.setOnclick(item.getTitle());
 submenu.getChildren().add(item);

 }
 } catch (NullPointerException e) {
 MenuItem item = new MenuItem();
 item.setValue("No Content Available");
 }
 model.addSubmenu(submenu);
 }

 }
 */
