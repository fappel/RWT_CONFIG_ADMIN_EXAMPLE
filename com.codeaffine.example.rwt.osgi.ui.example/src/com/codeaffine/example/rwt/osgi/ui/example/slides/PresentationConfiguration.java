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
package com.codeaffine.example.rwt.osgi.ui.example.slides;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.rap.rwt.application.Application;
import org.eclipse.rap.rwt.application.ApplicationConfiguration;
import org.eclipse.rap.rwt.client.WebClient;

import com.codeaffine.example.rwt.osgi.ui.platform.ConfiguratorTracker;


public class PresentationConfiguration implements ApplicationConfiguration {
  static final String SLIDES = "slides";
  
  @Override
  public void configure( Application application ) {
	Map<String,String> properties = new HashMap<String,String>();
	properties.put( WebClient.THEME_ID, SLIDES );
	properties.put( WebClient.PAGE_TITLE, SLIDES );
    application.addEntryPoint( "/" + SLIDES, EntryPoint.class, properties );
    application.addStyleSheet( SLIDES, "themes/slides/theme.css" );
    new ConfiguratorTracker( this, application ).open();
  }
}