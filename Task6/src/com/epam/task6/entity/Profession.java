package com.epam.task6.entity;

import java.io.Serializable;

public class Profession implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int professionId;
	private String professionName;
	
	public Profession(){}

	public int getProfessionId() {
		return professionId;
	}

	public void setProfessionId( int professionId ) {
		this.professionId = professionId;
	}

	public String getProfessionName() {
		return professionName;
	}

	public void setProfessionName( String professionName ) {
		this.professionName = professionName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + professionId;
		result = prime * result
				+ ( ( professionName == null ) ? 0 : professionName.hashCode() );
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
		Profession other = (Profession) obj;
		if ( professionId != other.professionId )
			return false;
		if ( professionName == null ) {
			if ( other.professionName != null )
				return false;
		} else if ( !professionName.equals( other.professionName ) )
			return false;
		return true;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + " [professionId=" + professionId
				+ ", professionName=" + professionName + "]";
	}

}
