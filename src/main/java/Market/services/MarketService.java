package Market.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Market.models.MarketModel;
import Market.repositories.MarketRepository;

@Service
public class MarketService {
	
	
	@Autowired
	MarketRepository marketRepository;

	@Transactional
	public Object save(MarketModel marketModel) {
		return marketRepository.save(marketModel);
	}

	public List<MarketModel> findAll() {
		return marketRepository.findAll();
	}

	public Optional<MarketModel> findOne(@Valid Long id) {
		return marketRepository.findById(id);
	}

	public void delete(@Valid Long id) {
		marketRepository.deleteById(id);
		
	}
	
	
	

}
