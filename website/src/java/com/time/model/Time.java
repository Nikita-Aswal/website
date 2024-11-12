package com.time.model;

import java.sql.Timestamp;

public class Time {
    private int entryId;
    private Timestamp startTime;
    private Timestamp endTime;
    private String description;
    private Timestamp createdAt;
    private Timestamp updatedAt;
	
    
    public Time() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Time(int entryId, Timestamp startTime, Timestamp endTime, String description, Timestamp createdAt,
			Timestamp updatedAt) {
		super();
		this.entryId = entryId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.description = description;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}



	public int getEntryId() {
		return entryId;
	}



	public void setEntryId(int entryId) {
		this.entryId = entryId;
	}



	public Timestamp getStartTime() {
		return startTime;
	}



	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}



	public Timestamp getEndTime() {
		return endTime;
	}



	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public Timestamp getCreatedAt() {
		return createdAt;
	}



	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}



	public Timestamp getUpdatedAt() {
		return updatedAt;
	}



	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}



	@Override
	public String toString() {
		return "Time [entryId=" + entryId + ", startTime=" + startTime + ", endTime=" + endTime + ", description="
				+ description + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}
    

}


    