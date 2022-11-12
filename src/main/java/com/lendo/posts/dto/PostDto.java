package com.lendo.posts.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
 public class PostDto {
    private long id;
    @JsonProperty("user_id")
    private long userId;
    private String title;
    private String body;

}
