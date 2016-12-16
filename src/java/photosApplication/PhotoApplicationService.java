/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package photosApplication;

import com.sun.xml.ws.developer.SchemaValidation;
import java.math.BigInteger;
import java.util.List;
import javax.jws.WebService;
import namespace.webservice.xsd.AddAlbumRequest;
import namespace.webservice.xsd.AddAlbumResponse;
import namespace.webservice.xsd.AddPhotoRequest;
import namespace.webservice.xsd.AddPhotoResponse;
import namespace.webservice.xsd.AddPhotoToAlbumRequest;
import namespace.webservice.xsd.AddPhotoToAlbumResponse;
import namespace.webservice.xsd.AlbumType;
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
import namespace.webservice.xsd.PhotoType;
import namespace.webservice.xsd.StatusType;

/**
 *
 * @author brenda
 */
@WebService(serviceName = "PhotoalbumService", portName = "PhotoalbumPort", endpointInterface = "namespace.webservice._new.PhotoalbumPortType", targetNamespace = "http://new.webservice.namespace", wsdlLocation = "WEB-INF/wsdl/NewWebServiceFromWSDL/projekti_wsdl.wsdl")
@SchemaValidation
public class PhotoApplicationService {

    Data dataService = new Data();

    public AddPhotoResponse addPhoto(AddPhotoRequest parameter) {
        String responseString = checkTokens(parameter.getAPITOKEN(), parameter.getREQUESTID());
        String key = parameter.getAPITOKEN() + "_" + parameter.getREQUESTID();
        AddPhotoResponse response = new AddPhotoResponse();
        StatusType statusType = new StatusType();
        statusType.setMessage(responseString);
        statusType.setREQUESTID(parameter.getREQUESTID());
        response.setStatusMessage(statusType);
        if (responseString.equals("OK")) {
            PhotoType photo = new PhotoType();
            photo.setAuthor(parameter.getAuthor());
            photo.setDateTaken(parameter.getDateTaken());
            photo.setDescription(parameter.getDescription());
            
            photo.setExtension(parameter.getExtension());
            photo.setPhotoID(dataService.getNewPhotoId());
            photo.setPhotoURL(parameter.getPhotoURL());
            photo.setSize(parameter.getSize());
            photo.setTitle(parameter.getTitle());
            response.setPhoto(photo);
            dataService.addPhoto(photo);
            dataService.addResponseForKey(key, response);
        }
        if (responseString.equals("Incorrect request id!")) {
            Object rp = dataService.getResponseForDuplicateKey(key);
            if (rp instanceof AddPhotoResponse) {
                response = (AddPhotoResponse) rp;
                statusType.setMessage("Duplicate request id! Returning previous response!");
                response.setStatusMessage(statusType);
            }
        }
        return response;
    }

    public AddAlbumResponse addAlbum(AddAlbumRequest parameter) {
        String responseString = checkTokens(parameter.getAPITOKEN(), parameter.getREQUESTID());
        String key = parameter.getAPITOKEN() + "_" + parameter.getREQUESTID();
        AddAlbumResponse response = new AddAlbumResponse();
        StatusType statusType = new StatusType();
        statusType.setMessage(responseString);
        statusType.setREQUESTID(parameter.getREQUESTID());
        response.setStatusMessage(statusType);
        if (responseString.equals("OK")) {
            AlbumType album = new AlbumType();
            album.setAlbumID(dataService.getNewAlbumId());
            album.setTitle(parameter.getTitle());
            album.setDescription(parameter.getDescription());
            album.setOwner(parameter.getOwner());
            album.setDateCreated(dataService.getDateCreated());
            album.setSize(BigInteger.ZERO);
            album.setAlbumURL(dataService.getAlbumURL(album.getAlbumID()));
            response.setAlbum(album);
            dataService.addAlbum(album);
            dataService.addResponseForKey(key, response);
        }
        if (responseString.equals("Incorrect request id!")) {
            Object rp = dataService.getResponseForDuplicateKey(key);
            if (rp instanceof AddAlbumResponse) {
                response = (AddAlbumResponse) rp;
                statusType.setMessage("Duplicate request id! Returning previous response!");
                response.setStatusMessage(statusType);
            }
        }
        return response;
    }

