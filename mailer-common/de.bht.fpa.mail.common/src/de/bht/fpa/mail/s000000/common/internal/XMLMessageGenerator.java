/*******************************************************************************
 * Copyright (c) 2011 - 2012 Siamak Haschemi & Benjamin Haupt
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package de.bht.fpa.mail.s000000.common.internal;

import java.io.File;

import javax.xml.bind.JAXB;

import de.bht.fpa.mail.s000000.common.mail.model.Message;
import de.bht.fpa.mail.s000000.common.mail.testdata.MessageTestDataProvider;
import de.bht.fpa.mail.s000000.common.mail.testdata.RandomTestDataProvider;

/**
 * A executable Java Class which generates 50 messages to the 'maildata'
 * directory.
 * 
 * @author Siamak Haschemi
 * 
 */
public final class XMLMessageGenerator {

  private static final int NR_OF_MESSAGES = 50;

  private XMLMessageGenerator() {

  }

  public static void main(String[] args) {
    String baseDir = "maildata";

    MessageTestDataProvider messageTestDataProvider = new MessageTestDataProvider();
    messageTestDataProvider.setTestDataProvider(new RandomTestDataProvider(NR_OF_MESSAGES));
    Iterable<Message> randomMessages = messageTestDataProvider.getMessages();

    for (Message message : randomMessages) {
      File file = new File(baseDir + File.separator + message.getId() + ".xml");
      JAXB.marshal(message, file);
      System.out.println("Serialized XML-based Message file '" + file.getAbsolutePath() + "'.");
    }
  }

}
