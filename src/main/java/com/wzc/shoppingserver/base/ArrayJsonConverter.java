package com.wzc.shoppingserver.base;


import com.alibaba.fastjson.JSONArray;

import javax.persistence.AttributeConverter;
import java.util.Arrays;
import java.util.List;


public class ArrayJsonConverter implements AttributeConverter<JSONArray, String> {

    @Override
    public String convertToDatabaseColumn(JSONArray techSpecs){
        String technicalSpecs = null;
        try {
            technicalSpecs = techSpecs.toJSONString();
//            technicalSpecs = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(techSpecs);

        } catch (final Exception e) {
            System.out.println(e);
        }
        return technicalSpecs;
    }

    @Override
    public JSONArray convertToEntityAttribute(String techSpecJSON){
        JSONArray techSpecs = null;
        try {
            techSpecs = JSONArray.parseArray(techSpecJSON);
//            techSpecs =  objectMapper.readValue(techSpecJSON, objectMapper.getTypeFactory().constructMapType(HashMap.class, String.class, Specification.class));
        } catch (final Exception e) {
            System.out.println(e);
        }
        return techSpecs;
    }
}
