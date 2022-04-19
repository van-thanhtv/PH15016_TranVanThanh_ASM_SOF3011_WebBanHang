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
@Table(name = "users", schema = "Websales", catalog = "")
public class UsersEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "passwordUser")
    private String passwordUser;
    @Basic
    @Column(name = "numberPhone")
    private String numberPhone;
    @Basic
    @Column(name = "sex")
    private int sex;
    @Basic
    @Column(name = "birthday")
    private Date birthday;
    @Basic
    @Column(name = "address")
    private String address;
    @Basic
    @Column(name = "avatar")
    private String avatar;
    @Basic
    @Column(name = "isAdmin")
    private byte isAdmin;
    @Basic
    @Column(name = "status")
    private byte status;
    @OneToMany(mappedBy = "idUsers")
    private List<OrdersEntity> ordersList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersEntity that = (UsersEntity) o;

        if (id != that.id) return false;
        if (sex != that.sex) return false;
        if (isAdmin != that.isAdmin) return false;
        if (status != that.status) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (passwordUser != null ? !passwordUser.equals(that.passwordUser) : that.passwordUser != null) return false;
        if (numberPhone != null ? !numberPhone.equals(that.numberPhone) : that.numberPhone != null) return false;
        if (birthday != null ? !birthday.equals(that.birthday) : that.birthday != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (avatar != null ? !avatar.equals(that.avatar) : that.avatar != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (passwordUser != null ? passwordUser.hashCode() : 0);
        result = 31 * result + (numberPhone != null ? numberPhone.hashCode() : 0);
        result = 31 * result + sex;
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (avatar != null ? avatar.hashCode() : 0);
        result = 31 * result + (int) isAdmin;
        result = 31 * result + (int) status;
        return result;
    }
}
