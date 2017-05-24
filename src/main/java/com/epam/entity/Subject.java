package com.epam.entity;

/**
 * @author Yeralin Munar
 *         date: 5/24/17
 */
public class Subject extends BaseEntity{
    private Department department;

    public Subject() {}

    public Subject(int id, String name, Department department) {
        super(id, name);
        this.department = department;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        if (department != null)
            this.department = department;
    }
}
