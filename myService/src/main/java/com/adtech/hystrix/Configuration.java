/**
 * Copyright (c) 2015 ADTECH. All rights reserved.
 * 
 **/
package com.adtech.hystrix;

import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;

/**
 * @author Oliver Flegler <oliver.flegler@adtech.com>
 *
 */
@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        return new ServletRegistrationBean(new HystrixMetricsStreamServlet(), "/hystrix.stream");
    }

}
