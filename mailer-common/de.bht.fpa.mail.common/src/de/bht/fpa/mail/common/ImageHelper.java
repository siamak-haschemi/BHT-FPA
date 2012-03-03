package de.bht.fpa.mail.common;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import de.bht.fpa.mail.common.internal.Activator;

public class ImageHelper {
  private static final int MISSING_IMAGE_SIZE = 10;
  private Map<String, Image> imageMap = new HashMap<String, Image>();

  public ImageDescriptor getImageDescriptor(String path) {
    return AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, path);
  }

  public Image getImage(String path) {
    Image image = imageMap.get(path);
    if (image == null) {
      try {
        image = getImageDescriptor(path).createImage();
        imageMap.put(path, image);
      } catch (Exception e) {
        image = getMissingImage();
        imageMap.put(path, image);
      }
    }

    imageMap.put(path, image);
    return image;
  }

  private static Image getMissingImage() {
    Image image = new Image(Display.getCurrent(), MISSING_IMAGE_SIZE, MISSING_IMAGE_SIZE);
    //
    GC gc = new GC(image);
    gc.setBackground(getColor(SWT.COLOR_RED));
    gc.fillRectangle(0, 0, MISSING_IMAGE_SIZE, MISSING_IMAGE_SIZE);
    gc.dispose();
    //
    return image;
  }

  public static Color getColor(int systemColorID) {
    Display display = Display.getCurrent();
    return display.getSystemColor(systemColorID);
  }

  public void disposeImages() {
    // dispose loaded images
    for (Image image : imageMap.values()) {
      image.dispose();
    }
    imageMap.clear();
    imageMap = null;
  }
}
