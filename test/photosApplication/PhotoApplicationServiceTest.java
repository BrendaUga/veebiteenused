/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package photosApplication;

import java.math.BigInteger;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import namespace.webservice.xsd.AddAlbumRequest;
import namespace.webservice.xsd.AddAlbumResponse;
import namespace.webservice.xsd.AddPhotoRequest;
import namespace.webservice.xsd.AddPhotoResponse;
import namespace.webservice.xsd.AddPhotoToAlbumRequest;
import namespace.webservice.xsd.AddPhotoToAlbumResponse;
import namespace.webservice.xsd.ExtensionType;
import namespace.webservice.xsd.GetAlbumRequest;
import namespace.webservice.xsd.GetAlbumResponse;
import namespace.webservice.xsd.GetAlbumsWithParametersRequest;
import namespace.webservice.xsd.GetAlbumsWithParametersResponse;
import namespace.webservice.xsd.GetPhotoRequest;
import namespace.webservice.xsd.GetPhotoResponse;
import namespace.webservice.xsd.GetPhotosInAlbumRequest;
import namespace.webservice.xsd.GetPhotosInAlbumResponse;
import namespace.webservice.xsd.GetPhotosWithParametersRequest;
import namespace.webservice.xsd.GetPhotosWithParametersResponse;
import org.junit.After;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Brenda
 */
public class PhotoApplicationServiceTest {
    
    @Before
    public void setUp() {
        AddPhotoRequest parameter = new AddPhotoRequest();
        parameter.setAPITOKEN("abcdefg123456");
        parameter.setREQUESTID("1");
        parameter.setAuthor("Brenda");
        parameter.setDescription("kirjeldus");
        parameter.setSize("400x600");
        parameter.setPhotoURL("imgur.com/pilt");
        parameter.setExtension(ExtensionType.jpg);
        Date date = new Date();
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(date);
        try {
            XMLGregorianCalendar date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
            parameter.setDateTaken(date2);
        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
        parameter.setTitle("Pealkiri");
        PhotoApplicationService instance = new PhotoApplicationService();
        AddPhotoResponse result = instance.addPhoto(parameter);
        System.out.println("photoid: " + result.getPhoto().getPhotoID());
        
        AddAlbumRequest parameter1 = new AddAlbumRequest();
        parameter1.setAPITOKEN("abcdefg123456");
        parameter1.setREQUESTID("2");
        parameter1.setOwner("Brenda");
        parameter1.setTitle("Album 1");
        parameter1.setDescription("kirjeldus yo");
        AddAlbumResponse response = instance.addAlbum(parameter1);
        System.out.println("albumid: " + response.getAlbum().getAlbumID());
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addPhoto method, of class PhotoApplicationService.
     */
    @Test
    public void testAddPhoto() {
        System.out.println("-----------------");
        AddPhotoRequest parameter = new AddPhotoRequest();
        parameter.setAPITOKEN("abcdefg123456");
        parameter.setREQUESTID("2");
        parameter.setAuthor("LAlala");
        parameter.setDescription("kirjelddadasus");
        parameter.setSize("1600x600");
        parameter.setPhotoURL("imgur.com/pilt1");
        parameter.setExtension(ExtensionType.gif);
        Date date = new Date();
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(date);
        try {
            XMLGregorianCalendar date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
            parameter.setDateTaken(date2);
        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
        parameter.setTitle("Pealkiri111");
        PhotoApplicationService instance = new PhotoApplicationService();
        AddPhotoResponse result = instance.addPhoto(parameter);
        System.out.println("done second!");
        System.out.println("photoid: " + result.getPhoto().getPhotoID());
        System.out.println("result: " + result.getStatusMessage().getMessage());
    }

    /**
     * Test of addAlbum method, of class PhotoApplicationService.
     */
    @Test
    public void testAddAlbum() {
        System.out.println("addAlbum");
        AddAlbumRequest parameter = null;
        PhotoApplicationService instance = new PhotoApplicationService();
        AddAlbumResponse result = instance.addAlbum(parameter);
        
    }

    /**
     * Test of getPhoto method, of class PhotoApplicationService.
     */
    @Test
    public void testGetPhoto() {
        System.out.println("getPhoto");
        GetPhotoRequest parameter = new GetPhotoRequest();
        parameter.setAPITOKEN("abcdefg123456");
        parameter.setREQUESTID("2");
        parameter.setPhotoID(BigInteger.TEN);
        PhotoApplicationService instance = new PhotoApplicationService();
        GetPhotoResponse result = instance.getPhoto(parameter);
        System.out.println(result.getStatusMessage().getMessage());
        System.out.println(result.getPhoto().getPhotoID());
    }


    /**
     * Test of addPhotoToAlbum method, of class PhotoApplicationService.
     */
    @Test
    public void testAddPhotoToAlbum() {
        System.out.println("addPhotoToAlbum");
        AddPhotoToAlbumRequest parameter = new AddPhotoToAlbumRequest();
        parameter.setAPITOKEN("abcdefg123456");
        parameter.setREQUESTID("3");
        parameter.getAlbumID().add(BigInteger.ONE);
        parameter.getPhotoID().add(BigInteger.ONE);
        PhotoApplicationService instance = new PhotoApplicationService();
        AddPhotoToAlbumResponse result = instance.addPhotoToAlbum(parameter);
        System.out.println("size: " + result.getAlbum().size());
        System.out.println("added id: " + result.getAlbum().get(0).getAlbumID());
        System.out.println(result.getStatusMessage().getMessage());
        
    }


    private void assertEquals(GetAlbumResponse expResult, GetAlbumResponse result) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
