package com.epam.entity;

/**
 * @author Yeralin Munar
 *         date: 5/23/17
 */
public abstract class BaseEntity {
    private Integer id = null;
    private String name = "";

    public BaseEntity(){
    }

    public BaseEntity(Integer id) {
        if (id != null)
        this.id = id;
    }

    public BaseEntity(Integer id, String name){
        if (id != null && name != null && !name.equals("")){
            this.id = id;
            this.name = name;
        }
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Integer id) {
        if (id != null)
            this.id = id;
    }

    public void setName(String name) {
        if (name != null && !name.equals(""))
            this.name = name;
    }
}
