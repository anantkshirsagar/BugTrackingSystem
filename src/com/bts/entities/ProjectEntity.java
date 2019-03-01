package com.bts.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.bts.utils.DBConstants;

@Entity
@Table
public class ProjectEntity extends BaseEntity {
	private String projectName;
	private String description;
	private DBConstants.ProjectStatus status;
	@Temporal(TemporalType.DATE)
	private Date startDate;
	@Temporal(TemporalType.DATE)
	private Date endDate;
	private String feature;
	private String subFeature;
	private String reason;
	private String remark;
	private boolean isCurrentProject;
	private List<Bug> bugList;

	@ManyToMany
	private List<DeveloperEntity> developerList;

	@ManyToMany
	private List<TesterEntity> testerList;

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public String getSubFeature() {
		return subFeature;
	}

	public void setSubFeature(String subFeature) {
		this.subFeature = subFeature;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<Bug> getBugList() {
		return bugList;
	}

	public void setBugList(List<Bug> bugList) {
		this.bugList = bugList;
	}

	public boolean isCurrentProject() {
		return isCurrentProject;
	}

	public void setCurrentProject(boolean isCurrentProject) {
		this.isCurrentProject = isCurrentProject;
	}

	public List<DeveloperEntity> getDeveloperList() {
		return developerList;
	}

	public void setDeveloperList(List<DeveloperEntity> developerList) {
		this.developerList = developerList;
	}

	public List<TesterEntity> getTesterList() {
		return testerList;
	}

	public void setTesterList(List<TesterEntity> testerList) {
		this.testerList = testerList;
	}

	public DBConstants.ProjectStatus getStatus() {
		return status;
	}

	public void setStatus(DBConstants.ProjectStatus status) {
		this.status = status;
	}

}
