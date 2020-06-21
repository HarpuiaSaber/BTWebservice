package webservice.BHXH.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import webservice.BHXH.enums.HumanObject;

@Entity
@Table(name = "support_type")
public class SupportType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JoinColumn(name = "human_object")
	@Enumerated(EnumType.ORDINAL)
	private HumanObject humanObject;

	@Column(name = "percent")
	private Integer percent;

	@Column(name = "income")
	private Long income;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public HumanObject getHumanObject() {
		return humanObject;
	}

	public void setHumanObject(HumanObject humanObject) {
		this.humanObject = humanObject;
	}

	public Integer getPercent() {
		return percent;
	}

	public void setPercent(Integer percent) {
		this.percent = percent;
	}

	public Long getIncome() {
		return income;
	}

	public void setIncome(Long income) {
		this.income = income;
	}

}
