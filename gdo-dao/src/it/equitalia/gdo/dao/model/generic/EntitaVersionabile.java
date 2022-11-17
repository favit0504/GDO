package it.equitalia.gdo.dao.model.generic;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class EntitaVersionabile implements GenericType{

	@Id
	@Column(name="ID", insertable=true, updatable=false)
	protected Integer id;
	
	@Column(name="CODICE", insertable=true, updatable=false)
	protected Integer codice;
	
	@Column(name="OWNER")
	protected String owner;
	
	@Column(name="DATA_CREAZIONE")
	protected Date dataCreazione;
	
	@Column(name="OWNER_MODIFICA")
	protected String ownerModifica;
	
	@Column(name="DATA_MODIFICA")
	protected Date dataModifica;
	
	@Column(name="VALIDA")
	protected Boolean valida;

	
	
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
	
	
	
}
