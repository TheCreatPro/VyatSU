package biblio;

import javax.persistence.*;

@Entity  // то чтобы таблица создавалась
@Table(name = "catalogs")  // в идеале название в таблице в единственном числе
public class Catalog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")

    private String title;
    public Long getId() {return id;}

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Catalog() {}
}
