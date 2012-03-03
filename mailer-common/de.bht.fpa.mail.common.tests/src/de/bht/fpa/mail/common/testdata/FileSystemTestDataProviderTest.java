package de.bht.fpa.mail.common.testdata;

import static de.bht.fpa.mail.common.model.builder.Builders.newMessageBuilder;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Collection;

import javax.xml.bind.JAXB;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.bht.fpa.mail.common.model.Message;

public class FileSystemTestDataProviderTest {

  private File tempDirectory;
  private File mailDataDirectory;
  private int mailDirectorySize;

  @Before
  public void setUp() {
    tempDirectory = new File("temp");
    tempDirectory.mkdir();

    mailDataDirectory = new File("maildata");
    if (!mailDataDirectory.exists()) {
      fail("'maildata' directory is missing");
    }
    mailDirectorySize = mailDataDirectory.list(new FilenameFilter() {
      @Override
      public boolean accept(File dir, String name) {
        return name.endsWith("xml");
      }
    }).length;
  }

  @After
  public void cleanUp() {
    tempDirectory.delete();
  }

  @Test(expected = Exception.class)
  public void shouldFailIfDirectoryDoesNotExists() {
    new FileSystemTestDataProvider(new File("some_non_existent_directory"));
  }

  @Test
  public void shouldWorkWithEmptyDirectories() {
    new FileSystemTestDataProvider(tempDirectory).getMessages();
  }

  @Test
  public void shouldWorkWithOneMessage() {
    Message message = newMessageBuilder().build();
    JAXB.marshal(message, new File(tempDirectory, "01.xml"));

    Collection<Message> messages = new FileSystemTestDataProvider(tempDirectory).getMessages();
    assertThat(messages, is(notNullValue()));
    assertThat(messages.size(), is(1));
    assertThat(messages.iterator().next(), equalTo(message));
  }

  @Test
  public void shouldWorkWithManyMessages() {
    Collection<Message> messages = new FileSystemTestDataProvider(mailDataDirectory).getMessages();
    assertThat(messages, is(notNullValue()));
    assertThat(messages.size(), is(mailDirectorySize));
    for (Message message : messages) {
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

    assertThat(message.getRecipient().size(), is(greaterThan(0)));
    assertThat(message.getRecipient().get(0).getId(), is(notNullValue()));

    assertThat(message.getAttachment().size(), is(0));
    assertThat(message.isRead(), is(notNullValue()));
  }
}
