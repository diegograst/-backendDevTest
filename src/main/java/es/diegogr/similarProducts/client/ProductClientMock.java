package es.diegogr.similarProducts.client;

import java.util.Optional;

import es.diegogr.similarProducts.client.model.ProductDetailMock;
import es.diegogr.similarProducts.client.model.SimilarProductsMock;

/**
 * Client for call Mock Product Api.
 * 
 * @author diegogr
 *
 */
public interface ProductClientMock {

	/**
	 * Gets the ids of the similar products.
	 * 
	 * @param productId
	 *           identifier of the given product.
	 * @return the similar products to a given one ordered by similarity.
	 */
	SimilarProductsMock getIdsSimilarProducts(String productId);

	/**
	 * Gets a product detail.
	 * 
	 * @param productId
	 *           identifier of the given product.
	 * @return the product detail for a given productId
	 */
	Optional<ProductDetailMock> getDetail(String productId);
}
