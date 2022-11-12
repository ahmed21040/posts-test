package com.lendo.posts.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private long id;
    @JsonProperty("post_id")
    private long postId;
    private String name;
    private String body;
    private String email;

}
