package de.bht.fpa.mail.s000000.common.testdata;

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

import de.bht.fpa.mail.s000000.common.model.Message;

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
