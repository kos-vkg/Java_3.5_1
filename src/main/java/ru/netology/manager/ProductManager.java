package ru.netology.manager;

import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartfone;
import ru.netology.repository.ProductRepository;

public class ProductManager {

    private ProductRepository repository;

    public ProductManager(ProductRepository repository) {
        this.repository = repository;
    }

    public void add(Product item) {
        repository.save(item);
    }

    public boolean matches(Product product, String search) {
        if (product instanceof Book) {
            Book book = (Book) product;
            if (book.getName().equalsIgnoreCase(search)) {
                return true;
            }
            if (book.getAuthor().equalsIgnoreCase(search)) {
                return true;
            }
            return false;
        }
        if (product instanceof Smartfone) {
            Smartfone smartfone = (Smartfone) product;
            if (smartfone.getName().equalsIgnoreCase(search)) {
                return true;
            }
            if (smartfone.getManufacturer().equalsIgnoreCase(search)) {
                return true;
            }
            return false;
        }
        return false;
    }

    public Product[] searchBy(String text) {
        Product[] items = repository.findAll();
        Product[] result = new Product[0];
        for (Product item : items) {
            if (matches(item, text)) {
                // создаём новый массив размером на единицу больше
                Product[] tmp = new Product[result.length + 1];
                System.arraycopy(result, 0, tmp, 0, result.length);
                tmp[tmp.length - 1] = item; // кладём последним  элемент
                result = tmp;
            }
        }
        return result;
    }

//    public Product[] getAll() {
//        Product[] items = repository.findAll();
//        Product[] result = new Product[items.length];
//        for (int i = 0; i < result.length; i++) {
//            int index = items.length - i - 1;
//            result[i] = items[index];
//        }
//        return result;
//    }
//
//    public void removeById(int id) {
//        repository.removeById(id);
//    }
//
//    public Product findById(int id) {
//        return repository.findById(id);
//    }

}
