package com.szilardolah.mini.rest;

import com.szilardolah.mini.bean.Cart;
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
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */
@Path("cart")
@SessionScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CartResource implements Serializable{

    @Inject
    private Cart cart;
    
    @Inject
    private MobileInventory inventory;
    
    @POST
    @Path("add")
    public boolean add(@Context HttpServletRequest request, MobileType mobileType) {
        Object userObject = request.getSession().getAttribute(Attribute.USER_DEF_KEY);
        if (userObject != null && userObject instanceof UserDTO) {
            return cart.addPhone(mobileType, 1);
        }
        throw new LoginException(YOU_ARE_NOT_LOGGED_IN);
    }
    
    @DELETE
    @Path("delete/{mobiletype}")
    public boolean delete(@Context HttpServletRequest request, MobileType mobileType) {
        Object userObject = request.getSession().getAttribute(Attribute.USER_DEF_KEY);
        if (userObject != null && userObject instanceof UserDTO) {
            return cart.deletePhone(mobileType, 1);
        }
        throw new LoginException(YOU_ARE_NOT_LOGGED_IN);
    }
    
    @POST
    @Path("order")
    public Response order(@Context HttpServletRequest request) {
        Object userObject = request.getSession().getAttribute(Attribute.USER_DEF_KEY);
        if (userObject != null && userObject instanceof UserDTO) {
            cart.orderCart();
            return Response.ok().entity("Order list sent.").build();
        }
        throw new LoginException(YOU_ARE_NOT_LOGGED_IN);
    }
}
