package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {
    ProductManager manager = new ProductManager(new ProductRepository());
    private Book book1 = new Book(1, "Пляж", 500, "Гарленд");
    private Book book2 = new Book(2, "Некрономикон", 1000, "Лавкрафт");
    private Book book3 = new Book(3, "Тессеракт", 500, "Гарленд");
    private Smartphone phone1 = new Smartphone(1, "myPhone 1000", 1_000_000, "Mapple");
    private Smartphone phone2 = new Smartphone(2, "Universe", 50_000, "Sungsung");
    private Smartphone phone3 = new Smartphone(3, "Milky Way", 30_000, "Sungsung");

    @BeforeEach
    public void setUp() {
        manager.add(book1);
        manager.add(book2);
        manager.add(book3);
        manager.add(phone1);
        manager.add(phone2);
        manager.add(phone3);
    }

    @Test
    void shouldSearchBookByName() {
        Product[] actual = manager.searchBy("Некрономикон");
        Product[] expected = new Book[] {book2};

        assertArrayEquals(expected, actual);
    }

    @Test
    void  shouldSearchSmartphoneByName() {
        Product[] actual = manager.searchBy("myPhone 1000");
        Product[] expected = new Smartphone[] {phone1};

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchByAuthor() {
        Product[] actual = manager.searchBy("Гарленд");
        Product[] expected = new Book[] {book1, book3};

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchByManufacturer() {
        Product[] actual = manager.searchBy("Sungsung");
        Product[] expected = new Smartphone[] {phone2, phone3};

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldReturn0IfNoMatches() {
        Product[] actual = manager.searchBy("Oyvay");
        Product[] expected = new Product[0];

        assertArrayEquals(expected, actual);
    }
}