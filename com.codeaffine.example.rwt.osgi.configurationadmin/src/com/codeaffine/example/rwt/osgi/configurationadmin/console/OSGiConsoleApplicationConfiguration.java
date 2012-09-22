package com.codeaffine.example.rwt.osgi.configurationadmin.console;

import org.eclipse.rap.rwt.application.Application;
import org.eclipse.rap.rwt.application.ApplicationConfiguration;


public class OSGiConsoleApplicationConfiguration implements ApplicationConfiguration {

  public void configure( Application application ) {
    application.addEntryPoint( "/console", OSGiConsoleEntryPoint.class, null );
  }
}
