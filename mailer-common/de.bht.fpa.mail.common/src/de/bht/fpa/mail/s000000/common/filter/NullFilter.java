package de.bht.fpa.mail.s000000.common.filter;

import java.util.HashSet;
import java.util.Set;

import de.bht.fpa.mail.s000000.common.mail.model.Message;

/**
 * This class implements the {@link IFilter} interface, but does not filter
 * anything (Null-Object Pattern).
 */
public class NullFilter implements IFilter {

  @Override
  public Set<Message> filter(Iterable<Message> messagesToFilter) {
    Set<Message> messages = new HashSet<Message>();
    for (Message message : messagesToFilter) {
      messages.add(message);
    }
    return messages;
  }
}
