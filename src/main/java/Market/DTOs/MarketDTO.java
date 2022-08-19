package Market.DTOs;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data	
public class MarketDTO {
	
	@NotBlank
	private String nameItem;
	
	@NotNull
	private Double price;
	
	@NotNull
	private Integer quantity;


}
