package es.diegogr.similarProducts.model;

import java.math.BigDecimal;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import es.diegogr.similarProducts.client.model.ProductDetailMock;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * Product detail
 */
@Getter
@Setter
@Schema(description = "Product detail")
@Validated
public class ProductDetail {

	@Schema(required = true, description = "")
	@NotNull
	@Size(min = 1)
	@JsonProperty("id")
	private String id = null;

	@Schema(required = true, description = "")
	@NotNull
	@Size(min = 1)
	@JsonProperty("name")
	private String name = null;

	@Schema(required = true, description = "")
	@NotNull
	@Valid
	@JsonProperty("price")
	private BigDecimal price = null;

	@Schema(required = true, description = "")
	@NotNull
	@JsonProperty("availability")
	private Boolean availability = null;

	/**
	 * Constructor form ProductDetailMock
	 * 
	 * @param productDetailMock
	 *           the ProductDetailMock
	 * @return a new ProductDetails build from ProductDetailMock.
	 */
	public static ProductDetail build(ProductDetailMock productDetailMock) {

		if (productDetailMock == null) {
			return null;
		}

		ProductDetail result = new ProductDetail();
		result.setAvailability(productDetailMock.getAvailability());
		result.setId(productDetailMock.getId());
		result.setName(productDetailMock.getName());
		result.setPrice(productDetailMock.getPrice());
		return result;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ProductDetail productDetail = (ProductDetail) o;
		return Objects.equals(this.id, productDetail.id) && Objects.equals(this.name, productDetail.name)
				&& Objects.equals(this.price, productDetail.price) && Objects.equals(this.availability, productDetail.availability);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, price, availability);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ProductDetail {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    name: ").append(toIndentedString(name)).append("\n");
		sb.append("    price: ").append(toIndentedString(price)).append("\n");
		sb.append("    availability: ").append(toIndentedString(availability)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
