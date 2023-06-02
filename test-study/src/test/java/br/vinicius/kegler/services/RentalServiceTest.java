package br.vinicius.kegler.services;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

import br.vinicius.kegler.entities.Movie;
import br.vinicius.kegler.entities.Rental;
import br.vinicius.kegler.entities.User;
import br.vinicius.kegler.exceptions.RentalException;
import br.vinicius.kegler.utils.DateUtils;

public class RentalServiceTest {
	
	@Rule
	public ErrorCollector errorCollector = new ErrorCollector();


	/*	
	@Test
	public void rentalTest() throws Exception {
		RentalService ls = new RentalService();
		
		Integer stockQuantity = (int) Math.round((Math.random() * 15));
		Double moviePrice = Math.random() * 26;
		
		
		User user = new User("John");
		Movie movie = new Movie("Test of tests 1", stockQuantity, moviePrice);
		Rental rent = ls.rentMovie(user, movie);
		
		errorCollector.checkThat(movie.getRentalPrice(), is(equalTo(moviePrice)));
		errorCollector.checkThat(DateUtils.isSameDate(rent.getRentDate(), new Date()), is(true));
		errorCollector.checkThat(DateUtils.isSameDate(rent.getReturnDate(), DateUtils.getDateWithDaysDifference(1)), is(true));
	}
 	*/
	
	@Test
	public void rentalTestOutofStock() {
		RentalService ls = new RentalService();
		User user = new User("Johnson");
		Movie movie = new Movie("Test of tests 2", 0, Math.random() * 26);
		try {
			ls.rentMovie(user, movie);
			errorCollector.addError(new AssertionError("Should've failed due to no stock."));
		}catch (RentalException e) {
			errorCollector.checkThat(e.getMessage(), is(movie.getName() + " is out of stock."));
		}
	}
	
	@Test
	public void rentalTestEmptyName() {
		RentalService ls = new RentalService();
		User user = new User("Test fail");
		Movie movie = new Movie("Test of tests 2", 10, Math.random() * 26);
		try {
			ls.rentMovie(user, movie);
			errorCollector.addError(new AssertionError("Should've failed due to empty name."));
		}catch (RentalException e) {
			errorCollector.checkThat(e.getMessage(), is("User name is empty"));
		}
	}
	
	@Test
	public void rentalTestEmptyUser() {
		RentalService ls = new RentalService();
		User user = null;
		Movie movie = new Movie("Test of tests 2", 10, Math.random() * 26);
		try {
			ls.rentMovie(user, movie);
			errorCollector.addError(new AssertionError("Should've failed due to empty user."));
		}catch (RentalException e) {
			errorCollector.checkThat(e.getMessage(), is("No user selected!"));
		}
	}
	
	@Test
	public void rentalTestEmptyMovie() {
		RentalService ls = new RentalService();
		User user = new User("Johnson");
		Movie movie = null;
		try {
			ls.rentMovie(user, movie);
			errorCollector.addError(new AssertionError("Should've failed due to empty movie."));
		}catch (RentalException e) {
			errorCollector.checkThat(e.getMessage(), is("No movie selected!"));
		}
	}
}
