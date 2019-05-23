package com.lambda.essentialism;

import com.lambda.essentialism.model.*;
import com.lambda.essentialism.repository.RoleRepository;
import com.lambda.essentialism.repository.UserRepository;
import com.lambda.essentialism.repository.ValueRepository;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Transactional
public class SeedData implements CommandLineRunner {
  private ValueRepository valueRepos;
  RoleRepository rolerepos;
  UserRepository userrepos;

  public SeedData(
    ValueRepository valueRepos,
    RoleRepository rolerepos,
    UserRepository userrepos
  ) {
    this.valueRepos = valueRepos;
    this.rolerepos = rolerepos;
    this.userrepos = userrepos;
  }

  @Override
  public void run(String[] args) throws Exception {
//    Value val1 = new Value("Athletic ability");
//    Value val2 = new Value(
//      "Creativity, discovering, or inventing things to make a difference in the world"
//    );
//    Value val3 = new Value("Independence");
//    Value val4 = new Value("Kindness and generosity");
//    Value val5 = new Value("Living in the moment");
//    Value val6 = new Value(
//      "Membership in a social group (such as your community, racial group, or school club)"
//    );
//    Value val7 = new Value("Music");
//    Value val8 = new Value("My community");
//    Value val9 = new Value("My moral principles");
//    Value val10 = new Value("Nature and the environment");
//    Value val11 = new Value("Success in my career");
//
//    valueRepos.save(val1);
//    valueRepos.save(val2);
//    valueRepos.save(val3);
//    valueRepos.save(val4);
//    valueRepos.save(val5);
//    valueRepos.save(val6);
//    valueRepos.save(val7);
//    valueRepos.save(val8);
//    valueRepos.save(val9);
//    valueRepos.save(val10);
//    valueRepos.save(val11);
//
//    Role r1 = new Role("admin");
//    Role r2 = new Role("user");
//    Role r3 = new Role("data");
//
//    r1 = rolerepos.save(r1);
//    r2 = rolerepos.save(r2);
//    r3 = rolerepos.save(r3);
//
//    ArrayList<UserRoles> admins = new ArrayList<>();
//    admins.add(new UserRoles(new User(), r1));
//    admins.add(new UserRoles(new User(), r2));
//
//    ArrayList<UserRoles> users = new ArrayList<>();
//    users.add(new UserRoles(new User(), r2));
//
//    ArrayList<UserRoles> data = new ArrayList<>();
//    data.add(new UserRoles(new User(), r2));
//    data.add(new UserRoles(new User(), r3));
//
//    ArrayList<UserValues> userValues = new ArrayList<>();
//
//    User u1 = new User("Garrett", "Weems", "gwgraphicdesign@gmail.com","gw", "password", users);
//    User u2 = new User("Sudo", "Admin", "sudo@admin.com","admin", "password", admins);
//    User u3 = new User("Dummy", "User", "dummy@user.com","user", "password", data);
//    userrepos.save(u1);
//    userrepos.save(u2);
//    userrepos.save(u3);
  }

}

