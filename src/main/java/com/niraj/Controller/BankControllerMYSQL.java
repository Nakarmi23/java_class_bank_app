/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niraj.Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dell
 */
public class BankControllerMYSQL implements BankControllerInterface{
    dbConnection dbConn;

    public BankControllerMYSQL() {
        dbConn = new dbConnection();
    }
    
    
    @Override
    public Account findAccount(int accNo) {
        try {
            ResultSet rs;
            String sqlStmt = "SELECT * FROM account WHERE accNo= "+accNo+";";
            rs = dbConn.select(sqlStmt);
            rs.next();
            Account a = new Account(rs.getInt("accNo"), rs.getString("name"), rs.getInt("amount"), rs.getString("accType").charAt(0));
            return a;
        } catch (SQLException ex) {
            return null;
        }
    }

    @Override
    public int deposit(int accNo, int balance) {
        Account acc = findAccount(accNo);
        if (acc == null) return 2;
        String sqlStmt = "UPDATE account SET amount = "+(balance+ acc.amount)+" WHERE accNo= "+accNo+";";
        return dbConn.iud(sqlStmt)>0?1:0;
    }

    @Override
    public boolean newAccount(Account a) {
        String sqlStmt = "INSERT INTO account (accNo, name, amount, accType) VALUES ("+a.getAccNo()+", \""+a.getName()+"\", "+a.getAmount()+", '"+a.getAccType()+"');";
        if(dbConn.iud(sqlStmt)>0){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public ArrayList<Account> viewAllAccounts() {
        ArrayList<Account> ac = new ArrayList();
        ResultSet rs;
        String sqlStmt = "SELECT * FROM account;";
        rs = dbConn.select(sqlStmt);
        try{
            while(rs.next()){
                Account a = new Account(rs.getInt("accNo"), rs.getString("name"), rs.getInt("amount"), rs.getString("accType").charAt(0));
                ac.add(a);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return ac;
    }

    @Override
    public int withdrawal(int accNo, int withdrawalAmount) {
        try {
            Account acc = findAccount(accNo);
            if(acc == null){
                    return 2;
            }else{
                if(withdrawalAmount > acc.amount){
                    return 3;
                }else{
                    String sqlStmt = "UPDATE account SET amount= "+(acc.amount - withdrawalAmount)+" WHERE accNo = "+accNo+";";
                    if(dbConn.iud(sqlStmt)>0){
                        return 1;
                    }
                }
            }
            return 0;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public int delete(int accNo) {
        try {
            Account acc = findAccount(accNo);
            if(acc == null){
                return 2;
            }else {
                if(acc.amount > 0){
                    return 3;
                }else {
                    String sqlStatement = "DELETE FROM account WHERE accNo = "+accNo+";";
                    if(dbConn.iud(sqlStatement)>0){
                        return 1;
                    }
                } 
            }
            return 0;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public int fundTransfer(int from, int to, int amount) {
        try {
            Account fromAcc = findAccount(from);
            Account toAcc = findAccount(to);
            
            if(fromAcc == null || toAcc == null ) return 2;
            
            int withdrawResponse = this.withdrawal(from, amount);
            
            if(withdrawResponse != 1) return withdrawResponse;
            
            int depositResponse = this.deposit(to, amount);
            
            return depositResponse;
        } catch (Exception e) {
            return 0;
        }
    }
    
}
