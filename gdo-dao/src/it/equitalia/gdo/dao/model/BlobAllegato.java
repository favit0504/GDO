package it.equitalia.gdo.dao.model;


import it.equitalia.gdo.dao.model.generic.GenericType;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * 
 * @author Valerio Donnarumma
 *
 */
@Entity
@Table(name = "GDO.BLOB_ALLEGATO", uniqueConstraints = @UniqueConstraint(columnNames = {"ID_BLOB_ALLEGATO"}))
public class BlobAllegato  implements java.io.Serializable,GenericType {

	private static final long serialVersionUID = 1L;
	
	@Id	
	@Column(name="ID_BLOB_ALLEGATO", insertable=true, updatable=false)
	private Integer id;
	
	@Column(name="FILE_BLOB") 
	private byte[] fileBlob;

	@OneToOne(cascade = CascadeType.PERSIST, fetch=FetchType.EAGER, mappedBy="blob")
	private Allegato allegato;
	
	public BlobAllegato() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer idBlobAllegato) {
		this.id = idBlobAllegato;
	}

	public byte[] getFileBlob() {
		return fileBlob;
	}

	public void setFileBlob(byte[] fileBlob) {
		this.fileBlob = fileBlob;
	}

	public String getSequenceName() {
		return "GDO.SEQ_ID_BLOB_ALLEGATO";
	}

	public Allegato getAllegato() {
		return allegato;
	}

	public void setAllegato(Allegato allegato) {
		this.allegato = allegato;
	}

}
