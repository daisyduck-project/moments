package com.bagusmwicaksono.daisyduckproject.moments.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.bagusmwicaksono.daisyduckproject.moments.controller.dto.PostsDto;
import com.bagusmwicaksono.daisyduckproject.moments.repository.PostsRepository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Service
@Slf4j
public class PostsService {
    private final PostsRepository postsRepository;

    public PostsService(PostsRepository postsRepository) {
        this.postsRepository = postsRepository;
    }

    public Flux<PostsDto> getAllPosts(){
        log.info("[PostsService] getAllPosts");
        return postsRepository.findAllByOrderByPostingDateDesc().map(cred ->{
            PostsDto dto = new PostsDto();
            BeanUtils.copyProperties(cred, dto);
            return dto;
        });
    }
}
