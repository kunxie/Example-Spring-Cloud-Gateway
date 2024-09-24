package kunxie.personal.example.springcloudgateway.endpoint;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

@RestController
public class ExampleEndpoints {

  @GetMapping("/api/example")
  public ResponseEntity<String> getExample() {
    return ResponseEntity.ok("response from primary endpoint");
  }
}
