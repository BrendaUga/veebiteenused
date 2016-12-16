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
import namespace.webservice.xsd.AddAlbumResponse;
import namespace.webservice.xsd.AddPhotoResponse;
import namespace.webservice.xsd.AddPhotoToAlbumRequest;
import namespace.webservice.xsd.AddPhotoToAlbumResponse;
import namespace.webservice.xsd.AlbumType;
import namespace.webservice.xsd.ExtensionType;
import namespace.webservice.xsd.GetAlbumResponse;
import namespace.webservice.xsd.GetAlbumsWithParametersResponse;
import namespace.webservice.xsd.GetPhotosInAlbumResponse;
import namespace.webservice.xsd.PhotoType;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Brenda
 */
public class AlbumsResourceTest {
    
    public AlbumsResourceTest() {
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
        
        AlbumType album = new AlbumType();
        album.setAlbumID(BigInteger.ONE);
        album.setAlbumURL("photoapp.io/album/1");
        try {
            XMLGregorianCalendar date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
            album.setDateCreated(date2);
        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
        album.setDescription("kirjeldus");
        album.setOwner("Brenda");
        album.setSize(BigInteger.ZERO);
        album.setTitle("Pealkiri");
        AlbumsResource instance1 = new AlbumsResource();
        AddAlbumResponse result1 = instance1.addAlbum(album, API_TOKEN, REQUEST_ID+1);
    }

    /**
     * Test of addAlbum method, of class AlbumsResource.
     */
    @Test
    public void testAddAlbum() {
        System.out.println("addAlbum");
        AlbumType album = null;
        String API_TOKEN = "";
        String REQUEST_ID = "";
        AlbumsResource instance = new AlbumsResource();
        AddAlbumResponse expResult = null;
        AddAlbumResponse result = instance.addAlbum(album, API_TOKEN, REQUEST_ID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAlbum method, of class AlbumsResource.
     */
    @Test
    public void testGetAlbum() {
        System.out.println("getAlbum");
        String API_TOKEN = "";
        String REQUEST_ID = "";
        String id = "";
        AlbumsResource instance = new AlbumsResource();
        GetAlbumResponse expResult = null;
        GetAlbumResponse result = instance.getAlbum(API_TOKEN, REQUEST_ID, id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAlbumsWithParameters method, of class AlbumsResource.
     */
    @Test
    public void testGetAlbumsWithParameters() {
        System.out.println("getAlbumsWithParameters");
        String API_TOKEN = "";
        String REQUEST_ID = "";
        String owner = "";
        String title = "";
        String size = "";
        String dateCreated = "";
        AlbumsResource instance = new AlbumsResource();
        GetAlbumsWithParametersResponse expResult = null;
        GetAlbumsWithParametersResponse result = instance.getAlbumsWithParameters(API_TOKEN, REQUEST_ID, owner, title, size);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addPhotoToAlbum method, of class AlbumsResource.
     */
    @Test
    public void testAddPhotoToAlbum() {
        System.out.println("addPhotoToAlbum");
        AddPhotoToAlbumRequest content = new AddPhotoToAlbumRequest();
        String API_TOKEN = "abcdefg123456";
        String REQUEST_ID = "3";
        String id = "1";
        content.getAlbumID().add(BigInteger.ONE);
        content.getAlbumID().add(new BigInteger("2"));
        content.getPhotoID().add(BigInteger.ONE);
        content.getPhotoID().add(new BigInteger("2"));
        AlbumsResource instance = new AlbumsResource();
        AddPhotoToAlbumResponse result = instance.addPhotoToAlbum(content, API_TOKEN, REQUEST_ID, id);
        System.out.println(result.getStatusMessage().getMessage());
        System.out.println(result.getAlbum().get(0).getAlbumID());
        System.out.println(result.getAlbum().get(1).getAlbumID());
    }

    /**
     * Test of getPhotosInAlbum method, of class AlbumsResource.
     */
    @Test
    public void testGetPhotosInAlbum() {
        System.out.println("getPhotosInAlbum");
        String API_TOKEN = "";
        String REQUEST_ID = "";
        String id = "";
        AlbumsResource instance = new AlbumsResource();
        GetPhotosInAlbumResponse expResult = null;
        GetPhotosInAlbumResponse result = instance.getPhotosInAlbum(API_TOKEN, REQUEST_ID, id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
