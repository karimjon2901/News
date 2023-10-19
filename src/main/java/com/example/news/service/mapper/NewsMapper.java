package com.example.news.service.mapper;

import com.example.news.dto.NewsDto;
import com.example.news.model.News;
import com.example.news.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
@RequiredArgsConstructor
public abstract class NewsMapper implements CommonMapper<News, NewsDto>{
    @Autowired
    ImageService imageService;

    @Override
    @Mapping(target = "imageUrl", expression = "java(imageService.saveFile(newsDto.getImage()))")
    public abstract News toEntity(NewsDto newsDto);

    @Override
    //@Mapping(target = "bytes",expression = "java(imageService.getImage(news.getImageUrl()))")
    public abstract NewsDto toDto(News news);
}
