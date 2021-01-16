package ru.netology.manager;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartfone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {

    private ProductRepository repository = new ProductRepository();
    ProductManager manager = new ProductManager(repository);

    Book book1 = new Book(1, "name1", 1, "a1");
    Book book2 = new Book(2, "name2", 2, "a2");
    Book book3 = new Book(3, "name3", 3, "a2");
    Smartfone sm1 = new Smartfone(5, "name5", 5, "m1");
    Smartfone sm2 = new Smartfone(6, "name3", 5, "m2");
    Smartfone sm3 = new Smartfone(7, "name7", 5, "a2");

    @Test
    void shouldFindAuthor() {
        manager.add(book1);
        manager.add(book2);
        manager.add(book3);
        manager.add(sm1);
        manager.add(sm2);
        manager.add(sm3);

        Product[] actual = manager.searchBy("a2");
        Product[] expected = new Product[]{book2, book3, sm3};
        assertArrayEquals(expected, actual);

    }

    @Test
    void shouldFindName() {
        manager.add(book1);
        manager.add(book2);
        manager.add(book3);
        manager.add(sm1);
        manager.add(sm2);
        manager.add(sm3);

        Product[] actual = manager.searchBy("name3");
        Product[] expected = new Product[]{book3, sm2};
        assertArrayEquals(expected, actual);

    }

    @Test
    void shouldNotFind() {
        manager.add(book1);
        manager.add(book2);
        manager.add(book3);
        manager.add(sm1);
        manager.add(sm2);
        manager.add(sm3);

        Product[] actual = manager.searchBy("name");
        Product[] expected = new Product[0];
        assertArrayEquals(expected, actual);

    }

    @Test
    void shouldEmptyArray() {

        Product[] actual = manager.searchBy("name");
        Product[] expected = new Product[0];
        assertArrayEquals(expected, actual);

    }

    @Test
    void shouldFullArray() { // выбор всех записей

        manager.add(book3);
        manager.add(sm2);

        Product[] actual = manager.searchBy("name3");
        Product[] expected = new Product[]{book3, sm2};
        assertArrayEquals(expected, actual);

    }



}