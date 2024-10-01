package kunxie.personal.example.springcloudgateway.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

@Slf4j
@Component
@RequiredArgsConstructor
public class HttpHeaderRoutePredicate {

  private final HttpHeaderRouteConfig config;

  /**
   * Logic determining HTTP_HEADER strategy routing rule:
   * <p>
   * routing to primary url:
   * 1. when the config.getCandidates set is empty
   * 2. when the value of HttpHeader is absent
   * 3. when the value of HttpHeader is NOT contained in the config.getCandidates set
   * <p>
   * routing to secondary url:
   * 1. when the value of HttpHeader is contained in the config.getCandidates set
   *
   * @param ex ServerWebExchange object
   * @return true when routing to primary url, false when routing to secondary url
   */
  public boolean apply(final ServerWebExchange ex) {
    if (config.getCandidates().isEmpty()) {
      return true;
    }
    String httpHeader = fetchHttpHeader(ex);
    if (httpHeader == null) {
      return true;
    }
    return !config.getCandidates().contains(httpHeader);
  }

  /**
   * Fetches the HTTP header from the request
   *
   * @param ex ServerWebExchange object
   * @return the value of the HTTP header, or null if not found
   */
  private String fetchHttpHeader(ServerWebExchange ex) {
    try {
      return ex.getRequest().getHeaders().getFirst(config.getName());
    } catch (Exception e) {
      log.error("Exception caught when fetching the HttpHeader: {} routing to primary url...", config.getName(), e);
      return null;
    }
  }
}
