/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.blakeparmeter.metricsapi.Application;
import com.blakeparmeter.metricsapi.api.MetricAPI;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 *
 * @author Blake 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class ApplicationTest {
    
    private MockMvc mockMvc;

    //@Autowired
    //private WebApplicationContext webApplicationContext;
    
    @Before
    public void setup() throws Exception {
        mockMvc = standaloneSetup(new MetricAPI()).build();
    }
    
    @Test
    public void pingTest() throws Exception{
        mockMvc.perform(post("/test")).andExpect(status().isOk());
    }
}
