package controllers;

import Dao.ProvidedDao;
import Dao.UserDao;
import entitys.ProvidedEntity;
import entitys.UsersEntity;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.DateTimeConverter;
import utils.EncryptUtil;
import utils.fileUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@MultipartConfig
@WebServlet(name = "providedsServlet", value = {"/provideds/index",
        "/provideds/show",
        "/provideds/create",
        "/provideds/store",
        "/provideds/edit",
        "/provideds/update",
        "/provideds/delete",})
public class providedServlet extends HttpServlet {
    private ProvidedDao dao;

    public providedServlet() {
        this.dao = new ProvidedDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("index")) {
            System.out.println("day");
            index(request, response);
        } else if (uri.contains("create")) {
            create(request, response);
        } else if (uri.contains("show")) {
            show(request, response);
        } else {
            //404
        }
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
        request.setAttribute("pdid",-1);
        List<ProvidedEntity> ds = this.dao.findAll();
        request.setAttribute("ds", ds);
        request.setAttribute("view", "/views/admin/provideds/index.jsp");
        request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
    }

    private void create(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
    }

    private void edit(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        List<ProvidedEntity> ds = this.dao.findAll();
        request.setAttribute("ds", ds);
        String idStr = request.getParameter("pdid");
        try {
            int id = Integer.parseInt(request.getParameter("pdid"));
            ProvidedEntity entity = this.dao.findById(id);
            request.setAttribute("provided", entity);
            request.setAttribute("pdid",id);
            request.setAttribute("view", "/views/admin/provideds/index.jsp");
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
        HttpSession session = request.getSession();
        String idStr = request.getParameter("id");
        try {
            int id = Integer.parseInt(idStr);
            ProvidedEntity oldValue = this.dao.findById(id);
            ProvidedEntity newValue = new ProvidedEntity();
            BeanUtils.populate(newValue, request.getParameterMap());
            this.dao.edit(newValue);
            session.setAttribute("message", "Update thành công");
            response.sendRedirect("/provideds/index");
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("message", "Update thất bại");
            response.sendRedirect("/provideds/index");
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

            ProvidedEntity entity = new ProvidedEntity();
            BeanUtils.populate(entity, request.getParameterMap());
            this.dao.add(entity);
            session.setAttribute("message", "Thêm mới thành công");
            response.sendRedirect("/provideds/index");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/provideds/index");
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
        String s = request.getParameter("id");
        int id = Integer.parseInt(s);
        ProvidedEntity entity = this.dao.findById(id);
        try {
            this.dao.delete(entity);
            response.sendRedirect("/provideds/index");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
