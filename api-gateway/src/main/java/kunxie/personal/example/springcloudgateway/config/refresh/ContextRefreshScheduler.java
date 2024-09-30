package kunxie.personal.example.springcloudgateway.config.refresh;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.refresh.ConfigDataContextRefresher;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

@Slf4j
@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class ContextRefreshScheduler {

  private final ConfigDataContextRefresher refresher;

  @Scheduled(fixedDelayString = "${refresh.interval.ms}")
  public void refreshContext() {
    log.trace("***** refresh the context at {}", LocalDateTime.now());
    log.trace("***** refreshed properties: {}", refresher.refresh());
  }
}
