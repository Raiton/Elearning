  /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elearningproject.controllers;

import com.elearningproject.entities.Chapter;
import com.elearningproject.entities.Content;
import com.elearningproject.entities.Course;
import com.elearningproject.entities.Exam;
import com.elearningproject.entities.Test;
import com.elearningproject.entities.Topic;
import com.elearningproject.entities.UserTable;
import com.elearningproject.facades.ChapterFacade;
import com.elearningproject.facades.ContentFacade;
import com.elearningproject.facades.CourseFacade;
import com.elearningproject.facades.ExamFacade;
import com.elearningproject.facades.TestFacade;
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
    private int currentIndex;

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
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
    private String[] resultArray = {"correct", "result", "default"};

    public String[] getResultArray() {
        return resultArray;
    }

    public void setResultArray(String[] resultArray) {
        this.resultArray = resultArray;
    }

    public String getResultArray(int i) {
        return resultArray[i];
    }

    public void setResultArray(int i, String value) {
        resultArray[i] = value;
    }

    public Boolean getBool() {
        return bool;
    }

    public void setBool(Boolean bool) {
        this.bool = bool;
    }
    private String[] reponseArray = {"response", "user", "default"};

    public String[] getReponseArray() {
        return reponseArray;
    }

    public void setReponseArray(String[] reponseArray) {
        this.reponseArray = reponseArray;
    }

    public String getReponseArray(int i) {
        return resultArray[i];
    }

    public void setReponseArray(int i, String value) {
        resultArray[i] = value;
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
    @EJB
    private TestFacade testFacade;

    public TestFacade getTestFacade() {
        return testFacade;
    }

    public void setTestFacade(TestFacade testFacade) {
        this.testFacade = testFacade;
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
                result.add(examFacade.findByIdTopicAndIdUser(iTopic.getIdTopic(), user.getIdUserTable()));
            } catch (NullPointerException e) {
                result = (List<Exam>) examFacade.findByIdTopicAndIdUser(iTopic.getIdTopic(), user.getIdUserTable());
            }

        }
        return result;
    }

    public Exam examByTopic(Topic topic) {
        Exam result = null;
        try {
            result = examFacade.findByIdTopicAndIdUser(topic.getIdTopic(), user.getIdUserTable());
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

    public void setExamPassed(Exam exam) {
        entry = false;
        currentIndex = 3;
        selectedExam = exam;
        bool = false;
        resultArray = new String[3];
        reponseArray = resultByExam(selectedExam).toArray(reponseArray);
        if (selectedExam.getResponse() != null) {
            resultArray = selectedExam.getResponse().split(",");
            bool = true;
            passed = true;
        } else {
            passed = false;
        }
    }

    public void fullCourse() throws IOException {
        LoginController loginController = (LoginController) ManagedBeanRetriever.getManagedBean("loginController");
        loginController.security("coursedashboard");
        try {
            user = loginController.getUsertable();


            course = courseFacade.find(Integer.parseInt(courseIdPassed));
            //  selectedContent = contentFacade.find(1);

            listTopicByCourse = topicByCourse(course);
            selectedTopic = listTopicByCourse.get(0);


            selectedExam = examByTopic(listTopicByCourse.get(0));

            reponseArray = resultByExam(selectedExam).toArray(reponseArray);
            if (selectedExam.getResponse() != null) {
                resultArray = selectedExam.getResponse().split(",");
                bool = true;
            }

            //listExamByTopic = examFacade.findByIdTopicAndIdUser(4);
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

    public List<Test> testByExam(Exam exam) {
        String[] parts = exam.getExamContent().split(",");
        List<Test> result = new ArrayList<Test>();
        for (String s : parts) {
            int i = Integer.parseInt(s);
            result.add(testFacade.find(i));
        }
        return result;
    }

    public List<String> questionByExam(Exam exam) {
        List<String> result = new ArrayList<String>();
        List<Test> listTest = testByExam(exam);
        for (Test iTest : listTest) {
            result.add(iTest.getTestContent());
        }
        return result;
    }

    public List<List<String>> responseByExam(Exam exam) {
        List<Test> listTest = testByExam(exam);
        List<List<String>> result = new ArrayList<List<String>>();

        for (Test iTest : listTest) {
            List<String> strList = new ArrayList<String>();
            strList.add(iTest.getR1());
            strList.add(iTest.getR2());
            strList.add(iTest.getR3());
            result.add(strList);
        }
        return result;
    }

    public List<String> resultByExam(Exam exam) {
        List<String> result = new ArrayList<String>();
        List<Test> listTest = testByExam(exam);
        for (Test iTest : listTest) {
            switch (iTest.getR()) {
                case 1:
                    result.add(iTest.getR1());
                    break;
                case 2:
                    result.add(iTest.getR2());
                    break;
                case 3:
                    result.add(iTest.getR3());
                    break;
            }
        }
        return result;
    }
    private Boolean entry = true;

    public Boolean getEntry() {
        return entry;
    }

    public void setEntry(Boolean entry) {
        this.entry = entry;
    }

    public void submit() {

        if (selectedExam.getResponse() == null) {
            Double score = 0.0;

            for (int i = 0; i < reponseArray.length; i++) {
                if (reponseArray[i].equals(resultArray[i])) {
                    score += 10;
                }

            }
            System.out.println("score is " + score);
            selectedExam.setMark(score);
            String str = new String();
            str = (resultArray[0].toString() + "," + resultArray[1].toString() + "," + resultArray[2].toString());
            selectedExam.setResponse(str);
            examFacade.edit(selectedExam);
            bool = true;
            setExamPassed(selectedExam);
        }

    }
    private Boolean passed;

    public Boolean getPassed() {
        return passed;
    }

    public void setPassed(Boolean passed) {
        this.passed = passed;
    }

    public List<String> test() {
        List<String> list = new ArrayList<String>();
        list.add("aroua");
        list.add("7amdi");
        list.add("Mariem");
        list.add("Fat7iza");
        System.out.println(list.size());
        return list;
    }
    private Boolean bool;
    private String str;

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
    private Exam selectedExam;

    public Exam getSelectedExam() {
        return selectedExam;
    }

    public void setSelectedExam(Exam selectedExam) {
        this.selectedExam = selectedExam;
    }
}
