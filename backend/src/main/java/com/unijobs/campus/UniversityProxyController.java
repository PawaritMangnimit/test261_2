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

  @Value("${tu.auth.url}")      // URL จาก application.properties
  private String tuAuthUrl;

  @Value("${tu.api.token}")     // ค่า Application-Key จาก ENV
  private String tuToken;

  // รับ {"UserName":"...","PassWord":"..."} จาก frontend แล้วส่งต่อไป TU
  public static class TuCred { public String UserName; public String PassWord; }

  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestBody TuCred cred) {
    HttpHeaders h = new HttpHeaders();
    h.setContentType(MediaType.APPLICATION_JSON);
    h.set("Application-Key", tuToken); // <<=== HEADER

    HttpEntity<TuCred> req = new HttpEntity<>(cred, h); // <<=== BODY
    ResponseEntity<String> res = http.postForEntity(tuAuthUrl, req, String.class);
    return ResponseEntity.status(res.getStatusCode()).body(res.getBody());
  }
}
