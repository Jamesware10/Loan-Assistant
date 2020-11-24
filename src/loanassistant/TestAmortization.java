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
public class TestAmortization implements Amortization {

    public double loanAmt, intRate, mthlyPay, userMthlyPay, totalPayment, mthlyInt, finalPay, mthlyIntRate, startBal, payment, interest, principal, endBal, totalInterest, totalPrincipal, a, b, c, d;
    public int term, noOfMths, paymentCount;

    public void amortization(double mthlyPay) {
        //begin amortization loop
        startBal = loanAmt;

        //mthlyPay = (loanAmt * (((intRate / 12) * (Math.pow(1 + (intRate / 12), noOfMths))) / (Math.pow(1 + (intRate / 12), noOfMths) - 1)));
        while (mthlyPay < startBal) {

            startBal -= principal;
            mthlyInt = (startBal * intRate) / 12;
            principal = (mthlyPay - mthlyInt);
            endBal = startBal - principal;
            if (endBal <= 0) {
                endBal = 0;
            }
            if (mthlyPay < startBal) {
                finalPay = startBal;
            }
            totalInterest += mthlyInt;
            totalPayment += mthlyPay;

            paymentCount++;

        }

    }

    public void amortization(int noOfPayments) {
        //begin amortization loop
        startBal = loanAmt;

        mthlyPay = (loanAmt * (((intRate / 12) * (Math.pow(1 + (intRate / 12), noOfMths))) / (Math.pow(1 + (intRate / 12), noOfMths) - 1)));

        while (paymentCount < noOfMths) {

            startBal -= principal;
            mthlyInt = (startBal * intRate) / 12;
            principal = (mthlyPay - mthlyInt);
            endBal = startBal - principal;
            if (endBal <= 0) {
                endBal = 0;
            }
            if (mthlyPay < startBal) {
                finalPay = startBal;
            }
            totalInterest += mthlyInt;
            totalPayment += mthlyPay;

            paymentCount++;
        }
    }

//Test outputs of method
    public static void main(String[] args) {
        TestAmortization pmt = new TestAmortization();
        pmt.loanAmt = 100000;
        pmt.intRate = 0.06;
        pmt.noOfMths = 360;
        pmt.mthlyPay = 0;
        //pmt.calculateMonthlyPayment();
        int numPow;
        pmt.amortization(pmt.noOfMths);

        System.out.println(pmt.paymentCount + " " + pmt.startBal + " " + pmt.mthlyPay + " " + pmt.mthlyInt + " " + pmt.principal + " " + pmt.endBal + " " + pmt.totalInterest + " " + pmt.finalPay);

    }

}
