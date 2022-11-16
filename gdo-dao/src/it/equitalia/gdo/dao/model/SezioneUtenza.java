package it.equitalia.gdo.dao.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "GDO.SEZIONE_UTENZA", uniqueConstraints = @UniqueConstraint(columnNames = {"ID_SEZIONE", "UTENZA"}))
public class SezioneUtenza implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="UTENZA") 
	private String utenza;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_SEZIONE", nullable=false)
	private Sezione sezione;
	
	
	public SezioneUtenza() {
	}
	
	public Integer getId() {
		return null;
	}

	public void setId(Integer id) {
		return;
	}
	
	public String getUtenza() {
		return utenza;
	}

	public void setUtenza(String utenza) {
		this.utenza = utenza;
	}

	public Sezione getSezione() {
		return sezione;
	}

	public void setSezione(Sezione sezione) {
		this.sezione = sezione;
	}

	public String getSequenceName() {

		return "GDO.SEQ_ID_FILTRO_PROVINCIA";
	}


}
