package com.szilardolah.amusementpark.resource;

import com.szilardolah.amusementpark.entity.Machine;
import com.szilardolah.amusementpark.service.MachineService;
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
@Path("machine")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MachineResource implements Serializable{

    @Inject
    private MachineService machineService;
    
    @POST
    public Response add(Machine entity) {
        return Response.ok(machineService.createMachine(entity)).build();
    }
    
    @PUT
    @Path("{id}")
    public Response modifiy(@PathParam("id") Long id, Machine entity) {
        entity.setId(id);
        return Response.ok(machineService.modifyMachine(entity)).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteById(@PathParam("id") Long id) {
        return Response.ok(machineService.deleteMachine(id)).build();
    }
    
    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") Long id) {
        return Response.ok(machineService.get(id)).build();
    }
    
    @GET
    public Collection<Machine> getAll() {
        return machineService.values();
    }
}
