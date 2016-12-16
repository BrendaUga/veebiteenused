/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package photosApplication;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import namespace.webservice.xsd.AddPhotoResponse;
import namespace.webservice.xsd.ExtensionType;
import namespace.webservice.xsd.GetPhotoResponse;
import namespace.webservice.xsd.GetPhotosWithParametersResponse;
import namespace.webservice.xsd.PhotoType;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Joosep
 */
public class PhotosResourceTest {
    
    public PhotosResourceTest() {
    }

    @Before
    public void setUp() {
        PhotoType photo = new PhotoType();
        photo.setAuthor("Brenda");
        photo.setDescription("kirjeldus");
        photo.setSize("400x600");
        photo.setPhotoURL("imgur.com/pilt");
        photo.setExtension(ExtensionType.jpg);
        Date date = new Date();
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(date);
        try {
            XMLGregorianCalendar date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
            photo.setDateTaken(date2);
        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
        photo.setTitle("Pealkiri");
        String API_TOKEN = "abcdefg123456";
        String REQUEST_ID = "1";
        PhotosResource instance = new PhotosResource();
        AddPhotoResponse result = instance.addPhoto(photo, API_TOKEN, REQUEST_ID);
    }
    /**
     * Test of getPhoto method, of class PhotosResource.
     */
    @Test
    public void testGetPhoto() {
        System.out.println("getPhoto");
        String API_TOKEN = "abcdefg123456";
        String REQUEST_ID = "2";
        String id = "1";
        PhotosResource instance = new PhotosResource();
        GetPhotoResponse result = instance.getPhoto(API_TOKEN, REQUEST_ID, id);
        System.out.println("timestamp: " + result.getPhoto().getDateTaken().toString());
    }

    /**
     * Test of getPhotosWithParameters method, of class PhotosResource.
     */
    @Test
    public void testGetPhotosWithParameters() {
        System.out.println("getPhotosWithParameters");
        String API_TOKEN = "";
        String REQUEST_ID = "";
        String author = "";
        String title = "";
        String extension = "";
        String size = "";
        String dateTaken = "";
        PhotosResource instance = new PhotosResource();
        GetPhotosWithParametersResponse expResult = null;
        GetPhotosWithParametersResponse result = instance.getPhotosWithParameters(API_TOKEN, REQUEST_ID, author, title, extension, size);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addPhoto method, of class PhotosResource.
     */
    @Test
    public void testAddPhoto() {
        System.out.println("addPhoto");
        PhotoType photo = null;
        String API_TOKEN = "";
        String REQUEST_ID = "";
        PhotosResource instance = new PhotosResource();
        AddPhotoResponse expResult = null;
        AddPhotoResponse result = instance.addPhoto(photo, API_TOKEN, REQUEST_ID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