    public GetPhotoResponse getPhoto(GetPhotoRequest parameter) {
        String responseString = checkTokens(parameter.getAPITOKEN(), parameter.getREQUESTID());
        String key = parameter.getAPITOKEN() + "_" + parameter.getREQUESTID();
        GetPhotoResponse response = new GetPhotoResponse();
        StatusType statusType = new StatusType();
        statusType.setMessage(responseString);
        statusType.setREQUESTID(parameter.getREQUESTID());
        response.setStatusMessage(statusType);
        if (responseString.equals("OK")) {
            PhotoType photo = dataService.getPhotoById(parameter.getPhotoID());
            if (photo == null) {
                statusType.setMessage("No photo with this ID exists!");
                response.setStatusMessage(statusType);
            } else {
                response.setPhoto(photo);
            }
            dataService.addResponseForKey(key, response);
        }
        if (responseString.equals("Incorrect request id!")) {
            Object rp = dataService.getResponseForDuplicateKey(key);
            if (rp instanceof GetPhotoResponse) {
                response = (GetPhotoResponse) rp;
                statusType.setMessage("Duplicate request id! Returning previous response!");
                response.setStatusMessage(statusType);
            }
        }
        return response;
    }

    public GetAlbumResponse getAlbum(GetAlbumRequest parameter) {
        String responseString = checkTokens(parameter.getAPITOKEN(), parameter.getREQUESTID());
        String key = parameter.getAPITOKEN() + "_" + parameter.getREQUESTID();
        GetAlbumResponse response = new GetAlbumResponse();
        StatusType statusType = new StatusType();
        statusType.setMessage(responseString);
        statusType.setREQUESTID(parameter.getREQUESTID());
        response.setStatusMessage(statusType);
        if (responseString.equals("OK")) {
            AlbumType album = dataService.getAlbumById(parameter.getAlbumID());
            if (album == null) {
                statusType.setMessage("No album with this ID exists!");
                response.setStatusMessage(statusType);
            } else {
                response.setAlbum(album);
            }
            dataService.addResponseForKey(key, response);
        }
        if (responseString.equals("Incorrect request id!")) {
            Object rp = dataService.getResponseForDuplicateKey(key);
            if (rp instanceof GetAlbumResponse) {
                response = (GetAlbumResponse) rp;
                statusType.setMessage("Duplicate request id! Returning previous response!");
                response.setStatusMessage(statusType);
            }
        }
        return response;
    }

    public GetPhotosWithParametersResponse getPhotosWithParameters(GetPhotosWithParametersRequest parameter) {
        String responseString = checkTokens(parameter.getAPITOKEN(), parameter.getREQUESTID());
        String key = parameter.getAPITOKEN() + "_" + parameter.getREQUESTID();
        GetPhotosWithParametersResponse response = new GetPhotosWithParametersResponse();
        StatusType statusType = new StatusType();
        statusType.setMessage(responseString);
        statusType.setREQUESTID(parameter.getREQUESTID());
        response.setStatusMessage(statusType);
        if (responseString.equals("OK")) {
            List<PhotoType> matches = dataService.getPhotosWithParameters(parameter);
            if (matches.isEmpty()) {
                statusType.setMessage("No photos were found");
                response.setStatusMessage(statusType);
                return response;
            } else {
                matches.forEach(response.getPhoto()::add);
            }
            dataService.addResponseForKey(key, response);
        }
        if (responseString.equals("Incorrect request id!")) {
            Object rp = dataService.getResponseForDuplicateKey(key);
            if (rp instanceof GetPhotosWithParametersResponse) {
                response = (GetPhotosWithParametersResponse) rp;
                statusType.setMessage("Duplicate request id! Returning previous response!");
                response.setStatusMessage(statusType);
            }
        }
        return response;
    }

