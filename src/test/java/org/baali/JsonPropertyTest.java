package org.baali;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.baali.model.MultiJsonProperties;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class JsonPropertyTest
{
    @Test
    public void testMultiJsonProperty() throws IOException
    {
        ObjectMapper mapper = new ObjectMapper();
        String input = "{\"inputId\": 1,\"name\": \"ABC\"}";

        MultiJsonProperties m = mapper.readValue(input, MultiJsonProperties.class);
        MultiJsonProperties n = new MultiJsonProperties(2, "XYZ");
        System.out.println(m);
        System.out.println(mapper.writeValueAsString(n));
        assertThat(m.getId(), is(1));

    }
}
