package com.example.TodoListManager.Repository;


import com.example.TodoListManager.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName (String name);



//    @Query("SELECT * FROM Account WHERE accountHolderName=:accountHolder")
//    Account getAccountByAccountHolderName(@Param("accountHolder") String accountHolderName);

}
