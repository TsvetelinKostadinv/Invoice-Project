import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import com.util.date.Date;
import com.util.invoice.Invoice;

/*
 * 29/01/2020 23:40:34
 * OrderingTester.java created by Tsvetelin
 */

/**
 * @author Tsvetelin
 *
 */
public interface OrderingExample
{
    
    public static void main ( String [] args )
    {
        Invoice i = new Invoice( "1" , BigDecimal.ONE , new Date() );
        Invoice i2 = new Invoice( "2" , BigDecimal.ZERO , new Date().nextDay() );
        
        List< Invoice > invs = new LinkedList< Invoice >();
        
        invs.add( i );
        invs.add( i2 );
        
        invs.sort( Invoice.SORT_DESCENDING_BY_REMAINING ); // here is the comparator which is doing the work
        
        System.out.println( invs );
    }
}
