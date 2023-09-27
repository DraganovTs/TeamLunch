package com.homecode.user.service.init;

import com.homecode.user.service.model.Role;
import com.homecode.user.service.model.User;
import com.homecode.user.service.model.enums.ERole;
import com.homecode.user.service.repository.RoleRepository;
import com.homecode.user.service.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class RoleSeeder implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RoleSeeder(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args)  {
        if (roleRepository.count() == 0) {
            insertRoles();
        }
        if (userRepository.count()==0){
            insertUsers();
        }
    }



    public void insertUsers() {


        Set<Role> roleAdmin = new HashSet<>();
        roleAdmin.add(roleRepository.findByName(ERole.ROLE_ADMIN).get());
        User admin = new User("admin","admin@test.com",passwordEncoder.encode("adminpass"), roleAdmin);
        this.userRepository.save(admin);

        Set<Role> roleMod = new HashSet<>();
        roleMod.add(roleRepository.findByName(ERole.ROLE_MODERATOR).get());
        User mod = new User("mod","mod@test.com", passwordEncoder.encode("modpass"), roleMod);
        this.userRepository.save(mod);

        Set<Role> roleUser = new HashSet<>();
        roleUser.add(roleRepository.findByName(ERole.ROLE_USER).get());
        User user = new User("user","user@test.com",passwordEncoder.encode("userpass"), roleUser);
        this.userRepository.save(user);

    }

    private void insertRoles() {
        List<Role> roleList = new ArrayList<>();
        Role role1 = new Role(ERole.ROLE_USER);
        Role role2 = new Role(ERole.ROLE_MODERATOR);
        Role role3 = new Role(ERole.ROLE_ADMIN);

        roleList.add(role1);
        roleList.add(role2);
        roleList.add(role3);

        this.roleRepository.saveAll(roleList);
    }
}
