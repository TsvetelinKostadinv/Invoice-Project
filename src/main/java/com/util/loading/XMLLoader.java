/*
 * 29/01/2020 22:04:09
 * ObjectLoader.java created by Tsvetelin
 */
package com.util.loading;


import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;


/**
 * @author Tsvetelin
 *
 */
public interface XMLLoader
{

    @SuppressWarnings ( "unchecked" )
    public static < T > T load ( Class< T > clazz , String path )
    {
        try
        {
            JAXBContext jaxbContext = JAXBContext.newInstance( clazz );

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            T res = ( T ) jaxbUnmarshaller.unmarshal( new File( path ) );

            return res;
        } catch ( Exception e )
        {
            e.printStackTrace();
        }
        return null;
    }
}
