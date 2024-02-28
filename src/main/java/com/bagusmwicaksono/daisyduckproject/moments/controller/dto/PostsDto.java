package com.bagusmwicaksono.daisyduckproject.moments.controller.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostsDto {
    private String id;
    private String userId;
    private String userName;
    private String userCode;
    private String userInitial;
    private LocalDateTime postingDate;
    private String postingMessage;
    private int likes;
    private int replies;
}
