package com.shop.web.controller;

import com.shop.data.entity.ProductEntity;
import com.shop.service.dataservice.composition.CompositionService;
import com.shop.service.dataservice.product.InvalidProductOwnerException;
import com.shop.service.dataservice.product.ProductNotFoundException;
import com.shop.service.dataservice.product.ProductService;
import com.shop.service.dataservice.security.SecurityService;
import com.shop.service.dataservice.user.UserService;
import com.shop.service.dto.product.ProductCreateDto;
import com.shop.service.dto.product.ProductEditDto;
import com.shop.service.storage.ImageStorageException;
import com.shop.web.viewmodel.ProductForSellCreateRequest;
import com.shop.web.viewmodel.ProductForSellUpdateRequest;
import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

/**
 * Created by zhirayrg on 3/9/2017.
 */

@Controller
public class ProductController {
    private static final Logger logger = Logger.getLogger(ProductController.class.getName());

    @Autowired
    private Mapper mapper;
    @Autowired
    private ProductService productService;

    @Autowired
    private CompositionService compositionService;

    @Autowired
    private UserService userService;
    @Autowired
    private SecurityService securityService;


    @GetMapping("/add")
    public String addProduct(ModelMap modelMap, Principal principal) {
        Long userId = userService.findByUsername(principal.getName()).getId();
        logger.info(String.format("Going  add product page, userId: %s, userName: %s", userId, principal.getName()));
        modelMap.addAttribute("equipments", compositionService.getAll());
        return "addproduct";

    }

    @PostMapping("/add")
    public String handleFileUpload(@RequestParam("file") MultipartFile[] files,
                                   RedirectAttributes redirectAttributes, @ModelAttribute ProductForSellCreateRequest requestModel, Principal principal) {

        Long userId = userService.findByUsername(principal.getName()).getId();
        try {
            if (requestModel.getUserMessage().equals(""))
                requestModel.setUserMessage(null);

            ProductCreateDto productCreateDto = mapper.map(requestModel, ProductCreateDto.class);
            productCreateDto.setImages(files);
            productCreateDto.setUser(userId);
            long productId = productService.add(productCreateDto);
            redirectAttributes.addAttribute("id", productId);
            logger.info(String.format("Add product success, productId: %s, userId: %s, userName: %s", productId, userId, principal.getName()));
            return "redirect:/detail/{id}";
        } catch (ImageStorageException e) {
            logger.error(String.format("Can't add product, userId: %s", userId), e);
            return "ERROR";
        }
    }


    @GetMapping("/edit/{id}")
    public String editOffer(@PathVariable("id") Long productId, ModelMap modelMap, Principal principal) {
        Long userId = userService.findByUsername(principal.getName()).getId();

        logger.info(String.format("Trying edit product, productId: %s, userId: %s, userName:  %s", productId, userId, principal.getName()));
        ProductEntity product = null;
        try {

            if (securityService.hasRole("ROLE_ADMIN")) {
                product = productService.getById(productId);
            } else {
                product = productService.getByIdAndUserId(productId, userId);
            }
            modelMap.addAttribute("equipments", compositionService.getAll());
        } catch (InvalidProductOwnerException | ProductNotFoundException e ) {
            logger.warn(e.getMessage(), e.getCause());
            return "redirect:/";
        }
        modelMap.addAttribute("product", product);
        return "edit";

    }

    @PostMapping("/edit")
    public String editProduct(@RequestParam("file") MultipartFile[] files,
                              RedirectAttributes redirectAttributes, @ModelAttribute ProductForSellUpdateRequest requestModel, Principal principal) {

        try {
            if (requestModel.getUserMessage().equals(""))
                requestModel.setUserMessage(null);

            ProductEditDto editDto = mapper.map(requestModel, ProductEditDto.class);

            editDto.setImages(files);
            Long userId = userService.findByUsername(principal.getName()).getId();
            editDto.setUser(userId);
            productService.edit(editDto, securityService.hasRole("ROLE_ADMIN"));
            redirectAttributes.addAttribute("id", requestModel.getProductId());
            logger.info(String.format("Edit product success, productId: %s, userId: %s, userName:  %s", editDto.getProductId(), userId, principal.getName()));
            return "redirect:/detail/{id}";
        } catch (ImageStorageException e) {
            logger.error(e.getMessage(), e.getCause());
            return "ERROR";
        } catch (InvalidProductOwnerException | ProductNotFoundException e) {
            logger.info(e.getMessage(), e.getCause());

            return "redirect:/";
        }
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long productId, RedirectAttributes redirectAttributes, Principal principal) {

        try {

            Long userId = userService.findByUsername(principal.getName()).getId();
            productService.delete(productId, userId);
            return "redirect:/myoffers";

        } catch (InvalidProductOwnerException | ProductNotFoundException e) {
            logger.info(e.getMessage(), e.getCause());
            return "redirect:/";
        }
    }


    @GetMapping("/product/analytics/{id}")
    public String analytics(@PathVariable("id") Long productId, ModelMap modelMap, Device device, Principal principal) {
        Long userId = userService.findByUsername(principal.getName()).getId();

        ProductEntity product = null;
        try {
            product = productService.getByIdAndUserId(productId, userId);
            modelMap.addAttribute("product", product);
            modelMap.addAttribute("device", device);
            modelMap.addAttribute("productId", product.getId());
            logger.info(String.format("Viewing analytic, productId: %s, userId: %s, userName:  %s", productId, userId, principal.getName()));

        } catch (InvalidProductOwnerException | ProductNotFoundException e) {
            logger.warn(String.format("Trying view analytic, productId: %s, userId: %s, userName:  %s", productId, userId, principal.getName()), e.getCause());
            return "redirect:/";
        }
        return "productanalytic";

    }


}
