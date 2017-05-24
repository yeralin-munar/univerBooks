package com.epam.entity;

import com.epam.constant.Constant;

/**
 * @author Yeralin Munar
 *         date: 5/24/17
 */
public class Permission extends BaseEntity{
    final static String USER = "user";
    final static String ADMIN = "admin";
    final static String ADMIN_DEPARTMENT = "admin_department";
    final static String TEACHER = "teacher";

    String role = USER;
    Integer site = 1;
    Integer users = 0;
    Integer faculty = 0;
    Integer department = 0;
    Integer subject = 0;

    Boolean add = false;
    Boolean edit = false;
    Boolean delete = false;
    Boolean read = true;

    public Permission() {}

    public Permission(Integer id, String name,
                      Integer site, Integer users,
                      Integer faculty, Integer department, Integer subject,
                      Boolean add, Boolean edit, Boolean delete, Boolean read) {
        super(id, name);
        if (id != null && name != null && !name.isEmpty()
                && site != null && users != null
                && faculty != null && department != null && subject != null
                && add != null && edit != null && delete != null && read != null) {
            this.role = super.getName();
            this.site = site;
            this.users = users;
            this.faculty = faculty;
            this.department = department;
            this.subject = subject;
            this.add = add;
            this.edit = edit;
            this.delete = delete;
            this.read = read;
        }
    }

    public String getRole() {
        return role;
    }

    public Integer getSite() {
        return site;
    }

    public Integer getUsers() {
        return users;
    }

    public Integer getFaculty() {
        return faculty;
    }

    public Integer getDepartment() {
        return department;
    }

    public Integer getSubject() {
        return subject;
    }

    public Boolean getAdd() {
        return add;
    }

    public Boolean getEdit() {
        return edit;
    }

    public Boolean getDelete() {
        return delete;
    }

    public Boolean getRead() {
        return read;
    }

    public boolean hasPermissionFor(int id, String action, String sitePart){
        if (add && action.equals(Constant.ADD) ||
                edit && action.equals(Constant.EDIT) ||
                delete && action.equals(Constant.DELETE) ||
                read && action.equals(Constant.READ)) {
            if (site == 1)
                return true;
            switch (sitePart){
                case Constant.USERS:
                    if (!users.equals(0))
                        return true;
                    break;
                case Constant.FACULTY:
                    if (users.equals(2) && faculty != null && faculty.equals(id))
                        return true;
                    break;
                case Constant.DEPARTMENT:
                    if (users.equals(2) && department != null && department.equals(id))
                        return true;
                    break;
                case Constant.SUBJECT:
                    if (users.equals(2) && subject != null && subject.equals(id))
                        return true;
                    break;
            }
        }
        return false;
    }
}
