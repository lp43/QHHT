package com.qhhtofficial.qhht.model;

/**
 * Created by ehs_app_1 on 2018/1/3.
 */

public class QhhtObj {
    private String Header;

    private Group[] Group;

    public String getHeader ()
    {
        return Header;
    }

    public void setHeader (String Header)
    {
        this.Header = Header;
    }

    public Group[] getGroup ()
    {
        return Group;
    }

    public void setGroup (Group[] Group)
    {
        this.Group = Group;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Header = "+Header+", Group = "+Group+"]";
    }
}
