package kunxie.personal.example.springcloudgateway.config;

/**
 * Defines the route strategy between primary-url and secondary-url.
 */
public enum RouteStrategy {
  /**
   * Use HTTP headers to determine the route.
   */
  HTTP_HEADER,

  /**
   * Route based on a weighted distribution.
   */
  WEIGHT,

  /**
   * Always route to the primary URL.
   */
  PRIMARY,

  /**
   * Always route to the secondary URL.
   */
  SECONDARY
}
