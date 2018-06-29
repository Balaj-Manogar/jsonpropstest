package org.baali.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonPropertySetter
{
    Integer id;

    @JsonProperty("userName")
    String name;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
