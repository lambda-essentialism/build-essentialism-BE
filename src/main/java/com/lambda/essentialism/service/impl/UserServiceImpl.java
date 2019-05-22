package com.lambda.essentialism.service.impl;

import com.lambda.essentialism.exception.ResourceNotFoundException;
import com.lambda.essentialism.model.User;
import com.lambda.essentialism.model.UserRoles;
import com.lambda.essentialism.repository.RoleRepository;
import com.lambda.essentialism.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

import com.lambda.essentialism.repository.ValueRepository;
import com.lambda.essentialism.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService
{
  @Autowired
  private UserRepository userrepos;

  @Autowired
  private RoleRepository rolerepos;

  @Autowired
  private ValueRepository valuerepos;

  @Transactional
  public UserDetails loadUserByUsername(String username)
    throws
      ResourceNotFoundException {
    User user = userrepos.findByUsername(username);
    if (user == null) {
      throw new ResourceNotFoundException("Invalid username or password.");
    }
    return new org.springframework.security.core.userdetails.User(
      user.getUsername(),
      user.getPassword(),
      user.getAuthority()
    );
  }

  public User findUserById(long id) throws ResourceNotFoundException {
    return userrepos.findById(id).orElseThrow(
      () -> new ResourceNotFoundException(Long.toString(id))
    );
  }

  public List<User> findAll() {
    List<User> list = new ArrayList<>();
    userrepos.findAll().iterator().forEachRemaining(list::add);
    return list;
  }

  @Override
  public void delete(long id) {
    if (userrepos.findById(id).isPresent()) {
      userrepos.deleteById(id);
    } else {
      throw new ResourceNotFoundException(Long.toString(id));
    }
  }

  @Override
  @Transactional
  public User save(User user) {
    User newUser = new User();
    newUser.setFirstname(user.getFirstname());
    newUser.setLastname(user.getLastname());
    newUser.setEmail(user.getEmail());
    newUser.setUsername(user.getUsername());
    newUser.setPasswordNoEncrypt(user.getPassword());
    ArrayList<UserRoles> newRoles = new ArrayList<>();
    for (UserRoles ur : user.getUserRoles()) {
      newRoles.add(new UserRoles(newUser, ur.getRole()));
    }
    newUser.setUserRoles(newRoles);

    return userrepos.save(newUser);
  }

  @Override
  @Transactional
  public User update(User user, long id) {
    Authentication authentication = SecurityContextHolder.getContext(

    ).getAuthentication();
    User currentUser = userrepos.findByUsername(authentication.getName());

    if (currentUser != null) {
      if (id == currentUser.getUserid()) {
        if (user.getUsername() != null) {
          currentUser.setUsername(user.getUsername());
        }
        if (user.getPassword() != null) {
          currentUser.setPasswordNoEncrypt(user.getPassword());
        }
        if (user.getUserRoles().size() > 0) {
          // with so many relationships happening, I decided to go
          // with old school queries
          // delete the old ones
          rolerepos.deleteUserRolesByUserId(currentUser.getUserid());
          // add the new ones

          for (UserRoles ur : user.getUserRoles()) {
            rolerepos.insertUserRoles(id, ur.getRole().getRoleid());
          }
        }
        return userrepos.save(currentUser);
      } else {
        throw new ResourceNotFoundException(
          Long.toString(id) + " Not current user"
        );
      }
    } else {
      throw new ResourceNotFoundException(authentication.getName());
    }
  }

}

