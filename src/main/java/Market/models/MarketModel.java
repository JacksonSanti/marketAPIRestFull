package Market.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import Market.enuns.StatusItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="market")
public class MarketModel {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long Id;
	
	@Column(name="name")
	private String nameItem;
	
	@Column(name="status")
	private StatusItem statusItem;
	
	private Double price;
	
	private Integer quantity;

}
