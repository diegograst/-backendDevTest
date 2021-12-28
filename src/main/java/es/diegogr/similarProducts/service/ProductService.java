package es.diegogr.similarProducts.service;

import java.util.Optional;

import es.diegogr.similarProducts.model.SimilarProducts;

public interface ProductService {

	/**
	 * Gets the similar products to a given one.
	 * 
	 * @param productId
	 *           Identifier of the given product.
	 * @return Returns the similar products to a given one ordered by similarity
	 */
	Optional<SimilarProducts> getProductSimilar(String productId);
}
