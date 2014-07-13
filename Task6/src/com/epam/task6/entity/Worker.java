package com.epam.task6.entity;

public class Worker {
	private int workerId;
	private String name;
	private String surname;
	private int professionId;
	private int qualification;

	public int getWorkerId() {
		return workerId;
	}

	public void setWorkerId( int workerId ) {
		this.workerId = workerId;
	}

	public String getName() {
		return name;
	}

	public void setName( String name ) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname( String surname ) {
		this.surname = surname;
	}

	public int getProfessionId() {
		return professionId;
	}

	public void setProfessionId( int professionId ) {
		this.professionId = professionId;
	}

	public int getQualification() {
		return qualification;
	}

	public void setQualification( int qualification ) {
		this.qualification = qualification;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( name == null ) ? 0 : name.hashCode() );
		result = prime * result + professionId;
		result = prime * result + qualification;
		result = prime * result
				+ ( ( surname == null ) ? 0 : surname.hashCode() );
		result = prime * result + workerId;
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
		Worker other = (Worker) obj;
		if ( name == null ) {
			if ( other.name != null )
				return false;
		} else if ( !name.equals( other.name ) )
			return false;
		if ( professionId != other.professionId )
			return false;
		if ( qualification != other.qualification )
			return false;
		if ( surname == null ) {
			if ( other.surname != null )
				return false;
		} else if ( !surname.equals( other.surname ) )
			return false;
		if ( workerId != other.workerId )
			return false;
		return true;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + " [workerId=" + workerId
				+ ", name=" + name + ", surname=" + surname + ", professionId="
				+ professionId + ", qualification=" + qualification + "]";
	}

}
