/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blakeparmeter.metricsapi.api;

import com.blakeparmeter.metricsapi.beans.Metric;
import com.blakeparmeter.metricsapi.beans.MetricValue;
import com.blakeparmeter.metricsapi.controllers.MetricController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Handles all the REST responses logic of the Metrics services. 
 * Complex logic should be moved outside of this class.
 * @author Blake
 */
@RestController
@EnableAutoConfiguration
public class MetricAPI {
    
    @Autowired 
    private MetricController metricController;
    
    @RequestMapping(value="/addMetric", method=RequestMethod.POST)
    public ResponseEntity<?> addMetric(@Validated @RequestBody Metric record){
        return ResponseEntity.ok(metricController.addMetric(record));
    }
    
    @RequestMapping(value="/addMetricValue", method=RequestMethod.POST)
    public ResponseEntity<?> addMetricValue(@Validated @RequestBody MetricValue record){
        return ResponseEntity.ok(metricController.addMetricValue(record));
    }
    
    @RequestMapping(value="/getStatistics/{metricId}", method=RequestMethod.GET)
    public ResponseEntity<?> getStatistics(@PathVariable Long metricId){
        return ResponseEntity.ok(metricController.getStatistics(metricId));
    }
}
    
