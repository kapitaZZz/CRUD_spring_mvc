package crud.config;

import crud.model.Role;
import crud.model.User;
import crud.service.RoleService;
import crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
@Transactional
public class DataInit {

    private final RoleService roleService;
    private final UserService userService;

    @Autowired
    public DataInit(RoleService roleService, UserService userService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    private void postConstruct() {
        Role roleAdmin = new Role("ROLE_ADMIN");
        Role roleUser = new Role("ROLE_USER");
        roleService.addRole(roleAdmin);
        roleService.addRole(roleUser);

        Set<Role> roleSetAdmin = new HashSet<>();
        roleSetAdmin.add(roleService.getRoleByName("ROLE_ADMIN"));
        User admin = new User("admin", "adminSurname", "admin@gmail.com", "123456",
                "admin", roleSetAdmin);
        userService.addUser(admin);

        Set<Role> roleSetUser = new HashSet<>();
        roleSetUser.add(roleService.getRoleByName("ROLE_USER"));
        User user = new User("user", "surnameUser", "user@gmail.com", "456789",
                "user", roleSetUser);
        userService.addUser(user);
    }
}
