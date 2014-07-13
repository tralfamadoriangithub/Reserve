package com.epam.task6.entity;

public class Address {
	private int addressId;
	private String street;
	private int houseNumber;
	private int blockNumber;
	private int flatNumber;
	private int userId;

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId( int addressId ) {
		this.addressId = addressId;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet( String street ) {
		this.street = street;
	}

	public int getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber( int houseNumber ) {
		this.houseNumber = houseNumber;
	}

	public int getBlockNumber() {
		return blockNumber;
	}

	public void setBlockNumber( int blockNumber ) {
		this.blockNumber = blockNumber;
	}

	public int getFlatNumber() {
		return flatNumber;
	}

	public void setFlatNumber( int flatNumber ) {
		this.flatNumber = flatNumber;
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
		result = prime * result + blockNumber;
		result = prime * result + flatNumber;
		result = prime * result + houseNumber;
		result = prime * result + ( ( street == null ) ? 0 : street.hashCode() );
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
		Address other = (Address) obj;
		if ( addressId != other.addressId )
			return false;
		if ( blockNumber != other.blockNumber )
			return false;
		if ( flatNumber != other.flatNumber )
			return false;
		if ( houseNumber != other.houseNumber )
			return false;
		if ( street == null ) {
			if ( other.street != null )
				return false;
		} else if ( !street.equals( other.street ) )
			return false;
		if ( userId != other.userId )
			return false;
		return true;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + " [addressId=" + addressId
				+ ", street=" + street + ", houseNumber=" + houseNumber
				+ ", blockNumber=" + blockNumber + ", flatNumber=" + flatNumber
				+ ", userId=" + userId + "]";
	}

}
