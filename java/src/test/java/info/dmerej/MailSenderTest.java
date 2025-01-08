package info.dmerej;

import info.dmerej.mailprovider.SendMailRequest;
import info.dmerej.mailprovider.SendMailResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MailSenderTest {

    @Mock
    private HttpClient httpClient;

    @Captor
    ArgumentCaptor<SendMailRequest> requestCaptor;

    @Test
    void should_make_a_valid_http_request_with_mockito() {
        // Arrange
        SendMailRequest expectedRequest = new SendMailRequest("email", "New notification", "message");
        User user = new User("user", "email");
        when(httpClient.post(anyString(), any())).thenReturn(new SendMailResponse(200, "message"));
        MailSender mailSender = new MailSender(httpClient);

        // Act
        mailSender.sendV1(user, "message");
        verify(httpClient).post(anyString(), requestCaptor.capture());

        // Assert
        Assertions.assertEquals(expectedRequest, requestCaptor.getValue());
    }

    @Test
    void should_retry_when_getting_a_503_error_with_mockito() {
        // TODO: write a test to demonstrate the bug in MailSender.sendV2()
    }
}
