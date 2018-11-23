package com.gabriel.multipledatabaseconnection;

import com.gabriel.multipledatabaseconnection.model.persistent.Book;
import com.gabriel.multipledatabaseconnection.repository.persistent.BookRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.testng.Assert;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MultipleDatabaseConnectionApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	private BookRepository bookInterfaces;

	@Autowired
	private EntityManager entityManager;

	@Test
	public void testShouldCreateBookByNamingStoredProcedure() {

		String bookName = "Design Patterns: Elements of Reusable Object-Oriented Software";
		String authorFirstname = "Gang";
		String authorLastname = "of four";

		StoredProcedureQuery addBookNamedStoredProcedure = entityManager.createNamedStoredProcedureQuery("addBook_sp");
		addBookNamedStoredProcedure.setParameter("bookName", bookName);
		addBookNamedStoredProcedure.setParameter("bookReleaseDate", new Date(), TemporalType.DATE);
		addBookNamedStoredProcedure.setParameter("authorFirstname", authorFirstname);
		addBookNamedStoredProcedure.setParameter("authorLastname", authorLastname);

		Integer createdBookId = (Integer) addBookNamedStoredProcedure.getSingleResult();
		Assert.assertNotNull(createdBookId);

		List<Book> books = bookInterfaces.findAll();
		Assert.assertEquals(books.size(), 1, "Books size is not one");
		Assert.assertEquals(bookName, books.get(0).getName());
	}

}
