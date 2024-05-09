package cn.com.small_design.service.impl;

import cn.com.small_design.common.common.UserInfo;
import cn.com.small_design.common.utils.SecurityUtil;
import cn.com.small_design.controller.business.dto.ArticleDto;
import cn.com.small_design.dao.dao.ArticleMapper;
import cn.com.small_design.dao.dao.pojo.Article;
import cn.com.small_design.service.IArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

/**
 * @author gejj
 * @create 2024年04月29日 13:43
 * @version 1.0
 */
@Service
@Transactional
public class ArticleServiceImpl implements IArticleService {

    /**
     * 日志器
     */
    private static final Logger logger = LoggerFactory.getLogger(ArticleServiceImpl.class);

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private SecurityUtil securityUtil;
    /**
     * 查询所有文章
     * @return
     */
    @Override
    public List<Article> query() {
        return articleMapper.query();
    }

    /**
     * 新增文章
     * @param articleDto 文章实体类
     */
    @Override
    public void insert(ArticleDto articleDto) {
        logger.info("新增文章{}",articleDto.getTitle());
        //获取当前用户信息
        UserInfo localUser = securityUtil.getLocalUser();

        //封装文章实体类
        Article article = dataTypeConversion(articleDto);

        //生成日志id,后续更改为用户id前10位+2位标签id+8位时间戳+12位随机数
        String id = UUID.randomUUID().toString();
        article.setId(id);
        article.setUid(localUser.getUser().getId());

        //操作日志
        //插入文章
        articleMapper.insert(article);
        logger.info("{}文章新增成功",articleDto.getTitle());
    }


    /**
     * 根据文章id删除文章
     * @param id
     */
    @Override
    public void delete(String id) {
        logger.info("删除文章{}",id);
        Article old = articleMapper.queryById(id);
        //插入日志
        //删除文章
        articleMapper.delete(id);
        logger.info("文章{}已删除",id);
    }

    private Article dataTypeConversion(ArticleDto articleDto) {
        Article article = new Article();
        article.setTitle(articleDto.getTitle());
        article.setContent(articleDto.getContent());
        article.setLabel(articleDto.getLabel());
        return article;
    }
}
