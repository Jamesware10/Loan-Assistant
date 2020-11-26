/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loanassistant;

/**
 *
 * @authors Carissa Richards, Donaldson Laynes, Curtney James
 *
 * Role Assignment: 1) Carissa was responsible for Product Management 
 *                  2)Curtney James was responsible for Developing the GUI 
 *                  3)Donaldson Laynes was responsible for Quality Assurance 
 *                  4)Everyone contributed to coding the Amortization class in order 
 *                  to do calculations.
 */

public class Amortization {

    //Declaring variables
    public double loanAmount, interestRate, interest, monthlyInterestRate,
            monthlyInterest, monthlyPayment, startBal, payment, principal, endBal,
            totalInterest, totalPrincipal, totalPayment, finalPayment, a, b, c, d;

    public int numberOfPayments, paymentCount;
    //End of variable declaration

    /**
     * method : amortization
     *
     * Description: accepts monthly payment as an argument, uses a while loop to
     * calculate starting balance, monthly interest, amount of principal paid,
     * and end balance.This loop iterates until the monthly payment is greater
     * than the starting balance. at which point it sets end balance to 0. sets
     * final payment to whatever the start balance is at end of iteration
     *
     * Parameters : double:monthlyPayment
     *
     * return : nothing
     *
     * @param monthlyPayment
     */
    public void amortization(double monthlyPayment) {

        startBal = loanAmount;
        
        //begin amortization loop
        while (principal < startBal) {

            startBal -= principal;
            
            monthlyInterest = (startBal * interestRate) / 12;
            
            if (monthlyPayment > monthlyInterest) {
                principal = (monthlyPayment - monthlyInterest);
            } else if (monthlyInterest > monthlyPayment) {
                principal = (monthlyInterest - monthlyPayment);
            }
            
            totalInterest += monthlyInterest;
            totalPayment += monthlyPayment;

            if (endBal <= 0) {
                endBal = 0;
            }
            
            endBal = startBal - principal; 
            
            //keeps track of payments
            paymentCount++;
            
            //System.out.println();     Output tester
        }

        finalPayment = startBal;
    }

    /**
     * method : amortization
     *
     * Description: accepts number of payment as an argument, uses a while loop
     * to calculate starting balance, monthly interest, amount of principal
     * paid, and end balance. This loop iterates until the number of iterations
     * the sentinel value has been reached. The sentinel value is equal to
     * whatever the user specified as an argument for this method at which point
     * it sets end balance to 0. sets final payment to whatever the start
     * balance is at end of iteration
     *
     * Parameters : int:numberOfPayments
     *
     * return : nothing
     */
    public void amortization(int numberOfPayments) {
        
        startBal = loanAmount;
        
        //Monthly payment formula
        monthlyPayment = (loanAmount * (((interestRate / 12) * (Math.pow((1 + (interestRate / 12)), numberOfPayments))) / (Math.pow(1 + (interestRate / 12), numberOfPayments) - 1)));
        monthlyPayment = (double)Math.round(monthlyPayment*100)/100;
        
        //begin amortization loop
        while (paymentCount != numberOfPayments) {

            startBal -= principal;
            monthlyInterest = (startBal * interestRate) / 12;
            
            if (monthlyPayment > monthlyInterest) {
                principal = (monthlyPayment - monthlyInterest);
                
            } else if (monthlyInterest > monthlyPayment) {
                principal = (monthlyInterest - monthlyPayment);
            }
            
            totalInterest += monthlyInterest;
            totalPayment += monthlyPayment;

            if (endBal <= 0) {
                endBal = 0;
            }
            endBal = startBal - principal;
            
            //Keeps track of payments
            paymentCount++;
            
            //System.out.println();     Output tester
        
        }

        finalPayment = startBal;
    }

}
