package org.baali;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.baali.model.ChildTab;
import org.baali.model.EntityTab;
import org.baali.model.OutModel;
import org.baali.model.Parent;
import org.baali.model.Validator;
import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class DynamicAnnotationTest
{


    static List<ChildTab> rmt1Col = new ArrayList<>();
    static List<ChildTab> rmt1Col2 = new ArrayList<>();
    static List<EntityTab> dataList = new ArrayList<>();
    static Parent config = new Parent();
    static Set<String> mandatoryields = new HashSet<>();

    @BeforeClass
    public static void init()
    {
        mandatoryields.add("id");
        config.setValidationClassName("OutModel");

        ChildTab col1 = new ChildTab();
        col1.setColumnName("attr1");
        col1.setJsonPropertyName("type");
        ChildTab col2 = new ChildTab();
        col2.setColumnName("attr2");
        col2.setJsonPropertyName("quality");
        ChildTab col3 = new ChildTab();
        col3.setColumnName("attr3");
        col3.setJsonPropertyName("material");
        ChildTab col4 = new ChildTab();
        col4.setColumnName("attr4");
        col4.setJsonPropertyName("group");
        rmt1Col.add(col1);
        rmt1Col.add(col2);
        rmt1Col.add(col3);
        rmt1Col.add(col4);
        ChildTab col12 = new ChildTab();
        col12.setColumnName("attr1");
        col12.setJsonPropertyName("name");
        ChildTab col22 = new ChildTab();
        col22.setColumnName("attr2");
        col22.setJsonPropertyName("industry");
        ChildTab col32 = new ChildTab();
        col32.setColumnName("attr3");
        col32.setJsonPropertyName("year");
        ChildTab col42 = new ChildTab();
        col42.setColumnName("attr4");
        col42.setJsonPropertyName("manufacturer");
        rmt1Col2.add(col12);
        rmt1Col2.add(col22);
        rmt1Col2.add(col32);
        rmt1Col2.add(col42);

        // this is entity
        EntityTab data1 = new EntityTab();
        data1.setAttr1("T50");
        data1.setAttr2("India");
        data1.setAttr3("Mumbai Indians");
        data1.setAttr4("ICC International");
        // this is entity
        EntityTab data2 = new EntityTab();
        data1.setAttr1("T20");
        data1.setAttr2("SriLanka");
        data1.setAttr3("Chennai Super kings");
        data1.setAttr4("Ganguly");
        // this list will come from db
        dataList.add(data1);
        dataList.add(data2);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testCase() throws InterruptedException
    {
        Thread t1 = new Thread(() -> {
            JsonObject json = new JsonObject();
            json.addProperty("id", 12345L);
            OutModel dto = new OutModel();
            Map<String, String> rmt1Map = rmt1Col.stream().collect(
                    Collectors.toMap(ChildTab::getColumnName, ChildTab::getJsonPropertyName));

            System.out.println(rmt1Map);

            Gson gson = new Gson();
            OutModel inDTO = gson.fromJson(json, OutModel.class);
            changeClassAnnotationValue(dto, rmt1Map);

            try
            {
                Class c = Class.forName("org.baali.model." + config.getValidationClassName());
                Validator v = (Validator) c.newInstance();
                v.validate(inDTO);
            }
            catch (ClassNotFoundException | InstantiationException | IllegalAccessException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("JSON: " + inDTO);
            ObjectMapper mapper = new ObjectMapper();
            try
            {
                String s = mapper.writeValueAsString(inDTO);
                System.out.println("=============" + Thread.currentThread().getName() + "==========");
                System.out.println(s);
                System.out.println("=======================");
                System.out.printf("");

            }
            catch (JsonProcessingException e)
            {
                e.printStackTrace();
            }

            //checkAnnotation();
        });
        Thread t2 = new Thread(() -> {
            JsonObject json = new JsonObject();
            json.addProperty("id", 1234999L);
            OutModel dto = new OutModel();
            Map<String, String> rmt1Map = rmt1Col2.stream().collect(
                    Collectors.toMap(ChildTab::getColumnName, ChildTab::getJsonPropertyName));

            System.out.println(rmt1Map);


            Gson gson = new Gson();
            OutModel inDTO = gson.fromJson(json, OutModel.class);
            changeClassAnnotationValue(dto, rmt1Map);
            try
            {
                System.out.printf("");
                Class c = Class.forName("org.baali.model." + config.getValidationClassName());
                Validator v = (Validator) c.newInstance();
                v.validate(inDTO);
            }
            catch (ClassNotFoundException | InstantiationException | IllegalAccessException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("JSON: " + inDTO);
            ObjectMapper mapper = new ObjectMapper();
            try
            {
                String s = mapper.writeValueAsString(inDTO);
                System.out.println("=============" + Thread.currentThread().getName() + "==========");
                System.out.println(s);
                System.out.println("=======================");

            }
            catch (JsonProcessingException e)
            {
                e.printStackTrace();
            }

            //checkAnnotation();
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        assertThat(1, is(1));
    }

    private void changeClassAnnotationValue(OutModel dto, Map<String, String> map)
    {
        Field[] declaredFields = dto.getClass().getDeclaredFields();

        Arrays.stream(declaredFields).forEach(f -> {
            System.out.println(f.getName());
            if (f.isAnnotationPresent(JsonProperty.class))
            {
                JsonProperty annotation = f.getAnnotation(JsonProperty.class);
                if (map.get(f.getName()) != null)
                {
                    changeAnnotationValue(annotation, map.get(f.getName()));
                }
            }
        });
    }

    private void checkAnnotation()
    {
        Field[] declaredFields;
        OutModel dto2 = new OutModel();
        declaredFields = dto2.getClass().getDeclaredFields();
        Arrays.stream(declaredFields).forEach(f -> {
            if (f.isAnnotationPresent(JsonProperty.class))
            {
                JsonProperty annotation = f.getAnnotation(JsonProperty.class);
                System.out.println("Aftrer " + annotation);
            }
        });
    }

    private void changeAnnotationValue(Annotation annotationClazz, String newValue)
    {
        Field f;
        Map<String, Object> memberValues;

        try
        {
            Object a = Proxy.newProxyInstance(annotationClazz.getClass().getClassLoader(), new Class[]{Annotation
                    .class}, (d, e, g) -> null);
            Object handler = Proxy.getInvocationHandler(annotationClazz);
            System.out.println("Declared Fields: " + Arrays.toString(handler.getClass().getDeclaredFields()));
            f = handler.getClass().getDeclaredField("memberValues");
            f.setAccessible(true);
            System.out.println("New value: " + newValue);
            memberValues = (Map<String, Object>) f.get(handler);
            Object oldValue = memberValues.get("value");
            //memberValues.put("value", "value " + new Random().nextInt(10000));
            memberValues.put("value", newValue);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void testProxy()
    {
        Field[] declaredFields;


        OutModel dto2 = new OutModel();
        declaredFields = dto2.getClass().getDeclaredFields();
        Arrays.stream(declaredFields).forEach(f -> {
            if (f.isAnnotationPresent(JsonProperty.class))
            {
                Field fi = null;
                Map<String, Object> memberValues;
                JsonProperty annotation = f.getAnnotation(JsonProperty.class);
                Object a = Proxy.newProxyInstance(annotation.getClass().getClassLoader(), new Class[]{Annotation
                        .class}, (d, e, g) -> null);
                try
                {
                    fi = a.getClass().getDeclaredField("memberValues");
                    fi.setAccessible(true);
                    if(fi != null)
                    {
                        memberValues = (Map<String, Object>) fi.get(a);
                        Object oldValue = memberValues.get("value");
                        memberValues.put("value", "newValue");
                    }
                }
                catch (NoSuchFieldException e)
                {
                    e.printStackTrace();
                }
                catch (IllegalAccessException e)
                {
                    e.printStackTrace();
                }
                System.out.println("New value: ");
                System.out.println("Annotation: " + annotation);
            }

        });

    }

}



