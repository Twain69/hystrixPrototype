/**
 * Copyright (c) 2015 ADTECH. All rights reserved.
 * 
 **/
package com.adtech.hystrix;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adtech.hystrix.client.FailoverClient;
import com.adtech.hystrix.client.RegularClient;
import com.adtech.hystrix.model.RecommendationContainer;

/**
 * @author Oliver Flegler <oliver.flegler@adtech.com>
 *
 */
@RestController
public class MyController {

    private final Log LOGGER = LogFactory.getLog(MyController.class);

    public MyController() {
    }

    /**
     * Fetch data from the remote service using the "old" way
     * @param username
     * @return
     */
    @RequestMapping("/userdataClassic/{username}")
    public UserData getUserData(@PathVariable(value = "username") String username) {
        UserData userData = initFakeUserDataByName(username);

        RegularClient client = new RegularClient(userData.getId());
        RecommendationContainer recContainer = client.performRemoteCall();
        userData.setRecommendations(recContainer.getRecommendations());

        return userData;
    }

    /**
     * @param username
     * @return
     */
    @RequestMapping("/userdata/{username}")
    public UserData getUserDataWithFailoverClient(@PathVariable(value = "username") String username) {
        UserData userData = initFakeUserDataByName(username);

        FailoverClient client = new FailoverClient(userData.getId());
        RecommendationContainer recContainer = client.execute();
        userData.setRecommendations(recContainer.getRecommendations());

        return userData;
    }

    /**
     * Initializes UserData by username
     * 
     * @param username
     * @return
     */
    private UserData initFakeUserDataByName(String username) {
        UserData userData = new UserData();
        userData.setUsername(username);

        switch (username.toLowerCase()) {
        case "user1":
            userData.setId(1);
            break;
        case "user2":
            userData.setId(2);
            break;
        default:
            LOGGER.error("User not found");
            break;
        }

        return userData;
    }

}
