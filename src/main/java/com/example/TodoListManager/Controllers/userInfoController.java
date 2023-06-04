package com.example.TodoListManager.Controllers;
import com.example.TodoListManager.Models.Role;
import com.example.TodoListManager.Models.userInfo;
import com.example.TodoListManager.Services.RoleService;
import com.example.TodoListManager.Services.userInfroService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class userInfoController {
    @Autowired
    userInfroService UserServ;
    private final RoleService roleServ;

    @GetMapping("get")
    public ResponseEntity<?> findAll() {

        return ResponseEntity.ok(UserServ.findAll());
    }



    @GetMapping("get/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id ) {

        return ResponseEntity.ok(UserServ.findById(id));
    }
@GetMapping("/register")
@ResponseBody
public String getaccountQueryString(@RequestParam String fullName,@RequestParam String userName,@RequestParam String password,@RequestParam String role) throws JsonProcessingException {
    Set<Role> Roles = new HashSet<>();
 Roles.add(roleServ.findByName(role.toLowerCase(Locale.ROOT)));
 userInfo user = new   userInfo(null, fullName, userName, password,Roles);
    user.setCreatedDate(new Date());
    user.setIsActive(true);
    UserServ.save(user);
    return "user account created ,yay";
}





//    @RequestMapping(value = "country/get/{country}", method = RequestMethod.GET)
//    public List<userInfo> getAthletes(@RequestParam(value = "country", required = false) String country ) {
//        if (country != null) {
//            return UserServ.getUserbyName(country);
//        } else {
//            return userInfroService.getUserbyName();
//        }
//    }
    @GetMapping("/get/currentUser")
    @ResponseBody
    public Optional<userInfo> get(){
        return UserServ.getUserbyName();
    }

}