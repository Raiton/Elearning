/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elearningproject.controllers;

import com.elearningproject.entities.Course;
import com.elearningproject.entities.Field;
import com.elearningproject.entities.UserTable;
import com.elearningproject.facades.CourseFacade;
import com.elearningproject.facades.FieldFacade;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;



@ManagedBean
@SessionScoped

public class listefieldController implements Serializable {  
      
    
  
     
    private UserTable user;
    public UserTable getUser() {
        return user;
    }
    public void setUser(UserTable user) {
        this.user = user;
    }
    
    
  
    private List<Field> fields;

    public List<Field> getFields() {
        return fields;
    }
    public void setFields(List<Field> fields) {
        this.fields = fields;
    }
   
    private Field field;
    public Field getField() {
        return field;
    }
    public void setField(Field field) {
        this.field = field;
    }
    
    
    @EJB
    private FieldFacade fieldFacade;

    public FieldFacade getFieldFacade() {
        return fieldFacade;
    }

    public void setFieldFacade(FieldFacade fieldFacade) {
        this.fieldFacade = fieldFacade;
    }

   
    
    
    public List<Field> getListField () {
        fields= fieldFacade.findAll();
        
        
        return fields;
    }
 
        
     public String redirect() throws IOException {

        String result = "../course/listecours.xhtml?faces-redirect=true&includeViewParams=true";
 
        return result;
    } 
        
    }