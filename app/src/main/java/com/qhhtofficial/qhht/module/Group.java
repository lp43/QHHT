package com.qhhtofficial.qhht.module;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class Group extends ExpandableGroup<Content> {

  private int iconResId;

  public Group(String title, List<Content> items, int iconResId) {
    super(title, items);
    this.iconResId = iconResId;
  }

  public int getIconResId() {
    return iconResId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Group)) return false;

    Group group = (Group) o;

    return getIconResId() == group.getIconResId();

  }

  @Override
  public int hashCode() {
    return getIconResId();
  }
}

