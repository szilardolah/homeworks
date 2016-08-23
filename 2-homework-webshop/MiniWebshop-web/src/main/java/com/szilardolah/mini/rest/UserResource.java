package com.szilardolah.mini.rest;

import com.szilardolah.mini.bean.UserDTO;
import com.szilardolah.mini.database.UserDB;
import com.szilardolah.mini.exception.LoginException;
import static com.szilardolah.mini.exception.LoginException.HAVE_NOT_ADMIN_PERMISSION;
import static com.szilardolah.mini.exception.LoginException.YOU_ARE_NOT_LOGGED_IN;
import com.szilardolah.mini.util.Attribute;
import java.io.Serializable;
import java.util.Collection;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */
@Path("user")
@SessionScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource implements Serializable {
  
    @Inject
    private UserDB userDB;    
    
    
    @POST
    public UserDTO add(@Context HttpServletRequest request, UserDTO user) {
        Object userObj = request.getSession().getAttribute(Attribute.USER_DEF_KEY);
        if (userObj != null && userObj instanceof UserDTO) {
            if (((UserDTO) userObj).isAdmin()) {            
                return userDB.registrate(user);
            }
            throw new LoginException(HAVE_NOT_ADMIN_PERMISSION);
        }
        throw new LoginException(YOU_ARE_NOT_LOGGED_IN);
    }
   
    @DELETE
    @Path("{username}")
    public UserDTO deleteUser(@Context HttpServletRequest request, @PathParam("username") String username) {
        Object userObj = request.getSession().getAttribute(Attribute.USER_DEF_KEY);
        if (userObj != null && userObj instanceof UserDTO) {
            if (((UserDTO) userObj).isAdmin()) {            
                return userDB.remove(username);
            }
            throw new LoginException(HAVE_NOT_ADMIN_PERMISSION);
        }
        throw new LoginException(YOU_ARE_NOT_LOGGED_IN);
    }
    
    @GET
    @Path("{username}")
    public UserDTO getUser(@PathParam("username") String name) {
        return userDB.getUser(name);
    }
    
    @GET
    public Collection<UserDTO> getAll() {
        return userDB.getUsers();
    }
    
    @POST
    @Path("login")
    public UserDTO login(@Context HttpServletRequest request, UserDTO user) {
        HttpSession session = request.getSession(true);
        if (userDB.authenticate(user.getUsername(), user.getPassword())) {
                session.setMaxInactiveInterval(2000);
                session.setAttribute(Attribute.USER_DEF_KEY, user);
                return user;
        }
        session.invalidate();
        throw new LoginException(YOU_ARE_NOT_LOGGED_IN);
    }
}
