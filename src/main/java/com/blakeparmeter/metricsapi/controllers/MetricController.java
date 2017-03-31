/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blakeparmeter.metricsapi.controllers;

import com.blakeparmeter.metricsapi.beans.Metric;
import com.blakeparmeter.metricsapi.beans.MetricValue;
import com.blakeparmeter.metricsapi.beans.Statistics;
import com.blakeparmeter.metricsapi.repository.MetricRepository;
import com.blakeparmeter.metricsapi.repository.MetricValueRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Blake
 */
@Controller
public class MetricController {
    
    @Autowired 
    MetricRepository metricRepo;
    
    @Autowired 
    MetricValueRepository metricValueRepo;
    
    public Metric addMetric(Metric metric){
        return metricRepo.save(metric);
    }

    public MetricValue addMetricValue(MetricValue record) {
        return metricValueRepo.save(record);
    }

    public Statistics getStatistics(Long metricId) {
        if(metricRepo.findById(metricId) == null){
            throw new RuntimeException("The metric: "+metricId+" cannot be found.");
        }
        List<MetricValue> metricValues = metricValueRepo.getByMetricId(metricId);
        Statistics stats = new Statistics();
        
        return stats;
    }
}
