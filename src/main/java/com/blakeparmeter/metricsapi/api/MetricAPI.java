/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blakeparmeter.metricsapi.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
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
    
    //@Autowired MemberRepository memberRepo;
    //MessageService messageService = new MessageService();
    
    //TODO: Remove
    @RequestMapping("/test")
    public void test(@RequestBody Object model){
    
        System.out.println("test message");
        /*
        Response response = new Response();
        response.setStatus(Response.Status.SUCCESS);
        response.setMessage(model.toString());
        return response;
                */
    }
}
    