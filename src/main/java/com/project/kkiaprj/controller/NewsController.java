package com.project.kkiaprj.controller;

import com.project.kkiaprj.domain.NewsItem;
import com.project.kkiaprj.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class NewsController {

   @Autowired
   private NewsService newsService;

    @GetMapping("/news")
    public List<NewsItem> getNews() {
        return newsService.getNews();
    }
}
