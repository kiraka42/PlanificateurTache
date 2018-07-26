package com.diderot.projetGLA.jetty_jersey;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.Principal;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.DatatypeConverter;

@Provider
@PreMatching
public class SecurityFilter implements ContainerRequestFilter {

    @Inject
    javax.inject.Provider<UriInfo> uriInfo;

    @Override
    public void filter(ContainerRequestContext filterContext) throws IOException {
        User user = authenticate(filterContext);
        filterContext.setSecurityContext(new Authorizer(user));
    }

    private User authenticate(ContainerRequestContext filterContext) {
        String authentication = filterContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        if (authentication == null) {
        	throw new WebApplicationException(401);
        }
        if (!authentication.startsWith("Basic ")) {
            return null;
        }
        authentication = authentication.substring("Basic ".length());
        String[] values = new String(DatatypeConverter.parseBase64Binary(authentication), Charset.forName("ASCII")).split(":");
        if (values.length < 2) {
            throw new WebApplicationException(400);
        }
        String username = values[0];
        String password = values[1];
        if ((username == null) || (password == null)) {
            throw new WebApplicationException(400);
        }

        User user;

        if ((username.equals("mro") && password.equals("mro")) || (username.equals("mcc") && password.equals("mcc"))) {
          //if ()
        	user = new User(username, username);
        } else {
            throw new WebApplicationException(401);
        }
        return user;
    }

    public class Authorizer implements SecurityContext {

        private User user;
        private Principal principal;

        public Authorizer(final User user) {
            this.user = user;
            this.principal = new Principal() {

                public String getName() {
                    return user.username;
                }
            };
        }

        public Principal getUserPrincipal() {
            return this.principal;
        }

        public boolean isUserInRole(String role) {
            return (role.equals(user.role));
        }

        public boolean isSecure() {
            return "https".equals(uriInfo.get().getRequestUri().getScheme());
        }

        public String getAuthenticationScheme() {
            return SecurityContext.BASIC_AUTH;
        }
    }

    public class User {

        public String username;
        public String role;

        public User(String username, String role) {
            this.username = username;
            this.role = role;
        }
    }
}