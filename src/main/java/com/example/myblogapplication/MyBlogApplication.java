package com.example.myblogapplication;


import com.example.myblogapplication.model.Role;
import com.example.myblogapplication.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MyBlogApplication implements CommandLineRunner {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(MyBlogApplication.class, args);
    }

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
  if (roleRepository.findAll().isEmpty()) {
      Role roleAdmin = new Role();
      roleAdmin.setName("ROLE_ADMIN");
      roleRepository.save(roleAdmin);

      Role roleUser = new Role();
      roleUser.setName("ROLE_USER");
      roleRepository.save(roleUser);
  }
  }
}
