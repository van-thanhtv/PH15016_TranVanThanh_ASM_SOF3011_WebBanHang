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
@Table(name = "size", schema = "Websales", catalog = "")
public class SizeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "nameSize")
    private String nameSize;
    @OneToMany(mappedBy = "idSize")
    private List<ProductDetailsEntity> productDetailsList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SizeEntity that = (SizeEntity) o;

        if (id != that.id) return false;
        if (nameSize != null ? !nameSize.equals(that.nameSize) : that.nameSize != null) return false;

        return true;
    }

    @Override
    public String toString() {
        return "SizeEntity{}";
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nameSize != null ? nameSize.hashCode() : 0);
        return result;
    }
}
