package com.szilardolah.amusementpark.config;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */
@javax.ws.rs.ApplicationPath("rest")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        resources.add(com.szilardolah.amusementpark.resource.AmusementParkResource.class);
        resources.add(com.szilardolah.amusementpark.resource.MachineResource.class);
        resources.add(com.szilardolah.amusementpark.resource.GuestResource.class);
        resources.add(com.szilardolah.amusementpark.resource.GuestbookResource.class);
        return resources;
    }
}
