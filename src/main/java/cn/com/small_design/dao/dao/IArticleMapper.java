package cn.com.small_design.dao.dao;


import cn.com.small_design.dao.dao.pojo.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author gejj
 * @create 2024年04月29日 13:47
 * @version 1.0
 */
@Mapper
@Repository
public interface IArticleMapper {

    /**
     * 查询所有文章
     * @return
     */
    List<Article> query();

    /**
     * 新增文章
     * @param article
     */
    void insert(@Param("article") Article article);
}
