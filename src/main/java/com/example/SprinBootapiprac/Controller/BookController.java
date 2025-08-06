package com.example.SprinBootapiprac.Controller;


import com.example.SprinBootapiprac.entity.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/books")

public class BookController {


    private Map<Long, Book> bookDB = new HashMap<>();
    private  long c=1;

    @GetMapping
    public ResponseEntity<List<Book>> getAllbooks(){
        return  ResponseEntity.ok(new ArrayList<>(bookDB.values()));
    }
    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book){
bookDB.put(book.getId(),book);
return  ResponseEntity.status(HttpStatus.CREATED).body(book);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getbookbyId(@PathVariable Long id){
        Book book = bookDB.get(id);
        if(book==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return  ResponseEntity.ok(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateBook(@PathVariable Long id, @RequestBody Book book) {
        Book existing=bookDB.get(id);
        if(existing==null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        bookDB.put(id, book);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // 200 OK
    }

    @PatchMapping ("/{id}/price")
    public ResponseEntity<Book> updatePrice(@PathVariable Long id, @RequestBody Double newprice) {
        Book existing=bookDB.get(id);
        if(existing==null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        existing.setPrice(newprice);
        bookDB.put(id,existing);
        return ResponseEntity.ok(existing); // 200 OK
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable Long id) {
        Book existing=bookDB.remove(id);
        if(existing==null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.noContent().build();
    }
}
