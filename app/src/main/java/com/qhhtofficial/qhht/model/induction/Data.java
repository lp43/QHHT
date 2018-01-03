package com.qhhtofficial.qhht.model.induction;

/**
 * Created by ehs_app_1 on 2018/1/3.
 */

public class Data {
    private Content[] Content;

    public Content[] getContent ()
    {
        return Content;
    }

    public void setContent (Content[] Content)
    {
        this.Content = Content;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Content = "+Content+"]";
    }
}
