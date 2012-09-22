/*******************************************************************************
 * Copyright (c) 2011 EclipseSource and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    EclipseSource - initial API and implementation
 ******************************************************************************/
package com.eclipsesource.example.ece2011.ui.admin;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.rap.rwt.application.Application;
import org.eclipse.rap.rwt.application.ApplicationConfiguration;
import org.eclipse.rap.rwt.client.WebClient;
import org.eclipse.rap.rwt.lifecycle.IEntryPoint;
import org.eclipse.rap.rwt.lifecycle.IEntryPointFactory;


public class AdminConfiguration implements ApplicationConfiguration {

  private final class EntryPointFactory implements IEntryPointFactory {
	public IEntryPoint create() {
	  return new AdminUI();
	}
  }

  public void configure( Application application ) {
	Map<String,String> properties = new HashMap<String,String>();
	properties.put( WebClient.THEME_ID, "org.eclipse.rap.rwt.theme.Default" );
	properties.put( WebClient.PAGE_TITLE, "RAP Admin UI" );
    application.addEntryPoint( "/admin", new EntryPointFactory(), properties );
    application.addStyleSheet( "org.eclipse.rap.rwt.theme.Default", "resources/addon.css" );
  }
}