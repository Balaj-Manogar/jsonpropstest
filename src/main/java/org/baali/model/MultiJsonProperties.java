package org.baali.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MultiJsonProperties
{
    private int id;

    private String name;

    public MultiJsonProperties(int id, String name)
    {
        this.id = id;
        this.name = name;
    }
    public MultiJsonProperties(){}

    @JsonProperty("outputId")
    public int getId()
    {
        return id;
    }

    @JsonProperty("inputId")
    public void setId(int id)
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

    @Override
    public String toString()
    {
        return "MultiJsonProperties{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
