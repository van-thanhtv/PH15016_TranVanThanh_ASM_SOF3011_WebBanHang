package controllers;

import Dao.*;
import entitys.CategoryEntity;
import entitys.ProductDetailsEntity;
import entitys.ProductEntity;
import entitys.ProvidedEntity;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "HomeServlet", value = {"/home","/404"})
public class HomeServlet extends HttpServlet {
    private ProductDetailsDao productDetailsDao;
    private ProductDao productDao;
    private SizeDao sizeDao;
    private ProvidedDao providedDao;
    private ColorDao colorDao;
    private CategoryDao categoryDao;

    public HomeServlet() {
        this.productDetailsDao=new ProductDetailsDao();
        this.sizeDao=new SizeDao();
        this.productDao =new ProductDao();
        this.colorDao = new ColorDao();
        this.providedDao=new ProvidedDao();
        this.categoryDao = new CategoryDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<CategoryEntity> dsCategory = this.categoryDao.findAll();
        List<ProvidedEntity> dsProvided = this.providedDao.findAll();
        request.setAttribute("dsCategory",dsCategory);
        String catid = request.getParameter("ctid");
        String pvid = request.getParameter("pvid");
        if (catid==null||catid.equals("")){
            List<ProductDetailsEntity> dsProduct = this.productDetailsDao.findAll();
            request.setAttribute("dsProduct",dsProduct);
            if (pvid==null||pvid.equals("")){
                request.setAttribute("dsProduct",dsProduct);
            }else {
                ProvidedEntity provided = this.providedDao.findById(Integer.parseInt(pvid));
                List<ProductDetailsEntity> lsProductDetails = new ArrayList<ProductDetailsEntity>();
                for (ProductDetailsEntity productEntity : provided.getProductDetailsList()) {
                    lsProductDetails.add(productEntity);
                }
                request.setAttribute("dsProduct",lsProductDetails);
            }
        }else {
            CategoryEntity category = this.categoryDao.findById(Integer.parseInt(catid));
            List<ProductDetailsEntity> lsProductDetails = new ArrayList<ProductDetailsEntity>();
            List<ProductEntity> lsProduct = this.productDao.findByCategory(Integer.valueOf(category.getId()));
            for (ProductEntity productEntity : lsProduct) {
                ProductEntity entity = this.productDao.findById(productEntity.getId());
                System.out.println(entity.getProductDetailsList().size());
                lsProductDetails.addAll(this.productDetailsDao.findByProduct(productEntity.getId()));
            }
            request.setAttribute("dsProduct",lsProductDetails);
        }

        request.setAttribute("view","/views/home.jsp");
        request.setAttribute("dsProvided",dsProvided);

        request.setAttribute("slider","/views/slider.jsp");
        request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
