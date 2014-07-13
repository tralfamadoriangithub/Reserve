package com.epam.task6.entity;

public class ClaimStatus {
	private int claimStatusId;
	private String statusValue;

	public int getClaimStatusId() {
		return claimStatusId;
	}

	public void setClaimStatusId( int claimStatusId ) {
		this.claimStatusId = claimStatusId;
	}

	public String getStatusValue() {
		return statusValue;
	}

	public void setStatusValue( String statusValue ) {
		this.statusValue = statusValue;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + claimStatusId;
		result = prime * result
				+ ( ( statusValue == null ) ? 0 : statusValue.hashCode() );
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
		ClaimStatus other = (ClaimStatus) obj;
		if ( claimStatusId != other.claimStatusId )
			return false;
		if ( statusValue == null ) {
			if ( other.statusValue != null )
				return false;
		} else if ( !statusValue.equals( other.statusValue ) )
			return false;
		return true;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + " [claimStatusId=" + claimStatusId
				+ ", statusValue=" + statusValue + "]";
	}

}
