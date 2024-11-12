package com.goal.model;

public class Goal {
	
	
	 private int goal_Id;
	 private String title;
	 private String description;
	 private int start_date;
	 private int end_date;
	 
	 
	 
	
	 public Goal() {
		super();
		// TODO Auto-generated constructor stub
	}




	public Goal(int goal_Id, String title, String description, int start_date, int end_date) {
		super();
		this.goal_Id = goal_Id;
		this.title = title;
		this.description = description;
		this.start_date = start_date;
		this.end_date = end_date;
	}

     


	public int getGoal_Id() {
		return goal_Id;
	}




	public void setGoal_Id(int goal_Id) {
		this.goal_Id = goal_Id;
	}




	public String getTitle() {
		return title;
	}




	public void setTitle(String title) {
		this.title = title;
	}




	public String getDescription() {
		return description;
	}




	public void setDescription(String description) {
		this.description = description;
	}




	public int getStart_date() {
		return start_date;
	}




	public void setStart_date(int start_date) {
		this.start_date = start_date;
	}




	public int getEnd_date() {
		return end_date;
	}




	public void setEnd_date(int end_date) {
		this.end_date = end_date;
	}




	@Override
	public String toString() {
		return "Goal [goal_Id=" + goal_Id + ", title=" + title + ", description=" + description + ", start_date="
				+ start_date + ", end_date=" + end_date + "]";
	}
     
	
	
	 
	 

}

