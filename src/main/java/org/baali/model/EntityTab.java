package org.baali.model;

public class EntityTab
{
    private String attr1;
    private String attr2;
    private String attr3;
    private String attr4;

    public void setAttr1(String attr1)
    {
        this.attr1 = attr1;
    }

    public String getAttr1()
    {
        return attr1;
    }

    public void setAttr2(String attr2)
    {
        this.attr2 = attr2;
    }

    public String getAttr2()
    {
        return attr2;
    }

    public void setAttr3(String attr3)
    {
        this.attr3 = attr3;
    }

    public String getAttr3()
    {
        return attr3;
    }

    public void setAttr4(String attr4)
    {
        this.attr4 = attr4;
    }

    public String getAttr4()
    {
        return attr4;
    }

    @Override
    public String toString()
    {
        return "EntityTab{" +
                "attr1='" + attr1 + '\'' +
                ", attr2='" + attr2 + '\'' +
                ", attr3='" + attr3 + '\'' +
                ", attr4='" + attr4 + '\'' +
                '}';
    }
}