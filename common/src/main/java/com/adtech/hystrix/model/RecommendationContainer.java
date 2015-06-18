/**
 * Copyright (c) 2015 ADTECH. All rights reserved.
 * 
 **/
package com.adtech.hystrix.model;

import java.util.List;

/**
 * @author Oliver Flegler <oliver.flegler@adtech.com>
 *
 */
public class RecommendationContainer {

    private List<Recommendation> recommendations;

    public RecommendationContainer() {
    }

    public RecommendationContainer(List<Recommendation> recommendations) {
        this.recommendations = recommendations;
    }

    /**
     * @return the recommendations
     */
    public List<Recommendation> getRecommendations() {
        return recommendations;
    }

    /**
     * @param recommendations the recommendations to set
     */
    public void setRecommendations(List<Recommendation> recommendations) {
        this.recommendations = recommendations;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "RecommendationContainer [recommendations=" + recommendations + "]";
    }

}
