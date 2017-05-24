package com.epam.entity;

/**
 * @author Yeralin Munar
 *         date: 5/23/17
 */
public class Faculty extends BaseEntity {
    private String abbreviation = "";

    public Faculty(){};

    public Faculty(Integer id, String name) {
        super(id, name);
    }

    public Faculty(Integer id, String name, String abbreviation) {
        super(id, name);
        if (abbreviation != null && !abbreviation.equals(""))
            this.abbreviation = abbreviation;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        if (abbreviation != null && !abbreviation.equals(""))
            this.abbreviation = abbreviation;
    }
}
