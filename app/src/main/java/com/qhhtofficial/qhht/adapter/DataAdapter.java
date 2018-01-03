package com.qhhtofficial.qhht.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.qhhtofficial.qhht.R;
import com.qhhtofficial.qhht.module.Content;
import com.qhhtofficial.qhht.module.Group;
import com.qhhtofficial.qhht.viewholder.ContentViewHolder;
import com.qhhtofficial.qhht.viewholder.SectionViewHolder;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class DataAdapter extends ExpandableRecyclerViewAdapter<SectionViewHolder, ContentViewHolder> {
  Activity mActivity;

  public DataAdapter(Activity activity, List<? extends ExpandableGroup> groups) {
    super(groups);
    this.mActivity = activity;
  }

  @Override
  public SectionViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.list_item_section, parent, false);
    return new SectionViewHolder(view);
  }

  @Override
  public ContentViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.list_item_content, parent, false);
    return new ContentViewHolder(view);
  }

  @Override
  public void onBindChildViewHolder(ContentViewHolder holder, int flatPosition,
                                    ExpandableGroup group, int childIndex) {

    final Content content = ((Group) group).getItems().get(childIndex);
    holder.setContent(content.getEnglish());
    holder.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Toast.makeText(mActivity, content.getEnglish(), Toast.LENGTH_SHORT).show();
      }
    });
  }

  @Override
  public void onBindGroupViewHolder(SectionViewHolder holder, int flatPosition,
                                    ExpandableGroup group) {

    holder.setSectionTitle(group);
  }
}
