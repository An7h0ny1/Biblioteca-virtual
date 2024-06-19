package com.anthony.biblioteca_virtual.feedback;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeedBackResponse {

    private Double rating;
    private String comment;
    private boolean ownFeedback;
}
