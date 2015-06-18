/**
 * Copyright (c) 2015 ADTECH. All rights reserved.
 * 
 **/
package com.adtech.hystrix.client;

import java.util.ArrayList;
import java.util.List;

import com.adtech.hystrix.model.Recommendation;
import com.adtech.hystrix.model.RecommendationContainer;
import com.adtech.hystrix.model.Statics;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

/**
 * @author Oliver Flegler <oliver.flegler@adtech.com>
 *
 */
public class FailoverClient extends HystrixCommand<RecommendationContainer> {

    private RecommendationContainer recommendations;

    private final Integer userId;

    /**
     * <pre>
     * Setup Hystrix
     * 
     * - Add calls to testGroup (the actual group doesn't really matter, we just have to choose one)
     * - Sets execution timeout
     * - Enables circuit breaker (which is in fact enabled by default, just for the sake of being explicit)
     * - Sets the circuit breaker threshold
     * 
     * The submited ID will be stored for later usage
     * </pre>
     */
    public FailoverClient(Integer id) {
        super(Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("testGroup"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(Statics.TIMEOUT))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withCircuitBreakerEnabled(true))
                .andCommandPropertiesDefaults(
                        HystrixCommandProperties.Setter().withCircuitBreakerRequestVolumeThreshold(Statics.CIRCUITBREAKER_OPEN_THRESHOLD)));
        this.userId = id;
    }

    public RecommendationContainer getRecommendations() {
        return this.recommendations;
    }

    /**
     * This command is executed when the execute() command is invoked
     */
    @Override
    protected RecommendationContainer run() {
        RegularClient client = new RegularClient(this.userId);

        RecommendationContainer result = client.performRemoteCall();
        this.recommendations = result;

        return result;

    }

    /**
     * In case of error / exception in the run command the getFallback() command will be invoked
     */
    @Override
    protected RecommendationContainer getFallback() {
        this.recommendations = createDefaultRecommendations();
        return recommendations;
    }

    /**
     * Create some artificial default values
     * 
     * @return
     */
    private RecommendationContainer createDefaultRecommendations() {
        RecommendationContainer container = new RecommendationContainer();

        List<Recommendation> recommendations = new ArrayList<>();
        recommendations.add(new Recommendation("Default Movie 1", 5));
        recommendations.add(new Recommendation("Default Movie 2", 6));
        recommendations.add(new Recommendation("Default Movie 3", 4));

        container.setRecommendations(recommendations);

        return container;
    }

}
