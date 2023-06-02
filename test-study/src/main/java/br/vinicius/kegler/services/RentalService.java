package br.vinicius.kegler.services;

import static br.vinicius.kegler.utils.DateUtils.increaseDays;

import java.util.Date;

import br.vinicius.kegler.entities.Movie;
import br.vinicius.kegler.entities.Rental;
import br.vinicius.kegler.entities.User;
import br.vinicius.kegler.exceptions.RentalException;

public class RentalService {
	
	
	
	public Rental rentMovie(User user, Movie movie) throws RentalException {
		Rental rental = new Rental();
		if(movie == null) {
			throw  new RentalException("No movie selected!");
		}

		if(movie.getStock() == 0) {
			throw new RentalException(movie.getName() + " is out of stock.");
		}
		
		if(user == null) {
			throw new RentalException("No user selected!");
		}
		
		if(user.getName() == null || user.getName().isEmpty()) {
			throw new RentalException("User name is empty");
		}
		
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
}