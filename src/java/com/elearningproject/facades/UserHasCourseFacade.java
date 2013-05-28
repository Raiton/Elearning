/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elearningproject.facades;

import com.elearningproject.entities.Course;
import com.elearningproject.entities.UserHasCourse;
import com.elearningproject.entities.UserTable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author raiton
 */
@Stateless
public class UserHasCourseFacade extends AbstractFacade<UserHasCourse> {

    @PersistenceContext(unitName = "E-learning_projectPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserHasCourseFacade() {
        super(UserHasCourse.class);
    }

    public List<Course> findCourseByUserTable(UserTable usertable) {
        List<UserHasCourse> result = (List<UserHasCourse>) getEntityManager().createNamedQuery("UserHasCourse.findByIdUser").setParameter("idUserTable", usertable).getResultList();
        List<Course> resultcourse = new ArrayList<Course>();
        for (int i = 0; i < result.size(); i++) {
           resultcourse.add(result.get(i).getIdCourse());
        }
        return resultcourse;
    }
}
