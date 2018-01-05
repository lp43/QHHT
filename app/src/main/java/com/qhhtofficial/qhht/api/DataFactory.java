package com.qhhtofficial.qhht.api;


import android.text.TextUtils;

import com.qhhtofficial.qhht.Constants;
import com.qhhtofficial.qhht.R;
import com.qhhtofficial.qhht.model.Group;
import com.qhhtofficial.qhht.model.QhhtObj;

import java.util.ArrayList;
import java.util.List;

public class DataFactory {

  public static List<Section> makeSections(QhhtObj data) {
    ArrayList<Section> arrayList = new ArrayList<>();

    Group[] groups = data.getGroup();
    if(groups!=null && groups.length>0){
      for (int i = 0; i < groups.length; i++) {
        arrayList.add(new Section(groups[i].getGroup_Name(), makeContents(groups[i].getData().getContent()), R.drawable.ic_electric_guitar));
      }
    }
    return arrayList;
  }

  private static List<Content> makeContents(com.qhhtofficial.qhht.model.Content[] contents) {
    ArrayList<Content> contentArrayList = new ArrayList<>();

    if(contents!=null && contents.length>0){
      for (int j = 0; j < contents.length; j++) {

        String attribute = contents[j].getContent_Attribute();
        boolean hasSound = false;
        if(attribute.toLowerCase().contains(Constants.SOUND)){
          hasSound = true;
        }
        String title = TextUtils.isEmpty(contents[j].getTitle())?"":contents[j].getTitle();
        String eng = TextUtils.isEmpty(contents[j].getEnglish())?"":contents[j].getEnglish();
        String chi = TextUtils.isEmpty(contents[j].getChinese())?"":contents[j].getChinese();
        Content content = new Content(title, eng, chi, hasSound);
        contentArrayList.add(content);

      }
    }
    return contentArrayList;
  }

}

