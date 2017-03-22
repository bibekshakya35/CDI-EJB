/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.payroll.services.security;

import java.io.IOException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author bibekshakya
 */
@Provider
public class LoginFIlter implements ContainerRequestFilter,ContainerResponseFilter{

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        System.out.println("Header");
        System.out.println("Headers "+requestContext.getHeaders());
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        System.out.println("");
        System.out.println("Response filter");
        System.out.println(responseContext.getHeaders());
    }
    
        
    
}
