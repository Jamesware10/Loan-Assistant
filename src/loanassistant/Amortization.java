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
public interface Amortization {
    
    
    public double calculateMonthlyPayment();
    public int calculateNumberOfPayments();
    public double calculateTotalInterest();
    public double calculateTotalPrincipal();
    public double calculateFinalPayment();
    public double calculateEndBalance();
    public void amortization(double loanAmt, double intRate);
}
