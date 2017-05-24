package com.epam.controller;

import com.epam.action.Action;
import com.epam.action.ActionFactory;
import com.epam.connection.ConnectionPool;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author Yeralin Munar
 *         date: 5/20/17
 */
@WebServlet(name = "controller", urlPatterns = "/*")
public class Controller extends HttpServlet {
    private static final Logger log = Logger.getLogger(Controller.class);

    private ActionFactory actionFactory;
    private ConnectionPool connectionPool;

    @Override
    public void init() throws ServletException{
        log.info("The univerBooks servlet start working.");
        actionFactory = new ActionFactory();
        connectionPool = ConnectionPool.getInstance();

    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {
            Action action = actionFactory.getAction(request);

            String[] req = request.getPathInfo().split("/");
            String rr = "";
            for (int i=0; i < req.length; i++) rr+=req[i];
            String text = request.getMethod() + " " + rr;
            response.getOutputStream().write(text.getBytes());

        } catch (Exception e){
            throw new ServletException("Executing action failed.", e);
        }
    }

    /* URLs
    * 403 - Forbidden, доступ запрещен
    * 404 - Not Found, документ не найден
    * 500 - Internal Server Error, внутренняя ошибка сервера
    * 503 - Service Temporarily Unavailable, сервис временно недоступен
    *
    * /book
    *
    * Version 1
    * /book/{id_book}/section/{id_section}/paragraph/{id_paragraph}
    * Version 2
    * /book?id_book={id_book}&id_section={id_section}&id_paragraph={id_paragraph}
    *
    * /author
    * /department
    * /faculty
    * /subject
    * */

    @Override
    public void destroy(){
        try {
            connectionPool.dispose();
            log.info("The univerBooks servlet end working.");
        } catch (SQLException e) {
            log.error("Can't close connections.", e);
        }
    }
}
