package com.eohs.safetymonitoring.controller;

import com.eohs.safetymonitoring.entity.Role;
import com.eohs.safetymonitoring.entity.User;
import com.eohs.safetymonitoring.repository.RoleRepository;
import com.eohs.safetymonitoring.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
public class AuthController {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository,
                          RoleRepository roleRepository,
                          PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String doRegister(@RequestParam String fullName,
                             @RequestParam String email,
                             @RequestParam String password,
                             @RequestParam String role,
                             Model model) {

        if (userRepository.existsByEmail(email)) {
            model.addAttribute("error", "Email already registered!");
            return "register";
        }

        User u = new User();
        u.setFullName(fullName);
        u.setEmail(email);
        u.setPassword(passwordEncoder.encode(password));

        Role r = roleRepository.findByName(role)
                .orElseThrow(() -> new RuntimeException("Role not found: " + role));

        u.setRoles(Set.of(r));
        userRepository.save(u);

        return "redirect:/login?registered";
    }
}
