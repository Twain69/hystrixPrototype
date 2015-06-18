/**
 * Copyright (c) 2015 ADTECH. All rights reserved.
 * 
 **/
package com.adtech.hystrix.model;


/**
 * @author Oliver Flegler <oliver.flegler@adtech.com>
 *
 */
public class Statics {

    public final static String REMOTE_SERVICE_URL = "http://localhost:9090";

    public final static String RECOMMENDATION_URL = "/recommendations";
    public final static Integer RECOMMENDATION_DELAY = 10000;

    public final static Integer TIMEOUT = 1000;

    public final static Integer CIRCUITBREAKER_OPEN_THRESHOLD = 4;
    public final static Integer CIRCUITBREAKER_SLEEP_WINDOW = 10000;

}
