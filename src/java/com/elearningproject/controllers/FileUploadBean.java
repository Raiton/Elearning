package com.elearningproject.controllers;
 
import java.util.logging.Level;
import java.util.logging.Logger;
 
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
 
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
 
@ManagedBean
@RequestScoped
public class FileUploadBean
{
 
 private static final Logger logger = Logger.getLogger(FileUploadBean.class.getName());
 
 public void handleFileUpload(FileUploadEvent event)
 {
  UploadedFile file = event.getFile();
  String fileName = file.getFileName();
  byte[] fileBytes = file.getContents();
  logger.log(Level.INFO, "Processed uploaded file " + fileName + " of size:" + fileBytes.length);
  FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "The file was uploaded successfully.", null);
  FacesContext.getCurrentInstance().addMessage(null, message);
 }
 
}