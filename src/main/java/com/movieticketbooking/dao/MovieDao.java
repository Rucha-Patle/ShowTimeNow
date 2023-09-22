package com.movieticketbooking.dao;


import java.util.ArrayList;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.movieticketbooking.entity.Movie;

@Repository
public class MovieDao {
	
	@Autowired
	private SessionFactory sf;
	
	public Movie getMoviebyId(int id)
	{
		Session session=sf.openSession();
		Transaction tr=session.beginTransaction();
		Movie movie=session.get(Movie.class, id);
		tr.commit();
		return movie;
	}

	public String saveMovie(Movie movie) {
		Session session=sf.openSession();
		Transaction tr=session.beginTransaction();
		session.save(movie);
		tr.commit();
		return "Movie saved";
	}

	public String updateMovie(Movie movie) {
		Session session=sf.openSession();
		Transaction tr=session.beginTransaction();
		try{
			session.update(movie);
			tr.commit();
			return "Movie updated";
		}
		catch (Exception e) {
			return "Movie of this id is not present, please enter again";
		}
	}

	public String deleteMovie(int id) {
		Session session=sf.openSession();
		Transaction tr=session.beginTransaction();
		Movie movie=session.get(Movie.class, id);
		try{session.delete(movie);
		tr.commit();
		return "Movie deleted";
		}
		catch (Exception e) {
			return "Movie of this id is not present, please enter again";
		}
	}

	public String mergeMovie(Movie movie) {
		Session session=sf.openSession();
		Transaction tr=session.beginTransaction();
		session.merge(movie);
		tr.commit();
		return "Movie updated successfully";
	}

	public List<Movie> getAllMovie() {
		Session session=sf.openSession();
		Transaction tr=session.beginTransaction();
		Criteria crt=session .createCriteria(Movie.class);
		List<Movie>movie=crt.list();
		tr.commit();
		return movie;
	}

	public List<Movie> getAnimationMovie() {
		Session session=sf.openSession();
		Criteria crt=session .createCriteria(Movie.class);
		crt.add(Restrictions.eq("category", "Animation"));
		List<Movie>movie=crt.list();
		return movie;
	}

	public List<Movie> getHorrorAndTrillerMovie() {
		Session session=sf.openSession();
		Transaction tr=session.beginTransaction();
		Criteria crt=session .createCriteria(Movie.class);
		crt.add(Restrictions.or(
		        Restrictions.eq("category", "Horror"),
		        Restrictions.eq("category", "thriller")
		    ));
		List<Movie>movie=crt.list();
		tr.commit();
		return movie;
	}

	public List<Movie> getRatingGT4() {
		Session session=sf.openSession();
		Criteria crt=session .createCriteria(Movie.class);
		List<Movie>movie=crt.list();
		return movie;
	}

	public List<Movie> getRatingLT4() {
		Session session=sf.openSession();
		Criteria crt=session .createCriteria(Movie.class);
		List<Movie>movie=crt.list();
		return movie;
	}

	public List<String> getMovieName() {
		Session session=sf.openSession();
		Transaction tr=session.beginTransaction();
		Criteria crt=session .createCriteria(Movie.class);
		crt.setProjection(Projections.property("title"));
		List<String>moviename=crt.list();
		
		List<String> al=new ArrayList<String>();
		for(String movienames :moviename)
		{
			al.add(movienames);
		}
		tr.commit();
		return al;
	}

	public List<Movie> getApolloTheaterName() {
		Session session=sf.openSession();
		Transaction tr=session.beginTransaction();
		Criteria crt=session .createCriteria(Movie.class);
		crt.add(Restrictions.eq("theater","Apollo"));
		List<Movie>movie=crt.list();
		tr.commit();
		return movie;
	}

	public List<Movie> getCinemaHallsinPuneTheaterName() {
		Session session=sf.openSession();
		Transaction tr=session.beginTransaction();
		Criteria crt=session .createCriteria(Movie.class);
		crt.add(Restrictions.eq("theater","CinemaHallsinPune"));
		List<Movie>movie=crt.list();
		tr.commit();
		return movie;
	}

	public List<Movie> getCityPrideTheaterName() {
		Session session=sf.openSession();
		Transaction tr=session.beginTransaction();
		Criteria crt=session .createCriteria(Movie.class);
		crt.add(Restrictions.eq("theater","CityPride"));
		List<Movie>movie=crt.list();
		tr.commit();
		return movie;
	}

	public List<Movie> getRahul70MMTheaterName() {
		Session session=sf.openSession();
		Criteria crt=session .createCriteria(Movie.class);
		crt.add(Restrictions.eq("theater","Rahul70MM"));
		List<Movie>movie=crt.list();
		return movie;
	}

	public List<Movie> getSatyamMinitheaterTheaterName() {
		Session session=sf.openSession();
		Transaction tr=session.beginTransaction();
		Criteria crt=session .createCriteria(Movie.class);
		crt.add(Restrictions.eq("theater","SatyamMinitheater"));
		List<Movie>movie=crt.list();
		tr.commit();
		return movie;
	}

