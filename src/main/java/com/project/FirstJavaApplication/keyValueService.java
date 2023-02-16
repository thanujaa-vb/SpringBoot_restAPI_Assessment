package com.project.FirstJavaApplication;
import java.util.List;

public interface keyValueService {
    keyvalue createProduct(keyvalue kv);

    keyvalue updateProduct(keyvalue kv);

    List<keyvalue> getAllProduct();

    keyvalue getProductById(String productId);

    void deleteProduct(String id);


}
