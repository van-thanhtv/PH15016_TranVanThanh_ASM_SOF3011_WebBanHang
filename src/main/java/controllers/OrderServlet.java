package controllers;

import Dao.*;
import entitys.CustomerEntity;
import entitys.OrdersEntity;
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

@WebServlet(name = "OrderServlet", value =  {"/order/index",
        "/order/show",
        "/order/create",
        "/order/store",
        "/order/edit",
        "/order/update",
        "/order/delete",})
public class OrderServlet extends HttpServlet {
    private ProductDetailsDao productDetailsDao;
    private ProductDao productDao;
    private SizeDao sizeDao;
    private ProvidedDao providedDao;
    private ColorDao colorDao;
    private CategoryDao categoryDao;
    private customerDao customerDao;
    private ordersDao ordersDao;
    private ordersDetailDao ordersDetailDao;
    public OrderServlet() {
        this.productDetailsDao = new ProductDetailsDao();
        this.sizeDao = new SizeDao();
        this.productDao = new ProductDao();
        this.colorDao = new ColorDao();
        this.providedDao = new ProvidedDao();
        this.categoryDao = new CategoryDao();
        this.ordersDao = new ordersDao();
        this.customerDao = new customerDao();
        this.ordersDetailDao = new ordersDetailDao();
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
//        List<CustomerEntity> customerEntitys = this.customerDao.findAll();
        List<OrdersEntity> ordersEntities = this.ordersDao.findAll();
//        request.setAttribute("orders",ordersEntities);
        request.setAttribute("orders",ordersEntities);

        request.setAttribute("view", "/views/admin/users/orderManagement.jsp");
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

        try {
            int id = Integer.parseInt(request.getParameter("id"));


            request.setAttribute("id",id);
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
        String idOr = request.getParameter("id");
        OrdersEntity ordersEntity = this.ordersDao.findById(Integer.parseInt(idOr));
        ordersEntity.setStatus(2);
        try {
            this.ordersDao.edit(ordersEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("/order/index");
    }

    private void store(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        HttpSession session = request.getSession();
        try {
            // Định dạng thời gian nhập vào

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
        String idOr = request.getParameter("id");
        OrdersEntity ordersEntity = this.ordersDao.findById(Integer.parseInt(idOr));
        ordersEntity.setStatus(1);
        try {
            this.ordersDao.edit(ordersEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("/order/index");
    }
}
