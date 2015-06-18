/**
 * Copyright (c) 2015 ADTECH. All rights reserved.
 * 
 **/
package com.adtech.hystrix.client;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.client.RestTemplate;

import com.adtech.hystrix.model.RecommendationContainer;
import com.adtech.hystrix.model.Statics;

/**
 * @author Oliver Flegler <oliver.flegler@adtech.com>
 *
 */
public class RegularClient {

    private final Log LOGGER = LogFactory.getLog(RegularClient.class);

    private final Integer userId;

    public RegularClient(Integer id) {
        this.userId = id;
    }

    public RecommendationContainer performRemoteCall() {
        String serviceURL = Statics.REMOTE_SERVICE_URL + Statics.RECOMMENDATION_URL + "/" + userId;

        LOGGER.info("Remote serviceURL: " + serviceURL);
        return new RestTemplate().getForObject(serviceURL, RecommendationContainer.class);

    }
}