package br.vinicius.kegler.services;

import static br.vinicius.kegler.utils.DateUtils.increaseDays;

import java.util.Date;

import br.vinicius.kegler.entities.Movie;
import br.vinicius.kegler.entities.Rental;
import br.vinicius.kegler.entities.User;
import br.vinicius.kegler.utils.DateUtils;

public class RentalService {
	
	public Rental rentMovie(User user, Movie movie) {
		Rental rental = new Rental();
		rental.setMovie(movie);
		rental.setUser(user);
		rental.setRentDate(new Date());
		rental.setRentPrice(movie.getRentalPrice());

		//Due on the next day
		Date dueDate = new Date();
		dueDate = increaseDays(dueDate, 1);
		rental.setReturnDate(dueDate);
		
		//Saving the rental...	
		//TODO 
		
		return rental;
	}

	public static void main(String[] args) {
		RentalService ls = new RentalService();
		User user = new User("Teste");
		Movie movie = new Movie("Teste", 10, 25.0);
		
		Rental rent = ls.rentMovie(user, movie);
		
		System.out.println(rent.getRentPrice() == 25 ? "Valid rent price." : "Invalid rent price.");
		
		System.out.println(DateUtils.isSameDate(rent.getRentDate(), new Date()) ? "Valid rent date." : "Invalid rent date.");
		
		System.out.println(DateUtils.isSameDate(rent.getReturnDate(), DateUtils.getDateWithDaysDifference(1)) ? "Valid return date." : "Invalid return date.");
	
	}
}