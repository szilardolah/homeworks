package com.szilardolah.mini.config;



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
        resources.add(com.szilardolah.mini.rest.UserResource.class);
        resources.add(com.szilardolah.mini.rest.CartResource.class);
        resources.add(com.szilardolah.mini.rest.MobileTypeResource.class);
        resources.add(com.szilardolah.mini.rest.MobileInventoryResource.class);
        return resources;
    }
}
