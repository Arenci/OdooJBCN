package com.odooCN.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the res_partner_title database table.
 * 
 */
@Entity
@Table(name="res_partner_title")
@NamedQuery(name="ResPartnerTitle.findAll", query="SELECT r FROM ResPartnerTitle r")
public class ResPartnerTitle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@Column(name="create_date")
	private Timestamp createDate;

	@Column(name="create_uid")
	private Integer createUid;

	private String name;

	private String shortcut;

	@Column(name="write_date")
	private Timestamp writeDate;

	@Column(name="write_uid")
	private Integer writeUid;

	//bi-directional many-to-one association to ResPartner
	@OneToMany(mappedBy="resPartnerTitle")
	private List<ResPartner> resPartners;

	public ResPartnerTitle() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public Integer getCreateUid() {
		return this.createUid;
	}

	public void setCreateUid(Integer createUid) {
		this.createUid = createUid;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortcut() {
		return this.shortcut;
	}

	public void setShortcut(String shortcut) {
		this.shortcut = shortcut;
	}

	public Timestamp getWriteDate() {
		return this.writeDate;
	}

	public void setWriteDate(Timestamp writeDate) {
		this.writeDate = writeDate;
	}

	public Integer getWriteUid() {
		return this.writeUid;
	}

	public void setWriteUid(Integer writeUid) {
		this.writeUid = writeUid;
	}

	public List<ResPartner> getResPartners() {
		return this.resPartners;
	}

	public void setResPartners(List<ResPartner> resPartners) {
		this.resPartners = resPartners;
	}

	public ResPartner addResPartner(ResPartner resPartner) {
		getResPartners().add(resPartner);
		resPartner.setResPartnerTitle(this);

		return resPartner;
	}

	public ResPartner removeResPartner(ResPartner resPartner) {
		getResPartners().remove(resPartner);
		resPartner.setResPartnerTitle(null);

		return resPartner;
	}

}