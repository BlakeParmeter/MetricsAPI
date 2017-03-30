/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blakeparmeter.metricsapi.repository;

import com.blakeparmeter.metricsapi.beans.Metric;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

/**
 *
 * @author Blake
 */
public interface MetricRepository extends CrudRepository<Metric, Long>{
    
    //Metric findById(Long id);
    //Metric save(Metric Metric);
    
}

