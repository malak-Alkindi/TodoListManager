package com.example.TodoListManager.Services;



import com.example.TodoListManager.Models.toDoList;
import com.example.TodoListManager.Repository.toDoListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class toDoListServices {

        @Autowired
        toDoListRepository todoRepo;


    public List<toDoList> findAll (){

        return todoRepo.findAll();
    }

    public toDoList findById (Long id){

        return todoRepo.findById(id).orElse(null);
    }

    public toDoList save(toDoList entity) {
       // entity.setPassword(passwordEncoder.encode(entity.getPassword()));

        return todoRepo.save(entity);

    }
    public List<toDoList> getAllListAssigendToUser(Long userId) {

//        toDoListModel exampletoDo = new toDoListModel();
//        exampletoDo.setUser_idd(userId);
//        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues();
//        System.out.println(matcher);
//        Example<toDoListModel> example = Example.of(exampletoDo, matcher);
//        return todoRepo.findAll(example);

        List<toDoList> filteredList = todoRepo.findAll().stream()
                .filter(c -> c.getUser_idd() ==userId)
                .collect(Collectors.toList());

        return filteredList;
    }
    public List<toDoList> getOneList(Long userId, String title) {



        List<toDoList> filteredList = getAllListAssigendToUser(userId).stream()
                .filter(c -> title.contains( c.getTitle().toLowerCase()))
                .collect(Collectors.toList());

        return filteredList;
    }

    public String deleteList(Long userId,Long lsitID) {



        List<toDoList> filteredList = getAllListAssigendToUser(userId).stream()
                .filter(c -> c.getList_id()==lsitID)
                .collect(Collectors.toList());
System.out.print(filteredList);
       if(filteredList.isEmpty()){
           return "no list assigned to you with this id";
       }
       else{
           todoRepo.deleteById(lsitID);
           return "list delated";
       }
    }


    public toDoList updateToDoList(toDoList toDoListRequest) {
        // get the product from DB by id
        // update with new value getting from request



        return todoRepo.save(toDoListRequest);
    }

    public toDoList updateProduct(Long productId, toDoList updatedProduct) {
        Optional<toDoList> optionalProduct = todoRepo.findById(productId);
        if (optionalProduct.isPresent()) {
            toDoList product = optionalProduct.get();
            product.setTitle(updatedProduct.getTitle());
            todoRepo.save(product);
            return todoRepo.getById(productId);
        }
        return null;
    }
    public  List<toDoList>  getListByTitle(String title){
        return todoRepo.getListByTitle(title);
    }
}

