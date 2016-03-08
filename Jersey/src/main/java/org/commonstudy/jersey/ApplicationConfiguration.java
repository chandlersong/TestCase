package org.commonstudy.jersey;

import org.commonstudy.jersey.services.SimpleServices;
import org.glassfish.jersey.server.ResourceConfig;

public class ApplicationConfiguration extends ResourceConfig {

    public ApplicationConfiguration() {

        register(SimpleServices.class);

    }

}
