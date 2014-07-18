package com.epam.task6.entity;

import java.util.ArrayList;
import java.util.List;

public class Squad {
	private int squadId;
	private int assignationId;
	private List<Integer> workersId;
	
	public Squad(){
		workersId = new ArrayList<>();
	}

	public int getSquadId() {
		return squadId;
	}

	public void setSquadId( int squadId ) {
		this.squadId = squadId;
	}

	public int getAssignationId() {
		return assignationId;
	}

	public void setAssignationId( int assignationId ) {
		this.assignationId = assignationId;
	}

	public List<Integer> getWorkersId() {
		return workersId;
	}

	public void setWorkersId( List<Integer> workersId ) {
		this.workersId = workersId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + assignationId;
		result = prime * result + squadId;
		result = prime * result
				+ ( ( workersId == null ) ? 0 : workersId.hashCode() );
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
		Squad other = (Squad) obj;
		if ( assignationId != other.assignationId )
			return false;
		if ( squadId != other.squadId )
			return false;
		if ( workersId == null ) {
			if ( other.workersId != null )
				return false;
		} else if ( !workersId.equals( other.workersId ) )
			return false;
		return true;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + " [squadId=" + squadId + ", assignationId=" + assignationId
				+ ", workersId=" + workersId + "]";
	}
	
	
}
