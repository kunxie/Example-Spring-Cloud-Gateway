# Requirements

## Tech Requirements

* Java 17
* Latest Spring Parent Pom
* Lombok library
* Compatible Spring Cloud Gateway dependency
* Spring Boot micrometer metrics
* The codebase needs to be maintainable and properly structured.
* A mermaid architecture graph is required.
* The test coverage should be above 80%, with JUnit5

## Business Requirements

* The main agenda is to route traffic between API pairs (a primary URI and a secondary URI) based on different routing
  strategies.
* API requests can be GET, POST, PUT, DELETE. The gateway route the traffic along with original headers and body, and
  return with original headers and body.
* There are multiple URI pairs with similar request paths except for the domain URI, dynamically loaded based on a
  configuration YAML file.
* For each URI pair, it has a unique param `api-name` and unique param `api-gateway-path`
  * The param `api-gateway-path` is the path of gateway-uri to routing different URI pairs.
  * For instance we have a URI pair https://xxx.com and https://yyy.com and `api-gateway-path=/api-1`
  * Then the gateway-url/api-1 is used for routing between https://xxx.com and https://yyy.com
* Initially, there will be one routing strategies:  HTTP_HEADER, represented as enums.
  * **For the HTTP_HEADER routing**
    * Introduce a `routing_http_header` param, a string, determining which HTTP header is used for routing.
    * Introduce a `routing_http_header_candidates` param, a list of distinct strings.
    * When the `routing_http_header_candidates` list contains the value of `routing_http_header`, route the traffic to
      the secondary URI; otherwise, route it to the primary URI.
    * If the `routing_http_header_candidates` list is empty, all traffic will always be routed to the primary URI.
    * Define a wildcard character like `#`. If the `routing_http_header_candidates` only contains the wildcard
      character, the traffic will always be routed to the secondary URI.
    * The `routing_http_header_candidates` list can't contain the wildcard character along with other candidate strings;
      if it does, throw an exception.
    * The `routing_http_header_candidates` will be updated dynamically using context-refresh

## Operational Excellence

* Need production-level logging and error-handling
* The Gateway will be exposed outside, proper security
* Expose Prometheus metrics like TPS among different APIs.
* Monitor memory usage, heap size, etc.