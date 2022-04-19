package controllers;

import Dao.*;
import entitys.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.DateTimeConverter;
import utils.EncryptUtil;
import utils.XCookie;
import utils.fileUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.xml.crypto.Data;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@WebServlet(name = "customerServlet", value = {"/customer/index", "/customer/cart", "/customer/sub",
        "/customer/showCart",
        "/customer/history",
        "/customer/store",
        "/customer/edit",
        "/customer/update",
        "/customer/delete",})
public class customerServlet extends HttpServlet {
    private ProductDetailsDao productDetailsDao;
    private ProductDao productDao;
    private SizeDao sizeDao;
    private ProvidedDao providedDao;
    private ColorDao colorDao;
    private CategoryDao categoryDao;
    private customerDao customerDao;
    private ordersDao ordersDao;
    private ordersDetailDao ordersDetailDao;

    public customerServlet() {
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
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String uri = request.getRequestURI();
        if (uri.contains("index")) {
            index(request, response);
        } else if (uri.contains("cart")) {
            cart(request, response);
        } else if (uri.contains("show")) {
            show(request, response);
        } else if (uri.contains("sub")) {
            sub(request, response);
        } else if (uri.contains("history")) {
            history(request, response);
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
            history(request, response);
        } else if (uri.contains("delete")) {
            delete(request, response);
        } else if (uri.contains("edit")) {
            edit(request, response);
        }
    }

    private void index(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        List<CategoryEntity> dsCategory = this.categoryDao.findAll();
        List<ProvidedEntity> dsProvided = this.providedDao.findAll();
        request.setAttribute("dsCategory", dsCategory);
        String catid = request.getParameter("ctid");
        String pvid = request.getParameter("pvid");
        if (catid == null || catid.equals("")) {
            List<ProductDetailsEntity> dsProduct = this.productDetailsDao.findAll();
            System.out.println(dsProduct.size());
            request.setAttribute("dsProduct", dsProduct);
            if (pvid == null || pvid.equals("")) {
                request.setAttribute("dsProduct", dsProduct);
            } else {
                ProvidedEntity provided = this.providedDao.findById(Integer.parseInt(pvid));
                List<ProductDetailsEntity> lsProductDetails = new ArrayList<ProductDetailsEntity>();
                for (ProductDetailsEntity productEntity : provided.getProductDetailsList()) {
                    lsProductDetails.add(productEntity);
                }
                request.setAttribute("dsProduct", lsProductDetails);
            }
        } else {
            CategoryEntity category = this.categoryDao.findById(Integer.parseInt(catid));
            List<ProductDetailsEntity> lsProductDetails = new ArrayList<ProductDetailsEntity>();
            List<ProductEntity> lsProduct = this.productDao.findByCategory(Integer.valueOf(category.getId()));
            for (ProductEntity productEntity : lsProduct) {
                ProductEntity entity = this.productDao.findById(productEntity.getId());
                lsProductDetails.addAll(this.productDetailsDao.findByProduct(productEntity.getId()));
            }

            request.setAttribute("dsProduct", lsProductDetails);
        }

        request.setAttribute("dsProvided", dsProvided);
        request.setAttribute("view", "/views/customer/index.jsp");
        request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
    }

    private void cart(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        String id = request.getParameter("id");
        System.out.println(id);
        Cookie arr[] = request.getCookies();
        String txt = "";
        for (Cookie o : arr) {
            if (o.getName().equals("id")) {
                txt = txt + o.getValue();
                o.setMaxAge(0);
                response.addCookie(o);
            }
        }
        if (txt.isEmpty()) {
            txt = id;
        } else {
            txt = txt + "p" + id;
        }
        XCookie.add(response, "id", String.valueOf(txt), 24);
        response.sendRedirect("/customer/showCart");
    }

    private void edit(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

    }

