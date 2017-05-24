package com.epam.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yeralin Munar
 *         date: 5/23/17
 */
public class Book extends BaseEntity{
    private static final String DRAFT = "draft";
    private static final String PUBLIC = "public";
    private static final String ARCHIVE = "archive";

    private List<Section> sections = new ArrayList<Section>();
    private User author;
    private Department department;
    private Faculty faculty;
    private Subject subject;
    private String status = DRAFT;

    public Book() {}

    public Book(Integer id, String name, User author, Department department, Faculty faculty, Subject subject) {
        super(id, name);
        if (id != null && !name.equals("") && author != null
                && department != null && faculty != null && subject != null) {
            this.author = author;
            this.department = department;
            this.faculty = faculty;
            this.subject = subject;
        }
    }

    public List<Section> getSections() {
        return sections;
    }

    public User getAuthor() {
        return author;
    }

    public Department getDepartment() {
        return department;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public Subject getSubject() {
        return subject;
    }

    public String getStatus() {
        return status;
    }

    public void setSections(List<Section> sections) {
        if (!sections.isEmpty())
            this.sections = sections;
    }

    public void addSection(List<Section> section){
        if (section != null)
            sections.add((Section) section);
    }

    public void setAutor(User autor) {
        if (autor != null)
            this.author = autor;
    }

    public void setDepartment(Department department) {
        if (department != null)
            this.department = department;
    }

    public void setFaculty(Faculty faculty) {
        if (faculty != null)
            this.faculty = faculty;
    }

    public void setSubject(Subject subject) {
        if (subject != null)
            this.subject = subject;
    }

    public void setStatus(String status) {
        if (!status.equals("") && status != null)
            this.status = status;
    }

    public boolean isPublic(){
        return status.equals(PUBLIC);
    }
}
