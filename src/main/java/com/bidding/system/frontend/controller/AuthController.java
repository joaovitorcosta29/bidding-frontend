/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bidding.system.frontend.controller;

import com.bidding.system.frontend.model.UserDTO;
import com.bidding.system.frontend.model.UserRequestDTO;
import com.bidding.system.frontend.service.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Aluno
 */
@Controller // Controller retorna html e restController retorna texto. todos retorna string no @controller
public class AuthController {
    
    @Autowired 
    private AuthService authService;
    
    @GetMapping("/")
    public String home() {
        return "index";
    }
    
    @GetMapping("/login")
    public String login(Model model){
        UserRequestDTO credenciais = new UserRequestDTO();
        model.addAttribute("credenciais", credenciais);
        return "login";
    }
    
    @PostMapping("/logar")
    public String logar(@ModelAttribute UserRequestDTO credenciais, HttpSession session){
        String token = authService.logar(credenciais);
        System.out.println("token: "+token);
        session.setAttribute("token", token);
        return "redirect:/editais";
    }
    
    
    @GetMapping("/registrar")
    public String registrar(Model model){
        UserDTO newUser = new UserDTO();
        model.addAttribute("user", newUser);
        return "registrar";
    }
    
    @PostMapping("/registrar")
    public String mandarRegistro(@ModelAttribute UserDTO user){
        authService.registrar(user);
        return "redirect:/login";
    }
}
