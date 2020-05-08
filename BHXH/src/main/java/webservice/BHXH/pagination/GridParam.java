package webservice.BHXH.pagination;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GridParam implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("sortDirection")
	private Integer sortDirection;
	@JsonProperty("seachText")
	private String seachText;
	@JsonProperty("filterItems")
	private List<FilterItem> filterItems;
	@JsonProperty("skipCount")
	private Integer skipCount;
	@JsonProperty("maxResultCount")
	private Integer maxResultCount;

	public GridParam() {
		skipCount = 0;
		maxResultCount = Constant.MAX_10;
	}

	public String getSeachText() {
		return seachText;
	}

	public void setSeachText(String seachText) {
		this.seachText = seachText;
	}

	public List<FilterItem> getFilterItems() {
		return filterItems;
	}

	public void setFilterItems(List<FilterItem> filterItems) {
		this.filterItems = filterItems;
	}

	public Integer getSkipCount() {
		return skipCount;
	}

	public void setSkipCount(Integer skipCount) {
		this.skipCount = skipCount;
	}

	public Integer getMaxResultCount() {
		return maxResultCount;
	}

	public void setMaxResultCount(Integer maxResultCount) {
		this.maxResultCount = maxResultCount;
	}

	public Integer getSortDirection() {
		return sortDirection;
	}

	public void setSortDirection(Integer sortDirection) {
		this.sortDirection = sortDirection;
	}

}
