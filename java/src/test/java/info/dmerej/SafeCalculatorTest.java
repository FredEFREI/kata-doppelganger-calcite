package info.dmerej;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SafeCalculatorTest {

  @Mock
  private Authorizer authorizer;

  @Test
  void should_not_throw_when_authorized() {

    Authorizer authorizer = new Authorizer() {
      @Override
      public boolean authorize() {
        return true;
      }
    };

    SafeCalculator safeCalculator = new SafeCalculator(authorizer);
    safeCalculator.add(1,1);
  }

  @Test
  void should_not_throw_when_authorized_with_mockito() {
    when(authorizer.authorize()).thenReturn(true);
    SafeCalculator safeCalculator = new SafeCalculator(authorizer);

    safeCalculator.add(1,1);
  }
}
