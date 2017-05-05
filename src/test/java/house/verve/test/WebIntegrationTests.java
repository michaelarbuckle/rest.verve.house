package house.verve.test;

import static org.hamcrest.CoreMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Integration tests to make sure the URI customizations are applied.
 *
 * @author Oliver Gierke
 * @soundtrack Clueso - Gewinner (Stadtrandlichter Live)
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WebIntegrationTests {

	@Autowired WebApplicationContext context;

	MockMvc mvc;

	@Before
	public void setUp() {
		this.mvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	
//	"structure/park1239/device/cc3200_01/measurable"
	
	
	@Test
	public void identifiesResourcesUsingUsername() throws Exception {

		mvc.perform(get("/things/umbrella")).//
				andExpect(status().isOk()).//
				andExpect(jsonPath("$._links.self.href", endsWith("umbrella")));
	}
}
