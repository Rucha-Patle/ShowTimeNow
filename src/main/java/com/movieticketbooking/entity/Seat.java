package com.movieticketbooking.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Seat {

	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int seatid;
	private int seatno;
	
	public int getSeatid() {
		return seatid;
	}
	public void setSeatid(int seatid) {
		this.seatid = seatid;
	}
	public int getSeatno() {
		return seatno;
	}
	public void setSeatno(int seatno) {
		this.seatno = seatno;
	}
	
	public Seat(int seatid, int seatno) {
		super();
		this.seatid = seatid;
		this.seatno = seatno;
	}
	
	public Seat() {
		super();
	}
	
	
}
