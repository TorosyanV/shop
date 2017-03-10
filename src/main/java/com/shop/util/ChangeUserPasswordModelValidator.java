package com.shop.util;

import com.shop.data.entity.UserEntity;
import com.shop.web.viewmodel.ChangePasswordModel;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by zhirayrg on 3/10/2017.
 */
@Component
public class ChangeUserPasswordModelValidator implements Validator {


    @Override
    public boolean supports(Class<?> aClass) {
        return UserEntity.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ChangePasswordModel model = (ChangePasswordModel) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (model.getPassword().length() < 6 || model.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        if (!model.getConfirmPassword().equals(model.getPassword())) {
            errors.rejectValue("confirmPassword", "Diff.userForm.passwordConfirm");
        }
    }
}
