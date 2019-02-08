package com.movie.movie.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Price {
	@Column(name = "price_krw")
	Integer krw;
	
	public Price() {}
	
	public Price(Integer krw) {
		this.krw = krw;
	}

	public Integer getKrw() {
		return krw;
	}
}
