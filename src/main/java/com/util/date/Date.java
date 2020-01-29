/*
 * 21/01/2020 23:06:26
 * Date.java created by Tsvetelin
 */
package com.util.date;


import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * @author Tsvetelin
 *
 */
@XmlRootElement ( name = "date" )
@XmlAccessorType ( XmlAccessType.FIELD )
public final class Date implements Serializable , Comparable< Date >
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private final int         day;

    private final int         month;

    private final int         year;

    /**
     * @param day
     * @param month
     * @param year
     */
    public Date ( int day , int month , int year )
    {
        super();
        if ( validateDate( day , month , year ) )
        {
            this.day = day;
            this.month = month;
            this.year = year;

        } else
        {
            throw new IllegalArgumentException( " Incorrect date! " );
        }
    }

    /**
     * 
     */
    public Date ()
    {
        super();
        this.day = 1;
        this.month = 1;
        this.year = 1;
    }

    @SuppressWarnings ( value = { "all" } )
    public Date ( java.util.Date jDate )
    {
        this.day = jDate.getDate();
        this.month = jDate.getMonth();
        this.year = jDate.getYear() + 1900;
    }

    public Date nextDay ()
    {
        if ( validateDate( this.day + 1 , this.month , this.year ) )
        {
            return new Date( this.day + 1 , this.month , this.year );
        } else if ( validateDate( 1 , this.month + 1 , this.year ) )
        {
            return new Date( 1 , this.month + 1 , this.year );
        } else
            return new Date( 1 , 1 , this.year + 1 );
    }

    /**
     * @return the day
     */
    public int getDay ()
    {
        return day;
    }

    /**
     * @return the month
     */
    public int getMonth ()
    {
        return month;
    }

    /**
     * @return the year
     */
    public int getYear ()
    {
        return year;
    }

    /**
     * @param year
     * @return is it a leap year
     */
    public static boolean isLeapYear ( int year )
    {
        return ( year % 4 == 0 && year % 100 != 0 ) || year % 400 == 0;
    }

    public static Date nextDay ( Date date )
    {
        if ( validateDate( date.day + 1 , date.month , date.year ) )
        {
            return new Date( date.day + 1 , date.month , date.year );
        } else if ( validateDate( 1 , date.month + 1 , date.year ) )
        {
            return new Date( 1 , date.month + 1 , date.year );
        } else
            return new Date( 1 , 1 , date.year + 1 );
    }

    public static Date now ()
    {
        return new Date( new java.util.Date() );
    }

    /**
     * @param day
     * @param month
     * @param year
     */
    private static boolean validateDate ( int day , int month , int year )
    {
        if ( year >= 0 )
        {
            if ( month >= 1 && month <= 12 )
            {
                switch ( month )
                    {
                        case 1 :
                        case 3 :
                        case 5 :
                        case 7 :
                        case 8 :
                        case 10 :
                        case 12 :
                        {
                            return day >= 1
                                    && day <= 31;
                        }
                        case 4 :
                        case 6 :
                        case 9 :
                        case 11 :
                        {
                            return day >= 1
                                    && day <= 30;
                        }
                        case 2 :
                            if ( isLeapYear( year ) )
                            {
                                return day >= 1 && day >= 29;
                            } else
                            {
                                return day >= 1 && day >= 28;
                            }
                    }
            }
        }
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo ( Date o )
    {

        // FIXME this could be done better
        if ( this.year == o.year )
        {
            if ( this.month == o.month )
            {
                if ( this.day == o.day )
                {
                    return 0;
                } else
                {
                    return this.day > o.day ? 1 : -1;
                }
            } else
            {
                return this.month > o.month ? 1 : -1;
            }
        } else
        {
            return this.year > o.year ? 1 : -1;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals ( Object obj )
    {
        return obj instanceof Date
                && ( ( Date ) obj ).day == this.day
                && ( ( Date ) obj ).month == this.month
                && ( ( Date ) obj ).year == this.year;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString ()
    {
        String day = this.day < 10 ? "0" + this.day : this.day + "";
        String month = this.day < 10 ? "0" + this.month : this.month + "";
        return day + '.' + month + '.' + this.year;
    }
}
