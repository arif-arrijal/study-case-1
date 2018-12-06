package com.mitrais.rms.controller;

import com.mitrais.rms.model.User;
import com.mitrais.rms.service.UserService;
import com.mitrais.rms.service.impl.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/users/*")
public class UserServlet extends AbstractController
{
    private UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        Optional<User> loginUser = getLoginUser(req, resp);
        if (loginUser.isPresent()) {
            req.setAttribute("loginUser", loginUser.get());

            String[] pathInfoArr = req.getPathInfo().split("/");

            String path = null;
            if ("/list".equalsIgnoreCase(req.getPathInfo())){
                path = getTemplatePath(req.getServletPath()+req.getPathInfo());
                List<User> users = userService.getAllUser();
                req.setAttribute("users", users);
            }
            else if ("/new".equalsIgnoreCase(req.getPathInfo())) {
                path = getTemplatePath(req.getServletPath() + "/form");
            }
            else if (pathInfoArr.length == 3) {
                Long userId = Long.valueOf(pathInfoArr[1]);
                String operation = pathInfoArr[2];

                if ("edit".equalsIgnoreCase(operation)) {
                    Optional<User> user = userService.getById(userId);
                    user.ifPresent(user1 -> req.setAttribute("user", user1));
                    path = getTemplatePath(req.getServletPath() + "/form");
                }
                else if ("delete".equalsIgnoreCase(operation)) {
                    userService.deleteUser(userId);
                    resp.sendRedirect(req.getContextPath() + "/users/list");
                }
            }

            if (path != null) {
                RequestDispatcher requestDispatcher = req.getRequestDispatcher(path);
                requestDispatcher.forward(req, resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = !req.getParameter("userId").isEmpty() ? Long.valueOf(req.getParameter("userId")) : null;
        String username = req.getParameter("username");
        String password = req.getParameter("userpass");

        try {
            userService.saveOrUpdate(new User(id, username, password));
            resp.sendRedirect(req.getContextPath() + "/users/list");
        } catch (RuntimeException e) {
            String path = getTemplatePath(req.getServletPath());
            req.setAttribute("errorMsg", e.getMessage());
            RequestDispatcher requestDispatcher = req.getRequestDispatcher(path);
            requestDispatcher.forward(req, resp);
        }
    }
}
