package odev;
import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.handler.RequestMatchResult;

import odev.HomeController;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = HomeController.class)
public class TestHomeController{

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;


	@Test
	public void testIndexStringModel() {
		ResultMatcher ok = MockMvcResultMatchers.status().isOk();
	    ResultMatcher msg = MockMvcResultMatchers.model()
	                           .attribute("email", "Giriş Başarılı");
	    try {
			this.mockMvc.perform(get("/"))
			               .andExpect(ok)
			               .andExpect(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testIndexStringStringModelHttpServletRequest() {
		ResultMatcher ok = MockMvcResultMatchers.status().isOk();
	    ResultMatcher msg = MockMvcResultMatchers.model()
	                           .attribute("msg", "index");

	    MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("");
	    builder.param("email", "demo@bumin.com.tr");
	    builder.param("password", "​cjaiU8CV");
	    try {
			this.mockMvc.perform(builder)
			               .andExpect(ok)
			               .andExpect(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
