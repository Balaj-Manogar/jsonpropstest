package org.baali.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OutModel
{
    private Long id;

    @JsonProperty("attr1")
    private String attr1;

    @JsonProperty("attr2")
    private String attr2;

    @JsonProperty("attr3")
    private String attr3;

    @JsonProperty("attr4")
    private String attr4;

    @JsonProperty("attr5")
    private String attr5;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getAttr1()
    {
        return attr1;
    }

    public void setAttr1(String attr1)
    {
        this.attr1 = attr1;
    }

    public String getAttr2()
    {
        return attr2;
    }

    public void setAttr2(String attr2)
    {
        this.attr2 = attr2;
    }

    public String getAttr3()
    {
        return attr3;
    }

    public void setAttr3(String attr3)
    {
        this.attr3 = attr3;
    }

    public String getAttr4()
    {
        return attr4;
    }

    public void setAttr4(String attr4)
    {
        this.attr4 = attr4;
    }

    public String getAttr5()
    {
        return attr5;
    }

    public void setAttr5(String attr5)
    {
        this.attr5 = attr5;
    }

    @Override
    public String toString()
    {
        return "OutModel{" +
                "id=" + id +
                ", attr1='" + attr1 + '\'' +
                ", attr2='" + attr2 + '\'' +
                ", attr3='" + attr3 + '\'' +
                ", attr4='" + attr4 + '\'' +
                ", attr5='" + attr5 + '\'' +
                '}';
    }
}
