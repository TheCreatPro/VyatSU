package biblio;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "readers")
public class Reader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // будет ли автоматически генерироваться ID?
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;  // в идеале фио разделять


    @ManyToMany // много читателей и много книг, при manytomany надо создавать новую таблицу
    // в ней указывается кто сколько книг взял
    @JoinTable(
            name = "books_readers",
            joinColumns = @JoinColumn(name = "reader_id"), // как у нас названо поле такой и идентификатор
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )

    private List<Book> books;

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
