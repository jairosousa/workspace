package com.jnsdev.back.end.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jnsdev.back.end.dto.UserDTO;

@Entity
@Table
public class User {

      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long id;

      private String nome;

      private String cpf;

      private String endereco;

      private String email;

      private String telefone;

      private Date dataCadastro;

      public User() {
      }

      public Long getId() {
            return id;
      }

      public void setId(Long id) {
            this.id = id;
      }

      public String getNome() {
            return nome;
      }

      public void setNome(String nome) {
            this.nome = nome;
      }

      public String getCpf() {
            return cpf;
      }

      public void setCpf(String cpf) {
            this.cpf = cpf;
      }

      public String getEndereco() {
            return endereco;
      }

      public void setEndereco(String endereco) {
            this.endereco = endereco;
      }

      public String getEmail() {
            return email;
      }

      public void setEmail(String email) {
            this.email = email;
      }

      public String getTelefone() {
            return telefone;
      }

      public void setTelefone(String telefone) {
            this.telefone = telefone;
      }

      public Date getDataCadastro() {
            return dataCadastro;
      }

      public void setDataCadastro(Date dataCadastro) {
            this.dataCadastro = dataCadastro;
      }

      public static User convert(UserDTO userDTO) {
            User user = new User();
            user.setNome(userDTO.getNome());
            user.setCpf(userDTO.getCpf());
            user.setEndereco(userDTO.getEndereco());
            user.setEmail(userDTO.getEmail());
            user.setTelefone(userDTO.getTelefone());
            user.setDataCadastro(userDTO.getDataCadastro());
            return user;
      }
      
}
