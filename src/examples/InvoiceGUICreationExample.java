import java.util.concurrent.Future;

import com.gui.InvoiceCreatorWindow;
import com.models.invoice.Invoice;

/*
 * 30/01/2020 16:34:19
 * InvoiceGUICreationExample.java created by Tsvetelin
 */

/**
 * @author Tsvetelin
 *
 */
public interface InvoiceGUICreationExample
{
    public static void main ( String [] args ) throws Exception
    {
        Future< Invoice > invoice = InvoiceCreatorWindow.getInvoiceFromUserInput();

        while ( !invoice.isDone() )
        {
            Thread.sleep( 1000 );
            System.out.println( "Waiting" );
        }

        Invoice res = invoice.get();
        System.out.println( "===Result===" );
        System.out.println( res );

    }
}
