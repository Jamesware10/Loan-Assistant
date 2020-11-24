/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loanassistant;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import static javax.swing.BorderFactory.createLineBorder;
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

    // Declare component variables 
    private JButton btnComputeMonthly, btnNewLoanAnalysis, btnSwitchField, btnExit;
    private JLabel lblInterestRate, lblLoanAnalysis, lblLoanBal, lblMonthlyPayments, lblNumOfPayments;
    private JTextArea txaNewLoanAnalysis;
    private JFormattedTextField txtInterestRate, txtLoanBal, txtMonthlyPayments, txtNumOfPayments;
    // End of component variable declaration

    private ArrayList<JFormattedTextField> fieldList = new ArrayList<>();

    TestAmortization pmt = new TestAmortization();
    private DecimalFormat decimalFormatter = new DecimalFormat("0.00");
    private NumberFormat amountFormat;

    private int numberOfPayments = 0;
    private double monthlyPayment = 0;

    public LoanAssistant() {

        // Sets up frame
        super("Loan Assistant");

        setLayout(new GridBagLayout());
        setSize(650, 275);
        setResizable(true);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(8, 8, 8, 8);

        // Creates components
        lblLoanBal = new JLabel("Loan Balance");
        lblInterestRate = new JLabel("Interest Rate");
        lblNumOfPayments = new JLabel("Number of Payments:");
        lblMonthlyPayments = new JLabel("Monthly Payments:");
        lblLoanAnalysis = new JLabel("Loan Analysis");
        txtLoanBal = new JFormattedTextField(amountFormat);
        txtLoanBal.setColumns(8);
        txtInterestRate = new JFormattedTextField(amountFormat);
        txtInterestRate.setColumns(8);
        txtNumOfPayments = new JFormattedTextField(amountFormat);
        txtNumOfPayments.setColumns(8);
        txtMonthlyPayments = new JFormattedTextField(amountFormat);
        txtMonthlyPayments.setColumns(8);
        btnComputeMonthly = new JButton("Compute Montly Payment");
        btnNewLoanAnalysis = new JButton("New Loan Analysis");
        btnSwitchField = new JButton("X");
        btnExit = new JButton("Exit");
        txaNewLoanAnalysis = new JTextArea(8, 25);

        Font font = new Font("Segoe UI", Font.PLAIN, 17);
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
        constraints.fill = GridBagConstraints.HORIZONTAL;
        lblLoanBal.setFont(font);
        add(lblLoanBal, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        lblInterestRate.setFont(font);
        add(lblInterestRate, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        lblNumOfPayments.setFont(font);
        add(lblNumOfPayments, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridheight = 1;
        lblMonthlyPayments.setFont(font);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        add(lblMonthlyPayments, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridheight = 1;
        txtLoanBal.setFont(font);
        txtLoanBal.setHorizontalAlignment(JFormattedTextField.RIGHT);
        add(txtLoanBal, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridheight = 1;
        txtInterestRate.setFont(font);
        txtInterestRate.setHorizontalAlignment(JFormattedTextField.RIGHT);
        add(txtInterestRate, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        txtNumOfPayments.setFont(font);
        txtNumOfPayments.setHorizontalAlignment(JFormattedTextField.RIGHT);
        add(txtNumOfPayments, constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        txtMonthlyPayments.setFont(font);
        txtMonthlyPayments.setHorizontalAlignment(JFormattedTextField.RIGHT);
        add(txtMonthlyPayments, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridheight = 1;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.CENTER;
        add(btnComputeMonthly, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridheight = 1;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.CENTER;
        add(btnNewLoanAnalysis, constraints);

        constraints.gridx = 2;
        constraints.gridy = 2;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        add(btnSwitchField, constraints);

        constraints.gridx = 3;
        constraints.gridy = 0;
        constraints.gridheight = 1;
        lblLoanAnalysis.setFont(font);
        add(lblLoanAnalysis, constraints);

        constraints.gridx = 3;
        constraints.gridy = 1;
        constraints.gridheight = 4;
        constraints.gridwidth = 1;
        //txaNewLoanAnalysis.setPreferredSize(preferredSize);
        txaNewLoanAnalysis.setFont(new Font("Courier New", Font.PLAIN, 13));
        constraints.fill = GridBagConstraints.VERTICAL;
        txaNewLoanAnalysis.setBorder(createLineBorder(Color.BLACK));
        txaNewLoanAnalysis.setEditable(false);
        txaNewLoanAnalysis.setFocusable(false);
        add(txaNewLoanAnalysis, constraints);

        constraints.gridx = 3;
        constraints.gridy = 5;
        constraints.gridheight = 1;
        add(btnExit, constraints);

        pack();
        //===================================================

        //Disables Monthly payment field by default
        txtMonthlyPayments.setEnabled(false);
        btnComputeMonthly.setEnabled(false);
        txtMonthlyPayments.setBackground(Color.YELLOW);

        btnComputeMonthly.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                btnSwitchField.setLocation(btnSwitchField.getX(), (btnSwitchField.getY()));

                // gets text from fields and stores them in global variables from Amortization2 class
                pmt.loanAmt = Double.parseDouble(txtLoanBal.getText());
                pmt.intRate = (Double.parseDouble(txtInterestRate.getText()) / 100);

                //gets and sets text based on which field is enabled.
                if (txtNumOfPayments.isEnabled()) {
                    pmt.noOfMths = Integer.parseInt(txtNumOfPayments.getText());
                    
                    pmt.amortization(pmt.noOfMths);
                    txtMonthlyPayments.setText((String.valueOf(decimalFormatter.format((pmt.mthlyPay)))));
                } else if (txtMonthlyPayments.isEnabled()) {
                    pmt.mthlyPay = Double.parseDouble(txtMonthlyPayments.getText());
                    
                    pmt.amortization(pmt.mthlyPay);
                    txtNumOfPayments.setText(String.valueOf(pmt.noOfMths));
                }

                //pmt.amortization(Double.parseDouble(txtLoanBal.getText()), Double.parseDouble(txtInterestRate.getText()));
                txaNewLoanAnalysis.setText(
                        "Loan balance: " + currencyFormatter(pmt.loanAmt)
                        + "\n"
                        + "Interest rate is: " + percentFormatter(pmt.intRate)
                        + "\n\n"
                        + pmt.noOfMths + " Payments of " + currencyFormatter(pmt.mthlyPay)
                        + "\n"
                        + "Final Payments of: " + currencyFormatter(pmt.finalPay)
                        + "\n"
                        + "Total Payments: " + currencyFormatter(pmt.totalInterest)
                        + "\n"
                        + "Interest Paid: " + currencyFormatter(pmt.totalInterest)
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
                    btnSwitchField.setLocation(btnSwitchField.getX(), 87);

                } else if (txtNumOfPayments.isEnabled()) {
                    txtNumOfPayments.setBackground(Color.YELLOW);
                    txtNumOfPayments.setEnabled(false);
                    txtMonthlyPayments.setEnabled(true);
                    txtMonthlyPayments.setBackground(Color.WHITE);
                    txtNumOfPayments.setText("");
                    btnSwitchField.setLocation(btnSwitchField.getX(), 127);
                }
            }
        });

        btnNewLoanAnalysis.addActionListener(new ActionListener() {
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
                pmt.intRate = 0;
                pmt.noOfMths = 0;
                pmt.mthlyPay = 0;
                numberOfPayments = 0;
                monthlyPayment = 0;
                btnComputeMonthly.setEnabled(false);
            }
        });

        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                System.exit(0);
            }
        });
    }

    /*
        Method Header
     */
    public String currencyFormatter(double number) {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        return currencyFormat.format(number);
    }

    public String percentFormatter(double number) {
        NumberFormat percentFormat = NumberFormat.getPercentInstance();
        return percentFormat.format(number);
    }

    /*
        Method Header
     */
    public void fieldListener() {

        /*When looping through arraylist, it uses an interenal variable to keep track of
        the amount of structural modifications(changing the size of the array or using, add.remove methods)
        done to the arraylist. An exception will be thrown if the size of the arrylist is manually changed 
        during iteration.*/
        for (Iterator<JFormattedTextField> itr = fieldList.iterator(); itr.hasNext();) {
            int i = 0;

            JFormattedTextField fields = itr.next();

            if (fields.isEnabled()) {
                if (fields.getText().isEmpty()) {
                    btnComputeMonthly.setEnabled(false);
                } else {
                    btnComputeMonthly.setEnabled(true);
                }
            }
        }
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
