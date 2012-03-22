/*******************************************************************************
 * Copyright (c) 2011 - 2012 Siamak Haschemi & Benjamin Haupt
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package de.bht.fpa.mail.s000000.common.mail.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * This class is used by JAXB for marshalling/unmarshalling dates. It extends
 * {@link XmlAdapter} and converts {@link Date} instances to a string
 * representation, but also can parses a string representation back to a
 * {@link Date} object.
 * 
 * @author Siamak Haschemi
 * 
 */
public class DateAdapter extends XmlAdapter<String, Date> {
  private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

  @Override
  public synchronized String marshal(Date v) throws Exception {
    return DATE_FORMAT.format(v);
  }

  @Override
  public synchronized Date unmarshal(String v) throws Exception {
    return DATE_FORMAT.parse(v);
  }
}