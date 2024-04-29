package cn.com.small_design.service.impl;

import cn.com.small_design.common.common.UserInfo;
import cn.com.small_design.common.utils.SecurityUtil;
import cn.com.small_design.controller.business.dto.ArticleDto;
import cn.com.small_design.dao.dao.IArticleMapper;
import cn.com.small_design.dao.dao.pojo.Article;
import cn.com.small_design.dao.dao.pojo.User;
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

    private static final Logger logger = LoggerFactory.getLogger(ArticleServiceImpl.class);

    @Autowired
    private IArticleMapper IArticleMapper;
    @Autowired
    private SecurityUtil securityUtil;
    /**
     * 查询所有文章
     * @return
     */
    @Override
    public List<Article> query() {
        return IArticleMapper.query();
    }

    /**
     * 新增文章
     * @param articleDto 文章实体类
     */
    @Override
    public void insert(ArticleDto articleDto) {
        //获取当前用户信息
        UserInfo localUser = securityUtil.getLocalUser();

        //封装文章实体类
        Article article = dataTypeConversion(articleDto);

        //生成日志id,后续更改为用户id前10位+2位标签id+8位时间戳+12位随机数
        String id = UUID.randomUUID().toString();
        article.setId(id);
        article.setUid(localUser.getUser().getId());

        //插入文章
        IArticleMapper.insert(article);
    }

    private Article dataTypeConversion(ArticleDto articleDto) {
        Article article = new Article();
        article.setTitle(articleDto.getTitle());
        article.setContent(articleDto.getContent());
        article.setLabel(articleDto.getLabel());
        return article;
    }
}
