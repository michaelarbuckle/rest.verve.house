package house.verve.test;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.PoolConfig;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import redis.clients.jedis.Protocol;
import redis.embedded.RedisServer;
 
@Configuration
@EnableRedisHttpSession
@Profile(Profiles.CLOUD)
public class RedisCloudSessionConfiguration 
//extends AbstractCloudConfig
{

	   @Autowired
	    private RedisConnectionFactory connectionFactory;

	
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
      //  PoolConfig poolConfig = new PoolConfig(10, 200);// TODO: configure!
       // return connectionFactory().redisConnectionFactory("REDISCLOUD");//, new PooledServiceConnectorConfig(poolConfig));
    //todo: refactor with current framework
    	return connectionFactory();
    }
    
    @Bean
    public RedisConnectionFactory connectionFactory()
    {
    	return connectionFactory;
    }
}