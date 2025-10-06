package com.unijobs.campus;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@CrossOrigin(origins="*") // โปรโตไทป์
@RestController
@RequestMapping("/tu")
public class TuAuthController {

  private final RestTemplate http = new RestTemplate();

  @Value("${tu.auth.url}")   private String tuAuthUrl;   // จาก application.properties
  @Value("${tu.api.token}")  private String tuToken;     // จาก ENV: TU_API_TOKEN

  // body รูปแบบตามคู่มือ TU: {"UserName":"xxx","PassWord":"yyy"}
  public static class TuCred { public String UserName; public String PassWord; }

  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestBody TuCred cred) {
    try {
      HttpHeaders h = new HttpHeaders();
      h.setContentType(MediaType.APPLICATION_JSON);
      h.set("Application-Key", tuToken); // << ใส่ header ที่นี่

      HttpEntity<TuCred> req = new HttpEntity<>(cred, h);
      ResponseEntity<String> res = http.postForEntity(tuAuthUrl, req, String.class);
      return ResponseEntity.status(res.getStatusCode()).body(res.getBody());
    } catch (Exception ex) {
      return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
              .body("{\"error\":\"proxy_failed\",\"message\":\""+ex.getMessage()+"\"}");
    }
  }
}
