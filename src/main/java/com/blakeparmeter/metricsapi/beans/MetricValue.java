/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blakeparmeter.metricsapi.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Blake
 */
@Entity
@Getter @Setter
public class MetricValue {
    
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    @NotBlank(message = "You must specify a metric")
    private Long metricId;
    
    @NotBlank(message = "The value cannot be empty")
    private double value;
}
