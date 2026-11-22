
import com.uptc.exceptions.BookAlReadyExistsException;
import com.uptc.exceptions.BookNotFoundException;
import com.uptc.logic.Catalog;
import com.uptc.models.Book;
import com.uptc.models.BookStatus;
import com.uptc.models.Genre;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author admin
 */
public class CatalogTest {
    private Catalog catalog;
    
    @BeforeEach
    public void setUp(){
        catalog = new Catalog();
       Book  book1= new Book("123", "El Principito", "Antoine de Saint-Exupéry", Genre.FICTION, BookStatus.AVAILABLE);
       Book  book2= new Book("456", "El origen de las especies", "Charles Darwin", Genre.SCIENCE, BookStatus.AVAILABLE);
       Book  book3= new Book("767", "El otoño de la Edad Media", "Johan Huizinga", Genre.HISTORY, BookStatus.AVAILABLE);
       catalog.addBook(book1);
       catalog.addBook(book2);
       catalog.addBook(book3);
    }
    
    @Test
    public void testAddBook(){
        Book book= new Book("987", "Cien años de soledad", "Gabriel Garcia MMarquez", Genre.FICTION, BookStatus.AVAILABLE);
        catalog.addBook(book);
        assertEquals(3, catalog.listAll().size());
    }
    
    @Test
    public void testDuplicatedIsbn(){
        Book book= new Book("767", "El otoño de la Edad Media", "Johan Huizinga", Genre.HISTORY, BookStatus.AVAILABLE);
        assertThrows(BookAlReadyExistsException.class,()->catalog.addBook(book));
        
    }
    
    @Test
    public void testFindByIsbn(){
        Book book = catalog.findByIsbn("123");
        assertEquals("El Principito", book.getTitle());
    }
    
    @Test 
    public void testFindByTitle(){
        List <Book> result = catalog.findByTitle("El origen de las especies");
        assertEquals(1, result.size());
        
    }
    
    @Test
    public void testFilterByGenre(){
        List <Book> result= catalog.findByGenre(Genre.FICTION);
        assertEquals(1, result.size());
        
    }
    
    @Test
    public void testChangeStatus(){
        catalog.changeStatus("123", BookStatus.BORROWED);
        assertEquals(BookStatus.BORROWED, catalog.findByIsbn("123").getStatus());
    }
    
    @Test
    public void testDeleteBook(){
        catalog.deleteBook("767");
        assertEquals(2, catalog.listAll().size());
        assertThrows( BookNotFoundException.class, () -> catalog.findByIsbn("767"));
    }
    
    @Test
    public void testListAll(){
        assertEquals(3, catalog.listAll().size());
    }
  
}
