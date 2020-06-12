package webservice.BHXH.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "retirement_mode")
public class RetirementMode implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "year")
	private Integer year;

	@Column(name = "gender")
	private String gender;

	@Column(name = "old")
	private Integer old;

	@Column(name = "percentMin")
	private Double minPercent;

	@Column(name = "percentMax")
	private Double maxPercent;

	@Column(name = "increase")
	private Double increase;

	@Column(name = "time")
	private String time;

	@Column(name = "is_deleted")
	private Boolean isDeleted;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getOld() {
		return old;
	}

	public void setOld(Integer old) {
		this.old = old;
	}

	public Double getMinPercent() {
		return minPercent;
	}

	public void setMinPercent(Double minPercent) {
		this.minPercent = minPercent;
	}

	public Double getMaxPercent() {
		return maxPercent;
	}

	public void setMaxPercent(Double maxPercent) {
		this.maxPercent = maxPercent;
	}

	public Double getIncrease() {
		return increase;
	}

	public void setIncrease(Double increase) {
		this.increase = increase;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

}
