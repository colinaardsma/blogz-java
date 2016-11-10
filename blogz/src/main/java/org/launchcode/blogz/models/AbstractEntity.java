package org.launchcode.blogz.models;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

@MappedSuperclass // makes sure that variables in abstrct class end up in db
public abstract class AbstractEntity {
	private int uid;
	
	@Id
	@GeneratedValue // hibernate will make its own values (we don't have to pick)
	@NotNull
	@Column(name = "uid", unique = true)
	public int getUID() {
		return this.uid;
	}
	
	protected void setUid(int uid) {
		this.uid = uid;
	}
		
}
