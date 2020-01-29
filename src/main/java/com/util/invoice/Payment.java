/*
 * 21/01/2020 22:59:29
 * Payment.java created by Tsvetelin
 */
package com.util.invoice;


import java.io.Serializable;
import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.util.Constants;
import com.util.date.Date;


/**
 * @author Tsvetelin
 *
 */
@XmlRootElement ( name = "payment" )
//@XmlAccessorType(XmlAccessType.FIELD)
public class Payment implements Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private final Date        dateOfPayment;

    private final BigDecimal  payedAmount;

    /**
     * @param dateOfPayment
     * @param payedAmount
     */
    public Payment ( Date dateOfPayment , BigDecimal payedAmount )
    {
        super();
        this.dateOfPayment = dateOfPayment;
        this.payedAmount = payedAmount.setScale( Constants.DECIMAL_PLACES ,
                                                 Constants.ROUNDING_MODE );
    }

    /**
     * 
     */
    public Payment ()
    {
        super();
        this.dateOfPayment = new Date();
        this.payedAmount = BigDecimal.ZERO;
    }

    /**
     * @return the dateOfPayment
     */
    @XmlElement
    public Date getDateOfPayment ()
    {
        return dateOfPayment;
    }

    /**
     * @return the payedAmount
     */
    @XmlElement
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
