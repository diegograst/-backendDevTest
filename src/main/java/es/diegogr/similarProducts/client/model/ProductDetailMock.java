package es.diegogr.similarProducts.client.model;

import java.math.BigDecimal;
import java.util.Objects;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * Product detail
 */
@Getter
@Setter
@Schema(description = "Product detail")
public class ProductDetailMock {

	@Schema(required = true, description = "")
	private String id = null;

	@Schema(required = true, description = "")
	private String name = null;

	@Schema(required = true, description = "")
	private BigDecimal price = null;

	@Schema(required = true, description = "")
	private Boolean availability = null;

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ProductDetailMock productDetail = (ProductDetailMock) o;
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
