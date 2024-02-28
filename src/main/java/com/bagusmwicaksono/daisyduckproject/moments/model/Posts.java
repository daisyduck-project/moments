package com.bagusmwicaksono.daisyduckproject.moments.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "moments")
public class Posts {
    @Id
    private String id;
    private String userId;
    private String userName;
    private String userCode;
    private String userInitial;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime postingDate;
    private String postingMessage;
    private int likes;
    private int replies;
}
