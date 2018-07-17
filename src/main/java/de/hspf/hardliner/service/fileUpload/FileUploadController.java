/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hspf.hardliner.service.fileUpload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author dachs
 */
@Named(value = "fileUploadController")
@SessionScoped
public class FileUploadController implements Serializable {

    private final String destination = "C:\\temp\\";
    private UploadedFile file;

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    /**
     * Creates a new instance of FileUploadController
     */
    public FileUploadController() {
    }

    public void TransferFile(String fileName, InputStream in) {
        try {
            OutputStream out = new FileOutputStream(new File(destination + fileName));
            int reader = 0;
            byte[] bytes = new byte[(int) getFile().getSize()];
            while ((reader = in.read(bytes)) != -1) {
                out.write(bytes, 0, reader);
            }
            in.close();
            out.flush();
            out.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void upload() {
        String extValidate;
        if (getFile() != null) {
            String ext = getFile().getFileName();
            if (ext != null) {
                extValidate = ext.substring(ext.indexOf(".") + 1);
            } else {
                extValidate = null;
            }
            if (extValidate.equals("jpg")) {
                try {
                    TransferFile(getFile().getFileName(), getFile().getInputstream());
                } catch (IOException ex) {
                    Logger.getLogger(FileUploadController.class.getName()).log(Level.SEVERE, null, ex);
                    FacesContext context = FacesContext.getCurrentInstance();
                    context.addMessage(null, new FacesMessage("Wrong", "Error Uploading File"));
                }
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Successfull", getFile().getFileName() + " was uploaded successfully. typecontent " + getFile().getContentType() + ". Size" + getFile().getSize()));
            } else {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Wrong", "Only .jpg files"));
            }

        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Wrong", "Select a File"));
        }

    }
}
