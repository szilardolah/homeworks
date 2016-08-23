package com.szilardolah.mini.rest;


import com.szilardolah.mini.bean.MobileType;
import com.szilardolah.mini.bean.UserDTO;
import com.szilardolah.mini.database.MobileInventory;
import com.szilardolah.mini.exception.LoginException;
import static com.szilardolah.mini.exception.LoginException.HAVE_NOT_ADMIN_PERMISSION;
import static com.szilardolah.mini.exception.LoginException.YOU_ARE_NOT_LOGGED_IN;
import com.szilardolah.mini.util.Attribute;
import java.io.Serializable;
import java.util.Collection;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
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
@Path("mobile")
@SessionScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MobileTypeResource implements Serializable{

    @Inject
    private MobileInventory inventory;
    
    @POST
    public MobileType add(@Context HttpServletRequest request, MobileType mobileType) {
        Object userObj = request.getSession().getAttribute(Attribute.USER_DEF_KEY);
        if (userObj != null && userObj instanceof UserDTO) {
            if (((UserDTO) userObj).isAdmin()) {            
                return inventory.addNewMobileType(mobileType);
            }
            throw new LoginException(HAVE_NOT_ADMIN_PERMISSION);
        }
        throw new LoginException(YOU_ARE_NOT_LOGGED_IN);
    }
    
    @DELETE
    @Path("{name}")
    public MobileType delete(@Context HttpServletRequest request, @PathParam("name") String mobileType){
        Object userObj = request.getSession().getAttribute(Attribute.USER_DEF_KEY);
        if (userObj != null && userObj instanceof UserDTO) {
            if (((UserDTO) userObj).isAdmin()) {            
                return inventory.deleteMobile(mobileType);
            }
            throw new LoginException(HAVE_NOT_ADMIN_PERMISSION);
        }
        throw new LoginException(YOU_ARE_NOT_LOGGED_IN);
    }
            
    @GET
    @Path("{id}")
    public MobileType get(@PathParam("id") String id) {
        return inventory.getMobile(id);
    }
    
    @GET
    public Collection<MobileType> getAll() {
        return inventory.getMobiles();
    }
}
