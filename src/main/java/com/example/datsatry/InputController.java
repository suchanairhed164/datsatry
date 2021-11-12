package com.example.datsatry;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class InputController {

    @GetMapping("/in")
    public String inputForm(Model model) {
        model.addAttribute("in", new Input());
        return "in";
    }

    //static String poluchenniy_massiv;

    @PostMapping("/in")
    public String inputSubmit(@ModelAttribute Input in, Model model) {
        model.addAttribute("in", in);

        //System.out.println(Input.text2+"!!!ETO IZ CONTROLLERA-POST");
        //poluchenniy_massiv = Input.text2;
        //System.out.println(poluchenniy_massiv+"   - !!!THAT'S FROM InputController.java");

        return "result";
    }

}
