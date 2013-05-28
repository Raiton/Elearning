package com.elearningproject.controllers;

import com.elearningproject.entities.Content;
import com.elearningproject.entities.Course;
import com.elearningproject.personalisedclasses.ManagedBeanRetriever;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.faces.application.FacesMessage;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import org.primefaces.event.FileUploadEvent;

@ManagedBean
@RequestScoped
public class FileUploadBean {

    private static final int BUFFER_SIZE = 6124;
    private String folderToUpload = FacesContext.getCurrentInstance().getExternalContext().getRealPath("resources/ContentFile");

 
    

    public void handleFileUpload(FileUploadEvent event) {

        try {
            
            FileOutputStream fileOutputStream = new FileOutputStream(folderToUpload +"/"+ event.getFile().getFileName());

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
            TreeBean pp = (TreeBean) ManagedBeanRetriever.getManagedBean("treeBean");
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

    public void handleFileUploadModified(FileUploadEvent event) {

        try {
            
            FileOutputStream fileOutputStream = new FileOutputStream(folderToUpload +"/"+ event.getFile().getFileName());

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
            TreeBeanModified pp = (TreeBeanModified) ManagedBeanRetriever.getManagedBean("treeBeanModified");
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
            FileOutputStream fileOutputStream = new FileOutputStream(folderToUpload +"/"+ event.getFile().getFileName());
            BufferedImage bimg = ImageIO.read(event.getFile().getInputstream());
            int width = bimg.getWidth();
            int height = bimg.getHeight();

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
            TreeBean pp = (TreeBean) ManagedBeanRetriever.getManagedBean("treeBean");
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
 
        public void handleFileUploadPhotoModified(FileUploadEvent event) {

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(folderToUpload +"/"+ event.getFile().getFileName());
            BufferedImage bimg = ImageIO.read(event.getFile().getInputstream());
            int width = bimg.getWidth();
            int height = bimg.getHeight();

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
            TreeBeanModified pp = (TreeBeanModified) ManagedBeanRetriever.getManagedBean("treeBeanModified");
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
 
}
