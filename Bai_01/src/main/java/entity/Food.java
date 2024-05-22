package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="foods")
public class Food extends Item {
	

	
	@Enumerated(EnumType.STRING)
	@Column(name="type")
	private Type type;
	
	@Column(name="preparation_time")
	private int preparationTime;
	
	@Column(name="serving_time")
	private int servingTime;
	
	public enum Type {
		APPETIZER, MAIN_COURSE, DESSERT
	}
}
