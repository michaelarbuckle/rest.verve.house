/*
 * Copyright 2014-2016 the original author or authors.
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

package house.verve.web;

import java.io.IOException;
//import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;

import redis.clients.jedis.Protocol;
import redis.embedded.RedisServer;

 
@Configuration
@EnableRedisHttpSession // <1>
public class HttpSessionConfig {

	@Value( "${redis.embedded}" )
	private Boolean useRedisEmbedded;
	
	@Bean
	public HttpSessionStrategy httpSessionStrategy() {
		return new HeaderHttpSessionStrategy(); // <3>
	}
	  private static RedisServer redisServer;

	@Bean
	public JedisConnectionFactory connectionFactory() {
	    JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
	    
	    if (useRedisEmbedded == true)
	    {
	    	   try {
				redisServer = new RedisServer(Protocol.DEFAULT_PORT);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		        redisServer.start();
	    }
	    else 
	    {    
	    	connectionFactory.setPort(6379);	    
	    	connectionFactory.setHostName("localhost");
	    } 	
	    
	    // connection.setPassword("secret");
	    return connectionFactory;
	}

    @PreDestroy
    public void destroy() {
        redisServer.stop();
    }

	
	
	/*	

	    @Bean
	    public JedisConnectionFactory connectionFactory() throws IOException {
	        redisServer = new RedisServer(Protocol.DEFAULT_PORT);
	        redisServer.start();
	        return new JedisConnectionFactory();
	    }

*/
}
 
