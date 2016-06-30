package com.uspaceacademy.vo;

import java.io.Serializable;

public class Secret implements Serializable{
	private String secret;

	public Secret(){}
	
	public Secret(String secret) {
		super();
		this.secret = secret;
	}

	@Override
	public String toString() {
		return "Secret [secret=" + secret + "]";
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((secret == null) ? 0 : secret.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Secret other = (Secret) obj;
		if (secret == null) {
			if (other.secret != null)
				return false;
		} else if (!secret.equals(other.secret))
			return false;
		return true;
	}	
}
