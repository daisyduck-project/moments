package com.bagusmwicaksono.daisyduckproject.moments.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bagusmwicaksono.daisyduckproject.moments.controller.dto.PostsDto;
import com.bagusmwicaksono.daisyduckproject.moments.service.PostsService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("v1/posts")
@Slf4j
public class PostsController {
    private final PostsService postsService;

    public PostsController(PostsService postsService) {
        this.postsService = postsService;
    }

    @GetMapping("")
    @CrossOrigin(origins = "*")
    public Flux<PostsDto> getAllPosts() {
        log.info("[PostsController] getAllPosts");
        return postsService.getAllPosts();
    }

    @PostMapping("")
    @CrossOrigin(origins = "*")
    public Mono<PostsDto> createPost(@RequestBody PostsDto newDto){
        log.info("[PostsController] createPost credDto="+newDto.toString());
        return postsService.performCreatePost(newDto);
    }
}
