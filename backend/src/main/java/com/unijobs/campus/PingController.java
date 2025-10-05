package com.unijobs.campus;

import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")   // <— เปิด CORS ชั่วคราว
@RestController
public class PingController {
  @GetMapping("/ping")
  public String ping() { return "pong"; }
}
