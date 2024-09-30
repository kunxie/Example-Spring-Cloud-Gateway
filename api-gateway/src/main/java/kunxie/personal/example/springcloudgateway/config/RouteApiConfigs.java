package kunxie.personal.example.springcloudgateway.config;

import jakarta.annotation.PostConstruct;
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

  List<ApiConfig> apiConfigs;

  @Data
  public static class ApiConfig {
    private String routeId;
    private RouteStrategy routeStrategy;
    private String gatewayPath;
    private String gatewayPathRegex;
    private String primaryUrl;
    private String secondaryUrl;
  }

  @PostConstruct
  public void log() {
    log.trace("created RouteApiConfigs: {}", apiConfigs);
  }
}
