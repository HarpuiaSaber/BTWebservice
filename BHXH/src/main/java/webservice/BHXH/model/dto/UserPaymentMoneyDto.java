package webservice.BHXH.model.dto;

import java.io.Serializable;

public class UserPaymentMoneyDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long userId;
    private Integer month;
    private Double paymentMoney;
    private Double supportMoney;
    private Double totalMoney;
    private boolean paid;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Double getPaymentMoney() {
        return paymentMoney;
    }

    public void setPaymentMoney(Double paymentMoney) {
        this.paymentMoney = paymentMoney;
    }

    public Double getSupportMoney() {
        return supportMoney;
    }

    public void setSupportMoney(Double supportMoney) {
        this.supportMoney = supportMoney;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}
