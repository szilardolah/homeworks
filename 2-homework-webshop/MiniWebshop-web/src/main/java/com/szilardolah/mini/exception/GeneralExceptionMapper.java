package com.szilardolah.mini.exception;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */
@Provider
public class GeneralExceptionMapper implements ExceptionMapper<Throwable> {

    private static final Logger LOGGER = Logger.getLogger(GeneralExceptionMapper.class.toString());

    @Override
    public Response toResponse(Throwable throwable) {
        LOGGER.log(Level.SEVERE, "General Exception", throwable);
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ErrorDTO(throwable.getMessage() + " - " + throwable.getCause())).type(MediaType.APPLICATION_JSON).build();
    }
}
