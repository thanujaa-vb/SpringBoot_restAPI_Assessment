package com.project.FirstJavaApplication;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;

@Service
// @Transactional
public class keyValueServiceImplementation implements keyValueService {
    public keyvalue createProduct(keyvalue kv) {
        return kv;
    }

    public keyvalue updateProduct(keyvalue kv) {
        return kv;
    }

    public List<keyvalue> getAllProduct() {
        List<keyvalue> kvList = new ArrayList<keyvalue>();
        kvList.add(0, new keyvalue("0", "kv1", "value 1"));
        kvList.add(1, new keyvalue("1", "kv2", "value 2"));
        return kvList;
    }

    public keyvalue getProductById(String productId) {
        return null;
    }

    public void deleteProduct(String id) {

    }
}
