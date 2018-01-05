package com.qhhtofficial.qhht.viewholder;

import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.qhhtofficial.qhht.R;
import com.qhhtofficial.qhht.api.Section;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import static android.view.animation.Animation.RELATIVE_TO_SELF;

public class SectionViewHolder extends com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder {

  private TextView sectionName;
  private ImageView arrow;
  private ImageView icon;

  public SectionViewHolder(View itemView) {
    super(itemView);
    sectionName = (TextView) itemView.findViewById(R.id.list_item_section_name);
    arrow = (ImageView) itemView.findViewById(R.id.list_item_section_arrow);
    icon = (ImageView) itemView.findViewById(R.id.list_item_section_icon);
  }

  public void setSectionTitle(ExpandableGroup section) {
    if (section instanceof Section) {
      sectionName.setText(section.getTitle());
      icon.setBackgroundResource(((Section) section).getIconResId());
    }

  }

  @Override
  public void expand() {
    animateExpand();
  }

  @Override
  public void collapse() {
    animateCollapse();
  }

  private void animateExpand() {
    RotateAnimation rotate =
        new RotateAnimation(360, 180, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
    rotate.setDuration(300);
    rotate.setFillAfter(true);
    arrow.setAnimation(rotate);
  }

  private void animateCollapse() {
    RotateAnimation rotate =
        new RotateAnimation(180, 360, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
    rotate.setDuration(300);
    rotate.setFillAfter(true);
    arrow.setAnimation(rotate);
  }
}
