/**
 * Copyright (c) 2015 ADTECH. All rights reserved.
 * 
 **/
package com.adtech.hystrix.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.adtech.hystrix.model.Recommendation;
import com.adtech.hystrix.model.RecommendationContainer;

/**
 * @author Oliver Flegler <oliver.flegler@adtech.com>
 *
 */
public class RecommendationService {

    private static final Map<Integer, List<Recommendation>> resultMap = new HashMap<>();

    public RecommendationService() {
    }

    private static void initializeLookupMap() {
        List<Recommendation> input1 = new ArrayList<>();
        input1.add(new Recommendation("Movie 1", 5));
        input1.add(new Recommendation("Movie 2", 6));
        input1.add(new Recommendation("Movie 3", 4));
        input1.add(new Recommendation("Movie 4", 6));
        resultMap.put(1, input1);

        List<Recommendation> input2 = new ArrayList<>();
        input2.add(new Recommendation("Trashmovie 1", 2));
        input2.add(new Recommendation("Trashmovie 3", 3));
        resultMap.put(2, input2);
    }

    public static RecommendationContainer getRecommendationForUser(Integer id) {
        if (resultMap.size() == 0) {
            initializeLookupMap();
        }

        if (resultMap.containsKey(id)) {
            return new RecommendationContainer(resultMap.get(id));
        } else {
            return null;
        }
    }

}
