package com.szilardolah.amusementpark.resource;

import com.szilardolah.amusementpark.entity.Guest;
import com.szilardolah.amusementpark.service.GuestService;
import java.io.Serializable;
import java.util.Collection;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */
@Path("guest")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class GuestResource implements Serializable{

    @Inject
    private GuestService guestService;

    @POST
    public Response add(Guest entity) {
        return Response.ok(guestService.createGuest(entity)).build();
    }
    
    @PUT
    @Path("{id}")
    public Response modifiy(@PathParam("id") Long id, Guest entity) {
        entity.setId(id);
        return Response.ok(guestService.modifyGuest(entity)).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteById(@PathParam("id") Long id) {
        return Response.ok(guestService.deleteGuest(id)).build();
    }
    
    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") Long id) {
        return Response.ok(guestService.get(id)).build();
    }
    
    @GET
    public Collection<Guest> getAll() {
        return guestService.values();
    }
}
