package com.example.posts.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"userId","fileURL"}))
public class Post {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String postId;

    private String userId;

    private String postCategory;

    private String profileType;

    @CreatedDate
    private Date uploadTime;

    private String fileType;

    private String fileURL;

    private String businessId;

    private boolean isApproved;
}
