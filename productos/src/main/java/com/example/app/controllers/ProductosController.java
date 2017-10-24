/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.app.controllers;

/**
 *
 * @author Gonzalo
 */
import com.example.app.configuration.DatabaseLoader;
import com.example.app.configuration.IProductoService;
import com.example.app.entities.Producto;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller("/")
public class ProductosController {

        @Autowired
    private IProductoService productoService;
    
    @RequestMapping("/")
    public ModelAndView index() {
        return new ModelAndView("home");
    }


    @RequestMapping("/home")
    public ModelAndView home() {
        ModelAndView model = new ModelAndView("home");
        return model;
    }


    @GetMapping("/insertar")
    public ModelAndView edit(Producto producto) {
        return new ModelAndView("insertar");
    }

    @PostMapping("/insertar")
    public String insertar(@Valid Producto producto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println("Error");
        }
        productoService.save(producto);
        return "redirect:/home";
    }

    @RequestMapping("/delete")
    public ModelAndView delete(@RequestParam int codigo) {
        productoService.delete(codigo);
        return new ModelAndView("home");
    }

    @RequestMapping("/listado")
    public ModelAndView listado() {
        return new ModelAndView("listado").addObject("productService", productoService.findAll());
    }

    @RequestMapping("/productoIndividual")
    public ModelAndView productoIndividual(@RequestParam int codigo) {
        return new ModelAndView("productoIndividual").addObject("producto", productoService.getProducto(codigo));
    }

    @GetMapping("/editar")
    public ModelAndView getProductoCodigo(@RequestParam int codigo) {
        return new ModelAndView("editar").addObject("producto", productoService.getProducto(codigo));
    }

    @PostMapping("/editar")
    public String editar1(@Valid Producto producto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println("Error");
        }
        productoService.edit(producto);
        return "redirect:/home";
    }

}
