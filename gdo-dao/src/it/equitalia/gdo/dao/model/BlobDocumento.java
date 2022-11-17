package it.equitalia.gdo.dao.model;


import it.equitalia.gdo.dao.model.generic.GenericType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * 
 * @author Valerio Donnarumma
 *
 */
@Entity
@Table(name = "GDO.BLOB_DOCUMENTO", uniqueConstraints = @UniqueConstraint(columnNames = {"ID_BLOB_DOCUMENTO"}))
public class BlobDocumento implements java.io.Serializable, GenericType {
	private final static String SEQUENCE_NAME = "GDO.SEQ_ID_BLOB_DOCUMENTO";
	private static final long serialVersionUID = 1L;
	
	@Id	
	@Column(name="ID_BLOB_DOCUMENTO", insertable=true, updatable=false)
	private Integer id;
	
	@Column(name="FILE_BLOB")
	private byte[] fileBlob;
	

	public BlobDocumento() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public byte[] getFileBlob() {
		return fileBlob;
	}

	public void setFileBlob(byte[] fileBlob) {
		this.fileBlob = fileBlob;
	}

	public String getSequenceName() {
		return SEQUENCE_NAME;
	}




}
