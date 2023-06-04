package com.example.TodoListManager.Repository;

import com.example.TodoListManager.Models.toDoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface toDoListRepository extends JpaRepository<toDoList, Long> {

    @Query("SELECT t FROM toDoList t WHERE t.title =:tt")
    List<toDoList> getListByTitle(@Param("tt") String title);
}
