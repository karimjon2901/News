package com.example.news.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewsDto {
    private Integer id;
    private String description;
    private MultipartFile image;
    private String imageUrl;
    private LocalDateTime createdAt;
    private String title;
}