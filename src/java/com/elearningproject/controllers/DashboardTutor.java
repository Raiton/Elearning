/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elearningproject.controllers;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author raiton
 */
@ManagedBean
@RequestScoped
public class DashboardTutor implements  Serializable{
    private int test;

    public int getTest() {
        return test;
    }

    public void setTest(int test) {
        this.test = test;
    }

    public DashboardTutor() {
    }
    
    
    public String redirect(){
        System.out.println("dsq");
        return "dashboarduser.xhtml?faces-redirect=true";
    }
    
}
