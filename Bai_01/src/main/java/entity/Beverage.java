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
@Table(name = "beverages")
public class Beverage extends Item{
	
	@Enumerated(EnumType.STRING)
	@Column(name = "size")
	private Size size;

	@Column(name = "supplier_name")
	private String supplierName;
	
	private enum Size {
		LARGE, MEDIUM, SMALL
	}

}
