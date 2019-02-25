package com.bts.entities;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Employee extends BaseEntity {
	public abstract void setFullName(String fullName);

	public abstract String getFullName();

	public abstract void setEmail(String email);

	public abstract String getEmail();

	public abstract void setPhoneNo(long phoneNo);

	public abstract long getPhoneNo();

	public abstract void setApproved(boolean isApproved);

	public abstract boolean isApproved();

	public abstract void setPassword(String password);

	public abstract String getPassword();
}
