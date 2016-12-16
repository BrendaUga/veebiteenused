/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package photosApplication;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import namespace.webservice.xsd.AlbumType;
import namespace.webservice.xsd.GetAlbumsWithParametersRequest;
import namespace.webservice.xsd.GetPhotosWithParametersRequest;
import namespace.webservice.xsd.PhotoType;

/**
 *
 * @author brenda
 */
public class Data {
    
    public static List<String> requestIds = new ArrayList<>();
    public static BigInteger photoId = BigInteger.ZERO; 
    public static BigInteger albumId = BigInteger.ZERO; 
    public static List<PhotoType> photos = new ArrayList<>();
    public static List<AlbumType> albums = new ArrayList<>();
    public static HashMap<String, Object> requests = new HashMap<>();
    
    protected void addUsedRequestId(String usedRequestId) {
        requestIds.add(usedRequestId);
    }

    protected BigInteger getNewPhotoId() {
        photoId = photoId.add(BigInteger.ONE);
        return photoId;
    }

    protected BigInteger getNewAlbumId() {
        albumId = albumId.add(BigInteger.ONE);
        return albumId;
    }
    
    protected boolean checkForDuplicateKey(String key) {
        return !requests.containsKey(key);
    }
    
    protected Object getResponseForDuplicateKey(String key) {
        return requests.get(key);
    }
    
    protected void addResponseForKey(String key, Object response) {
        requests.put(key, response);
    }

    protected XMLGregorianCalendar getDateCreated() {
        Date date = new Date();
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(date);
        try {
            XMLGregorianCalendar date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
            return date2;
        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    protected String getAlbumURL(BigInteger albumID) {
        String url = "http://photoapp.io/albums/" + albumID;
        return url;
    }
    
    protected PhotoType getPhotoById(BigInteger id) {
        Optional<PhotoType> photo = photos.stream().filter(p -> p.getPhotoID().equals(id)).findFirst();
        if (photo.isPresent()) {
            return photo.get();
        } else {
            return null;
        }
    }

    public void addPhoto(PhotoType photo) {
        photos.add(photo);
    }
    
    protected AlbumType getAlbumById(BigInteger id) {
        Optional<AlbumType> album = albums.stream().filter(p -> p.getAlbumID().equals(id)).findFirst();
        if (album.isPresent()) {
            return album.get();
        } else {
            return null;
        }
    }

    public void addAlbum(AlbumType album) {
        albums.add(album);
    }

    List<PhotoType> getPhotosWithParameters(GetPhotosWithParametersRequest parameter) {
        List<PhotoType> matches = photos;
        if (parameter.getAuthor() != null) {
            matches = photos.stream().filter(p -> p.getAuthor().equals(parameter.getAuthor())).collect(Collectors.toList());
        }
        if (parameter.getExtension() != null) {
            matches = photos.stream().filter(p -> p.getExtension() == parameter.getExtension()).collect(Collectors.toList());
        }
        if (parameter.getSize() != null) {
            matches = photos.stream().filter(p -> p.getSize().equals(parameter.getSize())).collect(Collectors.toList());
        }
        if (parameter.getTitle() != null) {
            matches = photos.stream().filter(p -> p.getTitle().equals(parameter.getTitle())).collect(Collectors.toList());
        }
        return matches;
    }

    List<AlbumType> getAlbumsWithParameters(GetAlbumsWithParametersRequest parameter) {
        List<AlbumType> matches = albums;
        if (parameter.getOwner() != null) {
            matches = albums.stream().filter(p -> p.getOwner().equals(parameter.getOwner())).collect(Collectors.toList());
        }
        if (parameter.getSize() != null) {
            matches = albums.stream().filter(p -> p.getSize().equals(parameter.getSize())).collect(Collectors.toList());
        }
        if (parameter.getTitle() != null) {
            matches = albums.stream().filter(p -> p.getTitle().equals(parameter.getTitle())).collect(Collectors.toList());
        }
        return matches;
    }

    List<AlbumType> addPhotosToAlbums(List<BigInteger> photoID, List<BigInteger> albumID) {
        List<AlbumType> allAlbums = new ArrayList<>(albums);
        List<PhotoType> allPhotos = new ArrayList<>(photos);
        allAlbums = allAlbums.stream().filter(a -> albumID.contains(a.getAlbumID())).collect(Collectors.toList());
        allPhotos = allPhotos.stream().filter(p -> photoID.contains(p.getPhotoID())).collect(Collectors.toList());
       
        for (AlbumType album : allAlbums) {
            for (PhotoType photo : allPhotos) {
                album.getPhoto().add(photo);
                BigInteger newSize = album.getSize().add(BigInteger.ONE);
                album.setSize(newSize);
            }
        }
        return allAlbums;
    }

    List<PhotoType> getPhotosInAlbum(BigInteger albumID) {
        Optional<AlbumType> album = albums.stream().filter(a -> a.getAlbumID().equals(albumID)).findFirst();
        if (album.isPresent()) {
            return album.get().getPhoto();
        } else {
            return null;
        }
        
    }
}
