package com.example.TodoListManager.Models;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class toDoList extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long List_id;



    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    userInfo registrationInfoModel;
long user_idd ;



    String title;
    String description;
    boolean completed;


}
