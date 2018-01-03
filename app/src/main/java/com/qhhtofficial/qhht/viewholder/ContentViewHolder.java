package com.qhhtofficial.qhht.viewholder;

import android.view.View;
import android.widget.TextView;

import com.qhhtofficial.qhht.R;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

public class ContentViewHolder extends ChildViewHolder {

  private TextView childTextView;

  public ContentViewHolder(View itemView) {
    super(itemView);
    childTextView = (TextView) itemView.findViewById(R.id.list_item_artist_name);
  }

  public void setContent(String name) {
    childTextView.setText(name);
  }

  public void setOnClickListener(View.OnClickListener listener){
    itemView.setOnClickListener(listener);
  }
}
