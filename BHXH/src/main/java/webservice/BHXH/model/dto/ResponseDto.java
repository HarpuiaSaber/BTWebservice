package webservice.BHXH.model.dto;

import java.io.Serializable;
import java.util.List;

public class ResponseDto<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    private int totalRecord;
    private List<T> data;

    public ResponseDto(int totalRecord, List<T> data) {
        this.totalRecord = totalRecord;
        this.data = data;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

}
