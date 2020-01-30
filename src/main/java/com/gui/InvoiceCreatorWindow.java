/*
 * 30/01/2020 14:48:11
 * InvoiceCreatorWindow.java created by Tsvetelin
 */
package com.gui;


import java.awt.Button;
import java.awt.Label;
import java.awt.TextArea;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.models.date.Date;
import com.models.invoice.Invoice;
import com.models.invoice.Payment;
import com.util.Constants;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;


/**
 * @author Tsvetelin
 * FIXME The GUI should not be responsible for the creation of objects
 */
public class InvoiceCreatorWindow extends JFrame implements Future< Invoice >
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private JPanel            contentPane;

    /**
     * Launch the application.
     */
    public static Future< Invoice > getInvoiceFromUserInput ()
    {
        try
        {
            InvoiceCreatorWindow frame = new InvoiceCreatorWindow();
            frame.setVisible( true );
            return frame;
        } catch ( Exception e )
        {
            e.printStackTrace();
        }
        return null;
    }

    public static final int   WIDTH  = 400;

    public static final int   HEIGHT = 600;

    private final TextArea    txtInvoiceNumber;

    private final TextArea    txtValue;

    private final TextArea    txtDate;

    private final TextArea    txtPayment;

    private Invoice           result    = null;

    /**
     * Create the frame.
     */
    public InvoiceCreatorWindow ()
    {

        setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
        setBounds( 0 , 0 , WIDTH , HEIGHT );
        this.setLocationRelativeTo( null );
        this.setAutoRequestFocus( true );
        contentPane = new JPanel();
        contentPane.setBorder( new EmptyBorder( 5 , 5 , 5 , 5 ) );

        JPanel numberPanel = new JPanel();

        Label lblNumber = new Label( "Номер:" );
        lblNumber.setFont( Constants.ARIAL_20.get() );

        this.txtInvoiceNumber = new TextArea( "" ,
                                              1 ,
                                              11 ,
                                              TextArea.SCROLLBARS_NONE );
        this.txtInvoiceNumber.setFont( Constants.ARIAL_20.get() );

        numberPanel.add( lblNumber );
        numberPanel.add( txtInvoiceNumber );

        JPanel valuePanel = new JPanel();

        Label lblValue = new Label( "Стойност: " );
        lblNumber.setFont( Constants.ARIAL_20.get() );

        this.txtValue = new TextArea( "" , 1 , 11 , TextArea.SCROLLBARS_NONE );
        this.txtValue.setFont( Constants.ARIAL_20.get() );

        valuePanel.add( lblValue );
        valuePanel.add( txtValue );

        JPanel datePanel = new JPanel();

        Label lblDate = new Label( "Дата:   " );
        lblNumber.setFont( Constants.ARIAL_20.get() );

        this.txtDate = new TextArea( "" , 1 , 11 , TextArea.SCROLLBARS_NONE );
        this.txtDate.setFont( Constants.ARIAL_20.get() );

        datePanel.add( lblDate );
        datePanel.add( txtDate );

        JPanel paymentPanel = new JPanel();

        Label lblPayment = new Label( "Платено:" );
        lblPayment.setFont( Constants.ARIAL_20.get() );

        this.txtPayment = new TextArea( "" ,
                                        1 ,
                                        11 ,
                                        TextArea.SCROLLBARS_NONE );
        this.txtPayment.setFont( Constants.ARIAL_20.get() );

        paymentPanel.add( lblPayment );
        paymentPanel.add( txtPayment );

        Button btnSubmit = new Button( "Създай фактура" );
        btnSubmit.setFont( Constants.ARIAL_20.get() );
        
        JFrame ref = this;
        
        btnSubmit.addMouseListener( new MouseAdapter()
        {

            @Override
            public void mouseClicked ( MouseEvent e )
            {
                String invoiceNumber = txtInvoiceNumber.getText();
                if ( invoiceNumber.length() != 10 )
                {
                    JOptionPane.showMessageDialog( contentPane ,
                                                   "Фактурата има 10 цифри в номера си" ,
                                                   "Невалиден брой цифри" ,
                                                   JOptionPane.ERROR_MESSAGE );
                    return;
                }

                String txtValueRaw = txtValue.getText();
                BigDecimal value = BigDecimal.ZERO;
                txtValueRaw.replace( ',' , '.' );
                try
                {
                    value = new BigDecimal( txtValueRaw ).setScale( Constants.DECIMAL_PLACES ,
                                                                    Constants.ROUNDING_MODE );
                } catch ( NumberFormatException e1 )
                {
                    JOptionPane.showMessageDialog( contentPane ,
                                                   "Невалидна стойност" ,
                                                   "Невалидна стойност" ,
                                                   JOptionPane.ERROR_MESSAGE );
                    return;
                }

                String dateRaw = txtDate.getText();
                String [] dayMonthYear = dateRaw.split( "\\." );
                if ( dayMonthYear.length != 3 )
                {
                    dayMonthYear = dateRaw.split( "\\/" );
                    if ( dayMonthYear.length != 3 )
                    {
                        JOptionPane.showMessageDialog( contentPane ,
                                                       "Невалидна дата, може да бъде определена само от"
                                                               + " \".\" или \"/\"" ,
                                                       "Невалидна дата" ,
                                                       JOptionPane.ERROR_MESSAGE );
                        return;
                    }
                }

                String txtPaymentRaw = txtPayment.getText();
                BigDecimal payment = BigDecimal.ZERO;
                if ( txtPaymentRaw.length() != 0 )
                {
                    txtPaymentRaw.replace( ',' , '.' );
                    try
                    {
                        payment = new BigDecimal( txtPaymentRaw ).setScale( Constants.DECIMAL_PLACES ,
                                                                            Constants.ROUNDING_MODE );
                    } catch ( NumberFormatException e1 )
                    {

                        JOptionPane.showMessageDialog( contentPane ,
                                                       "Невалидно плащане" ,
                                                       "Невалидно плащане" ,
                                                       JOptionPane.ERROR_MESSAGE );
                        return;
                    }

                }
                int day = Integer.parseInt( dayMonthYear[0] );
                int month = Integer.parseInt( dayMonthYear[1] );
                int year = Integer.parseInt( dayMonthYear[2] );
                Date date = new Date( day , month , year );

                Invoice res = new Invoice( invoiceNumber , value , date );
                res.addPayment( new Payment( date , payment ) );

                System.out.println( "Did it========" );

                result = res;
                ref.dispose();
            }
        } );

        this.contentPane.setLayout( new GridLayout( 0 , 1 ) );
        this.contentPane.add( numberPanel );
        this.contentPane.add( valuePanel );
        this.contentPane.add( datePanel );
        this.contentPane.add( paymentPanel );
        this.contentPane.add( btnSubmit );

        setContentPane( contentPane );
    }

    /* (non-Javadoc)
     * @see java.util.concurrent.Future#cancel(boolean)
     */
    @Override
    public boolean cancel ( boolean mayInterruptIfRunning )
    {
        return false;
    }

    /* (non-Javadoc)
     * @see java.util.concurrent.Future#isCancelled()
     */
    @Override
    public boolean isCancelled ()
    {
        return false;
    }

    /* (non-Javadoc)
     * @see java.util.concurrent.Future#isDone()
     */
    @Override
    public boolean isDone ()
    {
        return result != null;
    }

    /* (non-Javadoc)
     * @see java.util.concurrent.Future#get()
     */
    @Override
    public Invoice get () throws InterruptedException , ExecutionException
    {
        return result != null ? result : null;
    }

    /* (non-Javadoc)
     * @see java.util.concurrent.Future#get(long, java.util.concurrent.TimeUnit)
     */
    @Override
    public Invoice get ( long timeout ,
                         TimeUnit unit )
                                         throws InterruptedException ,
                                         ExecutionException ,
                                         TimeoutException
    {
        Thread.sleep( timeout );
        return get();
    }
}
