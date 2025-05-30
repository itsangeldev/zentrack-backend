package remzen.zentrack.security.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/zentrack")
public class DemoController {
    @PostMapping(value = "demo")
    public String demo() {
        return "We are inside";
    }


}
