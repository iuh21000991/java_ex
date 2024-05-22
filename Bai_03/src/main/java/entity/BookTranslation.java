package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Table(name = "book_translations")
public class BookTranslation extends Book{
	
	@Column(name ="translate_name")
	private String translateName;
	
	@Column(name ="language")
	private String language;
	
}
