package com.bagusmwicaksono.daisyduckproject.moments.service;

import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.bagusmwicaksono.daisyduckproject.moments.controller.dto.PostsDto;
import com.bagusmwicaksono.daisyduckproject.moments.model.Posts;
import com.bagusmwicaksono.daisyduckproject.moments.repository.PostsRepository;
import com.bagusmwicaksono.daisyduckproject.moments.utils.TestUtils;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@ExtendWith(SpringExtension.class)
class PostsServiceTest {
    @InjectMocks
    PostsService postsService;

    @Mock
    PostsRepository postsRepository;

    @Test
    void testGetAllPosts_WhenSuccess_ReturnCorrectValue() throws StreamReadException, DatabindException, IOException {
        List<Posts> postsList = TestUtils.getPostsTestData();
        when(postsRepository.findAllByOrderByPostingDateDesc()).thenReturn(Flux.fromIterable(postsList));

        Flux<PostsDto> postDtoFlux = postsService.getAllPosts();

        StepVerifier.create(postDtoFlux).expectNextCount(2)
            .verifyComplete();

    }
}
