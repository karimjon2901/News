package com.example.news.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class News {
    @Id
    @GeneratedValue(generator = "newsIdSeq")
    @SequenceGenerator(name = "newsIdSeq", sequenceName = "news_id_seq", allocationSize = 1)
    private Integer id;
    private String description;
    private String imageUrl;
    @CreatedDate
    @CreationTimestamp
    private LocalDateTime createdAt;
    private String title;
}
