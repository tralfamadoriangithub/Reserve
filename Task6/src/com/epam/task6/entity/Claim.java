package com.epam.task6.entity;

public class Claim {
	private int claimId;
	private String problemDescription;
	private int addressId;
	private int userId;

	public int getClaimId() {
		return claimId;
	}

	public void setClaimId( int claimId ) {
		this.claimId = claimId;
	}

	public String getProblemDescription() {
		return problemDescription;
	}

	public void setProblemDescription( String problemDescription ) {
		this.problemDescription = problemDescription;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId( int addressId ) {
		this.addressId = addressId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId( int userId ) {
		this.userId = userId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + addressId;
		result = prime * result + claimId;
		result = prime
				* result
				+ ( ( problemDescription == null ) ? 0 : problemDescription
						.hashCode() );
		result = prime * result + userId;
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
		Claim other = (Claim) obj;
		if ( addressId != other.addressId )
			return false;
		if ( claimId != other.claimId )
			return false;
		if ( problemDescription == null ) {
			if ( other.problemDescription != null )
				return false;
		} else if ( !problemDescription.equals( other.problemDescription ) )
			return false;
		if ( userId != other.userId )
			return false;
		return true;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + " [claimId=" + claimId
				+ ", problemDescription=" + problemDescription + ", addressId="
				+ addressId + ", userId=" + userId + "]";
	}

}
