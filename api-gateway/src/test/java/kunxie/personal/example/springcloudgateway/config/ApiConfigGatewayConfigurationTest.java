package kunxie.personal.example.springcloudgateway.config;

import static org.junit.jupiter.api.Assertions.*;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.util.ReflectionUtils;

@Slf4j
@SpringBootTest(classes = ApiGatewayConfiguration.class)
@TestPropertySource(locations = {"classpath:routing-api-config.yml"})
class ApiGatewayConfigurationTest {

  @Autowired
  ApiGatewayConfiguration apiGatewayConfiguration;

  @Test
  void testRoutingApiConfigLoading() {

    ReflectionUtils.doWithMethods(apiGatewayConfiguration.getClass(), method -> {
      if (method.getName().startsWith("get") && !method.getName().startsWith("getRouting")) {
        var value = ReflectionUtils.invokeMethod(method, apiGatewayConfiguration);

        log.info("{} returns: {}", method.getName(), value);
//        assertNotNull(value);
      }
    });
  }
}