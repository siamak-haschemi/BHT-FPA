/*******************************************************************************
 * Copyright (c) 2011 - 2012 Siamak Haschemi & Benjamin Haupt
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package de.bht.fpa.mail.s000000.common.filter.entry;

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
