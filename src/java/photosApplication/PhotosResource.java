/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package photosApplication;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import namespace.webservice.xsd.AddPhotoRequest;
import namespace.webservice.xsd.AddPhotoResponse;
import namespace.webservice.xsd.ExtensionType;
import namespace.webservice.xsd.GetPhotoRequest;
import namespace.webservice.xsd.GetPhotoResponse;
import namespace.webservice.xsd.GetPhotosWithParametersRequest;
import namespace.webservice.xsd.GetPhotosWithParametersResponse;
import namespace.webservice.xsd.PhotoType;

/**
 * REST Web Service
 *
 * @author Brenda
 */
@Path("photos")
public class PhotosResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of PhotosResource
     */
    public PhotosResource() {
    }

    @POST //http://localhost:8080/veebiteenused/webresources/photos
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public AddPhotoResponse addPhoto(PhotoType photo, 
                                @QueryParam ("API_TOKEN") String API_TOKEN, @QueryParam ("REQUEST_ID") String REQUEST_ID) {
        PhotoApplicationService service = new PhotoApplicationService();
        AddPhotoRequest request = new AddPhotoRequest();
        request.setAPITOKEN(API_TOKEN);
        request.setREQUESTID(REQUEST_ID);
        request.setPhotoURL(photo.getPhotoURL());
        request.setTitle(photo.getTitle());
        request.setDescription(photo.getDescription());
        request.setAuthor(photo.getAuthor());
        
        request.setDateTaken(photo.getDateTaken());
        request.setSize(photo.getSize());
        request.setExtension(photo.getExtension());
        return service.addPhoto(request);
    }
    
    @GET // http://localhost:8080/veebiteenused/webresources/photos/1
    @Path("{id :  \\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public GetPhotoResponse getPhoto(@QueryParam ("API_TOKEN") String API_TOKEN, 
            @QueryParam ("REQUEST_ID") String REQUEST_ID, @PathParam ("id") String id ) {
        PhotoApplicationService service = new PhotoApplicationService();
        GetPhotoRequest request = new GetPhotoRequest();
        request.setAPITOKEN(API_TOKEN);
        request.setREQUESTID(REQUEST_ID);
        request.setPhotoID(new BigInteger(id));
        return service.getPhoto(request);
    }
    
    @GET // http://localhost:8080/veebiteenused/webresources/photos
    @Produces(MediaType.APPLICATION_JSON)
    public GetPhotosWithParametersResponse getPhotosWithParameters(@QueryParam ("API_TOKEN") String API_TOKEN, 
            @QueryParam ("REQUEST_ID") String REQUEST_ID, @QueryParam ("author") String author, @QueryParam ("title") String title,
            @QueryParam ("extension") String extension, @QueryParam ("size") String size ) {
        PhotoApplicationService service = new PhotoApplicationService();
        GetPhotosWithParametersRequest request = new GetPhotosWithParametersRequest();
        request.setAPITOKEN(API_TOKEN);
        request.setREQUESTID(REQUEST_ID);
        if (author!= null ) request.setAuthor(author);
        if (title != null) request.setTitle(title);
        if (size != null) request.setSize(size);
        if (extension != null) request.setExtension(ExtensionType.fromValue(extension));
       
        return service.getPhotosWithParameters(request);
    }
   
  
    
}
