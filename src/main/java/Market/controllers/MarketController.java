package Market.controllers;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import Market.DTOs.MarketDTO;
import Market.enuns.StatusItem;
import Market.models.MarketModel;
import Market.services.MarketService;

@RestController
public class MarketController {
	
	
	@Autowired
	MarketService marketService;
	
	
	@PostMapping("/market/")
	public ResponseEntity<Object> createMarket(@RequestBody @Valid MarketDTO marketDTO){
		var marketModel = new MarketModel();
		BeanUtils.copyProperties(marketDTO, marketModel);
		marketModel.setStatusItem(StatusItem.TO_PAY);
		marketModel.setPrice(marketDTO.getPrice() * marketDTO.getQuantity());
		return ResponseEntity.status(HttpStatus.CREATED).body(marketService.save(marketModel));
	}
	
	@GetMapping("/market/")
	public ResponseEntity<List<MarketModel>> getAll(){
		var listMarket = marketService.findAll();
		if(listMarket.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(listMarket);
		}
		return ResponseEntity.status(HttpStatus.OK).body(listMarket);
	}
	
	@GetMapping("/market/{id}")
	public ResponseEntity<Object> getOne(@PathVariable(value= "id") @Valid Long id){
		Optional<MarketModel> marketOptional = marketService.findOne(id);
		if(!marketOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found");
		}
		return ResponseEntity.status(HttpStatus.OK).body(marketOptional.get());
	}
	
	@PutMapping("/market/{id}")
	public ResponseEntity<Object> updateMarket(@PathVariable(value= "id") Long id,@Valid @RequestBody MarketDTO marketDTO){
		Optional<MarketModel> marketOptional = marketService.findOne(id);
		if(!marketOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found");
		}
			var marketModel = marketOptional.get();
			marketModel.setNameItem(marketDTO.getNameItem());
			marketModel.setPrice(Double.valueOf(new DecimalFormat("#,##0.00").format(marketDTO.getPrice()* marketDTO.getQuantity())));
			marketModel.setQuantity(marketDTO.getQuantity());
		return ResponseEntity.status(HttpStatus.OK).body(marketService.save(marketModel));
	}
	
	@DeleteMapping("/market/{id}")
	public ResponseEntity<Object> deleteMarket(@PathVariable(value= "id")@Valid Long id){
		Optional<MarketModel> marketOptional = marketService.findOne(id);
		if(marketOptional.isPresent()) {
			marketService.delete(id);
			return ResponseEntity.status(HttpStatus.OK).body("Item successfully deleted");
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found");	
	}
}
