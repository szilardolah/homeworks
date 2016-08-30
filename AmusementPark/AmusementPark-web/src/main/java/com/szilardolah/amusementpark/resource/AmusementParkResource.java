package com.szilardolah.amusementpark.resource;

import com.szilardolah.amusementpark.entity.AmusementPark;
import com.szilardolah.amusementpark.entity.BuyedMachine;
import com.szilardolah.amusementpark.entity.Guest;
import com.szilardolah.amusementpark.service.AmusementParkService;
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
 * REST Web Service
 * 
 * @author Szilard <szilard.olah@yahoo.com>
 */

@Path("park")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AmusementParkResource implements Serializable{

    @Inject
    private AmusementParkService parkService;
   
    @Inject
    private GuestService guestService;
    
    @POST
    public Response add(AmusementPark entity) {
        return Response.ok(parkService.createPark(entity)).build();
    }
    
    @PUT
    @Path("{id}")
    public Response modifiy(@PathParam("id") Long id, AmusementPark entity) {
        entity.setId(id);
        return Response.ok(parkService.modifyPark(entity)).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteById(@PathParam("id") Long id) {
        return Response.ok(parkService.deletePark(id)).build();
    }
    
    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") Long id) {
        return Response.ok(parkService.get(id)).build();
    }
    
    @GET
    public Collection<AmusementPark> getAll() {
        return parkService.values();
    }
    
    @POST
    @Path("{park_id}/machine/{machine_id}") 
    public Response addMachine(@PathParam("park_id") Long parkId, @PathParam("machine_id") Long machineId) {
        parkService.addMachine(parkId, machineId);
        return Response.ok().build();
    }
    
    @DELETE
    @Path("{park_id}/machine/{machine_id}") 
    public Response deleteMachine(@PathParam("park_id") Long parkId, @PathParam("machine_id") Long machineId) {
        parkService.dropMachine(parkId, machineId);
        return Response.ok().build();
    }

    @POST
    @Path("{park_id}/guest/{guest_id}")
    public Response addGuestToPark(@PathParam("park_id") Long parkId, @PathParam("guest_id") Long guestId) {
        parkService.addGuestToPark(parkId, guestId);
        return Response.ok().build();
    }

    @DELETE
    @Path("{park_id}/guest/{guest_id}")
    public Response removeGuestFromPark(@PathParam("park_id") Long parkId, @PathParam("guest_id") Long guestId) {
        parkService.removeGuestFromPark(parkId, guestId);
        return Response.ok().build();
    }
    
        
    @POST
    @Path("{park_id}/machine/{machine_id}/guest/{guest_id}") 
    public Response addGuestToMachine(@PathParam("park_id") Long parkId, @PathParam("machine_id") Long machineId, @PathParam("guest_id") Long guestId) {
        parkService.addGuestToMachine(parkId, machineId, guestId);
        return Response.ok().build();
    }
    
    @DELETE
    @Path("{park_id}/machine/{machine_id}/guest/{guest_id}") 
    public Response removeGuestFromMachine(@PathParam("park_id") Long parkId, @PathParam("machine_id") Long machineId, @PathParam("guest_id") Long guestId) {
        parkService.removeGuestFromMachine(parkId, machineId, guestId);
        return Response.ok().build();
    }   
    
    @PUT
    @Path("{park_id}/open/{machine_id}")
    public Response openMachine(@PathParam("park_id") Long parkId, @PathParam("machine_id") Long machineId) {
        parkService.openMachine(parkId, machineId);
        return Response.ok().build();
    }
    
    @PUT
    @Path("{park_id}/close/{machine_id}")
    public Response closeMachine(@PathParam("park_id") Long parkId, @PathParam("machine_id") Long machineId) {
        parkService.closeMachine(parkId, machineId);
        return Response.ok().build();
    }
    
    @GET
    @Path("{park_id}/machines")
    public Collection<BuyedMachine> getAllBuyedMachines(@PathParam("park_id") Long parkId) {
        return parkService.machineValues(parkId);
    }

    @GET
    @Path("nonzero")
    public Collection<AmusementPark> getParksWithNonZeroFund() {
        return parkService.getWithNonZeroFund();
    }
               
    @GET
    @Path("/{park_id}/machine/{machine_id}")
    public Collection<Guest> getGuestsOfMachine(@PathParam("park_id") Long parkId, @PathParam("machine_id") Long machineId) {
        return guestService.getGuestsOfMachine(parkId, machineId);
    }
    
    @GET
    @Path("{park_id}/rest")
    public Collection<Guest> getGuestsByState(@PathParam("park_id") Long parkId) {
        return guestService.getGuestsByState(parkId);
    }   
}
