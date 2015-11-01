package org.websocket.user.service;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.websocket.dtos.StatusDTO;
import org.websocket.dtos.UserDTO;

@RestController
@ComponentScan(basePackages = "org.websocket.dtos")
public class UserController {

   @Autowired
   private SimpMessagingTemplate simpMessagingTemplate;

   @Autowired
   private UserService userService;

   @RequestMapping(value = "/user/login", method = { RequestMethod.POST })
   public String validateLogin(@RequestBody UserDTO userDTO)
         throws JsonProcessingException {
      return userService.authenticateUser(userDTO);
   }

   @RequestMapping(value = "/user/register", method = { RequestMethod.POST })
   public String validateRegister(@RequestBody UserDTO userDTO)
         throws JsonProcessingException {
      return userService.registerUser(userDTO);
   }

   @RequestMapping(value = "/user/{username}/friends", method = { RequestMethod.GET })
   public List<UserDTO> getFriends(@PathVariable String username)
         throws JsonProcessingException {
      List<UserDTO> userDTOs = userService.getFriends(username);
      return userDTOs;
   }

   //verify the update flow
   @MessageMapping("/userStatus")
   public void chat(StatusDTO statusDTO)
         throws Exception {
      userService.updateUserStatus(statusDTO);
      simpMessagingTemplate.convertAndSend("/topic/userStatus", statusDTO);
   }

   @MessageMapping("/newUserNotifier")
   public void newUserNotifier(UserDTO userDTO) throws Exception {
      simpMessagingTemplate.convertAndSend("/topic/newUserNotifier", userDTO);
   }
}

/*@Autowired
private AccountService accountService;

@RequestMapping(value = "/accounts", method = { RequestMethod.POST })
public ResponseEntity<AccountDTO> createAccount(@RequestBody AccountDTO accountModel)
      throws JsonProcessingException {
   AccountDTO account = accountService.createAccount(accountModel);
   HttpHeaders responseHeaders = new HttpHeaders();
   responseHeaders.add("accountId", account.getProductId());
   return new ResponseEntity<AccountDTO>(account, responseHeaders, HttpStatus.CREATED);
}

@RequestMapping(value = "/accounts/{accountId}/details", method = { RequestMethod.GET })
public Map<String, Object> getAccountDetails(@PathVariable String accountId) {
   return accountService.getAccountDetails(accountId);
}

@RequestMapping(value = "/accounts/{productId}", method = { RequestMethod.GET })
public AccountDTO getAccount(@PathVariable String productId) {
   return accountService.getAccount(productId);
}

@RequestMapping(value = "/accounts/{productId}", method = { RequestMethod.PUT })
public AccountDTO updateAccount(@PathVariable String productId,
      @RequestBody AccountDTO accountModel) throws JsonProcessingException {
   return accountService.updateAccount(productId, accountModel);
}*/

