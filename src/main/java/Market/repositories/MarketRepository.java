package Market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Market.models.MarketModel;

@Repository
public interface MarketRepository extends JpaRepository<MarketModel, Long>{

}
