package com.example.posts.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@Entity
@Builder
@Table(name = "pbuser")
public class User {

    @Id
    private String userId;

    private String userName;

    private String userImgURL;
}
