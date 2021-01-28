package com.example.posts.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnalyticsDTO {
    private int channel_id;

    private String userId;

    private String category;

    private String type;

    private String typeId;

    private String action;

    //time:(java.sql.TimeStamp or any other timestamp format that can be converted to java.time.LocalDateTime)
    private LocalDateTime time;
}
