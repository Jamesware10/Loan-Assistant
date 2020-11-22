/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loanassistant;

/**
 *
 * @author Curtney James
 */
public class Amortization {

    public static double loanAmt, noOfMonths, interestRate, monthlyPay, monthlyExtra, finalPay, monthlyInterest,
            startBal, payment, interest, principal, endBal, totalInterest, newLoanAmt, monthlyInterestRate;
    public static int term, paymentCount, numberOfPayments;

    
    /*Method headers*/
    public static double calculateMonthlyPayment() {

        monthlyInterestRate = (interestRate / 12);
        double a = monthlyInterestRate + 1;
        double b = Math.pow(a, -numberOfPayments);
        double c = 1 - b;
        double d = (monthlyInterestRate / c);
        monthlyPay = (d * loanAmt);

        return monthlyPay;
    }
    
    /*Method headers*/
    public static int numberOfPayments() {
        numberOfPayments = (int) (loanAmt/monthlyPay);
        
        return numberOfPayments;
    }

    /*Method headers*/
    public static double calculateTotalInterest() {
        totalInterest += interest;
        
        return totalInterest;
    }

    /*Method headers*/
    public static double calculateTotalPrincipal() {
        principal = monthlyPay - interest;
        double totalPrincipal = 0;
        totalPrincipal += principal;
        
        return totalPrincipal;
    }

    /*Method headers*/
    public static double calculateFinalPayment() {
        return finalPay;
    }

    /*Method headers*/
    public static double calculateEndBalance() {
        endBal = startBal - principal;
        
        return endBal;
    }
}
