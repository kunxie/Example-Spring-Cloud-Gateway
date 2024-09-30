package kunxie.personal.example.springcloudgateway.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

/**
 * Note: @jakarta.annotation.PostConstruct hook won't work with the refresh functionality.
 */
@Data
@Slf4j
@RefreshScope
@Configuration
@ConfigurationProperties(prefix = "routing.strategy-params.http-header")
public class HttpHeaderRouteConfig {

  private String name;
  private Set<String> candidates;
}
