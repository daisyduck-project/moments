package com.bagusmwicaksono.daisyduckproject.moments.controller;

import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.bagusmwicaksono.daisyduckproject.moments.controller.dto.PostsDto;
import com.bagusmwicaksono.daisyduckproject.moments.service.PostsService;
import com.bagusmwicaksono.daisyduckproject.moments.utils.TestUtils;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

import reactor.core.publisher.Flux;

@WebFluxTest(PostsController.class)
class PostsControllerTest {
    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private PostsService postsService;

    @Test
    void testGetAllPosts_WhenSuccess_ReturnCorrectData()
            throws StreamReadException, DatabindException, BeansException, IOException {
        List<PostsDto> postsDtoList = TestUtils.getPostsDtoTestEData();
        when(postsService.getAllPosts()).thenReturn(Flux.fromIterable(postsDtoList));

        webTestClient.get().uri("/v1/posts")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(PostsDto[].class);
    }
}
