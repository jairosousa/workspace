package com.jnsdev.back.end.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jnsdev.back.end.dto.UserDTO;
import com.jnsdev.back.end.model.User;
import com.jnsdev.back.end.repository.UserRepository;

@Service
public class UserService {

      @Autowired
      private UserRepository userRepository;

      public List<UserDTO> getAll() {
            return userRepository.findAll()
                        .stream()
                        .map(UserDTO::convert)
                        .collect(Collectors.toList());
      }

      public UserDTO findById(Long id) {
            Optional<User> usuario = userRepository.findById(id);

            if (usuario.isPresent()) {
                  return UserDTO.convert(usuario.get());
            }
            return null;
      }

      public UserDTO save(UserDTO userDTO) {
            User user = userRepository.save(User.convert(userDTO));
            return UserDTO.convert(user);
      }

      public UserDTO delete(Long id) {
            Optional<User> user = userRepository.findById(id);

            if (user.isPresent()) {
                  userRepository.delete(user.get());
            }
            return null;
      }

      public UserDTO findByCpf(String cpf) {
            User user = userRepository.findByCpf(cpf);

            if (user != null) {
                  return UserDTO.convert(user);
            }
            return null;
      }

      public List<UserDTO> queryByName(String nome) {
            List<User> usuarios = userRepository.queryByNomeLike(nome);

            return usuarios
            .stream()
            .map(UserDTO::convert)
            .collect(Collectors.toList());
      }

}
