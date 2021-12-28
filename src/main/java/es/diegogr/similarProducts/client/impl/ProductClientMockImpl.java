package es.diegogr.similarProducts.client.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import es.diegogr.similarProducts.client.ProductClientMock;
import es.diegogr.similarProducts.client.model.ProductDetailMock;
import es.diegogr.similarProducts.client.model.SimilarProductsMock;

@Service
public class ProductClientMockImpl implements ProductClientMock {

	private final static String SIMILAR_IDS_ENDPOINT = "product/%s/similarids";
	private final static String DETAIL_ENDPOINT = "product/%s";

	@Autowired
	private RestTemplateBuilder restTemplateBuilder;

	@Value("${mock.host}")
	private String mockHost;

	@Override
	public SimilarProductsMock getIdsSimilarProducts(String productId) {
		return this.buildRestTemplate().getForObject(this.mockHost.concat(String.format(SIMILAR_IDS_ENDPOINT, productId)),
				SimilarProductsMock.class);
	}

	@Override
	public Optional<ProductDetailMock> getDetail(String productId) {

		ResponseEntity<ProductDetailMock> responseEntity = this.buildRestTemplate()
				.getForEntity(this.mockHost.concat(String.format(DETAIL_ENDPOINT, productId)), ProductDetailMock.class);

		if (responseEntity.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
			return Optional.empty();
		} else {
			return Optional.of(responseEntity.getBody());
		}

	}

	private RestTemplate buildRestTemplate() {
		return this.restTemplateBuilder.build();
	}

}
