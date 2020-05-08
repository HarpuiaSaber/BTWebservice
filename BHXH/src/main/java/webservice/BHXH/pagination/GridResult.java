package webservice.BHXH.pagination;

import java.io.Serializable;
import java.util.List;

public class GridResult<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	private long totalRecord;
	private List<T> items;

	public long getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(long totalRecord) {
		this.totalRecord = totalRecord;
	}

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}

}
