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

    //Adds JFormattedTextFields to ArrayList to check fieldList loop
    private ArrayList<JFormattedTextField> fieldList = new ArrayList<>();

    //Instances of Formatter class for calculations
    Amortization pmt = new Amortization();

    //Formatter objects
    private DecimalFormat decimalFormatter = new DecimalFormat("0.00");
    private NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
    private NumberFormat percentFormat = NumberFormat.getPercentInstance();
    private NumberFormat amountFormat;

    //Frame constructor
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
        btnComputeMonthly = new JButton("Compute Monthly Payment");
        btnNewLoanAnalysis = new JButton("New Loan Analysis");
        btnSwitchField = new JButton("X");
        btnExit = new JButton("Exit");
        txaNewLoanAnalysis = new JTextArea(7, 35);

        //Font object that sets font type, font style and font size
        Font font = new Font("Segoe UI", Font.PLAIN, 17);

        //adds formatted textfields to field list. line 342
        fieldList.add(txtLoanBal);
        txtLoanBal.getDocument().addDocumentListener(this);
        fieldList.add(txtInterestRate);
        txtInterestRate.getDocument().addDocumentListener(this);
        fieldList.add(txtNumOfPayments);
        txtNumOfPayments.getDocument().addDocumentListener(this);
        fieldList.add(txtMonthlyPayments);
        txtMonthlyPayments.getDocument().addDocumentListener(this);

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

        //Disables Monthly payment field and Compute button by default
        txtMonthlyPayments.setEnabled(false);
        btnComputeMonthly.setEnabled(false);
        txtMonthlyPayments.setBackground(Color.YELLOW);

        btnComputeMonthly.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //btnSwitchField.setLocation(btnSwitchField.getX(), (btnSwitchField.getY()));
                // gets text from fields and stores them in global variables from Amortization2 class
                pmt.loanAmount = Double.parseDouble(txtLoanBal.getText());
                pmt.interestRate = (Double.parseDouble(txtInterestRate.getText()) / 100);

                if (txtNumOfPayments.isEnabled()) {
                    /**
                     * If Number Of payment TextField is enabled Get the text
                     * and pass it into amortization(int noOfMths) to determine
                     * Amount of Payment after calculations are completed
                     * display Payment amount in respective JTextField
                     */

                    pmt.numberOfPayments = Integer.parseInt(txtNumOfPayments.getText());
                    pmt.amortization(pmt.numberOfPayments);
                    txtMonthlyPayments.setText((String.valueOf(decimalFormatter.format((pmt.monthlyPayment)))));

                } else if (txtMonthlyPayments.isEnabled()) {
                    /**
                     * If Payment Amount TextField is enabled Get the text and
                     * pass it into amortization(pmt.mthlyPay) to determine The
                     * number of Payments after calculations are completed
                     * display Amount of Payments in respective JTextField
                     */
                    pmt.monthlyPayment = Double.parseDouble(txtMonthlyPayments.getText());
                    pmt.amortization(pmt.monthlyPayment);
                    txtNumOfPayments.setText(String.valueOf(pmt.paymentCount));
                    pmt.numberOfPayments = pmt.paymentCount;
                }

                //Displays and formats Loan Analysis
                txaNewLoanAnalysis.setText(
                        "Loan Balance: " + currencyFormat.format(pmt.loanAmount)
                        + "\n"
                        + "Interest Rate: " + percentFormat.format(pmt.interestRate)
                        + "\n\n"
                        + (pmt.numberOfPayments-1) + " Payments of " + currencyFormat.format(pmt.monthlyPayment)
                        + "\n"
                        + "Final Payment of: " + currencyFormat.format(pmt.finalPayment)
                        + "\n"
                        + "Total Payments: " + currencyFormat.format(pmt.totalInterest + pmt.loanAmount)
                        + "\n"
                        + "Interest Paid: " + currencyFormat.format(pmt.totalInterest)
                );

                pmt.loanAmount = 0;
                pmt.interestRate = 0;
                pmt.numberOfPayments = 0;
                pmt.monthlyPayment = 0;
                pmt.totalInterest = 0;
                pmt.totalPayment = 0;
                pmt.totalPrincipal = 0;
                pmt.finalPayment = 0;
                pmt.paymentCount = 0;
            }
        });

        btnSwitchField.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //if either textField is enabled, disable it and change its appearance
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

                pmt.loanAmount = 0;
                pmt.interestRate = 0;
                pmt.numberOfPayments = 0;
                pmt.monthlyPayment = 0;
                pmt.totalInterest = 0;
                pmt.totalPayment = 0;
                pmt.totalPrincipal = 0;
                pmt.finalPayment = 0;
                pmt.paymentCount = 0;
                txtMonthlyPayments.setText("");
                txtNumOfPayments.setText("");
            }
        });

        btnNewLoanAnalysis.addActionListener(new ActionListener() {
            @Override
            // Clears all fields and re-intialize variables to 0
            public void actionPerformed(ActionEvent e
            ) {
                txaNewLoanAnalysis.setText("");
                txtInterestRate.setText("");
                txtLoanBal.setText("");
                txtMonthlyPayments.setText("");
                txtNumOfPayments.setText("");
                pmt.loanAmount = 0;
                pmt.interestRate = 0;
                pmt.numberOfPayments = 0;
                pmt.monthlyPayment = 0;
                pmt.totalInterest = 0;
                pmt.totalPayment = 0;
                pmt.totalPrincipal = 0;
                pmt.finalPayment = 0;
                btnComputeMonthly.setEnabled(false);
            }
        });

        //Exits the program
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                System.exit(0);
            }
        });
    }
    
    /**
     * *
     * Method : fieldListener
     *
     * Description: Loops through ArrayList containing JFormattedFields and
     * check if each list item is enabled if they say returns and the if the
     * JformattedTextField contains text, enable the compute button else compute
     * button is disabled.note(ArrayLists use an internal iterator which keeps
     * track of the size of the list if the size of the list is changed during
     * iteration an exception is thrown).
     *
     * Parameter: none
     *
     * returns: nothing
     */
    public void fieldListener() {

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

    //Listens for Field updates and calls methods.
    @Override
    public void insertUpdate(DocumentEvent e) {
        fieldListener();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        fieldListener();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        fieldListener();
    }

    public static void main(String[] args) {
        LoanAssistant newForm = new LoanAssistant();
        newForm.setLocationRelativeTo(null);
        newForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newForm.setVisible(true);
    }

}
