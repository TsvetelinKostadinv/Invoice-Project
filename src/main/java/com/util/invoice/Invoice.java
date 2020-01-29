/*
 * 21/01/2020 22:57:51
 * Invoice.java created by Tsvetelin
 */
package com.util.invoice;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.util.Constants;
import com.util.date.Date;


/**
 * @author Tsvetelin
 *
 */
public class Invoice implements Serializable
@XmlRootElement ( name = "invoice" )
@XmlAccessorType ( XmlAccessType.FIELD )
{

    /**
     * 
     */
    private static final long      serialVersionUID       = 1L;

    public static final String     DEFAULT_INVOICE_NUMBER = "DEFAULT_INVOICE_NUMBER";

    public static final BigDecimal DEFAULT_INVOICE_VALUE  = BigDecimal.ZERO;

    public static final Date       DEFAULT_INVOICE_DATE   = new Date();

    private final String           number;

    private final BigDecimal       value;

    private final Date             dateOfInvoice;

    @XmlElement ( name = "payment" )
    private final List< Payment >  payments;

    /**
     * @param number
     * @param value
     * @param dateOfInvoice
     */
    public Invoice ( String number , BigDecimal value , Date dateOfInvoice )
    {
        super();
        this.number = number;
        this.value = value.setScale( Constants.DECIMAL_PLACES ,
                                     Constants.ROUNDING_MODE );
        this.dateOfInvoice = dateOfInvoice;
        this.payments = new LinkedList< Payment >();
    }

    /**
     * Default invoice
     */
    public Invoice ()
    {
        super();
        this.number = DEFAULT_INVOICE_NUMBER;
        this.value = DEFAULT_INVOICE_VALUE;
        this.dateOfInvoice = DEFAULT_INVOICE_DATE;
        this.payments = new LinkedList< Payment >();
    }

    public Invoice addPayment ( Payment payment )
    {
        this.payments.add( payment );
        return this;
    }

    public BigDecimal paid ()
    {
        return payments.stream()
                       .map( x -> x.getPayedAmount() )
                       .reduce( BigDecimal.ZERO , BigDecimal::add )
                       .setScale( Constants.DECIMAL_PLACES ,
                                  Constants.ROUNDING_MODE );
    }

    public BigDecimal rest ()
    {
        return this.isPaid() ? BigDecimal.ZERO
                : this.value.subtract( this.paid() );
    }

    public boolean isPaid ()
    {
        return this.value.compareTo( this.paid() ) != 1;
    }

    public boolean isOverPaid ()
    {
        return this.value.compareTo( this.paid() ) == -1;
    }

    /**
     * @return the number
     */
    public String getNumber ()
    {
        return number;
    }

    /**
     * @return the value
     */
    public BigDecimal getValue ()
    {
        return value;
    }

    /**
     * @return the dateOfInvoice
     */
    public Date getDateOfInvoice ()
    {
        return dateOfInvoice;
    }

    /**
     * @return the payments
     */
    public List< Payment > getPayments ()
    {
        return payments;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString ()
    {
        return String.format( "Invoice %n N %s %n Date: %s %n Value: %s %n Payments: %s" ,
                              this.number ,
                              this.dateOfInvoice ,
                              this.value ,
                              this.payments.toString() );
    }

}
