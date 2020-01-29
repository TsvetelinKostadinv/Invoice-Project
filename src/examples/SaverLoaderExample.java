import java.math.BigDecimal;

import com.util.date.Date;
import com.util.invoice.Invoice;
import com.util.invoice.Payment;
import com.util.loading.XMLLoader;
import com.util.saving.XMLSaver;

/*
 * 29/01/2020 23:49:41
 * SaverLoaderExample.java created by Tsvetelin
 */

/**
 * @author Tsvetelin
 *
 */
public interface SaverLoaderExample
{
    
    final String path = "examples/saved/invoice.xml";
    
    public static void main ( String [] args ) throws Exception
    {
        final Invoice invoice = new Invoice( "1" ,
                                             new BigDecimal( "123" ) ,
                                             new Date( 1 , 1 , 2001 ) );

        invoice.addPayment( new Payment( new Date() , BigDecimal.ONE ) );
        invoice.addPayment( new Payment( new Date() , BigDecimal.ZERO ) );
        invoice.addPayment( new Payment( new Date() , BigDecimal.ONE ) );
        invoice.addPayment( new Payment( new Date() , BigDecimal.ZERO ) );
        
        XMLSaver.saveObjectToFile( invoice , path );
        
        
        Invoice loaded = XMLLoader.load( Invoice.class , path );
        
        
        System.out.println( "===Start===" );
        System.out.println( invoice );
        
        
        System.out.println( "===Parsed===" );
        System.out.println( loaded );
    }
}
