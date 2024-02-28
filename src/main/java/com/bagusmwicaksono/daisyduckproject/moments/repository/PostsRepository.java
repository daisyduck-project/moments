package com.bagusmwicaksono.daisyduckproject.moments.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.bagusmwicaksono.daisyduckproject.moments.model.Posts;

import reactor.core.publisher.Flux;

public interface PostsRepository extends ReactiveMongoRepository<Posts, String> {
    Flux<Posts> findAllByOrderByPostingDateDesc();
}
