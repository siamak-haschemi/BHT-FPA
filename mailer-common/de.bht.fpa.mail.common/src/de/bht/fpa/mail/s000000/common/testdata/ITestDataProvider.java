package de.bht.fpa.mail.s000000.common.testdata;

import java.util.Collection;

import de.bht.fpa.mail.s000000.common.model.Message;

public interface ITestDataProvider {
  Collection<Message> getMessages();
}
