package com.bagusmwicaksono.daisyduckproject.moments.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.domain.PageRequest;

import com.bagusmwicaksono.daisyduckproject.moments.model.Posts;
import com.bagusmwicaksono.daisyduckproject.moments.utils.TestUtils;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@DataMongoTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PostsRepositoryTest {
    @Autowired
    PostsRepository postsRepository;

    List<Posts> postList;

    @BeforeAll
    void setup() throws StreamReadException, DatabindException, IOException{
        postList = TestUtils.getPostsTestData();
    }

    @AfterAll
    void cleanup(){
        postsRepository.deleteAll(postList);
    }

    @Test
    void testFindAllByOrderByPostingDateDesc_WhenSuccess_RetrunCorrectValue() {
        Flux<Posts> postsFlux = postsRepository.saveAll(postList).thenMany(postsRepository.findAllByOrderByPostingDateDesc(PageRequest.of(0, 10)));
        StepVerifier.create(postsFlux)
            .assertNext(Assertions::assertNotNull)
            .assertNext(post ->{
                assertEquals("Second", post.getPostingMessage());
            })
            .assertNext(post ->{
                assertEquals("First", post.getPostingMessage());
            });
    }
}
