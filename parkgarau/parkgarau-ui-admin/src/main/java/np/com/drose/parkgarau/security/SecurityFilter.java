/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.parkgarau.security;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author bibek
 */
@WebFilter(filterName = "SecurityFilter", urlPatterns = {"/park/*"})
public class SecurityFilter implements Filter {

    private static final Logger LOG = Logger.getLogger(SecurityFilter.class.getName());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        LOG.info("checking user authentication");
        Optional<Object> optional = Optional.ofNullable(req.getSession().getAttribute(Identity.SESSION_KEY));
        if (!optional.isPresent()) {

            LOG.info("redirecting user to login");
            HttpServletResponse resp = (HttpServletResponse) response;

            resp.sendRedirect(req.getContextPath() + "/logout");
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

}
