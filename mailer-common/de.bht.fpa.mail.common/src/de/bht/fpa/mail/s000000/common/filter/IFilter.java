/*******************************************************************************
 * Copyright (c) 2011 - 2012 Siamak Haschemi & Benjamin Haupt
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package de.bht.fpa.mail.s000000.common.filter;

import java.util.Set;

import de.bht.fpa.mail.s000000.common.mail.model.Message;

/**
 * This interface represents {@link Message} filters. Filters can be applied to
 * a {@link Collection} of {@link Message}s. Filters can also be combined. For
 * example, there could be a UnionFilter, which creates the union set of two or
 * more other Filters.
 * 
 * @author Siamak Haschemi
 * 
 */
public interface IFilter {
  /**
   * This method filters the messages. It ensures that the filtered messages are
   * in a {@link Set}, so that a {@link Message} is contained only once. The
   * method does not modify the given list of {@link Message}s.
   * 
   * @param messagesToFilter
   *          the {@link Iterable} (parent class of {@link Collection},
   *          {@link List}, etc.) list of {@link Message}s to filter.
   * @return the filtered {@link Set} of {@link Message}s, where each
   *         {@link Message} is contained exactly once.
   */
  Set<Message> filter(Iterable<Message> messagesToFilter);
}
