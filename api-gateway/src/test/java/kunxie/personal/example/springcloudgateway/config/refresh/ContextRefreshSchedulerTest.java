package kunxie.personal.example.springcloudgateway.config.refresh;

import kunxie.personal.example.springcloudgateway.refresh.ContextRefreshScheduler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cloud.context.refresh.ConfigDataContextRefresher;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
class ContextRefreshSchedulerTest {

  @Mock
  ConfigDataContextRefresher refresher;
  @InjectMocks
  ContextRefreshScheduler scheduler;

  @Test
  void test() {
    when(refresher.refresh()).thenReturn(Set.of());
    assertDoesNotThrow(() -> scheduler.refreshContextPeriodically());
  }
}
