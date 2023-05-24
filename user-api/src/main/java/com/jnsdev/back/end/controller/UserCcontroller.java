package com.jnsdev.back.end.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jnsdev.back.end.dto.UserDTO;
import com.jnsdev.back.end.service.UserService;

@RestController
public class UserCcontroller {

      @Autowired
      private UserService userService;

      @GetMapping("/user")
      public List<UserDTO> gerUsers() {
            return userService.getAll();
      }

      @GetMapping("/user/{id}")
      public UserDTO gerUsersFiltro(@PathVariable Long id) {
            return userService.findById(id);
      }

      @PostMapping("/user")
      UserDTO newUser(@RequestBody UserDTO userDTO) {
            userDTO.setDataCadastro(new Date());
            return userService.save(userDTO);
      }

      @GetMapping("/user/cpf/{cpf}")
      public UserDTO findByCpf(@PathVariable String cpf) {
            return userService.findByCpf(cpf);
      }


      @DeleteMapping("/user/{id}")
      public UserDTO remover(@PathVariable Long id) {
            return userService.delete(id);
      }

      @GetMapping("/user/search")
      public List<UserDTO> queryByName(@RequestParam(name="nome", required = true) String nome) {
            return userService.queryByName(nome);
      }

}
