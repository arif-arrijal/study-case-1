package com.mitrais.rms.controller;

import com.mitrais.rms.model.User;
import com.mitrais.rms.service.UserService;
import com.mitrais.rms.service.impl.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends AbstractController
{

    private UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String path = getTemplatePath(req.getServletPath());
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(path);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String path;
        String username = req.getParameter("username");
        String password = req.getParameter("userpass");
        try {
            User loginUser = userService.login(username, password);
            HttpSession session = req.getSession(true);
            session.setAttribute("loginUser", loginUser);
            resp.sendRedirect(req.getContextPath() + "/users/list");
        } catch (RuntimeException e) {
            path = getTemplatePath(req.getServletPath());
            req.setAttribute("errorMsg", e.getMessage());
            req.getRequestDispatcher(path).forward(req, resp);
        }
    }
}
