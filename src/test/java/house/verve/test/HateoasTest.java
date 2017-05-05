package house.verve.test;
import java.util.*;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.hal.Jackson2HalModule;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import house.verve.model.Action;
import house.verve.model.ActionParameter;
import house.verve.model.DeviceData;
import house.verve.model.Chat;
import house.verve.model.Device;
import house.verve.model.Message;
import house.verve.model.Topic;
import house.verve.model.Sensor;
import house.verve.model.SensorData;
import house.verve.model.Structure;
import house.verve.model.Space;
import house.verve.model.SpaceRole;
import house.verve.model.User;

public class HateoasTest {

	String port = "8080";

	public static void main(String[] args) 
	{

		HateoasTest ht = new HateoasTest();
		ht.test();
		 
	 }
	 public void test() {
	  RestTemplate restTemplate = restTemplate();

	  String url = "http://localhost:8080/sensors";

	  ResponseEntity<Resources<Sensor>> responseEntity = restTemplate.exchange(
	    url, HttpMethod.GET, null,
	    new ParameterizedTypeReference<Resources<Sensor>>() {},
	    port, 0, 100);
	  Resources<Sensor> resources = responseEntity.getBody();
	  List<Sensor> customers = new ArrayList(resources.getContent());
	  for (Sensor c:customers){
		  System.out.println(c);
	  }
	  System.out.println(customers.get(0).getClass());
	 }

	 private RestTemplate restTemplate() {
	  ObjectMapper mapper = new ObjectMapper();
	  mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	  mapper.registerModule(new Jackson2HalModule());

	  MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
	  converter.setSupportedMediaTypes(MediaType.parseMediaTypes("application/hal+json"));
//	  converter.setSupportedMediaTypes(MediaType.parseMediaTypes("application/hal+json,application/json"));//example for multiple media types
	  converter.setObjectMapper(mapper);
	  return new RestTemplate(Arrays.asList(converter));
	 }
	
}
