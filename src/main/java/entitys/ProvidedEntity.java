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
@Table(name = "provided", schema = "Websales", catalog = "")
public class ProvidedEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "address")
    private String address;
    @Basic
    @Column(name = "nameProvided")
    private String nameProvided;
    @OneToMany(mappedBy = "idProvided")
    private List<ProductDetailsEntity> productDetailsList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProvidedEntity that = (ProvidedEntity) o;

        if (id != that.id) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (nameProvided != null ? !nameProvided.equals(that.nameProvided) : that.nameProvided != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (nameProvided != null ? nameProvided.hashCode() : 0);
        return result;
    }
}
