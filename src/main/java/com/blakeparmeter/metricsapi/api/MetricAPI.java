/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blakeparmeter.metricsapi.api;

import com.blakeparmeter.metricsapi.beans.Metric;
import com.blakeparmeter.metricsapi.controllers.MetricController;
import com.blakeparmeter.metricsapi.repository.MetricRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
    
    @RequestMapping("/addMetric")
    public ResponseEntity<?> addMetric(@RequestBody Metric record){
        if(record.getName() == null || record.getName().isEmpty()){
            return ResponseEntity.badRequest().body("The name is required and cannot be empty.");
        }
        if(record.getUnits()== null || record.getUnits().isEmpty()){
            return ResponseEntity.badRequest().body("The name is required and cannot be empty.");
        }
        metricController.addMetric(record);
        return ResponseEntity.ok().build();
    }
    
    @RequestMapping("/test")
    public ResponseEntity<?> test(){
        System.out.println("Reciving test message...");
        return ResponseEntity.ok("test");
    }
}
    
