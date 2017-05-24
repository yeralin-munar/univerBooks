package com.epam.entity;

import com.epam.constant.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yeralin Munar
 *         date: 5/23/17
 */
public class User extends BaseEntity {
    private List<Book> books = new ArrayList<>();
    private Faculty faculty;
    private Department department;
    private Subject subject;
    private String firstName;
    private String secondName;
    private String middleName;
    private Permission permission;
    private String role;

    public User() {}

    public User(Integer id, String name, String secondName, String middleName) {
        super(id, name);
        if (name != null && name.equals("")
                && secondName != null && secondName.equals("")
                && middleName != null && middleName.equals("")) {
            this.firstName = super.getName();
            this.secondName = secondName;
            this.middleName = middleName;
        }
    }

    public User(Integer id, String name, String secondName, String middleName,
                Faculty faculty, Department department, Subject subject) {
        super(id, name);
        if (name != null && name.equals("")
                && secondName != null && secondName.equals("")
                && middleName != null && middleName.equals("")
                && faculty != null && department != null && subject != null){
            this.faculty = faculty;
            this.department = department;
            this.subject = subject;
            this.firstName = super.getName();
            this.secondName = secondName;
            this.middleName = middleName;
        }
    }

    public List<Book> getBooks() {
        return books;
    }

    public Department getDepartment() {
        return department;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public Permission getPermission() {
        if (this.permission == null)
            this.permission = new Permission();
        return permission;
    }

    public String getRole() {
        return role;
    }

    public void setBooks(List<Book> books) {
        if (!books.isEmpty())
            this.books = books;
    }

    public void setDepartment(Department department) {
        if (department != null)
            this.department = department;
    }

    public void setFaculty(Faculty faculty) {
        if (faculty != null)
            this.faculty = faculty;
    }

    public void setFirstName(String firstName) {
        if (firstName != null && !secondName.isEmpty())
            this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        if (secondName != null && !secondName.isEmpty())
            this.secondName = secondName;
    }

    public void setMiddleName(String middleName) {
        if (middleName != null && !secondName.isEmpty())
            this.middleName = middleName;
    }

    public void setPermission(Permission permission) {
        if (permission != null)
            this.permission = permission;
    }

    public void setRole(String role) {
        if (role != null && !role.isEmpty())
            this.role = role;
    }

    private int getSitePartId(String sitePart){
        int id = -1;
        switch (sitePart){
            case Constant.FACULTY: id = faculty.getId(); break;
            case Constant.DEPARTMENT: id = department.getId(); break;
            case Constant.SUBJECT: id = subject.getId(); break;
        }
        return id;
    }

    public boolean hasAddPermission(String sitePart){
        Permission permission = getPermission();
        int id = getSitePartId(sitePart);
        return permission.hasPermissionFor(id, Constant.ADD, sitePart);
    }

    public boolean hasEditPermission(String sitePart){
        Permission permission = getPermission();
        int id = getSitePartId(sitePart);
        return permission.hasPermissionFor(id, Constant.EDIT, sitePart);
    }

    public boolean hasDeletePermission(String sitePart){
        Permission permission = getPermission();
        int id = getSitePartId(sitePart);
        return permission.hasPermissionFor(id, Constant.DELETE, sitePart);
    }

    public boolean hasReadPermission(String sitePart){
        Permission permission = getPermission();
        int id = getSitePartId(sitePart);
        return permission.hasPermissionFor(id, Constant.READ, sitePart);
    }
}
