package webservice.BHXH.model.dto;

import java.io.Serializable;

public class ConfigDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long baseSalary;
    private Integer methodId;

    public Long getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(Long baseSalary) {
        this.baseSalary = baseSalary;
    }

    public Integer getMethodId() {
        return methodId;
    }

    public void setMethodId(Integer methodId) {
        this.methodId = methodId;
    }
}
