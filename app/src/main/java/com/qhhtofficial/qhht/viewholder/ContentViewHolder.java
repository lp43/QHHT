package com.qhhtofficial.qhht.viewholder;

import android.view.View;
import android.widget.TextView;

import com.qhhtofficial.qhht.R;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

public class ContentViewHolder extends ChildViewHolder {
  private TextView textViewTitle;
  private TextView textView1;
  private TextView textView2;

  public ContentViewHolder(View itemView) {
    super(itemView);

    textViewTitle = (TextView) itemView.findViewById(R.id.tvTitle);
    textView1 = (TextView) itemView.findViewById(R.id.list_item_1);
    textView2 = (TextView) itemView.findViewById(R.id.list_item_2);
  }

  public void setContent(String title, String text1, String text2) {
    textViewTitle.setText(title);
    textView1.setText(text1);
    textView2.setText(text2);
    textView2.getPaint().setFakeBoldText(true);
  }

  public void setOnClickListener(View.OnClickListener listener){
    itemView.setOnClickListener(listener);
  }
}
