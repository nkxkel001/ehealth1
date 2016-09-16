package org.nkxkel001.thesis.ehealth;

import org.glassfish.jersey.server.ResourceConfig;
 

public class CustomApplication //extends ResourceConfig 
{
    public CustomApplication() 
    {
        //Register Auth Filter here
       // register(AuthenticationFilter.class);
        //register(DebugExceptionMapper.class);
    }
}
