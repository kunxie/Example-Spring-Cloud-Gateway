package kunxie.personal.example.springcloudgateway.refresh;

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

  /**
   * periodically refresh the context, default interval: 5 minutes
   */
  @Scheduled(fixedDelayString = "${refresh.interval.ms:300000}")
  public void refreshContextPeriodically() {
    log.trace("***** refresh the context at {} and refreshed properties: {} *****", LocalDateTime.now(), refresher.refresh());
  }
}
