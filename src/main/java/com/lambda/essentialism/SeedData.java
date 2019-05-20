package com.lambda.essentialism;

import com.lambda.essentialism.model.Value;


import javax.transaction.Transactional;

import com.lambda.essentialism.repo.ValueRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Transactional
public class SeedData implements CommandLineRunner {
private ValueRepo valueRepos;

  public SeedData(ValueRepo valueRepos) {
    this.valueRepos = valueRepos;
  }

  @Override
  public void run(String[] args) throws Exception {
    Value val1 = new Value("Athletic ability");
    Value val2 = new Value("Creativity, discovering, or inventing things to make a difference in the world");
    Value val3 = new Value("Independence");
    Value val4 = new Value("Kindness and generosity");
    Value val5 = new Value("Living in the moment");
    Value val6 = new Value("Membership in a social group (such as your community, racial group, or school club)");
    Value val7 = new Value("Music");
    Value val8 = new Value("My community");
    Value val9 = new Value("My moral principles");
    Value val10 = new Value("Nature and the environment");
    Value val11 = new Value("Success in my career");

    valueRepos.save(val1);
    valueRepos.save(val2);
    valueRepos.save(val3);
    valueRepos.save(val4);
    valueRepos.save(val5);
    valueRepos.save(val6);
    valueRepos.save(val7);
    valueRepos.save(val8);
    valueRepos.save(val9);
    valueRepos.save(val10);
    valueRepos.save(val11);
  }
}

