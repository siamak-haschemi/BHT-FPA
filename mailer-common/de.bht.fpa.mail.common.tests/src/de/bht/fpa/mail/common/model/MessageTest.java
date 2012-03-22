package de.bht.fpa.mail.common.model;

import static com.google.common.collect.Lists.newArrayList;
import static de.bht.fpa.mail.s000000.common.mail.model.builder.Builders.newMessageBuilder;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.xml.bind.JAXB;

import org.junit.After;
import org.junit.Test;

import de.bht.fpa.mail.s000000.common.mail.model.Message;

public class MessageTest {

  protected static final File SERIALIZATION_DESTINATION = new File("message.out");

  @After
  public void tearDown() {
    if (SERIALIZATION_DESTINATION.exists() && SERIALIZATION_DESTINATION.canWrite()) {
      SERIALIZATION_DESTINATION.delete();
    }
  }

  @Test
  public void shouldBeParseable() throws Exception {
    File[] messageFiles = new File("maildata").listFiles(new FilenameFilter() {
      @Override
      public boolean accept(File dir, String name) {
        return name.endsWith("xml");
      }
    });
    assertThat(messageFiles, is(notNullValue()));

    for (File messageFile : messageFiles) {
      Message message = JAXB.unmarshal(messageFile, Message.class);
      checkMessage(message);
    }
  }

  @Test
  public void shouldBeUsableWithoutAnId() throws Exception {
    Set<Message> messages = new HashSet<Message>();
    messages.add(new Message());
  }

  @Test
  public void shouldBeUseableInCollectionsWhichExpectComparables() throws Exception {
    Set<Message> messages = new TreeSet<Message>();

    messages.add(newMessageBuilder().id(4L).build());
    messages.add(newMessageBuilder().id(1L).build());
    messages.add(newMessageBuilder().id(2L).build());
    messages.add(newMessageBuilder().id(3L).build());
  }

  @Test
  public void shouldBeSortable() throws Exception {
    Message message1 = newMessageBuilder().id(4L).build();
    Message message2 = newMessageBuilder().id(1L).build();
    Message message3 = newMessageBuilder().id(2L).build();
    Message message4 = newMessageBuilder().id(3L).build();

    List<Message> messages = newArrayList(message1, message2, message3, message4);

    Collections.sort(messages);

    assertThat(messages.get(0), is(message2));
    assertThat(messages.get(1), is(message3));
    assertThat(messages.get(2), is(message4));
    assertThat(messages.get(3), is(message1));
  }

  @Test
  public void shouldBeSerializable() throws Exception {
    Message message = newMessageBuilder().id(4711L).build();
    Message readMessage = serializeToFileAndRead(message);

    assertThat(readMessage, is(notNullValue()));
    assertThat(readMessage.getId(), is(4711L));
  }

  private void checkMessage(Message message) {
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

  @SuppressWarnings("unchecked")
  protected <T> T serializeToFileAndRead(T someObject) throws IOException, FileNotFoundException,
      ClassNotFoundException {
    new ObjectOutputStream(new FileOutputStream(SERIALIZATION_DESTINATION)).writeObject(someObject);
    return (T) new ObjectInputStream(new FileInputStream(SERIALIZATION_DESTINATION)).readObject();
  }

}
