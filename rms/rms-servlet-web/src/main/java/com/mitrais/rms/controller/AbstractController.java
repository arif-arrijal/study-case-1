package com.mitrais.rms.controller;

import com.mitrais.rms.model.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

public abstract class AbstractController extends HttpServlet
{
    private static final String VIEW_PREFIX = "/WEB-INF/jsp";
    private static final String VIEW_SUFFIX = ".jsp";

    String getTemplatePath(String path) {
        if (path.equalsIgnoreCase("/"))
        {
            return VIEW_PREFIX + path + "index" + VIEW_SUFFIX;
        }
        else
        {
            return VIEW_PREFIX + path + VIEW_SUFFIX;
        }
    }

    Optional<User> getLoginUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("loginUser");
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return Optional.empty();
        } else {
            return Optional.of(user);
        }
    }
}
