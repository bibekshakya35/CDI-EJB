/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.payroll.services.security;

import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import np.com.drose.data.modules.user.domain.User;



/**
 *
 * @author bibekshakya
 */
@Provider
public class SecurityFilter implements ContainerRequestFilter {

    //first step is to check THe AUTHORIZATION content that lies in http headercontent
    private static final String AUTHORIZATION_HEADER_KEY = "Authorization";
    //and it alwasy come with Basic username:password so need to seperate
    private static final String BASIC = "Basic ";
    //after that look for url path that shoul contains secured resoure url 
    private static final String SECURED_URL_PRFIX = "secured";

    @Inject
    LoginService login;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        //chec if the headerr content secured
        if (requestContext.getUriInfo().getPath().contains(SECURED_URL_PRFIX)) {
            //list of the value of the key authorization
            List<String> authHeader = requestContext.getHeaders().get(AUTHORIZATION_HEADER_KEY);
            //check wheather list has value more that 0
            if (authHeader != null && authHeader.size() > 0) {
                //get first li
                String authoToken = authHeader.get(0);
                authoToken = authoToken.replaceFirst(BASIC, "");
                byte[] decodeByte =Base64.getDecoder().decode(authoToken);
                String decodeString= new String(decodeByte);
                StringTokenizer tokenizer = new StringTokenizer(decodeString, ":");
                String username = tokenizer.nextToken();
                String password = tokenizer.nextToken();
               
                try {
                    Optional<Object> objectUser = Optional.ofNullable(login.getAuthentication(username, password));
                    if (objectUser.isPresent()) {
                           Response.status(Response.Status.ACCEPTED).entity((User)objectUser.get()).build();
                           return;
                    }
                } catch (Exception ex) {
                    Logger.getLogger(SecurityFilter.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            Response unauthorizedStatus = Response.status(Response.Status.UNAUTHORIZED)
                    .entity("user cannot access resources")
                    .build();
            requestContext.abortWith(unauthorizedStatus);
        }
    }

}
