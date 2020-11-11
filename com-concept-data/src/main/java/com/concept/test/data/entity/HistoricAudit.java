package com.concept.test.data.entity;

import java.io.Serializable;

import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public abstract class HistoricAudit extends Audit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Lob
	private byte[] historic;

	@Version
	private Long version;

	public byte[] getHistoric() {
		return historic;
	}

	public void setHistoric(byte[] historic) {
		this.historic = historic;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
}
