/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loanassistant;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author Curtney James
 */
public class LoanAssistant extends JFrame implements DocumentListener {

    // declare component variables 
    private JButton btnComputeMonthly, btnNewLoanAnalysis, btnSwitchField, btnExit;
    private JLabel lblInterestRate, lblLoanAnalysis, lblLoanBal, lblMonthlyPayments, lblNumOfPayments;
    private JTextArea txaNewLoanAnalysis;
    private JFormattedTextField txtInterestRate, txtLoanBal, txtMonthlyPayments, txtNumOfPayments;
    // End of component variable declaration

    private ArrayList<JFormattedTextField> fieldList = new ArrayList<>();

    Amortization pmt = new Amortization();

    private NumberFormat amountFormat;

    public LoanAssistant() {

        // Sets up frame
        super("Loan Assistant");
        setLayout(new GridBagLayout());
        setSize(630, 275);
        setResizable(false);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.fill = GridBagConstraints.HORIZONTAL;

        // Creates components
        lblLoanBal = new JLabel("Loan Balance");
        lblInterestRate = new JLabel("Interest Rate");
        lblNumOfPayments = new JLabel("Number of Payments:");
        lblMonthlyPayments = new JLabel("Monthly Payments:");
        lblLoanAnalysis = new JLabel("Loan Analysis");
        txtLoanBal = new JFormattedTextField(amountFormat);
        txtLoanBal.setColumns(10);
        txtInterestRate = new JFormattedTextField(amountFormat);
        txtInterestRate.setColumns(10);
        txtNumOfPayments = new JFormattedTextField(amountFormat);
        txtNumOfPayments.setColumns(10);
        txtMonthlyPayments = new JFormattedTextField(amountFormat);
        txtMonthlyPayments.setColumns(10);
        btnComputeMonthly = new JButton("Compute Montly Payment");
        btnNewLoanAnalysis = new JButton("New Loan Analysis");
        btnSwitchField = new JButton("X");
        btnExit = new JButton("Exit");
        txaNewLoanAnalysis = new JTextArea(8, 26);

        JButton btnTest = new JButton("test");
        add(btnTest);
        //adds formatted textfields to field list
        fieldList.add(txtLoanBal);
        txtLoanBal.getDocument().addDocumentListener(this);
        fieldList.add(txtInterestRate);
        txtInterestRate.getDocument().addDocumentListener(this);
        fieldList.add(txtNumOfPayments);
        txtNumOfPayments.getDocument().addDocumentListener(this);
        fieldList.add(txtMonthlyPayments);
        txtMonthlyPayments.getDocument().addDocumentListener(this);

//        for (JFormattedTextField fields : fieldList) {
//            fieldList.add(fields);
//            fields.getDocument().addDocumentListener(this);
//
//        }

        // Defines layout and add components to frame
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridheight = 1;
        add(lblLoanBal, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridheight = 1;
        add(lblInterestRate, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridheight = 1;
        add(lblNumOfPayments, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridheight = 1;
        add(lblMonthlyPayments, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridheight = 1;
        add(txtLoanBal, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridheight = 1;
        add(txtInterestRate, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridheight = 1;
        add(txtNumOfPayments, constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridheight = 1;
        add(txtMonthlyPayments, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridheight = 1;
        add(btnComputeMonthly, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridheight = 1;
        add(btnNewLoanAnalysis, constraints);

        constraints.gridx = 2;
        constraints.gridy = 2;
        constraints.gridheight = 1;
        add(btnSwitchField, constraints);

        constraints.gridx = 3;
        constraints.gridy = 0;
        constraints.gridheight = 1;
        add(lblLoanAnalysis, constraints);

        constraints.gridx = 3;
        constraints.gridy = 1;
        constraints.gridheight = 4;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.VERTICAL;
        add(txaNewLoanAnalysis, constraints);

        constraints.gridx = 3;
        constraints.gridy = 5;
        constraints.gridheight = 1;
        add(btnExit, constraints);
        btnSwitchField.setLocation(305, 65);
        pack();
        //===================================================

        //Disables Monthly payment field by default
        txtMonthlyPayments.setEnabled(false);
        btnComputeMonthly.setEnabled(false);
        txtMonthlyPayments.setBackground(Color.YELLOW);

        btnComputeMonthly.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //get location of X button, and sets its location
                Point location = btnSwitchField.getLocation();
                btnSwitchField.setLocation(location);

                // gets text from fields and stores them in global variables from Amortization class
                pmt.loanAmt = Double.parseDouble(txtLoanBal.getText());
                pmt.interestRate = Double.parseDouble(txtInterestRate.getText());

                //gets and sets text based on which field is enabled.
                if (txtNumOfPayments.isEnabled()) {
                    pmt.numberOfPayments = Integer.parseInt(txtNumOfPayments.getText());
                    txtMonthlyPayments.setText(String.valueOf(pmt.calculateMonthlyPayment()));
                } else if (txtMonthlyPayments.isEnabled()) {
                    pmt.monthlyPay = Double.parseDouble(txtMonthlyPayments.getText());
                    txtNumOfPayments.setText(String.valueOf(pmt.numberOfPayments()));
                }

                // Intialize variables to called methods
                int numberOfPayments = pmt.numberOfPayments();
                double calculateMonthlyPayment = pmt.calculateMonthlyPayment();

                txaNewLoanAnalysis.setText(
                        "Loan balance: " + pmt.loanAmt
                        + "Interest rate is: " + pmt.interestRate
                        + "\n\n"
                        + numberOfPayments + " Payments of " + calculateMonthlyPayment
                        + "\n"
                        + "Total Principal is: " + pmt.calculateTotalPrincipal()
                        + "\n"
                        + "Final Payments: " + pmt.finalPay
                        + "\n"
                        //                        + "Total Payments: " +Amortization2.totalPay
                        + "Total Interest: " + pmt.totalInterest
                );
            }
        });

        btnSwitchField.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {

                //if textField is enabled, disable it and change its appearance
                if (txtMonthlyPayments.isEnabled()) {
                    txtMonthlyPayments.setBackground(Color.YELLOW);
                    txtMonthlyPayments.setEnabled(false);
                    txtNumOfPayments.setEnabled(true);
                    txtNumOfPayments.setBackground(Color.WHITE);
                    txtMonthlyPayments.setText("");
                    btnSwitchField.setLocation(305, 65);

                } else if (txtNumOfPayments.isEnabled()) {
                    txtNumOfPayments.setBackground(Color.YELLOW);
                    txtNumOfPayments.setEnabled(false);
                    txtMonthlyPayments.setEnabled(true);
                    txtMonthlyPayments.setBackground(Color.WHITE);
                    txtNumOfPayments.setText("");
                    btnSwitchField.setLocation(305, 95);

                }
            }
        });

        btnNewLoanAnalysis.addActionListener(
                new ActionListener() {
            @Override
            // Clears all fields and set re-intialize variables to 0
            public void actionPerformed(ActionEvent e
            ) {
                txaNewLoanAnalysis.setText("");
                txtInterestRate.setText("");
                txtLoanBal.setText("");
                txtMonthlyPayments.setText("");
                txtNumOfPayments.setText("");
                pmt.loanAmt = 0;
                pmt.interestRate = 0;
                pmt.numberOfPayments = 0;
                pmt.monthlyPay = 0;
            }
        });

        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                System.exit(0);
            }
        });

        btnTest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                btnTest.setText(fieldList.get(1).getText());
            }
        });
    }

    /*
        Method
    */
    public void fieldListener() {

        /*When looping through arraylist, it uses an interenal variable to keep track of
        the amount of structural modifications(changing the size of the array or using, add.remove methods)
        done to the arraylist. An exception will be thrown if the size of the arrylist is manually changed 
        uring iteration.*/
        
        for (Iterator<JFormattedTextField> itr = fieldList.iterator(); itr.hasNext();) {
            JFormattedTextField fields = itr.next();
            if (!fields.isEnabled()) {
                itr.remove();
            }
            if (fields.getText().isEmpty()) {
                btnComputeMonthly.setEnabled(false);
            } else {
                btnComputeMonthly.setEnabled(true);
            }
        }

//          ENHANCED FOR LOOP BAD ! ! !
//           for (JFormattedTextField fields : fieldList) {
//            btnComputeMonthly.setEnabled(true);
//
//            if (!fields.isEnabled()) {
//                fieldList.remove(fields);
//            }
//
//            if (fields.getText().isEmpty()) {
//                btnComputeMonthly.setEnabled(false);
//            } else {
//                btnComputeMonthly.setEnabled(true);
//            }
//        }

    }

    //Listens for Field updates and calls methods
    @Override
    public void insertUpdate(DocumentEvent e
    ) {
        fieldListener();
    }

    @Override
    public void removeUpdate(DocumentEvent e
    ) {
        fieldListener();
    }

    @Override
    public void changedUpdate(DocumentEvent e
    ) {
        fieldListener();
    }

    public static void main(String[] args) {
        LoanAssistant newForm = new LoanAssistant();
        newForm.setLocationRelativeTo(null);
        newForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newForm.setVisible(true);
    }

}
