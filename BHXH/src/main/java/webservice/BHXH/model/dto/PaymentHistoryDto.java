package webservice.BHXH.model.dto;

import java.io.Serializable;

public class PaymentHistoryDto implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private InsuranceDto insurance;
    private String time;
    private Integer transactionId;
    private MethodDto method;
    private Double cost;
    private Long baseSalary;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public InsuranceDto getInsurance() {
        return insurance;
    }

    public void setInsurance(InsuranceDto insurance) {
        this.insurance = insurance;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public MethodDto getMethod() {
        return method;
    }

    public void setMethod(MethodDto method) {
        this.method = method;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Long getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(Long baseSalary) {
        this.baseSalary = baseSalary;
    }
}
