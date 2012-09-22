package com.codeaffine.example.rwt.osgi.ui.platform;

import org.eclipse.rap.rwt.RWT;
import org.eclipse.rap.rwt.application.Application;
import org.eclipse.rap.rwt.application.ApplicationConfiguration;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Filter;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;

import com.codeaffine.example.rwt.osgi.ui.platform.internal.Activator;


public class ConfiguratorTracker
  extends ServiceTracker<ApplicationConfiguration, ApplicationConfiguration>
{
  
  private static final String CONFIGURATOR_REFERENCE = "ApplicationReference";

  private final Application configuration;
  private final ApplicationConfiguration configurator;

  public ConfiguratorTracker( ApplicationConfiguration configurator,
                              Application configuration )
  {
    super( getBundleContext(), ApplicationConfiguration.class, null );
    this.configurator = configurator;
    this.configuration = configuration;
  }

  @Override
  public ApplicationConfiguration addingService( ServiceReference<ApplicationConfiguration> ref ) {
    ApplicationConfiguration result = super.addingService( ref );
    if( isTrackedConfiguration( result ) ) {
      storeConfigurationServiceReference( ref );
      close();
    }
    return result;
  }
  
  public static boolean matches( ServiceReference<UIContributorFactory> contributorReference ) {
    boolean result = true;
    if( hasApplicationConfiguration() ) {
      try {
        result = doMatches( contributorReference );
      } catch( InvalidSyntaxException shouldNotHappen ) {
        throw new IllegalStateException( shouldNotHappen );
      }
    }
    return result;
  }

  private static boolean doMatches( ServiceReference<UIContributorFactory> contributorReference )
    throws InvalidSyntaxException
  {
    String expression = createFilterExpression();
    Filter filter = FrameworkUtil.createFilter( expression );
    return filter.match( contributorReference );
  }

  private static boolean hasApplicationConfiguration() {
    @SuppressWarnings( "rawtypes" )
    ServiceReference serviceReference = getConfiguratorReference();
    return null != serviceReference.getProperty( getConfiguratorKey() );
  }
  
  public static boolean matchesType( String value,
                                     ServiceReference<UIContributorFactory> contributorReference )
  {
    try {
      String expression = createFilterExpression( "type", value );
      Filter filter = FrameworkUtil.createFilter( expression );
      return filter.match( contributorReference );
    } catch( InvalidSyntaxException shouldNotHappen ) {
      throw new IllegalStateException( shouldNotHappen );
    }
  }
  
  private static String createFilterExpression() {
    @SuppressWarnings( "rawtypes" )
    ServiceReference serviceReference = getConfiguratorReference();
    String value = ( String )serviceReference.getProperty( getConfiguratorKey() );
    String key = getConfiguratorKey();
    return createFilterExpression( key, value );
  }

  private static String createFilterExpression( String key, String value ) {
    return "(" + key + "=" + value + ")";
  }

  @SuppressWarnings( "rawtypes" )
  private static ServiceReference getConfiguratorReference() {
    return ( ServiceReference )RWT.getApplicationStore().getAttribute( CONFIGURATOR_REFERENCE );
  }

  private static String getConfiguratorKey() {
    return ApplicationConfiguration.class.getSimpleName();
  }

  private void storeConfigurationServiceReference( ServiceReference<ApplicationConfiguration> ref ) {
    configuration.setAttribute( ConfiguratorTracker.CONFIGURATOR_REFERENCE, ref );
  }

  private boolean isTrackedConfiguration( ApplicationConfiguration result ) {
    return result == configurator;
  }
  
  private static BundleContext getBundleContext() {
    return Activator.getBundleContext();
  }
}