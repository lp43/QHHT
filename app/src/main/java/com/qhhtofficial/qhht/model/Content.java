package com.qhhtofficial.qhht.model;

/**
 * Created by ehs_app_1 on 2018/1/3.
 */

public class Content {
    private String Content_Attribute;

    private String Chinese;

    private String English;

    private String Title;

    public String getContent_Attribute ()
    {
        return Content_Attribute;
    }

    public void setContent_Attribute (String Content_Attribute)
    {
        this.Content_Attribute = Content_Attribute;
    }

    public String getChinese ()
    {
        return Chinese;
    }

    public void setChinese (String Chinese)
    {
        this.Chinese = Chinese;
    }

    public String getEnglish ()
    {
        return English;
    }

    public void setEnglish (String English)
    {
        this.English = English;
    }

    public String getTitle ()
    {
        return Title;
    }

    public void setTitle (String Title)
    {
        this.Title = Title;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Content_Attribute = "+Content_Attribute+", Chinese = "+Chinese+", English = "+English+", Title = "+Title+"]";
    }
}
