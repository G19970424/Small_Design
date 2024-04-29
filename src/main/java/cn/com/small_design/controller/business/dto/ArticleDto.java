package cn.com.small_design.controller.business.dto;

/**
 * @author gejj
 * @create 2024年04月29日 14:28
 * @version 1.0
 */
public class ArticleDto {
    private String title;
    private String label;
    private String content;

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
}
