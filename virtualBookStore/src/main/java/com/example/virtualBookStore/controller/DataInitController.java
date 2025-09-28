package com.example.virtualBookStore.controller;

import com.example.virtualBookStore.model.Book;
import com.example.virtualBookStore.model.Category;
import com.example.virtualBookStore.repository.BookRepository;
import com.example.virtualBookStore.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/init")
@RequiredArgsConstructor
@Slf4j
public class DataInitController {
    private final CategoryRepository catRepo;
    private final BookRepository bookRepo;

    @PostMapping("/demo")
    public ResponseEntity<String> loadDemo() {
        log.info("Загрузка данных");
        if (catRepo.count() > 0) {
            return ResponseEntity.badRequest().body("Данные уже загружены");
        }

        Category fantasy = catRepo.save(new Category(null, "Fantasy", List.of()));
        Category classical = catRepo.save(new Category(null, "Classical", List.of()));
        Category sciFi = catRepo.save(new Category(null, "SciFiction", List.of()));
        Category mystery = catRepo.save(new Category(null, "Mystery", List.of()));
        Category romance = catRepo.save(new Category(null, "Romance", List.of()));

        // Фэнтези книги
        bookRepo.save(new Book(null, "The Hobbit", "J.R.R. Tolkien", "Bilbo Baggins...",
                BigDecimal.valueOf(12.99), 25, BigDecimal.ZERO, List.of(fantasy), List.of()));

        bookRepo.save(new Book(null, "Harry Potter and the Philosopher's Stone", "J.K. Rowling", "...",
                BigDecimal.valueOf(14.99), 30, BigDecimal.ZERO, List.of(fantasy), List.of()));

        bookRepo.save(new Book(null, "A Game of Thrones", "George R.R. Martin",
                "Noble families fight for control of the Iron Throne in the Seven Kingdoms of Westeros.",
                BigDecimal.valueOf(18.50), 18, BigDecimal.ZERO, List.of(fantasy), List.of()));

        // Научная фантастика
        bookRepo.save(new Book(null, "Dune", "Frank Herbert",
                "The story of Paul Atreides and his journey on the desert planet Arrakis.",
                BigDecimal.valueOf(15.75), 22, BigDecimal.ZERO, List.of(sciFi), List.of()));

        bookRepo.save(new Book(null, "1984", "George Orwell",
                "A dystopian social science fiction novel about totalitarian regime and thought control.",
                BigDecimal.valueOf(10.99), 35, BigDecimal.ZERO, List.of(sciFi, classical), List.of()));

        bookRepo.save(new Book(null, "Foundation", "Isaac Asimov",
                "A epic series that tells the story of the Foundation, a group of scientists preserving knowledge.",
                BigDecimal.valueOf(13.25), 20, BigDecimal.ZERO, List.of(sciFi), List.of()));

// Классическая литература
        bookRepo.save(new Book(null, "Pride and Prejudice", "Jane Austen",
                "The romantic story of Elizabeth Bennet and Mr. Darcy in 19th century England.",
                BigDecimal.valueOf(9.99), 40, BigDecimal.ZERO, List.of(classical, romance), List.of()));

        bookRepo.save(new Book(null, "To Kill a Mockingbird", "Harper Lee",
                "A powerful story of racial injustice and childhood innocence in the American South.",
                BigDecimal.valueOf(11.49), 28, BigDecimal.ZERO, List.of(classical), List.of()));

        bookRepo.save(new Book(null, "The Great Gatsby", "F. Scott Fitzgerald",
                "A tragic story of Jay Gatsby's pursuit of the American Dream during the Roaring Twenties.",
                BigDecimal.valueOf(8.99), 33, BigDecimal.ZERO, List.of(classical), List.of()));

// Мистика и детективы
        bookRepo.save(new Book(null, "The Da Vinci Code", "Dan Brown",
                "A murder mystery that uncovers a centuries-old conspiracy within the Catholic Church.",
                BigDecimal.valueOf(12.99), 26, BigDecimal.ZERO, List.of(mystery), List.of()));

        bookRepo.save(new Book(null, "Gone Girl", "Gillian Flynn",
                "A psychological thriller about the disappearance of Amy Dunne on her fifth wedding anniversary.",
                BigDecimal.valueOf(14.25), 19, BigDecimal.ZERO, List.of(mystery), List.of()));

// Романтика
        bookRepo.save(new Book(null, "The Notebook", "Nicholas Sparks",
                "A heartwarming love story about Noah and Allie that spans decades.",
                BigDecimal.valueOf(10.50), 31, BigDecimal.ZERO, List.of(romance), List.of()));

        // Книги в нескольких категориях
        bookRepo.save(new Book(null, "The Time Traveler's Wife", "Audrey Niffenegger",
                "A love story between a librarian and a man with a genetic disorder that causes him to time travel.",
                BigDecimal.valueOf(13.99), 23, BigDecimal.ZERO, List.of(romance, sciFi), List.of()));

        bookRepo.save(new Book(null, "The Lord of the Rings: The Fellowship of the Ring", "J.R.R. Tolkien",
                "The first volume of the epic trilogy about the quest to destroy the One Ring.",
                BigDecimal.valueOf(16.99), 17, BigDecimal.ZERO, List.of(fantasy, classical), List.of()));

        bookRepo.save(new Book(null, "Brave New World", "Aldous Huxley",
                "A dystopian novel set in a futuristic World State, centered around advanced technology and control.",
                BigDecimal.valueOf(11.75), 21, BigDecimal.ZERO, List.of(sciFi, classical), List.of()));

        log.info("Данные были загружены");
        return ResponseEntity.ok("Demo data loaded with 15 books!");
    }
}
