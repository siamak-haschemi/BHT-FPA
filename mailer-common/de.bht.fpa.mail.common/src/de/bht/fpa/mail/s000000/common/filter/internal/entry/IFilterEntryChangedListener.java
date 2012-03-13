package de.bht.fpa.mail.s000000.common.filter.internal.entry;

public interface IFilterEntryChangedListener {
  IFilterEntryChangedListener NULL = new IFilterEntryChangedListener() {

    @Override
    public void onAddFilter(FilterEntryComponent filterWithButtonsComponent) {
    }

    @Override
    public void onRemoveFilter(FilterEntryComponent filterWithButtonsComponent) {
    }
  };

  void onAddFilter(FilterEntryComponent filterWithButtonsComponent);

  void onRemoveFilter(FilterEntryComponent filterWithButtonsComponent);
}
