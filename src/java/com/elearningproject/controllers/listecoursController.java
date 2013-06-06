package com.elearningproject.controllers;

import com.elearningproject.entities.Course;
import com.elearningproject.entities.Exam;
import com.elearningproject.entities.Test;
import com.elearningproject.entities.Topic;
import com.elearningproject.entities.UserHasCourse;
import com.elearningproject.entities.UserTable;
import com.elearningproject.facades.CourseFacade;
import com.elearningproject.facades.ExamFacade;
import com.elearningproject.facades.TestFacade;
import com.elearningproject.facades.TopicFacade;
import com.elearningproject.facades.UserHasCourseFacade;
import com.elearningproject.personalisedclasses.ManagedBeanRetriever;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
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

    public ExamFacade getExamFacade() {
        return examFacade;
    }

    public void setExamFacade(ExamFacade examFacade) {
        this.examFacade = examFacade;
    }
    @EJB
    private TestFacade testFacade;

    public TestFacade getTestFacade() {
        return testFacade;
    }

    public void setTestFacade(TestFacade testFacade) {
        this.testFacade = testFacade;
    }
    @EJB
    private ExamFacade examFacade;

    public void setUserHasCourseFacade(UserHasCourseFacade userHasCourseFacade) {
        this.userHasCourseFacade = userHasCourseFacade;
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

    public String subscribe(Course course) throws ParseException {
        LoginController loginController = (LoginController) ManagedBeanRetriever.getManagedBean("loginController");
        if (loginController.getUserdata() != null) {
            if ("Subscribe".equals(nameSubscribe(course))) {
                UserHasCourse userHasCourse = new UserHasCourse();
                userHasCourse.setIdCourse(course);
                userHasCourse.setIdUserTable(loginController.getUsertable());
                //loginController.getUsertable();
                getUserHasCourseFacade().create(userHasCourse);
                createExams(course, loginController.getUsertable());

            } else {
                UserHasCourse userHasCourse = getUserHasCourseFacade().findCourseByIdCourseAndIdUserTable(course, loginController.getUsertable());
                getUserHasCourseFacade().remove(userHasCourse);
                for (Topic topic : course.getTopicList()) {
                    Exam iexam = getExamFacade().findByIdTopicAndIdUser(topic.getIdTopic(), loginController.getUsertable().getIdUserTable());
                    getExamFacade().remove(iexam);
                }


            }

            return "listecours.xhtml?faces-redirect=true";

        } else {
            return "../login/login.xhtml?faces-redirect=true";

        }


    }
      public String subscribe2(Course course) throws ParseException {
        LoginController loginController = (LoginController) ManagedBeanRetriever.getManagedBean("loginController");
        if (loginController.getUserdata() != null) {
            if ("Subscribe".equals(nameSubscribe(course))) {
                UserHasCourse userHasCourse = new UserHasCourse();
                userHasCourse.setIdCourse(course);
                userHasCourse.setIdUserTable(loginController.getUsertable());
                //loginController.getUsertable();
                getUserHasCourseFacade().create(userHasCourse);
                createExams(course, loginController.getUsertable());

            } else {
                UserHasCourse userHasCourse = getUserHasCourseFacade().findCourseByIdCourseAndIdUserTable(course, loginController.getUsertable());
                getUserHasCourseFacade().remove(userHasCourse);
                for (Topic topic : course.getTopicList()) {
                    Exam iexam = getExamFacade().findByIdTopicAndIdUser(topic.getIdTopic(), loginController.getUsertable().getIdUserTable());
                    getExamFacade().remove(iexam);
                }


            }

            return "../dashboard/dashboarduser.xhtml?faces-redirect=true";

        } else {
            return "../login/login.xhtml?faces-redirect=true";

        }


    }


    public List<Test> testsByTopic(Topic topic) {
        List<Test> result = null;
        try {
            result = testFacade.findByIdTopic(topic.getIdTopic());
        } catch (NullPointerException e) {
            result = null;
        }
        return result;
    }

    public Date updateDate(Date date, int num) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 7 * (num - 1));  // number of days to add
        String dt = sdf.format(c.getTime());  // dt is now the new date

        //return dt;
        return c.getTime();

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

   public void createExams(Course course, UserTable user) throws ParseException {
        String str;

        List<Topic> listTopic = topicFacade.findByIdCourse(course.getIdCourse());
        for (Topic iTopic : listTopic) {
            List<Test> listTest = testFacade.findByIdTopic(iTopic.getIdTopic());
            Random rand = new Random();
            Date beginDate = updateDate(course.getLaunchDate(), iTopic.getWeekNumber().intValue());
            Date deadLine = updateDate(beginDate, 2);
            str = new String();

            for (int i = 0; i < 3; i++) {
                int j = rand.nextInt(listTest.size());
                Test test = listTest.get(j);
                listTest.remove(test);
                str=str.concat(test.getIdTest().toString());
                str=str.concat(",");

            }

            Exam exam = new Exam();
            exam.setExamContent(str);
            exam.setBeginingDate(beginDate);
            exam.setDeadline(deadLine);
            exam.setIdTopic(iTopic);
            exam.setMark(null);
            exam.setResponse(null);
            exam.setIdUserTable(user);
            examFacade.create(exam);
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