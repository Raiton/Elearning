package com.elearningproject.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class MyBean {

    private String[] sarray = {"one", "two", "three"};
    private String[] sarray2 = {"four", "five", "six"};
    private String[] sarray3 = {"seven", "eight", "nine"};
    private List<List<String>> llist;
    private String[] resultArray = {"two", "four", "nine"};
    private String[] reponseArray = {"two", "six", "nine"};
    private Boolean bool; 

    public MyBean() {
        List<String> slist = Arrays.asList(sarray);
        List<String> slist2 = Arrays.asList(sarray2);
        List<String> slist3 = Arrays.asList(sarray3);

        llist = new ArrayList<List<String>>();
        llist.add(slist);
        llist.add(slist2);
        llist.add(slist3);
        bool=false;

    }

    public List<List<String>> getLlist() {
        return llist;
    }

    public String[] getResultArray() {
        return resultArray;
    }

    public String getResultArray(int i) {
        return resultArray[i];
    }

    public void setResultArray(int i, String value) {
        resultArray[i] = value;
    }

    public void setResultArray(String[] resultArray) {
        this.resultArray = resultArray;
    }

    public String[] getReponseArray() {
        return reponseArray;
    }
        public String getReponseArray(int i) {
        return resultArray[i];
    }

    public void setReponseArray(int i, String value) {
        resultArray[i] = value;
    }

    public void setReponseArray(String[] reponseArray) {
        this.reponseArray = reponseArray;
    }

    public void submit() {
        int score = 0;
        
        for (int i = 0; i < reponseArray.length; i++) {
            if (reponseArray[i].equals(resultArray[i])) {
                score += 10;
            }

        }
        System.out.println("score is "+score);
        bool=true;

    }

    public Boolean getBool() {
        return bool;
    }

    public void setBool(Boolean bool) {
        this.bool = bool;
    }
    
    
}