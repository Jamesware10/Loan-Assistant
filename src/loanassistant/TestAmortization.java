/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loanassistant;

import java.text.NumberFormat;

/**
 *
 * @author Curtney James
 */
public class TestAmortization implements Amortization{

    public double loanAmt, intRate, mthlyPay, userMthlyPay, mthlyExtra, finalPay, mthlyIntRate, startBal, payment, interest, principal, endBal, totalInterest, totalPrincipal, a, b, c, d;
    public int term, noOfMths, paymentCount;
    
    //Test outputs of method
    public static void main(String[] args) {
        TestAmortization pmt = new TestAmortization();
        pmt.loanAmt =10000;
        pmt.intRate = 6;
        pmt.noOfMths=144;
        pmt.calculateMonthlyPayment();
        int numPow;
        numPow = -(pmt.noOfMths);
        System.out.println(pmt.calculateNumberOfPayments());
        
    }
    
    @Override
    public double calculateMonthlyPayment() {
        mthlyIntRate = intRate / 12;
        a = mthlyIntRate+1;     
        b = (Math.pow(a,-(noOfMths)));
        c = 1 - b;
        d = mthlyIntRate / c;
        mthlyPay = d * loanAmt;  
        return mthlyPay;
    }
    
    @Override
    public int calculateNumberOfPayments() {
        return (int)(loanAmt/mthlyPay);
    }

    @Override
    public double calculateTotalInterest() {
        interest = (loanAmt*intRate);
        totalInterest += interest;
        return totalInterest;
    }

    @Override
    public double calculateTotalPrincipal() {
        principal = mthlyPay - interest;
        totalPrincipal += principal;
        return totalPrincipal;
    }

    @Override
    public double calculateFinalPayment() {
        finalPay = loanAmt%mthlyPay;
        return finalPay;
    }

    @Override
    public double calculateEndBalance() {
        endBal = startBal - principal;
        return endBal;
    }

    @Override
    public void amortization(double loanAmt, double intRate) {
        
        this.loanAmt = loanAmt;
        this.intRate = intRate;
        //begin amortization loop
        
        while (endBal > 0) {
            ++paymentCount;
            startBal = loanAmt;
            interest = calculateTotalInterest();
            principal = calculateTotalPrincipal();
            endBal = calculateEndBalance();
            totalInterest = calculateTotalInterest();
        }
    }
    
}
