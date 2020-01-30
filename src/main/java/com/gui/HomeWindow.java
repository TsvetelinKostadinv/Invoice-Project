/*
 * 22/01/2020 19:22:02
 * HomeWindow.java created by Tsvetelin
 */
package com.gui;


import java.awt.Button;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import com.dispatcher.InvoiceDispatcher;
import com.models.invoice.Invoice;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javax.swing.JTable;


/**
 * @author Tsvetelin
 *
 */
public class HomeWindow extends JFrame
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private JPanel            contentPane;

    /**
     * Launch the application.
     */
    public static void main ( String [] args )
    {
        EventQueue.invokeLater( new Runnable()
        {

            public void run ()
            {
                try
                {
                    HomeWindow frame = new HomeWindow();
                    frame.setVisible( true );
                } catch ( Exception e )
                {
                    e.printStackTrace();
                }
            }
        } );
    }

    public static final int WIDTH  = 800;

    public static final int HEIGHT = 600;

    private final Button    btnNewInvoice;

    private JTable          table;

    /**
     * Create the frame.
     */
    public HomeWindow ()
    {
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setBounds( 0 , 0 , WIDTH , HEIGHT );
        this.setLocationRelativeTo( null );
        contentPane = new JPanel();
        contentPane.setBorder( new EmptyBorder( 5 , 5 , 5 , 5 ) );

        this.btnNewInvoice = new Button( "Нова фактура" );
        btnNewInvoice.addMouseListener( new MouseAdapter()
        {

            @Override
            public void mouseClicked ( MouseEvent e )
            {

                Future< Invoice > Finvoice = InvoiceCreatorWindow.getInvoiceFromUserInput();

                new Thread( () -> {
                    while ( !Finvoice.isDone() )
                    {
                        try
                        {
                            Thread.sleep( 1000 );
                        } catch ( InterruptedException e1 )
                        {
                            e1.printStackTrace();
                        }
                    }
                    try
                    {
                        Invoice inv = Finvoice.get();
                        System.out.println( inv );
                        InvoiceDispatcher.dispatchForSaving( inv );
                    } catch ( InterruptedException e1 )
                    {
                        e1.printStackTrace();
                    } catch ( ExecutionException e1 )
                    {
                        e1.printStackTrace();
                    }
                } ).start();
            }
        } );
        final int newInvoiceButtonWidth = 10;
        final int newInvoiceFontSize = 30;
        final int newInvoiceButtonHeight = newInvoiceFontSize + 2 * 5;

        this.btnNewInvoice.setFont( new Font( "Arial" , 0 , 30 ) );
        this.btnNewInvoice.setBounds( 10 ,
                                      10 ,
                                      WIDTH - 4 * newInvoiceButtonWidth ,
                                      newInvoiceButtonHeight );

        String [] colomnNames = {
                "Номер",
                "Стойност",
                "Дата",
                "Платено",
                "Остава",
                "Общо"
        };

        // Invoice [] invoices = InvoiceDispatcher.getInvoices();

        // Object [] [] objData = convertToObjMatrix( invoices );

        table = new JTable( new Object[2][2] , colomnNames );
        JScrollPane scrollPane = new JScrollPane( table );
        table.setFillsViewportHeight( true );

        contentPane.setLayout( new GridLayout( 0 , 1 ) );
        contentPane.add( this.btnNewInvoice );
        // contentPane.add( scrollPane );

        setContentPane( contentPane );
    }

    /**
     * @param invoices
     * @return
     */
    private Object [] [] convertToObjMatrix ( Invoice [] invoices )
    {
        Object [] [] res = new Object[invoices.length][4];

        for ( int i = 0 ; i < invoices.length ; i++ )
        {
            res[i] = new Object[] { invoices[i].getNumber(),
                    invoices[i].getValue(),
                    invoices[i].getDateOfInvoice().toString(),
                    invoices[i].paid(),
                    invoices[i].rest(),
                    invoices[i].getValue() };
        }

        return res;
    }

}
