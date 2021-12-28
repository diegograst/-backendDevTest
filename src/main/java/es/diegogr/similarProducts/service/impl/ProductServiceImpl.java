package es.diegogr.similarProducts.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import es.diegogr.similarProducts.client.ProductClientMock;
import es.diegogr.similarProducts.client.model.SimilarProductsMock;
import es.diegogr.similarProducts.model.ProductDetail;
import es.diegogr.similarProducts.model.SimilarProducts;
import es.diegogr.similarProducts.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductClientMock productClientMock;

	@Override
	public Optional<SimilarProducts> getProductSimilar(String productId) {

		Assert.notNull(productId, "Required");

		// Check product exists before get similar products
		return this.productClientMock.getDetail(productId).map(p -> this.getSimilarProductsFromMockService(p.getId()))
				.orElse(Optional.empty());
	}

	private Optional<SimilarProducts> getSimilarProductsFromMockService(String productId) {

		SimilarProducts similarProducts = new SimilarProducts();

		SimilarProductsMock similarProductsMock = this.productClientMock.getIdsSimilarProducts(productId);

		// @formatter:off
		similarProductsMock.stream()
			.filter(productMockId -> StringUtils.hasText(productMockId))
			.forEach(productMockId -> {
				this.productClientMock.getDetail(productMockId).map(p -> similarProducts.add(ProductDetail.build(p)));	
			});
			
		// @formatter:on

		return Optional.of(similarProducts);
	}

}
