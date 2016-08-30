package com.szilardolah.amusementpark.resource;

import com.szilardolah.amusementpark.entity.Guestbook;
import com.szilardolah.amusementpark.entity.Note;
import com.szilardolah.amusementpark.service.AmusementParkService;
import com.szilardolah.amusementpark.service.GuestbookService;
import com.szilardolah.amusementpark.service.NoteService;
import java.io.Serializable;
import java.util.Collection;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */
@Path("book")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class GuestbookResource implements Serializable{

    
    @Inject
    private GuestbookService bookService;

    @Inject
    private NoteService noteService;
    
    @Inject
    private AmusementParkService parkService;
    
    @POST
    @Path("{park_id}/{guest_id}")
    public Response writeNote(@PathParam("park_id") Long parkId, @PathParam("guest_id") Long guestId, String text) {
        parkService.writeNote(parkId, guestId, text);
        return Response.ok().build();
    }
      
    @GET
    @Path("{park_id}/{guest_id}")
    public Collection<Note> getNote(@PathParam("guest_id") Long guestId, @PathParam("park_id") Long parkId) {
        return noteService.get(parkId, guestId);
    }
    
    @GET
    @Path("{book_id}")
    public Response getBook(@PathParam("book_id") Long bookId) {
        return Response.ok(bookService.get(bookId)).build();
    }
    
    @GET
    public Collection<Guestbook> getAllGuestbooks() {
        return bookService.values();
    }
    
}
