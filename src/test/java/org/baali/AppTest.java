package org.baali;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;

import org.baali.model.JsonPropertySetter;
import org.baali.model.RawBean;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void jsonRawTest() throws JsonProcessingException
    {
        RawBean bean = new RawBean("My bean", "{\"attr\":false}");

        String result = new ObjectMapper().writeValueAsString(bean);

        System.out.println(result);

        assertThat(result, containsString("My bean"));
        assertThat(result, containsString("{\"attr\":false}"));
    }

    @Test
    public void jsonTesterTest() throws JsonProcessingException
    {
        JsonPropertySetter jsonPropertySetter = new JsonPropertySetter();
        jsonPropertySetter.setId(1);
        jsonPropertySetter.setName("ABC");

        String result = new ObjectMapper().writeValueAsString(jsonPropertySetter);
        System.out.println(result);

        ObjectMapper mapper = new ObjectMapper();
        JavaType userType = mapper.getTypeFactory().constructType(JsonPropertySetter.class);
        BeanDescription introspection = mapper.getSerializationConfig().introspect(userType);
        List<BeanPropertyDefinition> properties = introspection.findProperties();
        //introspection.
        System.out.println(properties.get(1).getField().getAnnotated().getDeclaredAnnotations()[0] instanceof JsonProperty);
        if (properties.get(1).getField().getAnnotated().getDeclaredAnnotations()[0] instanceof JsonProperty) {
            JsonProperty property = (JsonProperty) properties.get(1).getField().getAnnotated().getDeclaredAnnotations()[0];
            //property.se
//            property.value() = "";
            System.out.println(property.value());
            //System.out.println(Arrays.toString(property.getClass().getFields()));
        }


        assertTrue("alse", true);
    }
}
