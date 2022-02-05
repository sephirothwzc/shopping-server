package com.wzc.shoppingserver.base;

import com.alibaba.fastjson.JSONObject;

import javax.persistence.AttributeConverter;

public class HashMapConverter implements AttributeConverter<JSONObject, String> {

    @Override
    public String convertToDatabaseColumn(JSONObject techSpecs){
        String technicalSpecs = null;
        try {
            technicalSpecs = JSONObject.toJSONString(techSpecs);
//            technicalSpecs = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(techSpecs);

        } catch (final Exception e) {
            System.out.println(e);
        }
        return technicalSpecs;
    }

    @Override
    public JSONObject convertToEntityAttribute(String techSpecJSON){
        JSONObject techSpecs = null;
        try {
            techSpecs = JSONObject.parseObject(techSpecJSON);
//            techSpecs =  objectMapper.readValue(techSpecJSON, objectMapper.getTypeFactory().constructMapType(HashMap.class, String.class, Specification.class));
        } catch (final Exception e) {
            System.out.println(e);
        }
        return techSpecs;
    }
}
