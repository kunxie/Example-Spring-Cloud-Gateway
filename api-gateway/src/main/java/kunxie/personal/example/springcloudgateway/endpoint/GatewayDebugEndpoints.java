package kunxie.personal.example.springcloudgateway.endpoint;

import kunxie.personal.example.springcloudgateway.config.HttpHeaderRouteConfig;
import kunxie.personal.example.springcloudgateway.config.RouteApiConfigs;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * providing endpoints for debugging purpose.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/debug")
public class GatewayDebugEndpoints {

  private final HttpHeaderRouteConfig httpHeaderRouteConfig;
  private final RouteApiConfigs routeApiConfigs;

  @GetMapping("/log/httpHeaderRouteConfig")
  public ResponseEntity<String> httpHeaderRouteConfig() {
    return ResponseEntity.ok(httpHeaderRouteConfig.toString());
  }

  @GetMapping("/log/routeApiConfigs")
  public ResponseEntity<String> routeApiConfigs() {
    return ResponseEntity.ok(routeApiConfigs.toString());
  }
}
