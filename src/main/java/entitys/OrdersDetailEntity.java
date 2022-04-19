package entitys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ordersDetail", schema = "Websales", catalog = "")
public class OrdersDetailEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @ManyToOne
    @JoinColumn(name = "id_Orders")
    private OrdersEntity idOrders;
    @ManyToOne
    @JoinColumn(name = "id_productDetails")
    private ProductDetailsEntity idProductDetails;
    @Basic
    @Column(name = "quantily")
    private int quantily;
    @Basic
    @Column(name = "price")
    private Double price;
    @Basic
    @Column(name = "status")
    private Integer status;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrdersDetailEntity that = (OrdersDetailEntity) o;

        if (id != that.id) return false;
        if (idOrders != that.idOrders) return false;
        if (quantily != that.quantily) return false;
        if (idProductDetails != null ? !idProductDetails.equals(that.idProductDetails) : that.idProductDetails != null)
            return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;

        return true;
    }

}
