package com.project.FirstJavaApplication;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class keyValueController {
    @Autowired
    private keyValueService productService;

    @RequestMapping(value = "/keyvalue", method = RequestMethod.GET)
    public List<keyvalue> getAllProduct() {
        return productService.getAllProduct();
    }
}
