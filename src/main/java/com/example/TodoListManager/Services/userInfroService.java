package com.example.TodoListManager.Services;

import com.example.TodoListManager.Models.userInfo;
import com.example.TodoListManager.Repository.userInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class userInfroService implements UserDetailsService {


    private final userInfoRepository userrep;

    private final PasswordEncoder passwordEncoder;
    public List<userInfo> findAll (){

        return userrep.findAll();
    }

    public userInfo findById (Long id){

        return userrep.findById(id).orElse(null);
    }

    public userInfo save(userInfo entity) {
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));

        return userrep.save(entity);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<userInfo> appUser =	userrep.findByUserName(username);

        if (!appUser.isPresent()) {

            throw new UsernameNotFoundException("This User Not found with selected user name :- " + username);
        }

        return new User(appUser.get().getUserName(),appUser.get().getPassword(),getAuthorities(appUser.get()));


    }
    private static List<GrantedAuthority> getAuthorities(userInfo user) {

        List<GrantedAuthority> authorities = new ArrayList<>();

        if(!user.getRoles().isEmpty()) {
            user.getRoles().forEach(role -> {
                authorities.add(new SimpleGrantedAuthority(role.getName()));
            });
        }
        return authorities;
    }


    //the one i didnt get
    public List<userInfo> getUsersbyName(String name) {
        userInfo exampleUser = new userInfo();
        exampleUser.setUserName(name);
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues();
        Example<userInfo> example = Example.of(exampleUser, matcher);
        return userrep.findAll(example);
    }
    public Optional<userInfo> getUserbyName() {
        return userrep.findByUserName(currentUserName());
    }
    private String currentUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String reqiredInfo = authentication.getName();
        return reqiredInfo;
    }
}
