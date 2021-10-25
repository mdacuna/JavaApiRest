package apirest.demo.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import apirest.demo.dao.BooksDAO;
import apirest.demo.entity.books;

@RestController
@RequestMapping("/books")
public class booksRest {

    @Autowired
    private BooksDAO booksDAO;

    @GetMapping()
    public ResponseEntity<List<books>> getBook(){
        List<books> book = booksDAO.findAll();
        //books book = new books();
        //book.setId("1");
        //book.setTitle("Ready Player One");
        //book.setDescription("Interesante");
        //book.setAuthor("Persona");
        return ResponseEntity.ok(book);
        //return null;
    }

    @RequestMapping(value = "{bookId}")
    public ResponseEntity<books> getBookById(@PathVariable("bookId") Long bookId){
        Optional<books> optionalBook = booksDAO.findById(bookId);
        if(optionalBook.isPresent()){
            return ResponseEntity.ok(optionalBook.get());
        }else{ 
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping()
    public ResponseEntity<books> createBook (@RequestBody books book){
        books newBook = booksDAO.save(book);
        return ResponseEntity.ok(newBook);
    }

    @DeleteMapping(value = "{bookId}")
    public ResponseEntity<Void> deleteBook (@PathVariable("bookId") Long bookId){
        booksDAO.deleteById(bookId);
        return ResponseEntity.ok(null);
    }

    @PutMapping()
    public ResponseEntity<books> updateBook(@RequestBody books book){
        Optional<books> optionalBook = booksDAO.findById(book.getId());
        if(optionalBook.isPresent()){
            //books updateBook = optionalBook.get();
            books updateBook = booksDAO.save(book);
            return ResponseEntity.ok(updateBook);
        }else{ 
            return ResponseEntity.noContent().build();
        }
    }

    //@GetMapping  //localhost:8080/
    //@RequestMapping(value = "hello", method = RequestMethod.GET)
    public String hello(){
        return "Hello world";
    }

}
