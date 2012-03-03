package de.bht.fpa.mail.common;

public interface Constants {
  // ID Key, i.e. text.setData(WIDGET_ID, "myId");
  String WIDGET_ID = "ID";

  // Screen
  int WINDOW_WIDTH = 800;
  int WINDOW_HEIGHT = 600;

  // Preference Store
  String BASE_DIRECTORY_PREFERENCE_KEY = "de.bht.fpa.mail.baseDirectory";

  // Actions
  String SET_BASE_DIRECTORY_MENU_TEXT = "Set Base Directory";

  // Status Line Messages
  String STATUS_LINE_OPENED_DIRECTORY_MESSAGE_PREFIX = "Opened Directory: ";
  String STATUS_LINE_SELECTED_MESSAGE_PREFIX = "Selected Message: ";

  // Navigation View
  String NAVIGATION_VIEW_ID = "de.bht.fpa.mail.navigation";

  // Message Table View
  String MESSAGE_TABLE_VIEW_ID = "de.bht.fpa.mail.messageTable";
  int MESSAGE_TABLE_COLUMN_WIDTH = 100;
  String MESSAGE_TABLE_COLUMN_ATACHMENTS = "Attachments";
  String MESSAGE_TABLE_COLUMN_DATE = "Received";
  String MESSAGE_TABLE_COLUMN_ABOUT = "About";
  String MESSAGE_TABLE_COLUMN_FROM = "From";

  // Message View
  String MESSAGE_VIEW_ID = "de.bht.fpa.mail.message";
  String MESSAGE_VIEW_TEXT_ID_TEXT = "text";
  String MESSAGE_VIEW_TEXT_ID_TO = "to";
  String MESSAGE_VIEW_TEXT_ID_DATE = "date";
  String MESSAGE_VIEW_TEXT_ID_ABOUT = "about";
  String MESSAGE_VIEW_TEXT_ID_FROM = "from";
  String MESSAGE_VIEW_LABEL_TO = "to:";
  String MESSAGE_VIEW_LABEL_DATE = "Date:";
  String MESSAGE_VIEW_LABEL_ABOUT = "About:";
  String MESSAGE_VIEW_LABEL_FROM = "From:";
}
