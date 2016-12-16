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
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import namespace.webservice.xsd.AddAlbumRequest;
import namespace.webservice.xsd.AddAlbumResponse;
import namespace.webservice.xsd.AddPhotoRequest;
import namespace.webservice.xsd.AddPhotoResponse;
import namespace.webservice.xsd.AddPhotoToAlbumRequest;
import namespace.webservice.xsd.AddPhotoToAlbumResponse;
import namespace.webservice.xsd.AlbumType;
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
import namespace.webservice.xsd.PhotoType;

/**
 * REST Web Service
 *
 * @author Brenda
 */
@Path("albums")
public class AlbumsResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AlbumsResource
     */
    public AlbumsResource() {
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public AddAlbumResponse addAlbum(AlbumType album, 
                                @QueryParam ("API_TOKEN") String API_TOKEN, @QueryParam ("REQUEST_ID") String REQUEST_ID) {
        PhotoApplicationService service = new PhotoApplicationService();
        AddAlbumRequest request = new AddAlbumRequest();
        request.setAPITOKEN(API_TOKEN);
        request.setREQUESTID(REQUEST_ID);
        request.setTitle(album.getTitle());
        request.setDescription(album.getDescription());
        request.setOwner(album.getOwner());
        return service.addAlbum(request);
    }
     
    @GET
    @Path("{id :  \\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public GetAlbumResponse getAlbum(@QueryParam ("API_TOKEN") String API_TOKEN, 
            @QueryParam ("REQUEST_ID") String REQUEST_ID, @PathParam ("id") String id ) {
        PhotoApplicationService service = new PhotoApplicationService();
        GetAlbumRequest request = new GetAlbumRequest();
        request.setAPITOKEN(API_TOKEN);
        request.setREQUESTID(REQUEST_ID);
        request.setAlbumID(new BigInteger(id));
        return service.getAlbum(request);
    }
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public GetAlbumsWithParametersResponse getAlbumsWithParameters(@QueryParam ("API_TOKEN") String API_TOKEN, 
            @QueryParam ("REQUEST_ID") String REQUEST_ID, @QueryParam ("owner") String owner, @QueryParam ("title") String title,
            @QueryParam ("size") String size) {
        
        PhotoApplicationService service = new PhotoApplicationService();
        GetAlbumsWithParametersRequest request = new GetAlbumsWithParametersRequest();
        request.setAPITOKEN(API_TOKEN);
        request.setREQUESTID(REQUEST_ID);
        if (owner != null ) request.setOwner(owner);
        if (title != null ) request.setTitle(title);
        if (size != null ) request.setSize(new BigInteger(size));

        return service.getAlbumsWithParameters(request);
    }
   
    
    @POST
    @Path("{id : \\d+}/photos") 
    @Consumes("application/json")
    @Produces("application/json")
    public AddPhotoToAlbumResponse addPhotoToAlbum(AddPhotoToAlbumRequest content, 
                                @QueryParam("API_TOKEN") String API_TOKEN, @QueryParam ("REQUEST_ID") String REQUEST_ID,
                                @PathParam("id") String id) {
        PhotoApplicationService service = new PhotoApplicationService();
        AddPhotoToAlbumRequest request = new AddPhotoToAlbumRequest();
        request.setAPITOKEN(API_TOKEN);
        request.setREQUESTID(REQUEST_ID);
        System.out.println("photos: " + content.getPhotoID().toString());
        System.out.println("albums: " + content.getAlbumID().toString());
        for (BigInteger pid : content.getPhotoID()) {
            request.getPhotoID().add(pid);
        }
        for (BigInteger aid : content.getAlbumID()) {
            request.getAlbumID().add(aid);
        }
        return service.addPhotoToAlbum(request);    
    }
    
    @GET
    @Path("{id : \\d+}/photos") //support digit only
    @Produces("application/json")
    public GetPhotosInAlbumResponse getPhotosInAlbum( 
                                @QueryParam("API_TOKEN") String API_TOKEN, @QueryParam ("REQUEST_ID") String REQUEST_ID, @PathParam("id") String id) {
        PhotoApplicationService service = new PhotoApplicationService();
        GetPhotosInAlbumRequest request = new GetPhotosInAlbumRequest();
        request.setAPITOKEN(API_TOKEN);
        request.setREQUESTID(REQUEST_ID);
        request.setAlbumID(new BigInteger(id));
        return service.getPhotosInAlbum(request); 
    }
    
}
