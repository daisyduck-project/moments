package com.bagusmwicaksono.daisyduckproject.moments.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;

import com.bagusmwicaksono.daisyduckproject.moments.controller.dto.PostsDto;
import com.bagusmwicaksono.daisyduckproject.moments.model.Posts;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class TestUtils {
    public static List<Posts> getPostsTestData() throws StreamReadException, DatabindException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        InputStream inJson = Posts.class.getResourceAsStream("/Posts.json");
        return Arrays.asList(mapper.readValue(inJson, Posts[].class));
    }
    public static List<PostsDto> getPostsDtoTestEData() throws StreamReadException, DatabindException, BeansException, IOException{
        List<PostsDto> dto = new ArrayList<PostsDto>();
        BeanUtils.copyProperties(getPostsTestData(), dto);
        return dto;
    }
}
