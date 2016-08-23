package com.szilardolah.mini.rest;


import com.szilardolah.mini.bean.MobileType;
import com.szilardolah.mini.bean.UserDTO;
import com.szilardolah.mini.database.MobileInventory;
import com.szilardolah.mini.exception.LoginException;
import static com.szilardolah.mini.exception.LoginException.YOU_ARE_NOT_LOGGED_IN;
import com.szilardolah.mini.util.Attribute;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */
@Path("mobile/quantity")
@SessionScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MobileInventoryResource implements Serializable{
    
    @Inject
    private MobileInventory inventory;
    
    @GET
    @Path("{mobile}")
    public String getQuantity(@PathParam("mobile") String mobileName) {
        return inventory.getQuantity(inventory.findMobile(mobileName)).toString();
    }
    
    @PUT
    @Path("{mobile}/increase/{increase}")
    public boolean increaseQuantity(@Context HttpServletRequest request, 
            @PathParam("mobile") String mobileName,  @PathParam("increase") Integer quantity) {
        Object userObject = request.getSession().getAttribute(Attribute.USER_DEF_KEY);
        if (userObject != null && userObject instanceof UserDTO) {
            MobileType mobileType = inventory.findMobile(mobileName);
            return inventory.returnMobile(mobileType, quantity);
        }
        throw new LoginException(YOU_ARE_NOT_LOGGED_IN);
    }
    
    @PUT
    @Path("{mobile}/decrease/{decrease}")
    public boolean decreaseQuantity(@Context HttpServletRequest request, 
            @PathParam("mobile") String mobileName, @PathParam("decrease") Integer quantity) {
        Object userObject = request.getSession().getAttribute(Attribute.USER_DEF_KEY);
        if (userObject != null && userObject instanceof UserDTO) {
            MobileType mobileType = inventory.findMobile(mobileName);
            return inventory.reserveMobile(mobileType, quantity);
        }        
       throw new LoginException(YOU_ARE_NOT_LOGGED_IN);
    }
}
