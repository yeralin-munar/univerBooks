package com.epam.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yeralin Munar
 *         date: 5/23/17
 */
public class Section extends BaseEntity {
    private List<Paragraph> paragraphs = new ArrayList<Paragraph>();
    private Integer order = 0;
    private String content = "";

    public Section() {}

    public Section(Integer id, String name) {
        super(id, name);
    }

    public Section(Integer id, String name, String content) {
        super(id, name);
        if (content != null & !content.equals("")){
            this.content = content;
        }
    }

    public Section(Integer id, String name, Integer order, String content) {
        super(id, name);
        if (order != null && content != null){
            this.order = order;
            this.content = content;
        }
    }

    public Section(Integer id, String name, List<Paragraph> paragraphs, Integer order, String content) {
        super(id, name);
        if (!paragraphs.isEmpty() && order != null && content != null & !content.equals("")){
            this.paragraphs = paragraphs;
            this.order = order;
            this.content = content;
        }
    }

    public List<Paragraph> getParagraphs() {
        return paragraphs;
    }

    public Integer getOrder() {
        return order;
    }

    public String getContent() {
        return content;
    }

    public void setParagraphs(List<Paragraph> paragraphs) {
        if (!paragraphs.isEmpty())
            this.paragraphs = paragraphs;
    }

    public void setOrder(Integer order) {
        if (order != null)
            this.order = order;
    }

    public void setContent(String content) {
        if (content != null & !content.equals(""))
            this.content = content;
    }
}
