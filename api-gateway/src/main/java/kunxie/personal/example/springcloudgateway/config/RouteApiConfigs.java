package kunxie.personal.example.springcloudgateway.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@Slf4j
@Configuration
@ConfigurationProperties(prefix = "routing")
public class RouteApiConfigs {

  private List<ApiConfig> apiConfigs;

  @Data
  public static class ApiConfig {
    private String routeId;
    private RouteStrategy routeStrategy;
    private String gatewayPath;
    private String gatewayPathRegex;
    private String primaryRouteId;
    private String primaryUrl;
    private String secondaryRouteId;
    private String secondaryUrl;
  }
}
