/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metrics;

import com.blakeparmeter.metricsapi.Application;
import com.blakeparmeter.metricsapi.api.MetricAPI;
import com.blakeparmeter.metricsapi.beans.Metric;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * @author Blake 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class MetricsTest {
   
    private MockMvc mockMvc;
    
    @Autowired
    ObjectMapper objectMapper;
    
    @Before
    public void setup() throws Exception {
        mockMvc = standaloneSetup(new MetricAPI()).build();
    }
    
    /**
     * Adds a valid metric
     * @throws Exception 
     */
    @Test
    public void addMetricTest() throws Exception{
        mockMvc.perform(post("/addMetric")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(new Metric())))
                .andExpect(status().isOk());
    }
}
