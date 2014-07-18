package com.epam.task6.entity;

import java.util.Date;

public class Assignation {
	private int assignationId;
	private int claimId;
	private int squadId;
	private Date beginWork;
	private Date endWork;
	
	public Assignation(){
		
	}

	public int getAssignationId() {
		return assignationId;
	}

	public void setAssignationId( int assignationId ) {
		this.assignationId = assignationId;
	}

	public int getClaimId() {
		return claimId;
	}

	public void setClaimId( int claimId ) {
		this.claimId = claimId;
	}

	public int getSquadId() {
		return squadId;
	}

	public void setSquadId( int squadId ) {
		this.squadId = squadId;
	}

	public Date getBeginWork() {
		return beginWork;
	}

	public void setBeginWork( Date beginWork ) {
		this.beginWork = beginWork;
	}

	public Date getEndWork() {
		return endWork;
	}

	public void setEndWork( Date endWork ) {
		this.endWork = endWork;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + assignationId;
		result = prime * result
				+ ( ( beginWork == null ) ? 0 : beginWork.hashCode() );
		result = prime * result + claimId;
		result = prime * result
				+ ( ( endWork == null ) ? 0 : endWork.hashCode() );
		result = prime * result + squadId;
		return result;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( getClass() != obj.getClass() )
			return false;
		Assignation other = (Assignation) obj;
		if ( assignationId != other.assignationId )
			return false;
		if ( beginWork == null ) {
			if ( other.beginWork != null )
				return false;
		} else if ( !beginWork.equals( other.beginWork ) )
			return false;
		if ( claimId != other.claimId )
			return false;
		if ( endWork == null ) {
			if ( other.endWork != null )
				return false;
		} else if ( !endWork.equals( other.endWork ) )
			return false;
		if ( squadId != other.squadId )
			return false;
		return true;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + " [assignationId=" + assignationId + ", claimId="
				+ claimId + ", squadId=" + squadId + ", beginWork=" + beginWork
				+ ", endWork=" + endWork + "]";
	}
	
	
}
