/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blakeparmeter.metricsapi.repository;

import com.blakeparmeter.metricsapi.beans.MetricValue;
import java.util.List;
import org.springframework.data.repository.Repository;

/**
 *
 * @author Blake
 */
public interface MetricValueRepository extends Repository<MetricValue, Long>{
    MetricValue save(MetricValue metric);
    public List<MetricValue> getByMetricId(Long metricId);
}
