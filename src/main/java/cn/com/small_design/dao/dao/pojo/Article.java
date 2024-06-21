package cn.com.small_design.dao.dao.pojo;

import java.util.Date;

/**
 * @author gejj
 * @create 2024年04月29日 13:47
 * @version 1.0
 */
public class Article {
    /**
     * 日记id
     */
    private String id;
    /**
     * 发布用户id
     */
    private int uid;
    /**
     * 日记标题
     */
    private String title;
    /**
     * 日记标签
     */
    private String label;
    /**
     * 日记内容
     */
    private String content;
    /**
     * 浏览量
     */
    private Integer heat;
    /**
     * 创建时间
     */
    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getHeat() {
        return heat;
    }

    public void setHeat(Integer heat) {
        this.heat = heat;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
