/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elearningproject.controllers;

import com.elearningproject.facades.AccountFacade;
import com.elearningproject.facades.UserTableFacade;
import com.elearningproject.entities.Account;
import com.elearningproject.entities.UserTable;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;


/**
 *
 * @author Shadow
 */
@ManagedBean
@RequestScoped
public class RegisterController implements Serializable {

    
    private String verifPassword;
    public String getVerifPassword() {
        return verifPassword;
    }
    public void setVerifPassword(String verifPassword) {
        this.verifPassword = verifPassword;
    }

    
    private Account account = new Account();
    public Account getAccount() {
        return account;
    }
    public void setAccount(Account account) {
        this.account = account;
    }
    
    
    private UserTable user = new UserTable();
    public UserTable getUser() {
        return user;
    }
    public void setUser(UserTable user) {
        this.user = user;
    }

    
    @EJB
    private AccountFacade accountFacade ;
    public AccountFacade getAccountFacade() {
        return accountFacade;
    }
    public void setAccountFacade(AccountFacade accountFacade) {
        this.accountFacade = accountFacade;
    }

    
    @EJB
    private UserTableFacade userFacade;
    public UserTableFacade getUserFacade() {
        return userFacade;
    }
    public void setUserFacade(UserTableFacade userFacade) {
        this.userFacade = userFacade;
    }

     
    public void register(){
        if ( this.verifPassword.equals( this.account.getPassword() ) ) {
                    account.setIdUserTable(user);
                    userFacade.create(user);
                    accountFacade.create(account);
                    System.out.print("Signed IN");
                }
        else{
            System.out.print("ERROR, PASSWORD INVALIDE");
        }
        
    }
}