	public List<Movie> getCityPridetheaterMovies() {
		Session session=sf.openSession();
		Transaction tr=session.beginTransaction();
		Criteria crt=session .createCriteria(Movie.class);
		crt.add(Restrictions.eq("theater", "City Pride"));
		List<Movie> movie=crt.list();
		
		List<Movie>al=new ArrayList<Movie>();
		for(Movie movies:movie)
		{
			al.add(movies);
		}
		tr.commit();
		return al;
	}

	public List<String> getCityPridetheaterMoviename() {
		Session session=sf.openSession();
		Transaction tr=session.beginTransaction();
		Criteria crt=session .createCriteria(Movie.class);
		crt.add(Restrictions.eq("theater", "City Pride"));
		
		crt.setProjection(Projections.property("title"));
		List<String> moviename=crt.list();
		List<String>al=new ArrayList<>();
		for(String movienames :moviename)
		{
			al.add(movienames);
		}
		tr.commit();
		return al;
	}

	public List<Movie> getCinemaHallsinPunetheaterMovies() {
		Session session=sf.openSession();
		Transaction tr=session.beginTransaction();
		Criteria crt=session .createCriteria(Movie.class);
		crt.add(Restrictions.eq("theater", "Cinema Halls in Pune"));
		List<Movie> movie=crt.list();
		
		List<Movie>al=new ArrayList<Movie>();
		for(Movie movies:movie)
		{
			al.add(movies);
		}
		tr.commit();
		return al;
	}

	public List<String> getCinemaHallsinPunetheaterMoviename() {
		Session session=sf.openSession();
		Transaction tr=session.beginTransaction();
		Criteria crt=session .createCriteria(Movie.class);
		crt.add(Restrictions.eq("theater", "Cinema Halls in Pune"));
		
		crt.setProjection(Projections.property("title"));
		List<String> moviename=crt.list();
		List<String>al=new ArrayList<>();
		for(String movienames :moviename)
		{
			al.add(movienames);
		}
		tr.commit();
		return al;
	}

	public List<Movie> getSatyamMinitheaterMovies() {
		Session session=sf.openSession();
		Transaction tr=session.beginTransaction();
		Criteria crt=session .createCriteria(Movie.class);
		crt.add(Restrictions.eq("theater", "Satyam Mini theater"));
		List<Movie> movie=crt.list();
		
		List<Movie>al=new ArrayList<Movie>();
		for(Movie movies:movie)
		{
			al.add(movies);
		}
		tr.commit();
		return al;
	}

	public List<String> getSatyamMinietheaterMoviename() {
		Session session=sf.openSession();
		Transaction tr=session.beginTransaction();
		Criteria crt=session .createCriteria(Movie.class);
		crt.add(Restrictions.eq("theater", "Satyam Mini theater"));
		
		crt.setProjection(Projections.property("title"));
		List<String> moviename=crt.list();
		List<String>al=new ArrayList<>();
		for(String movienames :moviename)
		{
			al.add(movienames);
		}
		tr.commit();
		return al;
	}

	public List<Movie> getRahul70MMtheaterMovies() {
		Session session=sf.openSession();
		Transaction tr=session.beginTransaction();
		Criteria crt=session .createCriteria(Movie.class);
		crt.add(Restrictions.eq("theater", "Rahul 70 MM"));
		List<Movie> movie=crt.list();
		
		List<Movie>al=new ArrayList<Movie>();
		for(Movie movies:movie)
		{
			al.add(movies);
		}
		tr.commit();
		return al;
	}

	public List<String> getRahul70MMtheaterMoviename() {
		Session session=sf.openSession();
		Transaction tr=session.beginTransaction();
		Criteria crt=session .createCriteria(Movie.class);
		crt.add(Restrictions.eq("theater", "Rahul 70 MM"));
		
		crt.setProjection(Projections.property("title"));
		List<String> moviename=crt.list();
		List<String>al=new ArrayList<>();
		for(String movienames :moviename)
		{
			al.add(movienames);
		}
		tr.commit();
		return al;
	}

	public List<Movie> getApollotheaterMovies() {
		Session session=sf.openSession();
		Transaction tr=session.beginTransaction();
		Criteria crt=session .createCriteria(Movie.class);
		crt.add(Restrictions.eq("theater", "Apollo"));
		List<Movie> movie=crt.list();
		
		List<Movie>al=new ArrayList<Movie>();
		for(Movie movies:movie)
		{
			al.add(movies);
		}
		tr.commit();
		return al;
	}

	public List<String> getApollotheaterMoviename() {
		Session session=sf.openSession();
		Transaction tr=session.beginTransaction();
		Criteria crt=session .createCriteria(Movie.class);
		crt.add(Restrictions.eq("theater", "Apollo"));
		
		crt.setProjection(Projections.property("title"));
		List<String> moviename=crt.list();
		List<String>al=new ArrayList<>();
		for(String movienames :moviename)
		{
			al.add(movienames);
		}
		tr.commit();
		return al;
	}

}
