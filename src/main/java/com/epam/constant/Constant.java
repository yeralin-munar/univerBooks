package com.epam.constant;

/**
 * @author Yeralin Munar
 *         date: 5/24/17
 */
public final class Constant {
    public static final String SITE = "site";
    public static final String USERS = "users";
    public static final String FACULTY = "faculty";
    public static final String DEPARTMENT = "department";
    public static final String SUBJECT = "subject";
    public static final String AUTHOR = "author";

    public static final String BOOK = "book";
    public static final String PARAGRAPH = "paragraph";
    public static final String SECTION = "section";

    public static final String ADD = "add";
    public static final String EDIT = "edit";
    public static final String DELETE = "delete";
    public static final String READ = "read";

    /*
     *      Fields
     */
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String STATUS = "status";
    /*
     *      SQL
     */

    public static final String DELETE_BY_COLUMN = "DELETE FROM $table WHERE $column=?";
    public static final String SELECT_BY_COLUMN = "SELECT * FROM $table WHERE $column=?";
    public static final String SELECT_ALL = "SELECT * FROM $table";
    public static final String INSERT_BY_COLUMNS = "INSERT INTO %table ($columns) VALUES ($values)";
    public static final String UPDATE_BY_COLUMNS = "UPDATE &table SET ($columns) = ($values) WHERE $column=?";


}
