package com.lambda.demo.lambdaspring;

import com.lambda.demo.lambdaspring.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    private static final Logger log = LogManager.getLogger(UserController.class);
    @GetMapping("/users")
    public List<User> getUsers(){
        long startTs = System.currentTimeMillis();
        log.info("***** Getting users for ******");
        List<User> result = List.of(new User("John", "Doe", "john.doe@baeldung.com"),
                new User("John", "Doe", "john.doe-2@baeldung.com"));
        log.info("Total time --> "+(System.currentTimeMillis()-startTs));
        return result;
    }
}
