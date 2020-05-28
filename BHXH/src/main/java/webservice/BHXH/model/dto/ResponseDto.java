package webservice.BHXH.model.dto;

import java.io.Serializable;
import java.util.List;

public class ResponseDto<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	private int totalRecord;
	private List<T> item;

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public List<T> getItem() {
		return item;
	}

	public void setItem(List<T> item) {
		this.item = item;
	}

}
