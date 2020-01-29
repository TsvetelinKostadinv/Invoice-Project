/*
 * 21/01/2020 23:49:35
 * Constants.java created by Tsvetelin
 */
package com.util;


import java.io.Serializable;
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
    
    
}
