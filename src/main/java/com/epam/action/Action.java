package com.epam.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Yeralin Munar
 *         date: 5/20/17
 */
public interface Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
