package com.epam.action;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Yeralin Munar
 *         date: 5/20/17
 */
public class ActionFactory {
    private Map<String, Action> actions;

    public Action getAction(HttpServletRequest request){
        if (actions.isEmpty()){
            init();
        }
        return actions.get(request.getMethod() + request.getPathInfo());
    }

    public void init(){
        actions = new HashMap<>();
    }


}
