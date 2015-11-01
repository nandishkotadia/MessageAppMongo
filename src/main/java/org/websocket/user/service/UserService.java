package org.websocket.user.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.websocket.dtos.StatusDTO;
import org.websocket.dtos.UserDTO;
import org.websocket.models.User;
import org.websocket.repositories.UserRepository;

@Service
public class UserService {

   @Autowired
   private UserRepository userRepository;

   public String authenticateUser(UserDTO userDTO) {
      User user = userRepository.findByUsernameAndPassword(userDTO.getUsername(),
            userDTO.getPassword());
      if (user != null) {
         if (user.getStatus().equalsIgnoreCase("OFFLINE")) {
            /*user.setStatus("ONLINE");
            userRepository.save(user);*/
            return "SUCCESS";
         }
         else {
            System.out.println("User is already online");
            return "SUCCESS";
         }

      } else {
         System.out.println("User not found");
         return "USER NOT FOUND";

      }
   }

   public List<UserDTO> getFriends(String username){
      /*List<User> friends= userRepository.findByNotUsername(username);*/
      List<User> friends = (List<User>) userRepository.findAll();
      List<UserDTO> friendsDTO = convertIntoUserDTO(friends);

      return friendsDTO;
   }

   private List<UserDTO> convertIntoUserDTO(List<User> friends) {
      List<UserDTO> userDTO =  new ArrayList<UserDTO>();
      for(User f:friends)
      {
         UserDTO user = new UserDTO();
         user.setUsername(f.getUsername());
         user.setDisplayName(f.getDisplayName());
         user.setStatus(f.getStatus());

         userDTO.add(user);
      }
      return userDTO;
   }

   public void updateUserStatus(StatusDTO statusDTO) {
      User user = userRepository.findByUsername(statusDTO.getUsername());
      user.setStatus(statusDTO.getStatus());
      userRepository.save(user);
      //update user status;

   }

   public String registerUser(UserDTO userDTO) {
      User checkUser = userRepository.findByUsername(userDTO.getUsername());
      if(checkUser !=null)
      {
         return "EXISTS";
      }
      User user = new User();
      user.setUsername(userDTO.getUsername());
      user.setDisplayName(userDTO.getDisplayName());
      user.setPassword(userDTO.getPassword());
      user.setStatus("OFFLINE");
      user.setCreateDate(new Date());
      user.setUpdateDate(new Date());
      userRepository.save(user);
      return "SUCCESS";
   }

}
