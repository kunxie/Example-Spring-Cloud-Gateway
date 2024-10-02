package kunxie.personal.example.springcloudgateway.route;

import kunxie.personal.example.springcloudgateway.config.RouteApiConfigs;
import kunxie.personal.example.springcloudgateway.config.RouteStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The RouteLocator creation for SECONDARY route strategy.
 */
@Slf4j
@Configuration
public class SecondaryRouterLocatorConfig {

  /**
   * Configures route locator for PRIMARY based routing strategy using the provided configurations.
   * <p>
   * Note: always route to SECONDARY url
   *
   * @param routeLocatorBuilder the builder to create custom route locator
   * @param apiConfigs          the configuration containing API route details
   * @return a configured RouteLocator based on the SECONDARY strategy
   */
  @Bean
  public RouteLocator primaryRouterLocator(RouteLocatorBuilder routeLocatorBuilder, RouteApiConfigs apiConfigs) {
    var builder = routeLocatorBuilder.routes();

    apiConfigs.getApiConfigs().stream()
        .filter(apiConfig -> RouteStrategy.SECONDARY.equals(apiConfig.getRouteStrategy()))
        .forEach(apiConfig -> builder
            .route(apiConfig.getSecondaryRouteId(), r -> r
                .path(apiConfig.getGatewayPath())
                .filters(f -> f
                    .rewritePath(apiConfig.getGatewayPathRegex(), "/${path}")
                    // ensure that the original Host header from the incoming request is preserved and sent to the downstream service
                    .preserveHostHeader()
                    // tag downstream source for future debugging
                    .addResponseHeader("X-SOURCE", "SECONDARY")
                )
                .uri(apiConfig.getSecondaryUrl()))
        );
    return builder.build();
  }
}
