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
@Table(name = "rooms")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties( allowGetters = true)
public class Lot implements Serializable{
	  	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	  	
	  	@NotNull
	  	private String typeService;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getTypeService() {
			return typeService;
		}

		public void setTypeService(String typeService) {
			this.typeService = typeService;
		}
	  	
}
