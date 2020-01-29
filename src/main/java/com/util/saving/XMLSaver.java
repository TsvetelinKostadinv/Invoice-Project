/*
 * 29/01/2020 22:04:18
 * ObjectSaver.java created by Tsvetelin
 */
package com.util.saving;


import java.io.File;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import com.tsvetelin.file_management.creation.IFileCreator;


/**
 * @author Tsvetelin
 *
 */
public interface XMLSaver
{
    // TODO Optimize the saving by adding a hash map with the JAXBContexts

    public static void saveObjectToFile ( final Object obj , final String path )
    {
        try
        {
            // Create JAXB Context
            JAXBContext jaxbContext = JAXBContext.newInstance( obj.getClass() );
            // Create Marshaller
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // Required formatting??
            jaxbMarshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT ,
                                        true );

            // Store XML to File
            File file = IFileCreator.createFile( path );
            

            // Writes XML file to file-system
            jaxbMarshaller.marshal( obj , file );

        } catch ( Exception e )
        {
            e.printStackTrace();
        }
    }

    /**
     * 
     */
    public static void printXML ( Object obj ) throws Exception
    {
        // FOR DEBUGGING ONLY!!!!!!

        // Create JAXB Context
        JAXBContext jaxbContext = JAXBContext.newInstance( obj.getClass() );

        // Create Marshaller
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        // Required formatting??
        jaxbMarshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT ,
                                    true );
        // Print XML String to Console
        StringWriter sw = new StringWriter();

        // Write XML to StringWriter
        jaxbMarshaller.marshal( obj , sw );

        // Verify XML Content
        String xmlContent = sw.toString();
        System.out.println( xmlContent );
    }
}
