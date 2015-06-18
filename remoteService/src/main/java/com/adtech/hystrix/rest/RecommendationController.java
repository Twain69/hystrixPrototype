/**
 * Copyright (c) 2015 ADTECH. All rights reserved.
 * 
 **/
package com.adtech.hystrix.rest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adtech.hystrix.model.RecommendationContainer;
import com.adtech.hystrix.model.Statics;

/**
 * @author Oliver Flegler <oliver.flegler@adtech.com>
 *
 */

@RestController
public class RecommendationController {

    private Boolean delayed = false;

    private final Log LOGGER = LogFactory.getLog(RecommendationController.class);

    @RequestMapping(Statics.RECOMMENDATION_URL + "/{id}")
    public RecommendationContainer recommendations(@PathVariable(value = "id") String idString) throws InterruptedException {
        Integer id;
        try {
            id = Integer.valueOf(idString);
        } catch (NumberFormatException e) {
            id = -1;
        }

        if (delayed) {
            Thread.sleep(Statics.RECOMMENDATION_DELAY);
        }

        return RecommendationService.getRecommendationForUser(id);
    }

    @RequestMapping("/delayed/{delayed}")
    public Boolean setDelayed(@PathVariable(value = "delayed") String delayed) {
        this.delayed = Boolean.valueOf(delayed);

        LOGGER.info("Set delayed to " + this.delayed);

        return this.delayed;
    }

}
