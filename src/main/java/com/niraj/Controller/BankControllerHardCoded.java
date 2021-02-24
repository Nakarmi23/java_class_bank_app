/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niraj.Controller;

import java.util.ArrayList;

/**
 *
 * @author Dell
 */
public class BankControllerHardCoded implements BankControllerInterface{
         
    @Override
    public Account findAccount(int accNo){
       for(Account a:ac){
           if(accNo == a.getAccNo()){
               return a;
           }
       }
       return null;
    }
    
  
    
    @Override
    public boolean newAccount(Account a){
        return ac.add(a);
    }
    
    @Override
    public ArrayList<Account> viewAllAccounts(){
        return ac;
    }

    @Override
    public int withdrawal(int accNo, int withdrawalAmount) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int accNo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int fundTransfer(int from, int to, int amount) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int deposit(int accNo, int balance) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
