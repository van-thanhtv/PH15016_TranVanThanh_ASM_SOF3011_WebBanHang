package entitys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "productDetails", schema = "Websales", catalog = "")
public class ProductDetailsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @ManyToOne
    @JoinColumn(name = "id_product")
    private ProductEntity idProduct;
    @ManyToOne
    @JoinColumn(name = "id_size")
    private SizeEntity idSize;
    @ManyToOne
    @JoinColumn(name = "id_color")
    private ColorEntity idColor;
    @ManyToOne
    @JoinColumn(name = "id_provided")
    private ProvidedEntity idProvided;
    @Basic
    @Column(name = "status")
    private int status;
    @Basic
    @Column(name = "price")
    private double price;
    @OneToMany(mappedBy = "idProductDetails")
    private List<OrdersDetailEntity> ordersDetailList;

    private int Amount=1;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductDetailsEntity that = (ProductDetailsEntity) o;

        if (id != that.id) return false;
        if (idProduct != that.idProduct) return false;
        if (idSize != that.idSize) return false;
        if (idColor != that.idColor) return false;
        if (idProvided != that.idProvided) return false;
        if (status != that.status) return false;
        if (Double.compare(that.price, price) != 0) return false;

        return true;
    }

    @Override
    public String toString() {
        return "ProductDetailsEntity{}";
    }
}
