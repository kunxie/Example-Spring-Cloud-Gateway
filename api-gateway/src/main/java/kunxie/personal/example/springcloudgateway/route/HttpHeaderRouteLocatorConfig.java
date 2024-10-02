package kunxie.personal.example.springcloudgateway.route;

import kunxie.personal.example.springcloudgateway.config.RouteApiConfigs;
import kunxie.personal.example.springcloudgateway.config.RouteStrategy;
import kunxie.personal.example.springcloudgateway.predicate.HttpHeaderRoutePredicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The RouteLocator creation for HTTP_DEADER route strategy.
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class HttpHeaderRouteLocatorConfig {

  private final HttpHeaderRoutePredicate predicate;

  /**
   * Configures route locator for HTTP_HEADER based routing strategy using the provided configurations.
   *
   * @param routeLocatorBuilder the builder to create custom route locator
   * @param apiConfigs          the configuration containing API route details
   * @return a configured RouteLocator based on the HTTP_HEADER strategy
   */
  @Bean
  public RouteLocator httpHeaderRouteLocator(RouteLocatorBuilder routeLocatorBuilder, RouteApiConfigs apiConfigs) {
    var builder = routeLocatorBuilder.routes();

    apiConfigs.getApiConfigs().stream()
        .filter(apiConfig -> RouteStrategy.HTTP_HEADER.equals(apiConfig.getRouteStrategy()))
        .forEach(apiConfig -> builder
            .route(apiConfig.getPrimaryRouteId(), r -> r
                .path(apiConfig.getGatewayPath())
                .and()
                .predicate(predicate::apply)
                .filters(f -> f
                    .rewritePath(apiConfig.getGatewayPathRegex(), "/${path}")
                    // ensure that the original Host header from the incoming request is preserved and sent to the downstream service
                    .preserveHostHeader()
                    // tag downstream source for future debugging
                    .addResponseHeader("X-SOURCE", "PRIMARY")
                )
                .uri(apiConfig.getPrimaryUrl()))
            .route(apiConfig.getSecondaryRouteId(), r -> r
                .path(apiConfig.getGatewayPath())
                .filters(f -> f
                    .rewritePath(apiConfig.getGatewayPathRegex(), "/${path}")
                    .preserveHostHeader()
                    .addResponseHeader("X-SOURCE", "SECONDARY")
                )
                .uri(apiConfig.getSecondaryUrl())
            ));
    return builder.build();
  }
}
