package org.fasttrackit.dailyfoodconsumptionapi.steps;

import org.fasttrackit.dailyfoodconsumptionapi.domain.User;
import org.fasttrackit.dailyfoodconsumptionapi.service.UserService;
import org.fasttrackit.dailyfoodconsumptionapi.transfer.user.CreateUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserSteps {

    @Autowired
    private UserService userService;

    public User createUser(){
        CreateUserRequest user = new CreateUserRequest();
        user.setFirstName("Alina");
        user.setLastName("Pop");
        user.setEmail("alina@exemple.com");
        user.setUsername("alinapop");
        user.setPassword("pass");

        return userService.createUser(user);
    }
}
