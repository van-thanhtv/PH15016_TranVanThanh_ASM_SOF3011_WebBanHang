package controllers;

import Dao.*;
import entitys.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.DateTimeConverter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import utils.EncryptUtil;
import utils.fileUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
@MultipartConfig
@WebServlet(name = "ProductServlet", value = {"/products/index",
        "/products/show",
        "/products/create",
        "/products/store",
        "/products/inputExcel",
        "/products/edit",
        "/products/update",
        "/products/delete",})
public class ProductServlet extends HttpServlet {
    private ProductDetailsDao productDetailsDao;
    private ProductDao productDao;
    private SizeDao sizeDao;
    private ProvidedDao providedDao;
    private ColorDao colorDao;
    private CategoryDao categoryDao;

    public ProductServlet() {
        this.productDetailsDao=new ProductDetailsDao();
        this.sizeDao=new SizeDao();
        this.productDao =new ProductDao();
        this.colorDao = new ColorDao();
        this.providedDao=new ProvidedDao();
        this.categoryDao = new CategoryDao();
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
        }else if (uri.contains("inputExcel")) {
            inputExcel(request, response);
        }
    }
    private void index(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        request.setAttribute("pid",-1);
        List<ProductDetailsEntity> ds = this.productDetailsDao.findAll();
        List<ProductEntity> prod = this.productDao.findAll();
        List<ProvidedEntity> dsProv = this.providedDao.findAll();
        request.setAttribute("ds", ds);
        request.setAttribute("dsprod", prod);
        request.setAttribute("dsProv", dsProv);
        request.setAttribute("view", "/views/admin/products/index.jsp");
        request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
    }
    private void inputExcel(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        Long startTime = System.currentTimeMillis();
        List<ProductEntity> productEntities = new ArrayList<ProductEntity>();
        List<ProductDetailsEntity> productDetailsEntities = new ArrayList<ProductDetailsEntity>();
        List<SizeEntity> sizeEntities = new ArrayList<SizeEntity>();
        List<ColorEntity> colorEntities = new ArrayList<ColorEntity>();
        List<ProvidedEntity> providedEntities = new ArrayList<ProvidedEntity>();

        File file = fileUtil.saveFileUpload(request.getServletContext().getRealPath("/images/img"), request.getPart("excel"));
        String excelFilePath = file.getPath();
        int batchSize = 50;


        try {

            FileInputStream inputStream = new FileInputStream(excelFilePath);

            Workbook workbook = new XSSFWorkbook(inputStream);

            Sheet firstSheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = firstSheet.iterator();


            int count = 0;

            rowIterator.next(); // skip the header row

            while (rowIterator.hasNext()) {
                Row nextRow = rowIterator.next();
                Iterator<Cell> cellIterator = nextRow.cellIterator();

                ProductDetailsEntity entity = new ProductDetailsEntity();
                while (cellIterator.hasNext()) {
                    Cell nextCell = cellIterator.next();
                    int columnIndex = nextCell.getColumnIndex();
                    switch (columnIndex) {
                        case 0:
                            ProductEntity productEntity = this.productDao.findById((int)nextCell.getNumericCellValue());
                            entity.setIdProduct(productEntity);
                            break;
                        case 1:
                            SizeEntity sizeEntity = this.sizeDao.findById((int)nextCell.getNumericCellValue());
                            entity.setIdSize(sizeEntity);
                            break;
                        case 2:
                            ColorEntity colorEntity = this.colorDao.findById((int)nextCell.getNumericCellValue());
                            entity.setIdColor(colorEntity);
                            break;
                        case 3:
                            ProvidedEntity providedEntity = this.providedDao.findById((int)nextCell.getNumericCellValue());
                            entity.setIdProvided(providedEntity);
                            break;
                        case 4:
                            entity.setStatus((int)nextCell.getNumericCellValue());
                            break;
                        case 5:
                            entity.setPrice(nextCell.getNumericCellValue());
                            break;
                        case 6:
                            entity.setAmount((int)nextCell.getNumericCellValue());
                            break;
                    }
                }
                productDetailsEntities.add(entity);
                if (count % batchSize == 0) {

                }

            }

            workbook.close();

            // execute the remaining queries





        } catch (IOException ex1) {
            System.out.println("Error reading file");
            ex1.printStackTrace();
        }
        System.out.println("-----------"+productDetailsEntities.size());
        try {
            int i = this.productDetailsDao.batchInsertadd(productDetailsEntities);
            System.out.println("asjh"+i);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        for (ProductDetailsEntity e:productDetailsEntities) {
//            System.out.println("---------))"+e.toString());
//        }
        long end = System.currentTimeMillis();
        System.out.printf("Import done in %d ms\n", (end - startTime));
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
        List<ProductDetailsEntity> ds = this.productDetailsDao.findAll();
        request.setAttribute("ds", ds);
        String idStr = request.getParameter("pid");
        try {
            List<ProvidedEntity> dsProv = this.providedDao.findAll();
            List<ProductEntity> prod = this.productDao.findAll();
            request.setAttribute("dsProv", dsProv);
            request.setAttribute("dsprod", prod);
            int id = Integer.parseInt(request.getParameter("pid"));
            ProductDetailsEntity entity = this.productDetailsDao.findById(id);
            request.setAttribute("product", entity);
            request.setAttribute("idProvided", entity.getIdProvided().getId());
            request.setAttribute("pid",id);
            request.setAttribute("view", "/views/admin/products/index.jsp");
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
        HttpSession session = request.getSession();
        try {
            //kiểm tra product có trong db chưa
            String nameProduct = request.getParameter("nameProduct");
            String nameCategory = request.getParameter("nameCategory");
            String nameSize = request.getParameter("nameSize");
            String nameColor = request.getParameter("nameColor");
            String idProvided = request.getParameter("idProvided");
            File file = fileUtil.saveFileUpload(request.getServletContext().getRealPath("/images/img"), request.getPart("img"));
            String img = "";
            if (file==null){
                img = "";
            }else {
                img=file.getName();
            }
            Double price = Double.parseDouble(request.getParameter("price"));
            if (nameProduct.isEmpty()){
                session.setAttribute("error", "Bạn chưa nhập Name Product");
            }else {
                if (!img.equals("")){
                    ProductEntity entity = this.productDao.findByName(nameProduct);
                    if (file.getName().equals("/images/img")) {
                        entity.setImg(entity.getImg());
                    } else {
                        entity.setImg(file.getName());
                    }
                    this.productDao.edit(entity);
                }
                int i =0;
                for (ProductEntity productEntity:this.productDao.findAll()) {
                    if (nameProduct.equals(productEntity.getNameProduct())){//Nếu như chưa có name product trong Database
                        i++;
                    }
                }
                if (i==0){
                    if (nameCategory.isEmpty()){//Kiểm tra nhập name category chưa
                        session.setAttribute("error", "Bạn chưa nhập Name Product");
                    }else {
                        int j=0;
                        for (CategoryEntity category :this.categoryDao.findAll()) {
                            if (nameCategory.equals(category.getNameCategory())){//Thêm category
                                j++;

                            }
                        }
                        if (j==0){
                            System.out.println("tạo category");
                            CategoryEntity newCategoryEntity = new CategoryEntity();
                            newCategoryEntity.setNameCategory(nameCategory);
                            this.categoryDao.add(newCategoryEntity);
                        }
                        System.out.println("tạo product");
                        CategoryEntity category = this.categoryDao.findByName(nameCategory);
                        if (category==null){
                            System.out.println("lôi dây");
                        }
                        ProductEntity newProduct = new ProductEntity();
                        newProduct.setImg(img);
                        if (file.getName().equals("/images/img")) {
                            newProduct.setImg(newProduct.getImg());
                        } else {
                            newProduct.setImg(file.getName());
                        }
                        newProduct.setNameProduct(nameProduct);
                        newProduct.setIdCategory(category);
                        newProduct.setStatus(0);
                        newProduct.setDescribe("");
                        this.productDao.add(newProduct);
                    }
                }
            }
            if (nameSize.isEmpty()){
                session.setAttribute("error", "Bạn chưa nhập size");
            }else {//Kiểm tra size
                int i = 0;
                if (this.sizeDao.findAll()!=null){
                    for (SizeEntity sizeEntity:this.sizeDao.findAll()) {
                        if (nameSize.equals(sizeEntity.getNameSize())){
                            System.out.println("from"+nameSize);
                            System.out.println("ds"+sizeEntity.getNameSize());
                            i++;
                        }
                    }
                }

                if (i==0){
                    System.out.println("tạo size");
                    SizeEntity newSize = new SizeEntity();
                    newSize.setNameSize(nameSize);
                    this.sizeDao.add(newSize);
                }
            }
            if (nameColor.isEmpty()){
                session.setAttribute("error", "Bạn chưa nhập color");
            }else {//Kiểm tra color
                int i = 0;
                if (this.colorDao.findAll()!=null){
                    for (ColorEntity colorEntity:this.colorDao.findAll()) {
                        if (nameColor.equals(colorEntity.getNameColor())){
                            i++;
                        }
                    }
                }
                if(i==0){
                    System.out.println("tạo color");
                    ColorEntity newColor = new ColorEntity();
                    newColor.setNameColor(nameColor);
                    this.colorDao.add(newColor);
                }
            }

            ProductDetailsEntity entity = new ProductDetailsEntity();
            ProductDetailsEntity entity1 = this.productDetailsDao.findById(Integer.parseInt(idStr));
            entity.setId(entity1.getId());
            SizeEntity sizeEntity = this.sizeDao.findByname(nameSize);
            ColorEntity colorEntity = this.colorDao.findByName(nameColor);
            ProductEntity productEntity = this.productDao.findByName(nameProduct);
            ProvidedEntity providedEntity = this.providedDao.findById(Integer.parseInt(idProvided));
            entity.setIdProduct(productEntity);
            entity.setIdColor(colorEntity);
            entity.setIdSize(sizeEntity);
            entity.setIdProvided(providedEntity);
            entity.setPrice(price);
            this.productDetailsDao.edit(entity);
            session.setAttribute("message", "Update thành công");
            response.sendRedirect("/products/index");
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("error", "Update thất bại");
            response.sendRedirect("edit?id=" + idStr);
        }
    }

    private void store(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        HttpSession session = request.getSession();
        try {
            //kiểm tra product có trong db chưa
            String nameProduct = request.getParameter("nameProduct");
            String nameCategory = request.getParameter("nameCategory");
            String nameSize = request.getParameter("nameSize");
            String nameColor = request.getParameter("nameColor");
            String idProvided = request.getParameter("idProvided");
            String img = request.getParameter("img");
            System.out.println("img"+img);
            Double price = Double.parseDouble(request.getParameter("price"));
           if (nameProduct.isEmpty()){
               session.setAttribute("error", "Bạn chưa nhập Name Product");
           }else {
               int i =0;
               for (ProductEntity productEntity:this.productDao.findAll()) {
                   if (nameProduct.equals(productEntity.getNameProduct())){//Nếu như chưa có name product trong Database
                       i++;
                   }
               }
               if (i==0){
                   if (nameCategory.isEmpty()){//Kiểm tra nhập name category chưa
                       session.setAttribute("error", "Bạn chưa nhập Name Product");
                   }else {
                       int j=0;
                       for (CategoryEntity category :this.categoryDao.findAll()) {
                           if (nameCategory.equals(category.getNameCategory())){//Thêm category
                               j++;

                           }
                       }
                       if (j==0){
                           System.out.println("tạo category");
                           CategoryEntity newCategoryEntity = new CategoryEntity();
                           newCategoryEntity.setNameCategory(nameCategory);
                           this.categoryDao.add(newCategoryEntity);
                       }
                       System.out.println("tạo product");
                       CategoryEntity category = this.categoryDao.findByName(nameCategory);
                       if (category==null){
                           System.out.println("lôi dây");
                       }
                       ProductEntity newProduct = new ProductEntity();
                       File file = fileUtil.saveFileUpload(request.getServletContext().getRealPath("/images/img"), request.getPart("img"));
                       if (file.getName().equals("/images/img")) {
                           newProduct.setImg(newProduct.getImg());
                       } else {
                           newProduct.setImg(file.getName());
                       }
                       newProduct.setNameProduct(nameProduct);
                       newProduct.setIdCategory(category);
                       newProduct.setStatus(0);
                       newProduct.setDescribe("");
                       this.productDao.add(newProduct);
                   }
               }
           }
           if (nameSize.isEmpty()){
               session.setAttribute("error", "Bạn chưa nhập size");
           }else {//Kiểm tra size
               int i = 0;
               if (this.sizeDao.findAll()!=null){
                   for (SizeEntity sizeEntity:this.sizeDao.findAll()) {
                       if (nameSize.equals(sizeEntity.getNameSize())){
                           System.out.println("from"+nameSize);
                           System.out.println("ds"+sizeEntity.getNameSize());
                           i++;
                       }
                   }
               }

               if (i==0){
                   System.out.println("tạo size");
                   SizeEntity newSize = new SizeEntity();
                   newSize.setNameSize(nameSize);
                   this.sizeDao.add(newSize);
               }
           }
           if (nameColor.isEmpty()){
               session.setAttribute("error", "Bạn chưa nhập color");
           }else {//Kiểm tra color
               int i = 0;
               if (this.colorDao.findAll()!=null){
                   for (ColorEntity colorEntity:this.colorDao.findAll()) {
                        if (nameColor.equals(colorEntity.getNameColor())){
                            i++;
                        }
                   }
               }
               if(i==0){
                   System.out.println("tạo color");
                   ColorEntity newColor = new ColorEntity();
                   newColor.setNameColor(nameColor);
                   this.colorDao.add(newColor);
               }
           }

            ProductDetailsEntity entity = new ProductDetailsEntity();
//            BeanUtils.populate(entity, request.getParameterMap());
            SizeEntity sizeEntity = this.sizeDao.findByname(nameSize);
            ColorEntity colorEntity = this.colorDao.findByName(nameColor);
            ProductEntity productEntity = this.productDao.findByName(nameProduct);
            ProvidedEntity providedEntity = this.providedDao.findById(Integer.parseInt(idProvided));
            entity.setIdProduct(productEntity);
            entity.setIdColor(colorEntity);
            entity.setIdSize(sizeEntity);
            entity.setIdProvided(providedEntity);
            entity.setPrice(price);
            this.productDetailsDao.add(entity);
            session.setAttribute("message", "Thêm mới thành công");
            response.sendRedirect("/products/index");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/products/index");
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
        ProductDetailsEntity entity = this.productDetailsDao.findById(id);
        entity.setStatus(1);
        try {
            this.productDetailsDao.edit(entity);
            response.sendRedirect("/products/index");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
