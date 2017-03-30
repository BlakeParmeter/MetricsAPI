package metrics;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.blakeparmeter.metricsapi.Application;
import com.blakeparmeter.metricsapi.api.MetricAPI;
import com.blakeparmeter.metricsapi.beans.Metric;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author Blake 
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class MetricsTest {
   
    private MockMvc mockMvc;
    
    @Autowired
    ObjectMapper objectMapper;
    
    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setup() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }
    
    /**
     * Pings all the endpoints, some require data so expect invalid requests (400)
     * @throws Exception 
     */
    @Test
    public void pingTest() throws Exception{
        mockMvc.perform(post("/test")).andExpect(status().isOk());
        mockMvc.perform(post("/addMetric")).andExpect(status().isBadRequest());
    }
    
    /**
     * Tests all cases of bad metrics being added into the system.
     * @throws java.lang.Exception
     */
    @Test
    public void addBadMetricsTest() throws Exception{
        Metric metricToAdd = new Metric();
        sendMetric(metricToAdd, status().isBadRequest());
        
        metricToAdd.setName("Test Metric");
        sendMetric(metricToAdd, status().isBadRequest());
        
        metricToAdd = new Metric();
        metricToAdd.setUnits("Test Units");
        sendMetric(metricToAdd, status().isBadRequest());
    }
    
    /**
     * Adds a valid metric
     * @throws Exception 
     */
    @Test
    public void addMetricTest() throws Exception{
        Metric metricToAdd = new Metric();
        metricToAdd.setName("Test Metric");
        metricToAdd.setUnits("Test Units");
        sendMetric(metricToAdd, status().isOk());
    }
    
    /* Start of testing Helpers */
    
    /**
     * 
     * @throws Exception 
     */
    private void sendMetric(Metric metric, ResultMatcher result) throws Exception{
        mockMvc.perform(post("/addMetric")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(metric)))
                .andExpect(result);
    }
}
