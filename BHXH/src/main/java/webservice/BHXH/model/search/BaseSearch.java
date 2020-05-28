package webservice.BHXH.model.search;

import java.io.Serializable;

public class BaseSearch<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	private T id;
	private Integer startCount;
	private Integer maxResultCount;

	public T getId() {
		return id;
	}

	public void setId(T id) {
		this.id = id;
	}

	public Integer getStartCount() {
		return startCount;
	}

	public void setStartCount(Integer startCount) {
		this.startCount = startCount;
	}

	public Integer getMaxResultCount() {
		return maxResultCount;
	}

	public void setMaxResultCount(Integer maxResultCount) {
		this.maxResultCount = maxResultCount;
	}

}
