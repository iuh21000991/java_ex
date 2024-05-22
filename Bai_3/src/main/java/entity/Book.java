package entity;

import java.util.List;
import java.util.Set;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Data
@Entity
@Table(name = "books")
@Inheritance(strategy = InheritanceType.JOINED)
public class Book {
	
	@Id
	protected String ISBN;
	protected String name;
	@Column(name = "publish_year")
	protected int publishYear;
	@Column(name = "num_of_pages")
	protected int numOfPages;
	protected double price;
	
	
	@ElementCollection(fetch = jakarta.persistence.FetchType.EAGER)
	@CollectionTable(name = "books_authors", joinColumns = @JoinColumn(name = "ISBN"))
	@Column(name = "author", nullable = false)
	protected Set<String> authors;

	
	@ManyToOne(fetch = jakarta.persistence.FetchType.EAGER)
	@JoinColumn(name = "publisher_id")
	protected Publisher publisher;
	
}
