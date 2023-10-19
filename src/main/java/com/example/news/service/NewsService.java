package com.example.news.service;



import com.example.news.dto.NewsDto;
import com.example.news.dto.ResponseDto;
import com.example.news.service.mapper.NewsMapper;
import com.example.news.model.News;
import com.example.news.repository.NewsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class NewsService {
    private final NewsRepository newsRepository;
    private final NewsMapper newsMapper;
    private final ImageService imageService;

    public ResponseDto<List<NewsDto>> getAll(){
        return ResponseDto.<List<NewsDto>>builder()
                .message("OK")
                .success(true)
                .data(newsRepository.findAll().stream().map(newsMapper::toDto).toList())
                .build();
    }

    public ResponseDto<NewsDto> getById(Integer id) {
        if (id == null) return ResponseDto.<NewsDto>builder()
                .message("ID_IS_NULL")
                .build();
        try{
            Optional<News> newsOptional = newsRepository.findById(id);

            if (newsOptional.isEmpty()) {
                return ResponseDto.<NewsDto>builder()
                        .message("NOT_FOUND")
                        .build();
            }
            NewsDto newsDto = null;
            newsDto = newsMapper.toDto(newsOptional.get());
            return ResponseDto.<NewsDto>builder()
                    .success(true)
                    .message("OK")
                    .data(newsDto)
                    .build();
        } catch (Exception e){
            return ResponseDto.<NewsDto>builder()
                    .message("DATABASE_ERROR : " + e.getMessage())
                    .build();
        }
    }

    public ResponseDto<NewsDto> delete(Integer id){
        if (id == null) return ResponseDto.<NewsDto>builder()
                .message("ID_IS_NULL")
                .build();
        try {
            Optional<News> newsOptional = newsRepository.findById(id);
            if (newsOptional.isEmpty()) {
                return ResponseDto.<NewsDto>builder()
                        .message("NOT_FOUND")
                        .build();
            }
            NewsDto newsDto = newsMapper.toDto(newsOptional.get());
            newsRepository.delete(newsOptional.get());
            return ResponseDto.<NewsDto>builder()
                    .message("OK")
                    .success(true)
                    .data(newsDto)
                    .build();
        }catch (Exception e){
            return ResponseDto.<NewsDto>builder()
                    .message("DATABASE_ERROR : " + e.getMessage())
                    .build();
        }
    }

    public ResponseDto<NewsDto> addNews(NewsDto newsDto) {
        try {
            imageService.saveFile(newsDto.getImage());

            News entity = newsMapper.toEntity(newsDto);

            newsRepository.save(entity);
            newsDto.setImage(null);
            return ResponseDto.<NewsDto>builder()
                    .message("OK")
                    .success(true)
                    .data(newsDto)
                    .build();
        }catch (Exception e){
            return ResponseDto.<NewsDto>builder()
                    .message("DATABASE_ERROR : " + e.getMessage())
                    .build();
        }
    }

    public ResponseDto<NewsDto> update(NewsDto newsDto) {
        if (newsDto.getId() == null) return ResponseDto.<NewsDto>builder()
                .message("ID_IS_NULL")
                .build();

        try{
            Optional<News> byId = newsRepository.findById(newsDto.getId());

            if (byId.isEmpty()) {
                return ResponseDto.<NewsDto>builder()
                        .message("NOT_FOUND")
                        .build();
            }

            News news = byId.get();

            if (newsDto.getDescription() != null){
                news.setDescription(newsDto.getDescription());
            }
            if (newsDto.getTitle() != null){
                news.setTitle(newsDto.getTitle());
            }
            if (newsDto.getImage() != null){
                news.setImageUrl(imageService.saveFile(newsDto.getImage()));
            }

            newsRepository.save(news);

            return ResponseDto.<NewsDto>builder()
                    .message("OK")
                    .success(true)
                    .data(newsMapper.toDto(news))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<NewsDto>builder()
                    .message("DATABASE_ERROR : " + e.getMessage())
                    .build();
        }
    }
}