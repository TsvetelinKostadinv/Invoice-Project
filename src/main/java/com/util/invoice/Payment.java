/*
 * 21/01/2020 22:59:29
 * Payment.java created by Tsvetelin
 */
package com.util.invoice;


import java.io.Serializable;
import java.math.BigDecimal;

import com.util.Constants;
import com.util.date.Date;


/**
 * @author Tsvetelin
 *
 */
public class Payment implements Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private final Date       dateOfPayment;

    private final BigDecimal payedAmount;

    /**
     * @param dateOfPayment
     * @param payedAmount
     */
    public Payment ( Date dateOfPayment , BigDecimal payedAmount )
    {
        super();
        this.dateOfPayment = dateOfPayment;
        this.payedAmount = payedAmount.setScale( Constants.DECIMAL_PLACES , Constants.ROUNDING_MODE );
    }

    
    /**
     * @return the dateOfPayment
     */
    public Date getDateOfPayment ()
    {
        return dateOfPayment;
    }

    
    /**
     * @return the payedAmount
     */
    public BigDecimal getPayedAmount ()
    {
        return payedAmount;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals ( Object obj )
    {
        return obj instanceof Payment
                && ( ( Payment ) obj ).dateOfPayment.equals( this.dateOfPayment )
                && ( ( Payment ) obj ).payedAmount.equals( this.payedAmount );
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString ()
    {
        return "Payment on " + this.dateOfPayment + ": " + this.payedAmount;
    }
}
