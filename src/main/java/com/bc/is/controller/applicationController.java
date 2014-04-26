/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bc.is.controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author bruno
 */
@ManagedBean
@RequestScoped
public class applicationController {

    private String username;
    private String password;
    

    
    

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * Creates a new instance of applicationController
     */
    public applicationController() {
    }
   

    public String logout() {
        //((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession());
        ((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false)).invalidate();
         System.out.println("logout");
        return "/index?faces-redirect=true";
        
    }
    public String login() {
    // ...
HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        try {
            request.login(username, password);
        } catch (ServletException ex) {
            Logger.getLogger(applicationController.class.getName()).log(Level.SEVERE, null, ex);
        return null;
        }

        System.out.println("init....");
  
    return "/home/Main?faces-redirect=true";
}

}
