package kunxie.personal.example.springcloudgateway.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Data
@Slf4j
@RefreshScope
@Configuration
@ConfigurationProperties(prefix = "routing.strategy-params.weight")
public class WeightRouteConfig {

  /**
   * percentage routing to primary, scale from 0 to 100.
   * accordingly the 100-weight percentage routing to secondary url
   * <p>
   * 0 means always routing to secondary url
   * 100 means always routing to primary url
   */
  private int value;
}
