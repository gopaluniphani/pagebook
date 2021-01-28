package com.example.posts.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pbuser")
public class User {

    @Id
    private String userId;

    private String userName;

    private String userImgURL;
}
