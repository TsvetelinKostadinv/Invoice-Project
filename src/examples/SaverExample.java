import java.math.BigDecimal;
import com.util.date.Date;
import com.util.invoice.Invoice;
import com.util.invoice.Payment;
import com.util.saving.XMLSaver;

/*
 * 29/01/2020 23:13:02
 * SaverExample.java created by Tsvetelin
 */


/**
 * @author Tsvetelin
 *
 */
public interface SaverExample
{

    public static void main ( String [] args )
    {
        final Invoice invoice = new Invoice( "1" ,
                                             new BigDecimal( "123" ) ,
                                             new Date( 1 , 1 , 2001 ) );

        invoice.addPayment( new Payment( new Date() , BigDecimal.ONE ) );
        invoice.addPayment( new Payment( new Date() , BigDecimal.ZERO ) );
        invoice.addPayment( new Payment( new Date() , BigDecimal.ONE ) );
        invoice.addPayment( new Payment( new Date() , BigDecimal.ZERO ) );

        // saveObjectToFile( p , "" );

        XMLSaver.saveObjectToFile( invoice , "examples/saved/invoice.xml" );
    }
}
