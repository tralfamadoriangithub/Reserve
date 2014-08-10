package com.epam.task6.tableentity;

import java.io.Serializable;

import com.epam.task6.entity.Address;

public class ClaimTableEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int claimId;
	private String problemDescription;
	private Address address;
	private String claimStatus;
	
	public ClaimTableEntity(){}

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

	public Address getAddress() {
		return address;
	}

	public void setAddress( Address address ) {
		this.address = address;
	}
	
	public String getClaimStatus() {
		return claimStatus;
	}

	public void setClaimStatus( String claimStatus ) {
		this.claimStatus = claimStatus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + claimId;
		result = prime * result
				+ ((claimStatus == null) ? 0 : claimStatus.hashCode());
		result = prime
				* result
				+ ((problemDescription == null) ? 0 : problemDescription
						.hashCode());
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
		ClaimTableEntity other = (ClaimTableEntity) obj;
		if ( address == null ) {
			if ( other.address != null )
				return false;
		} else if ( !address.equals( other.address ) )
			return false;
		if ( claimId != other.claimId )
			return false;
		if ( claimStatus == null ) {
			if ( other.claimStatus != null )
				return false;
		} else if ( !claimStatus.equals( other.claimStatus ) )
			return false;
		if ( problemDescription == null ) {
			if ( other.problemDescription != null )
				return false;
		} else if ( !problemDescription.equals( other.problemDescription ) )
			return false;
		
		return true;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + " [claimId=" + claimId + ", problemDescription="
				+ problemDescription + ", address=" + address + ", claimStatus=" + claimStatus + "]";
	}
	
}
