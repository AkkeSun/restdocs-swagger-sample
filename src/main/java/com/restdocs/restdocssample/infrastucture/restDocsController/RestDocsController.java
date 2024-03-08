package com.restdocs.restdocssample.infrastucture.restDocsController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RestDocsController {

    @GetMapping
    public String index () {
        return "index";
    }
}
