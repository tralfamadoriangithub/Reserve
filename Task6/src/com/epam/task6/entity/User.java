package com.epam.task6.entity;

import java.io.Serializable;

public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int userId;
	private String login;
	private String password;
	private String name;
	private String surname;
	private int status;
	
	public User(){}

	public int getUserId() {
		return userId;
	}

	public void setUserId( int userId ) {
		this.userId = userId;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin( String login ) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword( String password ) {
		this.password = password;
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

	public int getStatus() {
		return status;
	}

	public void setStatus( int status ) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( login == null ) ? 0 : login.hashCode() );
		result = prime * result + ( ( name == null ) ? 0 : name.hashCode() );
		result = prime * result
				+ ( ( password == null ) ? 0 : password.hashCode() );
		result = prime * result + status;
		result = prime * result
				+ ( ( surname == null ) ? 0 : surname.hashCode() );
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
		User other = (User) obj;
		if ( login == null ) {
			if ( other.login != null )
				return false;
		} else if ( !login.equals( other.login ) )
			return false;
		if ( name == null ) {
			if ( other.name != null )
				return false;
		} else if ( !name.equals( other.name ) )
			return false;
		if ( password == null ) {
			if ( other.password != null )
				return false;
		} else if ( !password.equals( other.password ) )
			return false;
		if ( status != other.status )
			return false;
		if ( surname == null ) {
			if ( other.surname != null )
				return false;
		} else if ( !surname.equals( other.surname ) )
			return false;
		if ( userId != other.userId )
			return false;
		return true;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + " [userId=" + userId + ", login="
				+ login + ", password=" + password + ", name=" + name
				+ ", surname=" + surname + ", status=" + status + "]";
	}

}
