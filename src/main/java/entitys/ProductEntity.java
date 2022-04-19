package entitys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "product", schema = "Websales", catalog = "")
public class ProductEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "nameProduct")
    private String nameProduct;
    @Basic
    @Column(name = "status")
    private int status;
    @ManyToOne
    @JoinColumn(name = "id_category")
    private CategoryEntity idCategory;
    @OneToMany(mappedBy = "idProvided")
    private List<ProductDetailsEntity> productDetailsList;
    @Basic
    @Column(name = "describes")
    private String describe;

    @Column(name = "img", length = 100)
    private String img;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductEntity that = (ProductEntity) o;

        if (id != that.id) return false;
        if (status != that.status) return false;
        if (idCategory != that.idCategory) return false;
        if (nameProduct != null ? !nameProduct.equals(that.nameProduct) : that.nameProduct != null) return false;
        if (describe != null ? !describe.equals(that.describe) : that.describe != null) return false;

        return true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public CategoryEntity getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(CategoryEntity idCategory) {
        this.idCategory = idCategory;
    }

    public List<ProductDetailsEntity> getProductDetailsList() {
        return productDetailsList;
    }

    public void setProductDetailsList(List<ProductDetailsEntity> productDetailsList) {
        this.productDetailsList = productDetailsList;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
