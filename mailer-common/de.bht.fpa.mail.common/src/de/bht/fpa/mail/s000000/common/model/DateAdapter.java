package de.bht.fpa.mail.s000000.common.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

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