package com.unijobs.campus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins="*")
@RestController
public class PingController {
  @GetMapping("/ping") public String ping() { return "pong"; }
}
