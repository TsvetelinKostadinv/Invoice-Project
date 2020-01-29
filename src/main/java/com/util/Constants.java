/*
 * 21/01/2020 23:49:35
 * Constants.java created by Tsvetelin
 */
package com.util;


import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;


/**
 * @author Tsvetelin
 *
 */
public abstract class Constants implements Serializable
{

    /**
     * 
     */
    private static final long        serialVersionUID = 1L;

    public static final int          DECIMAL_PLACES = 2;

    public static final RoundingMode ROUNDING_MODE  = RoundingMode.HALF_UP;
    
    
    public static enum BigDecimalNumbers
    {
        ZERO( BigDecimal.ZERO ),
        ONE( BigDecimal.ONE ),
        TWO( BigDecimal.ONE.add(BigDecimal.ONE) ),
        THREE( TWO.getValue().add( ONE.getValue() ) ),
        FOUR( THREE.getValue().add( ONE.getValue() ) ),
        FIVE( FOUR.getValue().add( ONE.getValue() ) ),
        SIX( FIVE.getValue().add( ONE.getValue() ) ),
        SEVEN( SIX.getValue().add( ONE.getValue() ) ),
        EIGHT( SEVEN.getValue().add( ONE.getValue() ) ),
        NINE( EIGHT.getValue().add( ONE.getValue() ) ),
        TEN( NINE.getValue().add( ONE.getValue() ) ),
        ELEVEN( TEN.getValue().add( ONE.getValue() ) ),
        TWELVE( ELEVEN.getValue().add( ONE.getValue() ) ),
        THIRTEEN( TWELVE.getValue().add( ONE.getValue() ) ),
        FOURTEEN( THIRTEEN.getValue().add( ONE.getValue() ) ),
        FIFTEEN( FOURTEEN.getValue().add( ONE.getValue() ) ),
        SIXTEEN( FIFTEEN.getValue().add( ONE.getValue() ) ),
        SEVENTEEN( SIXTEEN.getValue().add( ONE.getValue() ) ),
        EIGHTEEN( SEVENTEEN.getValue().add( ONE.getValue() ) ),
        NINETEEN( EIGHTEEN.getValue().add( ONE.getValue() ) ),
        TWENTY( NINETEEN.getValue().add( ONE.getValue() ) );
        
        private final BigDecimal val;
        
        BigDecimalNumbers( BigDecimal val)
        {
            this.val = val;
        }

        /**
         * @return the val
         */
        public BigDecimal getValue ()
        {
            return val;
        }
    }
    
}
