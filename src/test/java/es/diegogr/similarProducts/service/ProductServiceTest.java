package es.diegogr.similarProducts.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import es.diegogr.similarProducts.client.ProductClientMock;
import es.diegogr.similarProducts.client.model.ProductDetailMock;
import es.diegogr.similarProducts.client.model.SimilarProductsMock;
import es.diegogr.similarProducts.model.SimilarProducts;
import es.diegogr.similarProducts.service.impl.ProductServiceImpl;

@SpringBootTest
public class ProductServiceTest {

	@InjectMocks
	private ProductService productService = new ProductServiceImpl();

	@Mock
	private ProductClientMock productClientMock;

	@Test
	public void injectServices() {
		assertNotNull(this.productService);
	}

	@Test
	public void getProductSimiliar_failWithProductIdNull() {
		Exception exception = assertThrows(RuntimeException.class, () -> {
			this.productService.getProductSimilar(null);
		});

		assertEquals(exception.getMessage(), "Required");
	}

	@Test
	public void getProductSimiliar_findNoExistsProduct() {
		final String NO_EXISTS_PRODUCT_ID = "NoExistsProductId";

		// When
		when(this.productClientMock.getDetail(NO_EXISTS_PRODUCT_ID)).thenReturn(Optional.empty());

		// Given
		Optional<SimilarProducts> similarProducts = this.productService.getProductSimilar(NO_EXISTS_PRODUCT_ID);

		// Assert
		assertFalse(similarProducts.isPresent());
	}

	@Test
	public void getProductSimiliar_findExistsProduct() {

		// When
		when(this.productClientMock.getDetail(anyString())).thenReturn(Optional.of(this.buildExistingProductDetailMock()));
		when(this.productClientMock.getIdsSimilarProducts(anyString())).thenReturn(this.buildSimilarProductsMock());

		// Given
		Optional<SimilarProducts> similarProducts = this.productService.getProductSimilar("ExistsProductId");

		// Assert
		assertTrue(similarProducts.isPresent());
		assertEquals(similarProducts.get().size(), Integer.valueOf(8));
		similarProducts.get().stream().forEach(p -> {
			assertNotNull(p.getAvailability());
			assertNotNull(p.getId());
			assertNotNull(p.getName());
			assertNotNull(p.getPrice());
		});
	}

	private SimilarProductsMock buildSimilarProductsMock() {
		SimilarProductsMock result = new SimilarProductsMock();

		for (int i = 0; i < 8; i++) {
			result.add("a".concat(String.valueOf(i)));
		}

		return result;
	}

	private ProductDetailMock buildExistingProductDetailMock() {
		long aux = new Date().getTime();

		ProductDetailMock result = new ProductDetailMock();
		result.setAvailability(aux % 2 == 0);
		result.setId(String.valueOf(aux));
		result.setName("Product-".concat(result.getId().substring(result.getId().length() - 4)));
		result.setPrice(new BigDecimal(result.getId().substring(result.getId().length() - 4)));
		return result;
	}
}