    private void history(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException, IOException {

        Cookie arr[] = request.getCookies();
        String idSDT  = "";
        for (Cookie o : arr) {
            if (o.getName().equals("idCustome")) {
                idSDT=o.getValue();
            }
        }
        CustomerEntity customerEntity = this.customerDao.findByPhone(idSDT);
        List<OrdersEntity> ordersEntities = customerEntity.getOrdersList();
        request.setAttribute("orders",ordersEntities);
        request.setAttribute("view", "/views/customer/history.jsp");
        request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
    }

    private void store(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        HttpSession session = request.getSession();
        CustomerEntity customerEntity = new CustomerEntity();
//        Logger logger = Logger.getLogger(String.valueOf(loginServlet.class));
//        UsersEntity u = (UsersEntity) session.getAttribute("sessionUser");
//        logger.info("Người thực hiện : "+u.getName()+"Thời gian"+new  Date());
        try {
            customerEntity.setAddress(String.valueOf(request.getParameter("address")));
            customerEntity.setName(String.valueOf(request.getParameter("name")));
            customerEntity.setEmail(String.valueOf(request.getParameter("email")));
            customerEntity.setNumberPhone(String.valueOf(request.getParameter("numberPhone")));
            customerEntity.setSex(Integer.parseInt(String.valueOf(request.getParameter("sex"))));
            customerEntity.setBirthday(java.sql.Date.valueOf(request.getParameter("birthday")));

            int kt = 0;
            for (CustomerEntity c:this.customerDao.findAll()) {
                if (customerEntity.getNumberPhone().equals(c.getNumberPhone())){
                    kt++;
                }
            }
            CustomerEntity idCustome= new CustomerEntity();
            if (kt==0){
                idCustome= this.customerDao.add(customerEntity);
            }else {
                idCustome=this.customerDao.findByPhone(customerEntity.getNumberPhone());
            }
            DateTimeConverter dtc = new DateConverter();
            dtc.setPattern("yyyy-MM-dd");
            ConvertUtils.register(dtc, Date.class);
            XCookie.add(response, "idCustome", String.valueOf(idCustome.getNumberPhone()), 600);
            OrdersEntity ordersEntity = new OrdersEntity();
            ordersEntity.setIdCustomer(idCustome);
            ordersEntity.setIdUsers(null);
            ordersEntity.setStatus(0);
            Date t = new Date();
            ordersEntity.setDateCreate(new java.sql.Date(t.getTime()));
            OrdersEntity idOrders = this.ordersDao.add(ordersEntity);
            Cookie arr[] = request.getCookies();
            List<ProductDetailsEntity> list = new ArrayList<ProductDetailsEntity>();
            for (Cookie o : arr) {
                if (o.getName().equals("id")) {
                    String txt[] = o.getValue().split("p");
                    for (String s : txt) {
                        list.add(this.productDetailsDao.findById(Integer.parseInt(s)));
                    }
                }
            }
            int count = 1;
            for (int i = 0; i < list.size(); i++) {
                count = 1;
                for (int j = i + 1; j < list.size(); j++) {
                    if (list.get(i) != null && list.get(j) != null) {
                        if (list.get(i).getId() == list.get(j).getId()) {
                            count++;
                            list.remove(j);
                            j--;
                            list.get(i).setAmount(count);
                        }
                    }
                }
                list.get(i).setAmount(count);
            }
            for (ProductDetailsEntity entity : list) {
                OrdersDetailEntity ordersDetailEntity = new OrdersDetailEntity();
                ordersDetailEntity.setIdOrders(idOrders);
                ordersDetailEntity.setIdProductDetails(entity);
                ordersDetailEntity.setPrice(entity.getPrice() * entity.getAmount());
                ordersDetailEntity.setQuantily(entity.getAmount());
                ordersDetailEntity.setStatus(0);
                this.ordersDetailDao.add(ordersDetailEntity);
            }
            XCookie.add(response, "id", String.valueOf(0), 0);
            Cookie c = new Cookie("id", String.valueOf(0));
            c.setMaxAge(0);
            response.addCookie(c);
            session.setAttribute("message", "Đặt hàng thành công");

        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("error", "Đặt hàng thất bại");
        }
        response.sendRedirect("/customer/showCart");
    }

    private void show(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        Cookie arr[] = request.getCookies();
        List<ProductDetailsEntity> list = new ArrayList<ProductDetailsEntity>();
        for (Cookie o : arr) {
            if (o.getName().equals("id")) {
                String txt[] = o.getValue().split("p");
                for (String s : txt) {
                    if (!s.equals("")){
                        list.add(this.productDetailsDao.findById(Integer.parseInt(s)));
                    }
                }
            }
        }
        int count = 1;
        for (int i = 0; i < list.size(); i++) {
            count = 1;
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i) != null && list.get(j) != null) {
                    if (list.get(i).getId() == list.get(j).getId()) {
                        count++;
                        list.remove(j);
                        j--;
                        list.get(i).setAmount(count);
                    }
                }
            }
            list.get(i).setAmount(count);

        }
        double total = 0;
        for (ProductDetailsEntity o : list) {
            total = total + count * o.getPrice();
        }
        request.setAttribute("list", list);
        request.setAttribute("total", total);
        request.setAttribute("vat", 0.1 * total);
        request.setAttribute("sum", 1.1 * total);
        request.setAttribute("view", "/views/customer/cart.jsp");
        request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
    }

    private void sub(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        String id = request.getParameter("id");
        Cookie arr[] = request.getCookies();
        String txt = "";
        for (Cookie o : arr) {
            if (o.getName().equals("id")) {
                txt = txt + o.getValue();
                o.setMaxAge(0);
                response.addCookie(o);
            }
        }
        String ids[] = txt.split("p");
        String txtOutPut = "";
        int check = 0;
        for (int i = 0; i < ids.length; i++) {
            if (ids[i].equals(id)) {
                check++;
            }
            if (check != 1) {
                if (txtOutPut.isEmpty()) {
                    txtOutPut = ids[i];
                } else {
                    txtOutPut = txtOutPut + "p" + ids[i];
                }
            } else {
                check++;
            }
        }
        if (!txtOutPut.isEmpty()) {
            XCookie.add(response, "id", String.valueOf(txtOutPut), 24);
        }
        response.sendRedirect("/customer/showCart");
    }

    private void delete(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        String id = request.getParameter("id");
        Cookie arr[] = request.getCookies();
        String txt = "";
        for (Cookie o : arr) {
            if (o.getName().equals("id")) {
                XCookie.add(response, "id", txt = txt + o.getValue(), 0);
            }
        }
        String ids[] = txt.split("p");
        String txtOutPut = "";
        int check = 0;
        for (int i = 0; i < ids.length; i++) {
            if (!ids[i].equals(id)) {
                check++;
                if (txtOutPut.isEmpty()) {
                    txtOutPut = ids[i];
                } else {
                    txtOutPut = txtOutPut + "p" + ids[i];
                }
            }
        }
        if (check==0){
            System.out.println("xoa");
            Cookie c = new Cookie("id", String.valueOf(0));
            c.setMaxAge(0);
            response.addCookie(c);
        }else {
            if (!txtOutPut.isEmpty()) {
                Cookie c = new Cookie("id", txtOutPut);
                c.setMaxAge(60 * 60 * 24);
                response.addCookie(c);
            }
        }
        response.sendRedirect("/customer/showCart");
    }
}
