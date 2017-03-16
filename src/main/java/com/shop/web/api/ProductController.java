package com.shop.web.api;

import com.shop.data.entity.ProductEntity;
import com.shop.service.dataservice.composition.CompositionService;
import com.shop.service.dataservice.product.InvalidProductOwnerException;
import com.shop.service.dataservice.product.ProductNotFoundException;
import com.shop.service.dataservice.product.ProductService;
import com.shop.service.dataservice.security.SecurityService;
import com.shop.service.dataservice.user.UserService;
import com.shop.service.dto.CompositionSimpleDto;
import com.shop.service.dto.product.ProductCreateDto;
import com.shop.service.dto.product.ProductEditDto;
import com.shop.service.storage.ImageStorageException;
import com.shop.web.ResponseObject;
import com.shop.web.viewmodel.ProductForSellCreateRequest;
import com.shop.web.viewmodel.ProductForSellUpdateRequest;
import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

/**
 * Created by zhirayrg on 3/14/2017.
 */
@RestController
@RequestMapping("/api")
public class ProductController {

    private static final Logger logger = Logger.getLogger(ProductController.class.getName());

    @Autowired
    private Mapper mapper;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private CompositionService compositionService;

    @Autowired
    private SecurityService securityService;

    @GetMapping("/products/{id}")
    public  ProductEntity getProduct(@PathVariable("id") Long productId) {

        return productService.getById(productId);
    }



    @PostMapping("/products")
    public void handleFileUpload(HttpServletResponse response, @RequestParam("file") MultipartFile[] files,
                                 @ModelAttribute ProductForSellCreateRequest requestModel, Principal principal) {

        Long userId = userService.findByUsername(principal.getName()).getId();
        try {
            if (requestModel.getUserMessage().equals(""))
                requestModel.setUserMessage(null);

            ProductCreateDto productCreateDto = mapper.map(requestModel, ProductCreateDto.class);
            productCreateDto.setImages(files);
            productCreateDto.setUser(userId);
            long productId = productService.add(productCreateDto);
            logger.info(String.format("Add product success, productId: %s, userId: %s, userName: %s", productId, userId, principal.getName()));
            response.sendRedirect("/api/detail/"+ productId);
        } catch (ImageStorageException | IOException e) {
            logger.error(String.format("Can't add product, userId: %s", userId), e);
        }
    }


    @PutMapping("/products/productId")
    public ResponseObject editProduct(@RequestParam("file") MultipartFile[] files,
                                      @PathParam("productId") Long productId,
                                      @ModelAttribute ProductForSellUpdateRequest requestModel,
                                      Principal principal) {

        ResponseObject responseObject = new ResponseObject();

        try {
            if (requestModel.getUserMessage().equals(""))
                requestModel.setUserMessage(null);

            ProductEditDto editDto = mapper.map(requestModel, ProductEditDto.class);

            editDto.setImages(files);
            Long userId = userService.findByUsername(principal.getName()).getId();
            editDto.setUser(userId);
            productService.edit(editDto, securityService.hasRole("ROLE_ADMIN"));
            logger.info(String.format("Edit product success, productId: %s, userId: %s, userName:  %s", editDto.getProductId(), userId, principal.getName()));
            responseObject.setMessage("Product edited successfully");
            responseObject.setStatus(HttpStatus.OK);
            return responseObject;
        } catch (ImageStorageException | InvalidProductOwnerException | ProductNotFoundException e) {
            logger.error(e.getMessage(), e.getCause());
            responseObject.setStatus();
        }
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long productId,  Principal principal) {

        try {

            Long userId = userService.findByUsername(principal.getName()).getId();
            productService.delete(productId, userId);
            return "redirect:/myoffers";

        } catch (InvalidProductOwnerException | ProductNotFoundException e) {
            logger.info(e.getMessage(), e.getCause());
            return "redirect:/";
        }
    }


}
