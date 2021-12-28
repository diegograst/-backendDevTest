package es.diegogr.similarProducts.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import es.diegogr.similarProducts.model.SimilarProducts;
import es.diegogr.similarProducts.service.ProductService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
public class ProductApiController implements ProductApi {

	@Autowired
	private ProductService productService;

	@Override
	public ResponseEntity<SimilarProducts> getProductSimilar(
			@Parameter(in = ParameterIn.PATH, description = "", required = true, schema = @Schema()) @PathVariable("productId") String productId) {

		Optional<SimilarProducts> similarProduct = this.productService.getProductSimilar(productId);
		return similarProduct.map(p -> new ResponseEntity<SimilarProducts>(p, HttpStatus.OK))
				.orElse(new ResponseEntity<SimilarProducts>(HttpStatus.NOT_FOUND));
	}

}
