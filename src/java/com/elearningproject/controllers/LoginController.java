/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elearningproject.controllers;

import com.elearningproject.facades.AccountFacade;
import com.elearningproject.facades.UserTableFacade;
import com.elearningproject.entities.Account;
import com.elearningproject.entities.UserTable;
import com.elearningproject.personalisedclasses.ManagedBeanRetriever;
import java.io.IOException;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Shadow
 */
@ManagedBean
@SessionScoped
public class LoginController implements Serializable {

    private Account account;
    private UserTable usertable;
    @EJB
    private AccountFacade accountFacade;
    @EJB
    private UserTableFacade usertablefacade;

    public UserTable getUsertable() {
        return usertable;
    }

    public void setUsertable(UserTable usertable) {
        this.usertable = usertable;
    }

    public UserTableFacade getUsertablefacade() {
        return usertablefacade;
    }

    public void setUsertablefacade(UserTableFacade usertablefacade) {
        this.usertablefacade = usertablefacade;
    }

    public LoginController() {
        account = new Account();
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public AccountFacade getAccountFacade() {
        return accountFacade;
    }

    public void setAccountFacade(AccountFacade accountFacade) {
        this.accountFacade = accountFacade;
    }

    public String login() throws IOException {
        List<Account> accounts = accountFacade.findAll();
        String result = null;
        for (Account account1 : accounts) {
            if (account1.getUsername().equals(account.getUsername()) && account1.getPassword().equals(account.getPassword())) {
                account = account1;
                usertable = account1.getIdUserTable();
                if ("user".equals(usertable.getIdGroupTable().getGroupName())) {
                    result = "../dashboard/dashboarduser.xhtml?faces-redirect=true";
                } else if ("tutor".equals(usertable.getIdGroupTable().getGroupName())) {
                    result = "../dashboard/dashboardtutor.xhtml?faces-redirect=true;";
                }


            }
        }
        return result;
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "../login/login.xhtml?faces-redirect=true";
    }

    public UserTable getUserdata() {
        UserTable inspecting = null;
        try {
            inspecting = (UserTable) usertablefacade.find(account.getIdUserTable().getIdUserTable());


        } catch (Exception e) {
        }
        return inspecting;

    }

    public void security(String page) throws IOException {
        if (usertable == null) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("../login/login.xhtml");
        } else {
            FacesContext fc = FacesContext.getCurrentInstance();
            if ("user".equals(this.usertable.getIdGroupTable().getGroupName())) {
                if ("dashboardtutor".equals(page)){
                FacesContext.getCurrentInstance().getExternalContext().redirect("dashboarduser.xhtml");
                }
            }
            else if ("tutor".equals(this.usertable.getIdGroupTable().getGroupName()))
            {
                if ("dashboarduser".equals(page)){
                FacesContext.getCurrentInstance().getExternalContext().redirect("dashboardtutor.xhtml");
                }
            }

        }

    }
}