package br.vinicius.kegler.services;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;


import static org.hamcrest.CoreMatchers.is;

import br.vinicius.kegler.entities.Movie;
import br.vinicius.kegler.entities.User;
import br.vinicius.kegler.exceptions.RentalException;

public class RentalServiceTest {
	
	private RentalService rentalService;
	
	@Rule
	public ErrorCollector errorCollector = new ErrorCollector();

	@Before
	public void setup() {
		rentalService = new RentalService();
		System.out.println("Before");
	}

	@After
	public void tearDown() {
		System.out.println("After");
	}

	@BeforeClass
	public static void setupClass() {
		System.out.println("Before Class");
	}

	@AfterClass
	public static void tearDownClass() {
		System.out.println("After Class");
	}
	
	@Test
	public void rentalTestOutofStock() {
		User user = new User("Johnson");
		Movie movie = new Movie("Test of tests 2", 0, Math.random() * 26);
		try {
			rentalService.rentMovie(user, movie);
			errorCollector.addError(new AssertionError("Should've failed due to no stock."));
		}catch (RentalException e) {
			errorCollector.checkThat(e.getMessage(), is(movie.getName() + " is out of stock."));
		}
	}
	
	@Test
	public void rentalTestEmptyName() {
		User user = new User("Test fail");
		Movie movie = new Movie("Test of tests 2", 10, Math.random() * 26);
		try {
			rentalService.rentMovie(user, movie);
			errorCollector.addError(new AssertionError("Should've failed due to empty name."));
		}catch (RentalException e) {
			errorCollector.checkThat(e.getMessage(), is("User name is empty"));
		}
	}
	
	@Test
	public void rentalTestEmptyUser() {
		User user = null;
		Movie movie = new Movie("Test of tests 2", 10, Math.random() * 26);
		try {
			rentalService.rentMovie(user, movie);
			errorCollector.addError(new AssertionError("Should've failed due to empty user."));
		}catch (RentalException e) {
			errorCollector.checkThat(e.getMessage(), is("No user selected!"));
		}
	}
	
	@Test
	public void rentalTestEmptyMovie() {
		User user = new User("Johnson");
		Movie movie = null;
		try {
			rentalService.rentMovie(user, movie);
			errorCollector.addError(new AssertionError("Should've failed due to empty movie."));
		}catch (RentalException e) {
			errorCollector.checkThat(e.getMessage(), is("No movie selected!"));
		}
	}
}
