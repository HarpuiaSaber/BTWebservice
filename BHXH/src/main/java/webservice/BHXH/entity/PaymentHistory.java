package webservice.BHXH.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "user_payment_history")
public class PaymentHistory implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "insurance_id")
    private Insurance insurance;

    @Column(name = "time")
    private Date time;

    @Column(name = "transaction_id")
    private Integer transactionId;

    @JoinColumn(name = "method_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Method method;

    @Column(name = "cost")
    private Double cost;

    @Column(name = "base_salary")
    private Long baseSalary;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Insurance getInsurance() {
        return insurance;
    }

    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
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
