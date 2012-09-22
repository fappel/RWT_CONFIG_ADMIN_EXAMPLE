/*******************************************************************************
 * Copyright (c) 2011 Frank Appel and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Frank Appel - initial API and implementation
 ******************************************************************************/
package com.codeaffine.example.rwt.osgi.ui.example.apps;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.rap.rwt.application.Application;
import org.eclipse.rap.rwt.application.ApplicationConfiguration;
import org.eclipse.rap.rwt.client.WebClient;

import com.codeaffine.example.rwt.osgi.ui.platform.ConfiguratorTracker;
import com.codeaffine.example.rwt.osgi.ui.platform.ShellPositioner;


public class App1 implements ApplicationConfiguration {
  static final String EXAMPLE_UI = "example";
  
  @Override
  public void configure( Application application ) {
	Map<String,String> properties = new HashMap<String,String>();
	properties.put( WebClient.THEME_ID, EXAMPLE_UI );
	properties.put( WebClient.PAGE_TITLE, "Dynamic Duo" );
    application.addEntryPoint( "/" + EXAMPLE_UI, UIEntryPoint.class, properties );
    application.addStyleSheet( EXAMPLE_UI, "themes/codeaffine/theme.css" );
    application.addPhaseListener( new ShellPositioner() );
    new ConfiguratorTracker( this, application ).open();
  }
}
