package de.bht.fpa.mail.common.testdata;

import java.util.Collection;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bht.fpa.mail.common.model.Message;

import static com.google.common.collect.Lists.newArrayList;
import static de.bht.fpa.mail.common.model.builder.Builders.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

@RunWith(JMock.class)
public class MessageTestDataProviderTest {

  private final Mockery mockery = new JUnit4Mockery();

  @Test(expected = Exception.class)
  public void shouldFailIfNoTestDataProviderIsSet() {
    new MessageTestDataProvider().getMessages();
  }

  @Test
  public void shouldCallTestDataProviderForMessages() {
    MessageTestDataProvider messageTestDataProvider = new MessageTestDataProvider();
    final ITestDataProvider testDataProvider = mockery.mock(ITestDataProvider.class);

    final Message message = newMessageBuilder().build();

    mockery.checking(new Expectations() {
      {
        oneOf(testDataProvider).getMessages();
        will(returnValue(newArrayList(message)));
      }
    });
    messageTestDataProvider.setTestDataProvider(testDataProvider);
    Collection<Message> messages = messageTestDataProvider.getMessages();
    mockery.assertIsSatisfied();

    assertThat(messages.iterator().next(), equalTo(message));
  }
}
