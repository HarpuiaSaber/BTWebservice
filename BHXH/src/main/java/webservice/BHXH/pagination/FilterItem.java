package webservice.BHXH.pagination;

public class FilterItem {

	private String propertyName;
	private Object value;
	private int comparison;

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public int getComparison() {
		return comparison;
	}

	public void setComparison(int comparison) {
		this.comparison = comparison;
	}

}
