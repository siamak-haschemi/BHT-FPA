package de.bht.fpa.mail.common.testdata;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import de.bht.fpa.mail.s000000.common.mail.model.Message;
import de.bht.fpa.mail.s000000.common.mail.testdata.RandomTestDataProvider;

public class RandomTestDataProviderTest {

  @Test
  public void shouldGenerateMessages() {
    RandomTestDataProvider dataProvider = new RandomTestDataProvider(10);
    List<Message> randomMessages = dataProvider.getMessages();

    assertThat(randomMessages, is(notNullValue()));
    assertThat(randomMessages.size(), is(10));

    for (Message message : randomMessages) {
      checkMessage(message);
    }
  }

  protected void checkMessage(Message message) {
    assertThat(message.getId(), is(notNullValue()));
    assertTrue(message.getId() > -1);
    assertThat(message.getText(), is(notNullValue()));
    assertThat(message.getSubject(), is(notNullValue()));

    assertThat(message.getSender(), is(notNullValue()));
    assertThat(message.getSender().getId(), is(notNullValue()));

    assertThat(message.getImportance(), is(notNullValue()));
    assertThat(message.getReceived(), is(notNullValue()));
    assertThat(message.getSent(), is(notNullValue()));

    assertThat(message.getRecipients().size(), is(greaterThan(0)));
    assertThat(message.getRecipients().get(0).getId(), is(notNullValue()));

    assertThat(message.getAttachment().size(), is(0));
    assertThat(message.isRead(), is(notNullValue()));
  }

}
