package webservice.BHXH.model.search;

import java.io.Serializable;

public class BaseSearch<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    private T id;
    private Integer start;
    private Integer length;

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }
}
