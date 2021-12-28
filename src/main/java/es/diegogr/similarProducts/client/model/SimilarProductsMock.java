package es.diegogr.similarProducts.client.model;

import java.util.ArrayList;
import java.util.Objects;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * List of similar product Ids to a given one ordered by similarity
 */
@Schema(description = "List of similar product Ids to a given one ordered by similarity")
public class SimilarProductsMock extends ArrayList<String> {

	private static final long serialVersionUID = 4832726560000073711L;

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		return super.equals(o);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode());
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class SimilarProducts {\n");
		sb.append("    ").append(toIndentedString(super.toString())).append("\n");
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
