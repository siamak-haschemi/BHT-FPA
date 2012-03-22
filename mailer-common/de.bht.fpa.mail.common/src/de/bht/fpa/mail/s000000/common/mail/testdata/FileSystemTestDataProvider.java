/*******************************************************************************
 * Copyright (c) 2011 - 2012 Siamak Haschemi & Benjamin Haupt
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package de.bht.fpa.mail.s000000.common.mail.testdata;

import static com.google.common.collect.Collections2.filter;
import static com.google.common.collect.Collections2.transform;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Lists.newLinkedList;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Collection;

import javax.xml.bind.JAXB;

import com.google.common.base.Function;
import com.google.common.base.Predicate;

import de.bht.fpa.mail.s000000.common.mail.model.Message;

/**
 * This class provides its user with a number of {@link Message} objects for
 * testing purposes. It reads the contents of a file-system directory.<br/>
 * <br/>
 * For example, to generate 20 {@link Message}s, do: <br/>
 * {@code MessageTestDataProvider provider = new MessageTestDataProvider();}<br/>
 * {@code provider.setTestDataProvider(new RandomTestDataProvider("/my/cool/place"));}
 * <br/>
 * {@code List<Message> messages = provider.getMessages();}
 * 
 * 
 * @author Siamak Haschemi
 */
public class FileSystemTestDataProvider implements ITestDataProvider {

  private final File basePath;

  public FileSystemTestDataProvider(File basePath) {
    if (!basePath.exists()) {
      throw new IllegalArgumentException("Directory '" + basePath.getAbsolutePath() + " does not exist.");
    }
    this.basePath = basePath;
  }

  @Override
  public Collection<Message> getMessages() {
    // @formatter:off
    return filter(
        transform(
            getXMLFilesIn(basePath), 
            toParsedXMLMessages()
        ),
        byNonNullEntries()
    );
    // @formatter:on
  }

  private Collection<File> getXMLFilesIn(File basePath2) {
    File[] xmlFiles = basePath2.listFiles(filteredByXMLFiles());
    if (xmlFiles == null || xmlFiles.length == 0) {
      return newLinkedList();
    }
    return newArrayList(xmlFiles);
  }

  private FilenameFilter filteredByXMLFiles() {
    return new FilenameFilter() {
      @Override
      public boolean accept(File dir, String name) {
        return name.endsWith("xml");
      }
    };
  }

  private Predicate<Message> byNonNullEntries() {
    return new Predicate<Message>() {
      @Override
      public boolean apply(Message input) {
        return input != null;
      }
    };
  }

  private Function<File, Message> toParsedXMLMessages() {
    return new Function<File, Message>() {
      @Override
      public Message apply(File file) {
        try {
          return JAXB.unmarshal(file, Message.class);
        } catch (Exception e) {
          return null;
        }
      }
    };
  }
}
