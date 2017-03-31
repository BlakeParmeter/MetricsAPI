package metrics;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.blakeparmeter.metricsapi.Application;
import com.blakeparmeter.metricsapi.beans.Metric;
import com.blakeparmeter.metricsapi.beans.MetricValue;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

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
    private ObjectMapper objectMapper;
    
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
        mockMvc.perform(post("/addMetric")).andExpect(status().isBadRequest());
        mockMvc.perform(post("/addMetricValue")).andExpect(status().isBadRequest());
        mockMvc.perform(get("/getStatistics/1")).andExpect(status().isBadRequest());
    }
    
    /* Sunny day scenario tests */
    
    /**
     * Adds a valid metric 
     * @throws Exception 
     */
    @Test
    public void addValidMetricTest() throws Exception{
        Metric metricToAdd = new Metric();
        metricToAdd.setName("Test Metric");
        metricToAdd.setUnits("Test Units");
        sendMetric(metricToAdd, status().isOk());
    }
    
    /**
     * Adds valid metric values
     * @throws Exception 
     */
    @Test
    public void addValidMetricValues() throws Exception{
        MetricValue metricValueToAdd = new MetricValue();
        metricValueToAdd.setMetricId(1L);
        
        for(int i = -100; i < 100; i++){
            metricValueToAdd.setValue(Math.pow(i, 3));
            sendMetricValue(metricValueToAdd, status().isOk());
        }
    }
    
    /**
     * Gets the statistics for the metric
     * @throws Exception 
     */
    @Test
    public void getValidStatistics() throws Exception{
        mockMvc.perform(get("/getStatistics/1")).andExpect(status().isOk());
    }
    
    /* Bad request Scenario tests */
    
    /**
     * Tests all cases of bad metrics being added into the system.
     * @throws java.lang.Exception
     */
    @Test
    public void addBadMetricsTest() throws Exception{
        Metric metricToAdd = new Metric();
        sendMetric(metricToAdd, status().isBadRequest());
        
        //all below will have valid names but invalid units
        metricToAdd.setUnits(null);
        metricToAdd.setName("Test Metric");
        sendMetric(metricToAdd, status().isBadRequest());
        
        metricToAdd.setUnits("");
        sendMetric(metricToAdd, status().isBadRequest());
        
        metricToAdd.setUnits("Reallllllllllllllllllllllllllllly long unit name");
        sendMetric(metricToAdd, status().isBadRequest());
        
        //all below will have valid units but invalid names
        metricToAdd.setName(null);
        metricToAdd.setUnits("Test Units");
        sendMetric(metricToAdd, status().isBadRequest());
        
        metricToAdd.setName("");
        sendMetric(metricToAdd, status().isBadRequest());
        
        metricToAdd.setName("Reallllllllllllllllllllllllllllllllllly long name");
        sendMetric(metricToAdd, status().isBadRequest());
    }
    
    /* Testing Helpers */
    
    /**
     * @throws Exception 
     */
    private void sendMetric(Metric metric, ResultMatcher result) throws Exception{
        mockMvc.perform(post("/addMetric")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(metric)))
                .andExpect(result);
    }
    
    /**
     * @throws Exception 
     */
    private void sendMetricValue(MetricValue metric, ResultMatcher result) throws Exception{
        mockMvc.perform(post("/addMetricValue")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(metric)))
                .andExpect(result);
    }
}
