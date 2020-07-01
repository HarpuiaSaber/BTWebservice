package webservice.BHXH.model.search;

import java.io.Serializable;

public class DateSearch extends BaseSearch<Integer> {
    private String toDate;
    private String fromDate;

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }
}
