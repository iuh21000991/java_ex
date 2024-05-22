package entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="items")
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Item {
	@Id
	@Column(name="id")
	protected String id;
	
	@Column(name="name")
	protected String name;
	@Column(name="price")
	protected double price;
	@Column(name="description")
	protected String description;
	@Column(name="on_special")
	protected boolean onSpecial;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@ToString.Exclude
	@JoinTable(name="items_ingredients",
        joinColumns=@JoinColumn(name="item_id"),
        inverseJoinColumns=@JoinColumn(name="ingredient_id"))
	private List<Ingredient> ingredients;
}
