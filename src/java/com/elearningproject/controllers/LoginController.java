/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elearningproject.controllers;

import com.elearningproject.facades.AccountFacade;
import com.elearningproject.facades.UserTableFacade;
import com.elearningproject.entities.Account;
import com.elearningproject.entities.UserTable;
import java.io.IOException;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

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
        String result=null;
        for (Account account1 : accounts) {
            if (account1.getUsername().equals(account.getUsername()) && account1.getPassword().equals(account.getPassword())) {
                System.out.print("connected");
                account = account1;
                usertable=account1.getIdUserTable();
                  result = "../dashboard/dashboarduser.xhtml?faces-redirect=true;";

            }
        }
        return result;
    }

    public UserTable getUserdata() {
        UserTable inspecting = (UserTable) usertablefacade.find(account.getIdUserTable().getIdUserTable());
        return inspecting;
        
    }
}
