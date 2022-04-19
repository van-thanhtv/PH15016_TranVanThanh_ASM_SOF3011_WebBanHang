package entitys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Orders", schema = "Websales", catalog = "")
public class OrdersEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @ManyToOne
    @JoinColumn(name = "id_users")
    private UsersEntity idUsers;
    @ManyToOne
    @JoinColumn(name = "id_customer")
    private CustomerEntity idCustomer;
    @Basic
    @Column(name = "status")
    private Integer status;
    @Basic
    @Column(name = "dateCreate")
    private Date dateCreate;
    @OneToMany(mappedBy = "idOrders")
    private List<OrdersDetailEntity> ordersDetailList;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrdersEntity that = (OrdersEntity) o;

        if (id != that.id) return false;
        if (idUsers != that.idUsers) return false;
        if (idCustomer != null ? !idCustomer.equals(that.idCustomer) : that.idCustomer != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (dateCreate != null ? !dateCreate.equals(that.dateCreate) : that.dateCreate != null) return false;

        return true;
    }
}
