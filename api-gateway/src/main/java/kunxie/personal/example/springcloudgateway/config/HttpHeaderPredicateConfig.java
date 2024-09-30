package kunxie.personal.example.springcloudgateway.config;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Note: @PostConstruct lifetime hook won't work with the refresh functionality.
 */
@Data
@Slf4j
@RefreshScope
@Configuration
@ConfigurationProperties(prefix = "routing.strategy-params.http-header")
public class HttpHeaderPredicateConfig {

  private String name;
  private List<String> candidates;
}
