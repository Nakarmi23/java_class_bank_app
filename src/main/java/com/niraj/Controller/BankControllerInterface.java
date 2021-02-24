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
public interface BankControllerInterface {
    ArrayList<Account> ac = new ArrayList();
    public Account findAccount(int accNo);
    public int deposit(int accNo, int balance);
    public boolean newAccount(Account a);
    public ArrayList<Account> viewAllAccounts();
    public int withdrawal(int accNo, int withdrawalAmount);
    public int delete(int accNo);
    public int fundTransfer(int from, int to, int amount);
}
