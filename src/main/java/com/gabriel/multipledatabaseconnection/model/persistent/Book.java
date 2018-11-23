package com.gabriel.multipledatabaseconnection.model.persistent;

import com.gabriel.multipledatabaseconnection.model.persistent.Author;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "book")

@NamedStoredProcedureQuery(
        name = "addBook_sp",
        procedureName = "addBook",
        parameters = {
                @StoredProcedureParameter(name = "bookName", mode = ParameterMode.IN, type = String.class),
                @StoredProcedureParameter(name = "bookReleaseDate", mode = ParameterMode.IN, type = Date.class),
                @StoredProcedureParameter(name = "authorFirstname", mode = ParameterMode.IN, type = String.class),
                @StoredProcedureParameter(name = "authorLastname", mode = ParameterMode.IN, type = String.class),
        }
)


public class Book implements Serializable {

    private static final long serialVersionUID = -8105130932670794882L;

    @Id
    @SequenceGenerator(name = "idbook_seq", sequenceName = "idbook_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idbook_seq")
    @Column(name = "BOOK_ID")
    private Integer id;

    @Column
    private String name;

    @Column
    @Temporal(TemporalType.DATE)
    private Date releaseDate;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the releaseDate
     */
    public Date getReleaseDate() {
        return releaseDate;
    }

    /**
     * @param releaseDate the releaseDate to set
     */
    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    /**
     * @return the author
     */
    public Author getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(Author author) {
        this.author = author;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }
}