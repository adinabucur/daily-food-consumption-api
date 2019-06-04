package org.fasttrackit.dailyfoodconsumptionapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrackit.dailyfoodconsumptionapi.domain.User;
import org.fasttrackit.dailyfoodconsumptionapi.exception.ResourceNotFoundException;
import org.fasttrackit.dailyfoodconsumptionapi.persistence.UserRepository;
import org.fasttrackit.dailyfoodconsumptionapi.transfer.user.CreateUserRequest;
import org.fasttrackit.dailyfoodconsumptionapi.transfer.user.UpdateUserRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public UserService(UserRepository userRepository, ObjectMapper objectMapper) {
        this.userRepository = userRepository;
        this.objectMapper = objectMapper;
    }
    public User createUser(CreateUserRequest request){
        LOGGER.info("Creating user {}", request);
        User user = objectMapper.convertValue(request, User.class);
        return userRepository.save(user);
    }

    public User getUser(long id) throws ResourceNotFoundException {
        LOGGER.info("Retrieving user {}", id);
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User " + id + "not found"));
    }

    public User updateUser (long id, UpdateUserRequest request) throws ResourceNotFoundException {
        LOGGER.info("Updating user {}, {}", id, request);
        User user = getUser(id);

        BeanUtils.copyProperties(request, user);

        return userRepository.save(user);
    }

    public void deleteUser (long id){
        LOGGER.info("Deleting user {}", id);
        userRepository.deleteById(id);
        LOGGER.info("Deleted user {}", id);



    }
}
