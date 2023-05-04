package com.movieticketbooking.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.movieticketbooking.entity.BookedSeat;
import com.movieticketbooking.entity.Movie;
import com.movieticketbooking.entity.Screening;
import com.movieticketbooking.entity.Seat;

@Repository
public class BookedSeatDao {

	@Autowired
	private SessionFactory sf;

	public List<BookedSeat> getAllBookedSeat() {
		Session session = sf.openSession();
		Transaction tr = session.beginTransaction();
		Criteria crt = session.createCriteria(BookedSeat.class);
		List<BookedSeat> bookedSeat = crt.list();
		tr.commit();
		return bookedSeat;
	}

	public BookedSeat getBookedSeatById(int id) {
		Session session = sf.openSession();
		Transaction tr = session.beginTransaction();
		Criteria crt = session.createCriteria(BookedSeat.class);
		BookedSeat bookedSeat = session.get(BookedSeat.class, id);
		tr.commit();
		return bookedSeat;
	}

	public String updateBookedSeat(BookedSeat bookedSeat) {
		Session session = sf.openSession();
		Transaction tr=session.beginTransaction();
		
		CriteriaBuilder builder = session.getCriteriaBuilder();
	    CriteriaQuery<BookedSeat> query = builder.createQuery(BookedSeat.class);
	    Root<BookedSeat> root = query.from(BookedSeat.class);
	    
	    query.where(builder.equal(root.get("bookedseatid"), bookedSeat.getBookedseatid()));
	    
	    List<BookedSeat> result = session.createQuery(query).getResultList();
	    
		if(!result.isEmpty())
		{
			BookedSeat bookedseat=result.get(0);
			bookedseat.getSeat().setSeatno(bookedSeat.getSeat().getSeatno());
			bookedseat.getScreening().setDate(bookedSeat.getScreening().getDate());
			bookedseat.getScreening().setPrice(bookedSeat.getScreening().getPrice());
			
			Screening screen=bookedseat.getScreening();
			Movie movie=screen.getMovie();
			movie.setTitle(bookedSeat.getScreening().getMovie().getTitle());
			movie.setDuration(bookedSeat.getScreening().getMovie().getDuration());
			movie.setLeadactor_name(bookedSeat.getScreening().getMovie().getLeadactor_name());
			movie.setRating(bookedSeat.getScreening().getMovie().getRating());
			movie.setCategory(bookedSeat.getScreening().getMovie().getCategory());
			movie.setTheater(bookedSeat.getScreening().getMovie().getTheater());
			
			tr.commit();
			session.update(bookedseat);
			return "Updated successfully ";
		}
		else {
			return "No booked seat found with id :"+ bookedSeat.getBookedseatid();
		}
	}

	public List<Integer> getBookedSeatBySeatid(int id) {
		Session session = sf.openSession();
		Transaction tr = session.beginTransaction();
		Criteria crt = session.createCriteria(BookedSeat.class);
		crt.add(Restrictions.eq("seat.seatid", id));
		crt.setProjection(Projections.property("bookedseatid") );
		List<Integer> bookedseatid=crt.list();
		tr.commit();
		return bookedseatid;
	}

	public Seat getSeatByBookedseatId(int id) {
		Session session = sf.openSession();
		Transaction tr = session.beginTransaction();
		Criteria crt = session.createCriteria(BookedSeat.class);
		crt.add(Restrictions.eq("bookedseatid", id));
		crt.setProjection(Projections.property("seat"));
		Seat seat=(Seat)crt.uniqueResult();
		tr.commit();
		return seat;
	}

	public Screening getScreeningByBookedseatId(int id) {
		Session session = sf.openSession();
		Transaction tr = session.beginTransaction();
		Criteria crt = session.createCriteria(BookedSeat.class);
		crt.add(Restrictions.eq("bookedseatid", id));
		crt.setProjection(Projections.property("screening"));
		Screening screening=(Screening)crt.uniqueResult();
		tr.commit();
		return screening;
	}

}
