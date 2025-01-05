
package com.goal.model;
import java.sql.Timestamp;

public class Goal {
    private int goalId;
    private String title;
    private String goalDescription;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    
    

    public Goal() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Goal(int goalId, String title, String goalDescription, Timestamp createdAt, Timestamp updatedAt) {
        this.goalId = goalId;
        this.title = title;
        this.goalDescription = goalDescription;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
	
	public int getGoalId() {
		return goalId;
	}

	public void setGoalId(int goalId) {
		this.goalId = goalId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGoalDescription() {
		return goalDescription;
	}

	public void setGoalDescription(String goalDescription) {
		this.goalDescription = goalDescription;
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
		return "Goal [goalId=" + goalId + ", title=" + title + ", goalDescription=" + goalDescription + ", createdAt="
				+ createdAt + ", updatedAt=" + updatedAt + "]";
	}



}
	
	

    

