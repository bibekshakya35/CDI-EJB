/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.payroll.services.resource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.MimetypesFileTypeMap;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author bibekshakya
 */
@Path("secured")
@RequestScoped
public class ImageResource {

    private static final Logger LOG = Logger.getLogger(ImageResource.class.getName());

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ImageResource
     */
    public ImageResource() {
    }

    /**
     * Retrieves representation of an instance of
     * np.com.drose.payroll.services.ImageResource
     *
     * @param image resource URI parameter
     * @return an instance of java.lang.String
     */
    @GET
    @Path("/image/{image}")
    @Produces("image/*")
    public Response getImage(@PathParam("image") String image) {
        File file = new File("/opt/image/" + image);
        String mimeType = new MimetypesFileTypeMap().getContentType(file);
        return Response.ok(file, mimeType).build();
    }

    @POST
    @Path("/image/upload")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.WILDCARD)
    public Response getimage(@FormParam("image") String image)
    { 
        String name = null;
        try {
            byte[] decodeImage = org.jboss.resteasy.util.Base64.decode(image);
            File fileDir = new File("C:/payroll/image");
            File fileTemp = File.createTempFile("employee", ".jpg",fileDir);
            
            for(String filename : fileTemp.toString().split("/",4)){
                name = filename;
            }
            
            OutputStream outputStream = new FileOutputStream(fileTemp);
            outputStream.write(decodeImage);
            return Response.ok().status(200).entity(name).build();
        } catch (Exception e) {
            LOG.log(Level.INFO, "error uploading image: {0}", e.getMessage());
            return null;

        }

    }

}
