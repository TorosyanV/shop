package com.shop.service.mail;

import org.apache.commons.mail.EmailException;

/**
 * Created by vazgen on 1/6/17.
 */
public interface MailService {

    void sendVerificationEmail(Long userId, String token) throws EmailException;
}
