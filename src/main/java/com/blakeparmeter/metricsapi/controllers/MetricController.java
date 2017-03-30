/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blakeparmeter.metricsapi.controllers;

import com.blakeparmeter.metricsapi.beans.Metric;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Blake
 */
@Controller
public class MetricController {
    
    public Metric addMetric(Metric metric){
        System.out.println("Adding a metric!");
        return metric;
    }
}
