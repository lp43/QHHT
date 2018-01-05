package com.qhhtofficial.qhht.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qhhtofficial.qhht.R;
import com.qhhtofficial.qhht.api.Content;
import com.qhhtofficial.qhht.api.Section;
import com.qhhtofficial.qhht.viewholder.ContentViewHolder;
import com.qhhtofficial.qhht.viewholder.SectionViewHolder;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class DataAdapter extends ExpandableRecyclerViewAdapter<SectionViewHolder, ContentViewHolder> {
  Activity mActivity;
  private OnItemClickListener listener;

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
  public void onBindChildViewHolder(final ContentViewHolder holder, final int flatPosition,
                                    final ExpandableGroup group, final int childIndex) {

    final Content content = ((Section) group).getItems().get(childIndex);
    holder.setContent(content.getTitle(), content.getEnglish(), content.getChinese());
    holder.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if(listener!=null){
          listener.onClick(flatPosition, group, childIndex);
        }
      }
    });

  }

  public void setOnItemClickListener(OnItemClickListener listener){
    this.listener = listener;
  }

  @Override
  public void onBindGroupViewHolder(SectionViewHolder holder, int flatPosition,
                                    ExpandableGroup group) {

    holder.setSectionTitle(group);
  }

  public interface OnItemClickListener{
    void onClick(final int flatPosition,
                 ExpandableGroup group, final int childIndex);
  }
}
