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
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Blake
 */
@Entity
@Getter @Setter
public class Metric {
    
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    
    @NotBlank(message = "The name cannot be empty")
    @Size(max = 20, message = "Name must be less than 20 characteres")
    private String name;
    
    @NotBlank(message = "The units cannot be empty")
    @Size(max = 20, message = "Units must be less than 20 characteres")
    private String units;
}
