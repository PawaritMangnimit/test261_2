package com.unijobs.campus;

import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET})
@RestController
public class PingController {
  @GetMapping("/ping")
  public String ping() { return "pong"; }
}
