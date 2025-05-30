package remzen.zentrack.models.users.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import remzen.zentrack.models.users.service.UserService;

@RestController
@RequestMapping("api/zentrack/users")
@CrossOrigin(origins = {"http://localhost:8081", "http://192.168.1.88:8081"})

public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

}