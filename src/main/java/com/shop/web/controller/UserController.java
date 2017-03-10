package com.shop.web.controller;

import com.shop.data.entity.UserEntity;
import com.shop.service.dataservice.security.InvalidTokenException;
import com.shop.service.dataservice.security.SecurityService;
import com.shop.service.dataservice.user.UserService;
import com.shop.service.dto.user.UserCreateDto;
import com.shop.service.mail.MailService;
import com.shop.util.ChangeUserPasswordModelValidator;
import com.shop.util.RegistrationRequestValidator;
import com.shop.web.viewmodel.ChangePasswordModel;
import com.shop.web.viewmodel.LoginModel;
import com.shop.web.viewmodel.RegistrationRequest;
import org.apache.commons.mail.EmailException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zhirayrg on 3/10/2017.
 */

@Controller
public class UserController {

    private static final Logger logger = Logger.getLogger(UserController.class.getName());


    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private RegistrationRequestValidator userValidator;

    @Autowired
    private ChangeUserPasswordModelValidator changeUserPasswordModelValidator;

    @Autowired
    private MailService mailService;

    @GetMapping("/registration")
    public String registrationGet(RegistrationRequest registrationRequest) {
        logger.info("Going registration page");
        return "registration";
    }



    @PostMapping("/registration")
    public String registrationPost( RegistrationRequest registrationRequest, BindingResult bindingResult) {
        userValidator.validate(registrationRequest, bindingResult);

        if (bindingResult.hasErrors()) {
            logger.error(String.format("Error registration page, count %s", bindingResult.getErrorCount()));
            return "registration";
        }

        UserCreateDto userCreateDto = new UserCreateDto();
        userCreateDto.setUserName(registrationRequest.getUserName());
        userCreateDto.setPassword(registrationRequest.getPassword());
        userCreateDto.setJoinCode(registrationRequest.getJoinCode());

        long userId = userService.save(userCreateDto);

        String token = securityService.generateActivationToken(userId);

        try {
            mailService.sendVerificationEmail(userId, token);
            logger.info(String.format("Confirmation email sending success for userName: %s", registrationRequest.getUserName()));
        } catch (EmailException e) {
            logger.error(String.format("Can't send email to user with id: %s, email: %s ", userId, registrationRequest.getUserName()), e.getCause());
        }

        securityService.autoLogin(registrationRequest.getUserName(), registrationRequest.getPassword());
        logger.info(String.format("Registration success for userName: %s, redirecting", registrationRequest.getUserName()));
        return "redirect:/";
    }


    // Login form with error
    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginModel", new LoginModel());
        model.addAttribute("loginError", true);
        logger.error("Can't login");
        return "login";
    }


    @GetMapping({"/login"})
    public String login(LoginModel loginModel) {
        return "login";
    }



    @GetMapping("/activate/{token}")
    public String activation(@PathVariable("token") String token, ModelMap model) {


        try {
            long userId = securityService.activateAccount(token);
            logger.info(String.format("Activation with token success, userId: %s", userId));
        } catch (InvalidTokenException e) {
            logger.error("Can't activate with token", e.getCause());
            return "activationError";
        }

        return "activationSuccess";
    }

    @GetMapping({"/reset"})
    public String resetGet(Model model) {
//        model.addAttribute("loginModel", new LoginModel());
        return "resetPasswordSendMail";
    }

    @GetMapping({"/reset/{token}"})
    public String resetTokenGet(@PathVariable("token") String token, ModelMap model) {

        try {
            UserEntity userByResetToken = securityService.getUserByResetToken(token);
            model.addAttribute("token", token);
            model.addAttribute("changePasswordModel", new ChangePasswordModel());

        } catch (InvalidTokenException e) {
            logger.info("Invalid change password token");
            return "redirect:/resetPasswordInvalidToken";
        }
//        model.addAttribute("loginModel", new LoginModel());
        return "resetPasswordEnterNewPassword";
    }

    @GetMapping({"/resetPasswordInvalidToken"})
    public String resetPasswordInvalidToken(Model model) {
//        model.addAttribute("loginModel", new LoginModel());
        return "resetPasswordInvalidToken";
    }

    @PostMapping({"/reset/{token}"})
    public String resetTokenPost(@PathVariable("token") String token, ChangePasswordModel changePasswordModel, BindingResult bindingResult, ModelMap model) {

        changeUserPasswordModelValidator.validate(changePasswordModel, bindingResult);

        if (bindingResult.hasErrors()) {
            logger.error(String.format("Error registration page, count %s", bindingResult.getErrorCount()));
            return "resetPasswordEnterNewPassword";
        }

        try {
            securityService.changeUserPasswordByToken(token, changePasswordModel.getPassword());

        } catch (InvalidTokenException e) {
            logger.info("Can't reset password by token ", e);
        }
        return "redirect:/login";
//        model.addAttribute("loginModel", new LoginModel());
    }

    @GetMapping({"/reset-error"})
    public String resetError(Model model) {
//        model.addAttribute("loginModel", new LoginModel());
        return "resetPasswordSendMailError";
    }


    @GetMapping({"/reset-mail-success"})
    public String resetSendMailSuccess(Model model) {
//        model.addAttribute("loginModel", new LoginModel());
        return "resetPasswordSendMailSuccess";
    }


    @PostMapping({"/reset"})
    public String resetPost(String email, Model model) {


        UserEntity user = userService.findByUsername(email);
        if (user != null) {
            String token = securityService.generateResetToken(user.getId());
            try {
                mailService.sendPasswordResetEmail(user.getId(), token);
                logger.info(String.format("Reset password mail send success for email: %s, ", email));
            } catch (EmailException e) {
                logger.error(String.format("Can't send reset password email to user with id: %s, email: %s ", user.getId(), email), e.getCause());
            }
        } else {
            return "redirect:/reset-error";
        }

        return "redirect:/reset-mail-success";

    }

//    @PostMapping({"/login/facebook"})
//    public String resetPost(String facebookId, String token, Model model) {
//
//
//        try {
//            boolean loginSuccess = securityService.facebookLogin(facebookId, token);
//        } catch (InvalidFacebookTokenException e) {
//            e.printStackTrace();
//        }
//        return "redirect:/";
//
//
//    }
}
