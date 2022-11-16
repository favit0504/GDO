package it.equitalia.gdo.commons.valueobjects;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author Valerio Donnarumma
 *
 */
public class BeanVersionabile implements Serializable {

	private static final long serialVersionUID = 1L;
	
	protected Integer id;
	protected Integer codice;
	
	protected String owner;
	protected Date dataCreazione;
	
	protected String ownerModifica;
	protected Date dataModifica;	
	
	protected Boolean valida;
	protected String descrizioneValida;

	
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCodice() {
		return codice;
	}

	public void setCodice(Integer codice) {
		this.codice = codice;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public Date getDataCreazione() {
		return dataCreazione;
	}

	public void setDataCreazione(Date dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	public String getOwnerModifica() {
		return ownerModifica;
	}

	public void setOwnerModifica(String ownerModifica) {
		this.ownerModifica = ownerModifica;
	}

	public Date getDataModifica() {
		return dataModifica;
	}

	public void setDataModifica(Date dataModifica) {
		this.dataModifica = dataModifica;
	}

	public Boolean getValida() {
		return valida;
	}

	public void setValida(Boolean valida) {
		this.valida = valida;
	}

	public String getDescrizioneValida() {
		return descrizioneValida;
	}

	public void setDescrizioneValida(String descrizioneValida) {
		this.descrizioneValida = descrizioneValida;
	}
	
	
	

}
