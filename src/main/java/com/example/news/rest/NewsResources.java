package com.example.news.rest;

import com.example.news.dto.NewsDto;
import com.example.news.dto.ResponseDto;
import com.example.news.service.NewsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
public class NewsResources {
    private final NewsService newsService;

    @Operation(
            method = "Get all news",
            description = "This endpoint return all news",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "News info",
                    content = @Content(mediaType = "application/json"))
    )
    @GetMapping
    public ResponseDto<List<NewsDto>> getAll(){
        return newsService.getAll();
    }

    @Operation(
            method = "Add new news",
            description = "Need to send NewsDto to this endpoint to create new news",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "News info",
                    content = @Content(mediaType = "multipart/form-data"))
    )
    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseDto<NewsDto> addNews(@ModelAttribute NewsDto newsDto){
        return newsService.addNews(newsDto);
    }

    @Operation(
            method = "Get news by id",
            description = "Need to send id to this endpoint to get news",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "News info",
                    content = @Content(mediaType = "application/json"))
    )
    @GetMapping("by-id")
    public ResponseDto<NewsDto> getById(@RequestParam Integer id){
        return newsService.getById(id);
    }

    @Operation(
            method = "Update news",
            description = "Need to send NewsDto to this endpoint to update news",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "News info",
                    content = @Content(mediaType = "application/json"))
    )
    @PatchMapping
    public ResponseDto<NewsDto> update(@RequestBody NewsDto newsDto){
        return newsService.update(newsDto);
    }
    @Operation(
            method = "Delete news",
            description = "Need to send news id to this endpoint to delete this news",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "News info",
                    content = @Content(mediaType = "application/json"))
    )
    @DeleteMapping("/{id}")
    public ResponseDto<NewsDto> delete(@PathVariable Integer id){
        return newsService.delete(id);
    }
}