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
@Table(name = "color", schema = "Websales", catalog = "")
public class ColorEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "nameColor")
    private String nameColor;
    @OneToMany(mappedBy = "idColor")
    private List<ProductDetailsEntity> productDetailsList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ColorEntity that = (ColorEntity) o;

        if (id != that.id) return false;
        if (nameColor != null ? !nameColor.equals(that.nameColor) : that.nameColor != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nameColor != null ? nameColor.hashCode() : 0);
        return result;
    }
}
