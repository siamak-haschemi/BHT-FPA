package de.bht.fpa.mail.common.internal;

import java.io.File;

import javax.xml.bind.JAXB;

import de.bht.fpa.mail.common.model.Message;
import de.bht.fpa.mail.common.testdata.MessageTestDataProvider;
import de.bht.fpa.mail.common.testdata.RandomTestDataProvider;

public class XMLMessageGenerator {

  public static void main(String[] args) {
    String baseDir = "maildata";

    MessageTestDataProvider messageTestDataProvider = new MessageTestDataProvider();
    messageTestDataProvider.setTestDataProvider(new RandomTestDataProvider(50));
    Iterable<Message> randomMessages = messageTestDataProvider.getMessages();

    for (Message message : randomMessages) {
      File file = new File(baseDir + File.separator + message.getId() + ".xml");
      JAXB.marshal(message, file);
      System.out.println("Serialized XML-based Message file '" + file.getAbsolutePath() + "'.");
    }
  }

}
