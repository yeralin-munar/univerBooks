package com.epam.entity;

/**
 * @author Yeralin Munar
 *         date: 5/23/17
 */
public class Paragraph extends BaseEntity {
    private Integer order = 0;
    private String content = "";

    public Paragraph() {}

    public Paragraph(Integer id, String name) {
        super(id, name);
    }

    public Paragraph(Integer id, String name, String content) {
        super(id, name);
        if (content != null & !content.equals(""))
            this.content = content;
    }

    public Paragraph(Integer id, String name, Integer order, String content) {
        super(id, name);
        if (order != null && content != null & !content.equals("")){
            this.order = order;
            this.content = content;
        }
    }

    public Integer getOrder() {
        return order;
    }

    public String getContent() {
        return content;
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