    public GetAlbumsWithParametersResponse getAlbumsWithParameters(GetAlbumsWithParametersRequest parameter) {
        String responseString = checkTokens(parameter.getAPITOKEN(), parameter.getREQUESTID());
        String key = parameter.getAPITOKEN() + "_" + parameter.getREQUESTID();
        GetAlbumsWithParametersResponse response = new GetAlbumsWithParametersResponse();
        StatusType statusType = new StatusType();
        statusType.setMessage(responseString);
        statusType.setREQUESTID(parameter.getREQUESTID());
        response.setStatusMessage(statusType);
        if (responseString.equals("OK")) {
            List<AlbumType> matches = dataService.getAlbumsWithParameters(parameter);
            if (matches.isEmpty()) {
                statusType.setMessage("No albums were found");
                response.setStatusMessage(statusType);
                return response;
            } else {
                matches.forEach(response.getAlbum()::add);
            }
            dataService.addResponseForKey(key, response);
        }
        if (responseString.equals("Incorrect request id!")) {
            Object rp = dataService.getResponseForDuplicateKey(key);
            if (rp instanceof GetAlbumsWithParametersResponse) {
                response = (GetAlbumsWithParametersResponse) rp;
                statusType.setMessage("Duplicate request id! Returning previous response!");
                response.setStatusMessage(statusType);
            }
        }
        return response;
    }

    public AddPhotoToAlbumResponse addPhotoToAlbum(AddPhotoToAlbumRequest parameter) {
        String responseString = checkTokens(parameter.getAPITOKEN(), parameter.getREQUESTID());
        String key = parameter.getAPITOKEN() + "_" + parameter.getREQUESTID();
        AddPhotoToAlbumResponse response = new AddPhotoToAlbumResponse();
        StatusType statusType = new StatusType();
        statusType.setMessage(responseString);
        statusType.setREQUESTID(parameter.getREQUESTID());
        response.setStatusMessage(statusType);
        if (responseString.equals("OK")) {
            List<AlbumType> addedAlbums = dataService.addPhotosToAlbums(parameter.getPhotoID(), parameter.getAlbumID());
            if (addedAlbums.isEmpty()) {
                statusType.setMessage("No albums with these ids could be found");
                response.setStatusMessage(statusType);
                return response;
            } else {
                addedAlbums.forEach(response.getAlbum()::add);
            }
            dataService.addResponseForKey(key, response);
        }
        if (responseString.equals("Incorrect request id!")) {
           Object rp = dataService.getResponseForDuplicateKey(key);
            if (rp instanceof AddPhotoToAlbumResponse) {
                response = (AddPhotoToAlbumResponse) rp;
                statusType.setMessage("Duplicate request id! Returning previous response!");
                response.setStatusMessage(statusType);
            }
        }
        return response;
    }

    public GetPhotosInAlbumResponse getPhotosInAlbum(GetPhotosInAlbumRequest parameter) {
        String responseString = checkTokens(parameter.getAPITOKEN(), parameter.getREQUESTID());
        String key = parameter.getAPITOKEN() + "_" + parameter.getREQUESTID();
        GetPhotosInAlbumResponse response = new GetPhotosInAlbumResponse();
        StatusType statusType = new StatusType();
        statusType.setMessage(responseString);
        statusType.setREQUESTID(parameter.getREQUESTID());
        response.setStatusMessage(statusType);
        if (responseString.equals("OK")) {
            List<PhotoType> photosInAlbum = dataService.getPhotosInAlbum(parameter.getAlbumID());
            if (photosInAlbum == null) {
                statusType.setMessage("No album with this ID exists!");
                response.setStatusMessage(statusType);
            } else {
                photosInAlbum.forEach(response.getPhoto()::add);
            }
            dataService.addResponseForKey(key, response);
        }
        if (responseString.equals("Incorrect request id!")) {
            Object rp = dataService.getResponseForDuplicateKey(key);
            if (rp instanceof GetPhotosInAlbumResponse) {
                response = (GetPhotosInAlbumResponse) rp;
                statusType.setMessage("Duplicate request id! Returning previous response!");
                response.setStatusMessage(statusType);
            }
        }
        return response;
    }
    
    private String checkTokens(String API_TOKEN, String REQUEST_ID) {        
        String key = API_TOKEN + "_" + REQUEST_ID;
        boolean correctToken = API_TOKEN.equals("abcdefg123456");
        boolean correctId =  dataService.checkForDuplicateKey(key);
        
        if (correctToken && correctId) {
            dataService.addUsedRequestId(REQUEST_ID);
            return "OK";
        } else if (correctToken && !correctId) {
            return "Incorrect request id!";
        } else {
            return "Incorrect API Token!";  
        }
    }
    
}
