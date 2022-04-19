package controllers;

import Dao.UserDao;
import entitys.UsersEntity;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.DateTimeConverter;
import utils.EncryptUtil;
import utils.fileUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
@MultipartConfig
@WebServlet(name = "usersServlet", value = {"/users/index",
        "/users/show",
        "/users/create",
        "/users/store",
        "/users/edit",
        "/users/update",
        "/users/delete",})
public class usersServlet extends HttpServlet {
    private UserDao dao;

    public usersServlet() {
        this.dao = new UserDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("index")) {
            index(request, response);
        } else if (uri.contains("create")) {
            create(request, response);
        } else if (uri.contains("show")) {
            show(request, response);
        } else {
            //404
        }
//        request.getRequestDispatcher("views/layout.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String uri = request.getRequestURI();
        if (uri.contains("store")) {
            store(request, response);
        } else if (uri.contains("update")) {
            update(request, response);
        } else if (uri.contains("delete")) {
            delete(request, response);
        }else if (uri.contains("edit")) {
            edit(request, response);
        }

    }

    private void index(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        request.setAttribute("uid",-1);
        List<UsersEntity> ds = this.dao.findAll();
        request.setAttribute("ds", ds);
        request.setAttribute("view", "/views/admin/users/index.jsp");
        request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
    }

    private void create(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
//        request.setAttribute("view", "/Views/user/create.jsp");
        request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
    }

    private void edit(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        List<UsersEntity> ds = this.dao.findAll();
        request.setAttribute("ds", ds);
        String idStr = request.getParameter("uid");
        try {
            int id = Integer.parseInt(request.getParameter("uid"));
            UsersEntity entity = this.dao.findById(id);
            request.setAttribute("user", entity);
            request.setAttribute("uid",id);
            request.setAttribute("view", "/views/admin/users/index.jsp");
            request.getRequestDispatcher("/views/layout.jsp")
                    .forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    private void update(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException, IOException {
        String idStr = request.getParameter("id");
        try {
            int id = Integer.parseInt(idStr);
            UsersEntity oldValue = this.dao.findById(id);
            UsersEntity newValue = new UsersEntity();
            BeanUtils.populate(newValue, request.getParameterMap());
            File file = fileUtil.saveFileUpload(request.getServletContext().getRealPath("/images/avatar"), request.getPart("avatar"));
            if (file.getName().equals("/images/avatar")) {
                newValue.setAvatar(newValue.getAvatar());
            } else {
                newValue.setAvatar(file.getName());
            }
            newValue.setPasswordUser(oldValue.getPasswordUser());
            System.out.println(newValue);
            this.dao.edit(newValue);
            response.sendRedirect("/users/index");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("edit?id=" + idStr);
        }
    }

    private void store(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        HttpSession session = request.getSession();
        try {
            // Định dạng thời gian nhập vào
            DateTimeConverter dtc = new DateConverter();
            dtc.setPattern("yyyy-MM-dd");
            ConvertUtils.register(dtc, Date.class);

            UsersEntity entity = new UsersEntity();
            BeanUtils.populate(entity, request.getParameterMap());
            File file = fileUtil.saveFileUpload(request.getServletContext().getRealPath("/images/avatar"), request.getPart("avatar"));
            if (file.getName().equals("/images/avatar")) {
                entity.setAvatar(entity.getAvatar());
            } else {
                entity.setAvatar(file.getName());
            }
            entity.setPasswordUser(EncryptUtil.encrypt(entity.getPasswordUser()));
            this.dao.add(entity);
            session.setAttribute("message", "Thêm mới thành công");
            response.sendRedirect("/users/index");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/users/index");
            session.setAttribute("error", "Thêm mới thất bại");
        }
    }

    private void show(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
    }

    private void delete(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        System.out.println("delete");
        String s = request.getParameter("uid");
        int id = Integer.parseInt(s);
        UsersEntity entity = this.dao.findById(id);
        try {
            this.dao.delete(entity);
            response.sendRedirect("/users/index");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
