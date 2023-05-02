package com.example.ecommercebackend.Entities.Product;

import com.example.ecommercebackend.Entities.SearchFilter.ProductSearchFilter;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<Product>();
        productRepository.findAll().forEach(product -> products.add(product));
        return products;
    }

    public List<Product> getProductsByCategory(int category_id) {
        List<Product> products = new ArrayList<>();
        products = productRepository.getProductsByCategory(category_id);
        return products;
    }

    @Transactional
    public Product addProduct(Product product) {
        if (productRepository.existsById(product.getId())) {
            return null;
        }
        product = productRepository.saveAndFlush(product);
        return product;
    }

    public Product updateProduct(Product product) {
        if (productRepository.existsById(product.getId())) {
            return productRepository.saveAndFlush(product);
        }
        return null;
    }

    @Transactional
    public boolean deleteProduct(List<Integer> ids) {
        try {
            productRepository.deleteAllIds(ids);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private List<Integer> inCaseOfEmptyList = new ArrayList<>();

    public ProductService() {
        super();
        for (int i = 0; i < 100; i++) {
            inCaseOfEmptyList.add(i);
        }
    }

    public List<Product> searchProduct(ProductSearchFilter filter) {

        if (filter.getBrand_ids() == null || filter.getBrand_ids().isEmpty()) {
            filter.setBrand_ids(inCaseOfEmptyList);
        }
        if (filter.getCategory_ids() == null || filter.getCategory_ids().isEmpty()) {
            filter.setCategory_ids(inCaseOfEmptyList);
        }
        Pageable pageable;

        if (filter.getSortField() == null) {
            pageable = PageRequest.of(filter.getPageNum(), filter.getPerPage());
        } else {
            if (filter.getSortField().isEmpty()) {
                pageable = PageRequest.of(filter.getPageNum(), filter.getPerPage());
            } else {
                if (filter.getSortType() != null || !filter.getSortType().isEmpty()) {
                    if (filter.getSortType().equals("asc")) {
                        pageable = PageRequest.of(filter.getPageNum(), filter.getPerPage(), Sort.by(filter.getSortField()).ascending());
                    } else if (filter.getSortType().equals("des")) {
                        pageable = PageRequest.of(filter.getPageNum(), filter.getPerPage(), Sort.by(filter.getSortField()).descending());
                    } else {
                        pageable = PageRequest.of(filter.getPageNum(), filter.getPerPage());
                    }
                } else {
                    pageable = PageRequest.of(filter.getPageNum(), filter.getPerPage());
                }

            }
        }
        List<Product> products = productRepository.findAllByName(filter, pageable);

        return products;
    }

    public int GetTotalPageFromSearchFilter(ProductSearchFilter filter) {
        if (filter.getBrand_ids() == null || filter.getBrand_ids().isEmpty()) {
            filter.setBrand_ids(inCaseOfEmptyList);
        }
        if (filter.getCategory_ids() == null || filter.getCategory_ids().isEmpty()) {
            filter.setCategory_ids(inCaseOfEmptyList);
        }
        Pageable pageable;

        if (filter.getSortField() == null) {
            pageable = PageRequest.of(filter.getPageNum(), filter.getPerPage());
        } else {
            if (filter.getSortField().isEmpty()) {
                pageable = PageRequest.of(filter.getPageNum(), filter.getPerPage());
            } else {
                if (filter.getSortType() != null || !filter.getSortType().isEmpty()) {
                    if (filter.getSortType().equals("asc")) {
                        pageable = PageRequest.of(filter.getPageNum(), filter.getPerPage(), Sort.by(filter.getSortField()).ascending());
                    } else if (filter.getSortType().equals("des")) {
                        pageable = PageRequest.of(filter.getPageNum(), filter.getPerPage(), Sort.by(filter.getSortField()).descending());
                    } else {
                        pageable = PageRequest.of(filter.getPageNum(), filter.getPerPage());
                    }
                } else {
                    pageable = PageRequest.of(filter.getPageNum(), filter.getPerPage());
                }

            }
        }
        pageable = PageRequest.of(0, 1000);
        int pageCount = (int) Math.ceil(productRepository.findAllByName(filter, pageable).size() / filter.getPerPage());
        return pageCount;
    }
}
