package com.szilardolah.mini.exception;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */
public class LoginExceptionMapper implements ExceptionMapper<LoginException>{

    private static final Logger LOGGER = Logger.getLogger(GeneralExceptionMapper.class.toString());

    @Override
    public Response toResponse(LoginException ex) {
        LOGGER.log(Level.SEVERE, "General Exception", ex);
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ErrorDTO(ex.getMessage() + " - " + ex.getCause())).type(MediaType.APPLICATION_JSON).build();      
    }
}
