package com.shop.web.Dashboard;

import com.shop.data.entity.ProductEntity;
import com.shop.data.entity.UserEntity;
import com.shop.data.repository.ProductRepository;
import com.shop.service.dataservice.security.SecurityService;
import com.shop.service.dataservice.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

/**
 * Created by zhirayrg on 3/10/2017.
 */

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SecurityService securityService;

    @GetMapping
    public String dashboard(ModelMap modelMap, Principal principal) {
        return "dashboard/index";
    }


    @GetMapping("/users")
    public String userList(ModelMap modelMap, Principal principal) {

        List<UserEntity> users;
        boolean isAdmin = securityService.hasRole("ROLE_ADMIN");

        if (isAdmin) {
            users = userService.getAll();
        } else {
            UserEntity user = userService.findByUsername(principal.getName());
            users = userService.getAllByJoinCode(user.getUserCode());
        }


        modelMap.addAttribute("users", users);
        return "/dashboard/users";
    }

    @GetMapping("/products")
    @Transactional(readOnly = true)
    public String productList(ModelMap modelMap, Principal principal) {
        Iterable<ProductEntity> products = productRepository.findAll(new Sort(Sort.Direction.DESC, "id"));
        modelMap.addAttribute("products", products);
        return "dashboard/products";
    }


}