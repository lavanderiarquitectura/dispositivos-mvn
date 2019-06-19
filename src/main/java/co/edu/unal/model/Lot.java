package co.edu.unal.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "lots")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties( allowGetters = true)
public class Lot implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Integer typeOperation;

	private Integer typeFabric;

	private Boolean isFinished;
	
	private Integer state;

	@Column(name="capacity", columnDefinition="Integer(11) default '0'")
	private Integer capacity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getTypeOperation() {
		return typeOperation;
	}

	public void setTypeOperation(Integer typeOperation) {
		this.typeOperation = typeOperation;
	}

	public Integer getTypeFabric() {
		return typeFabric;
	}

	public void setTypeFabric(Integer typeFabric) {
		this.typeFabric = typeFabric;

	}

	public Boolean getIsFinished() {
		return isFinished;
	}

	public void setIsFinished(Boolean finished) {
		isFinished = finished;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
}
