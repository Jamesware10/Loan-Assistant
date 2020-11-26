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
        //numberOfPayments = (int)(startBal/monthlyPayment);
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
            paymentCount++;

        }

        //rounds all values to two decimal places
//        startBal = Math.round(startBal * 100.0) / 100;
//        principal = Math.round(principal * 100.0) / 100;
//        monthlyPayment = Math.round(monthlyPayment * 100) / 100;
//        monthlyInterest = Math.round(monthlyInterest * 100.0) / 100;
//        interestRate = Math.round(interestRate * 100.0) / 100;
//        endBal = Math.round(endBal * 100.0) / 100;
//        
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
        //begin amortization loop
        startBal = loanAmount;

        monthlyPayment = (loanAmount * (((interestRate / 12) * (Math.pow((1 + (interestRate / 12)), numberOfPayments))) / (Math.pow(1 + (interestRate / 12), numberOfPayments) - 1)));
        monthlyPayment = (double)Math.round(monthlyPayment*100)/100;
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
            paymentCount++;
            
            
        }
        //rounds all the values to two decimal places
//        startBal = Math.round(startBal * 100.0) / 100;
//        principal = Math.round(principal * 100.0) / 100;
//        monthlyPayment = Math.round(monthlyPayment * 100) / 100;
//        monthlyInterest = Math.round(monthlyInterest * 100.0) / 100;
//        interestRate = Math.round(interestRate * 100.0) / 100;
//        Math.round(endBal * 100.0);
        finalPayment = startBal;
    }

//Test outputs of method
    public static void main(String[] args) {
        Amortization pmt = new Amortization();
        pmt.loanAmount = 10000;
        pmt.interestRate = 0.06;
        pmt.numberOfPayments = 144;
        //pmt.monthlyPayment = 97.59;

        pmt.amortization(pmt.numberOfPayments);
        //double num = (10000) * (.06) * (Math.pow((1 + .06), 360)) / (Math.pow((1 + .06), 360) - 1);
        System.out.println(pmt.paymentCount + " " + pmt.startBal + " " + pmt.numberOfPayments + " " + pmt.monthlyInterest + " " + pmt.principal + " " + pmt.endBal + " " + pmt.totalInterest + " " + pmt.finalPayment);
        //System.out.println((Math.round(pmt.startBal * 100.0) / 100.0));
        //System.out.println(num);
    }

}
