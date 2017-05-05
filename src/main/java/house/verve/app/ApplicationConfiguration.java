/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package house.verve.app;

import javax.servlet.Filter; 
import static com.fasterxml.jackson.databind.DeserializationFeature.*;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.core.geo.GeoJson;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;
import org.springframework.data.repository.init.AbstractRepositoryPopulatorFactoryBean;
import org.springframework.data.repository.init.Jackson2RepositoryPopulatorFactoryBean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.session.ExpiringSession; 
import org.springframework.session.MapSessionRepository; 
import org.springframework.session.SessionRepository; 
import org.springframework.session.web.http.CookieHttpSessionStrategy; 
import org.springframework.session.web.http.SessionRepositoryFilter; 
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.annotation.JsonFilter;

import house.verve.async.WorkQueueController;
import house.verve.event.MessageChangeListener;
import house.verve.event.SensorChangeListener;
import house.verve.model.Space;
import house.verve.model.io.SpaceMixIn;
import house.verve.model.io.SpaceModule;

import house.verve.process.ActionController;
import house.verve.process.MessageController;
import house.verve.process.TimeSeriesProcessor;
import house.verve.rules.RulesEngine;
import house.verve.rules.KieRulesEngine;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import house.verve.security.VerveUserDetailsService;
import org.springframework.web.filter.CharacterEncodingFilter;
@SpringBootApplication
public class ApplicationConfiguration {

	/**
	 * Read JSON data from disk and insert those stores.
	 * 
	 * @return
	 */
	public @Bean AbstractRepositoryPopulatorFactoryBean repositoryPopulator() {

		System.out.println("Initializing Data with init.verve.house.json");
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.addMixIn(GeoJsonPoint.class, GeoJsonPointMixin.class);
		mapper.addMixIn(Space.class, SpaceMixIn.class);
	//	mapper.addMixInAnnotations(Rectangle.class, SpaceMixin.class);
		mapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);

		mapper.registerModule(new SpaceModule());
		
		Jackson2RepositoryPopulatorFactoryBean factoryBean = new Jackson2RepositoryPopulatorFactoryBean();
//		factoryBean.setResources(new Resource[] { new ClassPathResource("init.verve.house.withSensors.json") });
		factoryBean.setResources(new Resource[] { new ClassPathResource("init.verve.house.json") });
		factoryBean.setMapper(mapper);

		
/*
 *  -
 */
		
		// GeoJsonPolygon p = new GeoJsonPolygon(new Point("1","2"), null, null, null, null );
	 
		return factoryBean;
	}

	static abstract class GeoJsonPointMixin {
		GeoJsonPointMixin(@JsonProperty("longitude") double x, @JsonProperty("latitude") double y) {}
	}
	
 
	/*
	static abstract class GeoPolygonMixin {
	    GeoPolygonMixin(@JsonProperty("results") List<GeoResult> results, 
	                    @JsonProperty("averageDistance") Distance averageDistance) {
	    }

	    @JsonProperty("results")
	    abstract List<GeoResult> getContent(); 
	}
	*/


    @Bean
    public MessageChangeListener messageChangeListener() {
        return new MessageChangeListener();
    }
	
    @Bean
    public SensorChangeListener sensorChangeListener() {
        return new SensorChangeListener();
    }
    
    @Bean 
    public TimeSeriesProcessor TimeSeriesProcessor()
    {
    	return new TimeSeriesProcessor();
    }


    @Bean
    public ActionController actionController()
    {
    	return new ActionController();
    }
    @Bean
    public MessageController messageController()
    {
    	return new MessageController();
    }
    
    @Bean
    public  WorkQueueController workQueueController()
    {
    	return new WorkQueueController ();
    }
    /*
    @Bean 
    public SessionRepository<ExpiringSession> sessionRepository() { 
     MapSessionRepository mapSessionRepository = new MapSessionRepository(); 
     mapSessionRepository.setDefaultMaxInactiveInterval(1800); 
     return mapSessionRepository; 
    } 
    
    @Bean 
    public <S extends ExpiringSession> SessionRepositoryFilter<? extends ExpiringSession> sessionRepositoryFilter( 
      SessionRepository<S> sessionRepository) { 
     SessionRepositoryFilter<S> sessionRepositoryFilter = new SessionRepositoryFilter<>( 
       sessionRepository); 
   //  sessionRepositoryFilter.setHttpSessionStrategy(new CookieHttpSessionStrategy()); 
     return sessionRepositoryFilter; 
    } 
    */
    
    @Bean
    public KieContainer kieContainer() {
        return KieServices.Factory.get().getKieClasspathContainer();
    }
    
    @Bean
    public KieRulesEngine kieRulesEngine(KieContainer kieContainer) {
        return new KieRulesEngine(kieContainer);
    }
    @Bean 
    public UserDetailsService userDetailsService()
    {
    	return new VerveUserDetailsService();
    }
    
	@Bean
	public Filter characterEncodingFilter() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		return characterEncodingFilter;
	}
   /*
	 @Bean
	  public EmbeddedServletContainerFactory servletContainer() {
	    TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory() {
	        @Override
	        protected void postProcessContext(Context context) {
	          SecurityConstraint securityConstraint = new SecurityConstraint();
	          securityConstraint.setUserConstraint("CONFIDENTIAL");
	          SecurityCollection collection = new SecurityCollection();
	          collection.addPattern("/*");
	          securityConstraint.addCollection(collection);
	          context.addConstraint(securityConstraint);
	        }
	      };
	    
	    tomcat.addAdditionalTomcatConnectors(initiateHttpConnector());
	    return tomcat;
	  }
	  
	  private Connector initiateHttpConnector() {
	    Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
	    connector.setScheme("http");
	    connector.setPort(8080);
	    connector.setSecure(false);
	    connector.setRedirectPort(8443);
	    
	    return connector;
	  }
	*/
	
}
