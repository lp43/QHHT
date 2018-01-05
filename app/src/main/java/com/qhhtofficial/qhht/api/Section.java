package com.qhhtofficial.qhht.api;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class Section extends ExpandableGroup<Content> {

  private int iconResId;

  public Section(String title, List<Content> items, int iconResId) {
    super(title, items);
    this.iconResId = iconResId;
  }

  public int getIconResId() {
    return iconResId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Section)) return false;

    Section section = (Section) o;

    return getIconResId() == section.getIconResId();

  }

  @Override
  public int hashCode() {
    return getIconResId();
  }
}

