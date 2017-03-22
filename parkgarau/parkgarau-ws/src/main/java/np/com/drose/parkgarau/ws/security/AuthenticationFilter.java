/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.ws.security;

import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;
import np.com.drose.parkgarau.api.ParkGarauService;
import np.com.drose.parkgarau.modules.token.Token;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.1.1.1 $
 * @since Build {insert version here} (MM YYYY)
 * @author bibek
 */
@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

    private static final Logger LOG = Logger.getLogger(AuthenticationFilter.class.getName());
    Date date;
    @Inject
    ParkGarauService<Token> tokenParkGarauService;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        String authHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new NotAuthorizedException("Authorization header must be valid");
        }
        String token = authHeader.substring("Bearer".length()).trim();

        boolean valid = validateToken(token);
        if (!valid) {
            LOG.log(Level.INFO, "problem while validating token{0}");
            requestContext.abortWith(javax.ws.rs.core.Response.status(javax.ws.rs.core.Response.Status.UNAUTHORIZED).build());
        }

    }

    private boolean validateToken(String token){
        date = new Date();
        Token tkn = tokenParkGarauService.finder(token);
        if (tkn != null) {
            if (date.compareTo(tkn.getExpiredOn()) <= 0) {
                javax.ws.rs.core.Response.status(javax.ws.rs.core.Response.Status.OK).build();
                return true;
            }
        }
        return false;

    }
}
