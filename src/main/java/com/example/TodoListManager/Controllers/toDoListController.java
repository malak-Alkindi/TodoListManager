package com.example.TodoListManager.Controllers;
//import com.example.TodoListManager.Models.toDoListModel;
//import com.example.TodoListManager.requestObject.toDoListrRequestObject;
//import com.example.TodoListManager.Services.toDoListServices;
//import com.example.TodoListManager.responseObject.toDoListResponseObject;
//import com.example.TodoListManager.Repository.toDoListRepository;
//import org.springframework.beans.factory.annotation.Autowired;
import com.example.TodoListManager.Models.toDoList;
import com.example.TodoListManager.Services.RoleService;
import com.example.TodoListManager.Services.toDoListServices;
import com.example.TodoListManager.Services.userInfroService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequestMapping("/api/todos")
@RequiredArgsConstructor
@RestController
public class toDoListController {
    @Autowired
    toDoListServices toDoServ;
    private final userInfroService userServ;

    private final RoleService roleServ;




    @GetMapping("get")
    public ResponseEntity<?> findAll() {

        return ResponseEntity.ok(toDoServ.findAll());
    }

    @GetMapping("get/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id ) {

        return ResponseEntity.ok(toDoServ.findById(id));
    }


    @GetMapping("")
    @ResponseBody
    public String createList(@RequestParam String title,@RequestParam String description,@RequestParam boolean completed) throws JsonProcessingException {

        toDoList listObj = new toDoList(null,null,userServ.getUserbyName().get().getId(),title,description,completed);
        listObj.setCreatedDate(new Date());
        listObj.setIsActive(true);
        listObj.setCompleted(completed);

        toDoServ.save(listObj);
        return "list created ,yay";
    }

    @RequestMapping(value = "/get/all", method = RequestMethod.GET)
    public List<toDoList> getListsAssignedToUser() {

            return toDoServ.getAllListAssigendToUser(userServ.getUserbyName().get().getId());

    }

    @GetMapping (value = "/getone/{title}")
    public List<toDoList> getBytitle(@PathVariable String title) {

        return toDoServ.getOneList(userServ.getUserbyName().get().getId(),title);

    }

    @GetMapping  ("/delete/{lsitID}")
    public String deleteLissd(@PathVariable Long lsitID) {

        return toDoServ.deleteList(userServ.getUserbyName().get().getId(), lsitID);

    }

//    @GetMapping (value = "/delete")
//    public String deleteLisst(@PathVariable int hh) {
//
//       return toDoServ.deleteList(userServ.getUserbyName().get().getId(), r);
//
//    }
@PutMapping("/api/update/{productId}")
public ResponseEntity<toDoList> updateProduct(@PathVariable Long productId, @RequestBody toDoList updatedProduct) {
    toDoList response = toDoServ.updateProduct(productId, updatedProduct);
    if (response != null) {
        return ResponseEntity.ok(response);
    } else {
        return ResponseEntity.notFound().build();
    }
}

    @RequestMapping(value = "/findListByTitle", method = RequestMethod.GET)
    public  List<toDoList>  getListByTitle(@RequestParam String title){
        return toDoServ.getListByTitle(title);
    }
}
