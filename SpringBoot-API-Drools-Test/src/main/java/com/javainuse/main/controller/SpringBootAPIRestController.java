package com.javainuse.main.controller;

import com.javainuse.main.SpringBootUtility;
import com.javainuse.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pullapi")
class SpringBootAPIRestController {

    private SpringBootUtility util;

    @Autowired
    public SpringBootAPIRestController(SpringBootUtility util) {
        this.util = util;
    }

    @RequestMapping("/getValue")
    public Product getDiscount(@RequestBody Product partialProduct) {
        return util.execute(partialProduct);
    }

}
