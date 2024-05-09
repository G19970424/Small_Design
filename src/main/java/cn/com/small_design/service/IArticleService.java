package cn.com.small_design.service;

import cn.com.small_design.controller.business.dto.ArticleDto;
import cn.com.small_design.dao.dao.pojo.Article;

import java.util.List;

public interface IArticleService {

    List<Article> query();

    void insert(ArticleDto articleDto);

    void delete(String id);
}
