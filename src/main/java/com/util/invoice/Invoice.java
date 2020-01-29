/*
 * 21/01/2020 22:57:51
 * Invoice.java created by Tsvetelin
 */
package com.util.invoice;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.util.Constants;
import com.util.date.Date;


/**
 * @author Tsvetelin
 *
 */
public class Invoice implements Serializable
{

    /**
     * 
     */
    private static final long     serialVersionUID = 1L;

    private final String          number;

    private final BigDecimal      value;

    private final Date            dateOfInvoice;

    private final List< Payment > payments;

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
        this.payments = new ArrayList< Payment >();
    }

    public void addPayment ( Payment payment )
    {
        this.payments.add( payment );
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

}
