package com.bagusmwicaksono.daisyduckproject.moments.service;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bagusmwicaksono.daisyduckproject.moments.controller.dto.PostsDto;
import com.bagusmwicaksono.daisyduckproject.moments.model.Posts;
import com.bagusmwicaksono.daisyduckproject.moments.repository.PostsRepository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class PostsService {
    private final PostsRepository postsRepository;

    public PostsService(PostsRepository postsRepository) {
        this.postsRepository = postsRepository;
    }

    public Flux<PostsDto> getAllPosts(){
        log.info("[PostsService] getAllPosts");
        return postsRepository.findAllByOrderByPostingDateDesc(PageRequest.of(0, 10)).map(cred ->{
            PostsDto dto = new PostsDto();
            BeanUtils.copyProperties(cred, dto);
            return dto;
        });
    }
    public Mono<Page<PostsDto>> getAllPosts(Pageable pageable){
        log.info("[PostsService] getAllPosts Pageable");
        return postsRepository.findAllByOrderByPostingDateDesc(pageable).map(cred ->{
            PostsDto dto = new PostsDto();
            BeanUtils.copyProperties(cred, dto);
            return dto;
        }).collectList()
        .zipWith(postsRepository.count())
        .map(objects -> new PageImpl<>(objects.getT1(), pageable, objects.getT2()));
    }
    public Mono<PostsDto> performCreatePost(PostsDto newDto){
        log.info("[CredentialsService] performCreateCredential");
        Posts newPost = new Posts();
        BeanUtils.copyProperties(newDto,newPost);
        return postsRepository.save(newPost).map(savePost ->{
            PostsDto saveDto = new PostsDto();
            BeanUtils.copyProperties(savePost, saveDto);
            return saveDto;
        });
    }
}
