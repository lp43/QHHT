package com.qhhtofficial.qhht.model;

/**
 * Created by ehs_app_1 on 2018/1/3.
 */

public class Group {
    private Data Data;

    private String Group_Name;

    private String Group_Note;

    public Data getData ()
    {
        return Data;
    }

    public void setData (Data Data)
    {
        this.Data = Data;
    }

    public String getGroup_Name ()
    {
        return Group_Name;
    }

    public void setGroup_Name (String Group_Name)
    {
        this.Group_Name = Group_Name;
    }

    public String getGroup_Note ()
    {
        return Group_Note;
    }

    public void setGroup_Note (String Group_Note)
    {
        this.Group_Note = Group_Note;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Data = "+Data+", Group_Name = "+Group_Name+", Group_Note = "+Group_Note+"]";
    }
}
