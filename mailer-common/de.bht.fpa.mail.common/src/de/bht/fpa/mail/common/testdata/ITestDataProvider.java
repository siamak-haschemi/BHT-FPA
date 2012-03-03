package de.bht.fpa.mail.common.testdata;

import java.util.Collection;

import de.bht.fpa.mail.common.model.Message;

public interface ITestDataProvider {
  Collection<Message> getMessages();
}
