package com.unijobs.campus;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@CrossOrigin(origins = "*") // โปรโตไทป์
@RestController
@RequestMapping("/tu")
public class UniversityProxyController {

  private final RestTemplate http = new RestTemplate();

  @Value("${tu.api.url:https://restapi.tu.ac.th/tuapi/Authkey}")
  private String tuUrl;

  @Value("${tu.api.token:}") // รับจาก ENV บน Render
  private String tuToken;

  @GetMapping("/authkey")
  public ResponseEntity<String> authkey() {
    HttpHeaders h = new HttpHeaders();
    h.setBearerAuth(tuToken);
    h.setContentType(MediaType.APPLICATION_JSON);
    var req = new HttpEntity<Void>(h);
    var res = http.exchange(tuUrl, HttpMethod.GET, req, String.class);
    return ResponseEntity.status(res.getStatusCode()).body(res.getBody());
  }
}
