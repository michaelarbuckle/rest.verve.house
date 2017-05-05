package house.verve.model;
import java.math.BigDecimal;

public interface ThingRepositoryCustom {

	void removeProductsMoreExpensiveThan(BigDecimal price);
}
