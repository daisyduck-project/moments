package com.bagusmwicaksono.daisyduckproject.moments.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public Mono<Page<PostsDto>> getAllPosts(@RequestParam(name = "page", defaultValue = "0") int page,
                                      @RequestParam(name = "size", defaultValue = "10") int size) {
        log.info("[PostsController] getAllPosts; page="+page+" size="+size);
        Pageable pageable = PageRequest.of(page, size);
        return postsService.getAllPosts(pageable);
    }

    @PostMapping("")
    @CrossOrigin(origins = "*")
    public Mono<PostsDto> createPost(@RequestBody PostsDto newDto){
        log.info("[PostsController] createPost credDto="+newDto.toString());
        return postsService.performCreatePost(newDto);
    }
}
