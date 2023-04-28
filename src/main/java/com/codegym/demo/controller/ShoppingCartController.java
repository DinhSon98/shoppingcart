package com.codegym.demo.controller;

import com.codegym.demo.dto.ProductDto;
import com.codegym.demo.dto.ShoppingCartDto;
import com.codegym.demo.service.ProductService;
import com.codegym.demo.service.ShoppingCartService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;
    private final ProductService productService;

    public ShoppingCartController(ShoppingCartService shoppingCartService, ProductService productService) {
        this.shoppingCartService = shoppingCartService;
        this.productService = productService;
    }

    @GetMapping("/list")
    public ModelAndView listProducts() {
        ModelAndView modelAndView = new ModelAndView("/list");
        Iterable<ProductDto> productDtos = productService.findAll();
        modelAndView.addObject("productDtos", productDtos);
        return modelAndView;
    }
    @GetMapping("/list_card")
    public ModelAndView listCards() {
        ModelAndView modelAndView = new ModelAndView("/list_card");
        Iterable<ShoppingCartDto> shoppingCartDtos = shoppingCartService.findAll();
        modelAndView.addObject("shoppingCartDtos", shoppingCartDtos);
        return modelAndView;
    }

    @GetMapping("/addCard/{id}")
    public ModelAndView showCardForm(@PathVariable Integer id) {
        Optional<ProductDto> productDto = productService.findById(id);
        ModelAndView modelAndView = new ModelAndView("addCard");
            modelAndView.addObject("shoppingCartDto", new ShoppingCartDto());
            modelAndView.addObject("productDto", productDto.get());
        return modelAndView;
    }

    @PostMapping("/addCard")
    public ModelAndView addCart(@ModelAttribute("shoppingCartDto") ShoppingCartDto shoppingCartDto) {
        shoppingCartDto.setIs_Deleted(false);
        shoppingCartService.save(shoppingCartDto);
        ModelAndView modelAndView = new ModelAndView("redirect:list");
        modelAndView.addObject("shoppingCartDto", shoppingCartDto);
        modelAndView.addObject("message", "Add Cart successfully!! Continue buy product !!");
        return modelAndView;
    }
    @GetMapping("/editCard/{id}")
    public ModelAndView showEditCardForm(@PathVariable Integer id) {
        Optional<ShoppingCartDto> shoppingCartDto = shoppingCartService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/editCard");
        modelAndView.addObject("shoppingCartDto", shoppingCartDto);
        return modelAndView;
    }
    @PostMapping("/edit")
    public ModelAndView updateCard(@ModelAttribute("shoppingCartDto") ShoppingCartDto shoppingCartDto) {
        shoppingCartService.save(shoppingCartDto);
        ModelAndView modelAndView = new ModelAndView("/editCard");
        modelAndView.addObject("shoppingCartDto", shoppingCartDto);
        modelAndView.addObject("message", "Product updated successfully");
        return modelAndView;
    }
    @GetMapping("/delete/{id}")
    public ModelAndView showDeleteForm(@PathVariable Integer id) {
        Optional<ShoppingCartDto> shoppingCartDto = shoppingCartService.findById(id);
        if (shoppingCartDto.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/delete");
            modelAndView.addObject("shoppingCartDto", shoppingCartDto.get());
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error-404");
            return modelAndView;
        }
    }
    @PostMapping("/delete")
    public String deleteCard(@ModelAttribute("shoppingCartDto") ShoppingCartDto shoppingCartDto) {
        shoppingCartService.remove(shoppingCartDto.getId());
        return "redirect:list_card";
    }

    @PostMapping("/total")
    public ModelAndView totalAllCard(){
        Float total = 0.0f;
        Iterable<ShoppingCartDto> cartDtos = shoppingCartService.findAll();
        for (ShoppingCartDto cartDto: cartDtos
             ) {
            total += (cartDto.getPrice()*cartDto.getQuantity());
        }
        ModelAndView view = new ModelAndView("list_card");
        view.addObject("shoppingCartDtos", cartDtos);
        view.addObject("total", total);
        return view;
    }
}
