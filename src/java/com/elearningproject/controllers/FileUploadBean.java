package com.elearningproject.controllers;

import com.elearningproject.entities.Content;
import com.elearningproject.entities.Course;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.el.ELContext;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;

@ManagedBean
@RequestScoped
public class FileUploadBean {

    private static final int BUFFER_SIZE = 6124;
    private String folderToUpload = "/home/raiton/NetBeansProjects/E-learning_project/web/resources/ContentFile/";

    public void handleFileUpload(FileUploadEvent event) {

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(folderToUpload + event.getFile().getFileName());

            byte[] buffer = new byte[BUFFER_SIZE];

            int bulk;
            InputStream inputStream = event.getFile().getInputstream();
            while (true) {
                bulk = inputStream.read(buffer);
                if (bulk < 0) {
                    break;
                }
                fileOutputStream.write(buffer, 0, bulk);
                fileOutputStream.flush();
            }

            fileOutputStream.close();
            inputStream.close();
            TreeBean pp = (TreeBean) getManagedBean("treeBean");
            Content hh = (Content) pp.getSelectedNode().getEntity();
            hh.setContentUrl(event.getFile().getFileName());
            FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName()
                    + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, msg);

        } catch (IOException e) {
            e.printStackTrace();

            FacesMessage error = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "The files were not uploaded!", "");
            FacesContext.getCurrentInstance().addMessage(null, error);
        }
    }
    public void handleFileUploadPhoto(FileUploadEvent event) {

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(folderToUpload + event.getFile().getFileName());

            byte[] buffer = new byte[BUFFER_SIZE];

            int bulk;
            InputStream inputStream = event.getFile().getInputstream();
            while (true) {
                bulk = inputStream.read(buffer);
                if (bulk < 0) {
                    break;
                }
                fileOutputStream.write(buffer, 0, bulk);
                fileOutputStream.flush();
            }

            fileOutputStream.close();
            inputStream.close();
            TreeBean pp = (TreeBean) getManagedBean("treeBean");
            Course hh = (Course) pp.getSelectedNode().getEntity();
            hh.setPhoto(event.getFile().getFileName());
            FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName()
                    + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, msg);

        } catch (IOException e) {
            e.printStackTrace();

            FacesMessage error = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "The files were not uploaded!", "");
            FacesContext.getCurrentInstance().addMessage(null, error);
        }
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
}
