/*
 * 30/01/2020 16:39:15
 * InvoiceDispatcher.java created by Tsvetelin
 */
package com.dispatcher;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.models.invoice.Invoice;
import com.util.Constants;
import com.util.loading.XMLLoader;
import com.util.saving.XMLSaver;

/**
 * @author Tsvetelin
 *
 */
public interface InvoiceDispatcher
{

    /**
     * @param invoice
     */
    public static void dispatchForSaving ( Invoice invoice )
    {
        System.out.println( "Saving..." );
        XMLSaver.saveObjectToFile(
                                  invoice , 
                                  Constants.DEFAULT_PATH + "/" + invoice.getNumber() +".xml" );
    }

    /**
     * @return
     */
    public static Invoice [] getInvoices ()
    {
        File directory = Constants.DEFAULT_FILE;
        
        File[] internalFiles = directory.listFiles();
        
        List< Invoice > loaded = new LinkedList< Invoice >();
        
        for( File save : internalFiles )
        {
            try
            {
                loaded.add( XMLLoader.load( Invoice.class , save.getCanonicalPath() ) );
            } catch ( IOException e )
            {
                e.printStackTrace();
            }
        }
        
        
        return loaded.toArray( new Invoice[loaded.size()] );
    }

}
