package com.shop.service.mail;


import com.shop.service.dataservice.user.UserService;
import org.apache.commons.mail.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;


/**
 * Created by vazgen on 1/6/17.
 */

@Service
public class MailServiceImpl implements MailService {


    private static final Logger logger = Logger.getLogger(MailServiceImpl.class.getName());

    @Autowired
    private UserService userService;

    @Value("${shop.mail.host}")
    private String hostName;

    @Value("${shop.mail.smtp.port}")
    private int smtpPort;

    @Value("${shop.mail.ssl.enable}")
    private boolean sslEnabled;

    @Value("${shop.mail.starttls.enable}")
    private boolean starttlsEnable;

    @Value("${shop.mail.username}")
    private String username;

    @Value("${shop.mail.password}")
    private String password;


    private String verificationSubject="shop հատսատման նամակ";

    @Value("${shop.root.domain}")
    private String rootDomain;

    @Value("${shop.mail.type.verification.email}")
    private String verificationMail;


    private void sendMail(String from, String to, String subject, String message) throws EmailException {
        Email email = new HtmlEmail();
        email.setHostName(hostName);
        email.setSmtpPort(smtpPort);
        email.setAuthenticator(new DefaultAuthenticator(username, password));
        email.setSSLOnConnect(sslEnabled);
        email.setStartTLSEnabled(starttlsEnable);
        email.setCharset(EmailConstants.UTF_8);
        email.setFrom(from);
        email.setSubject(subject);
        email.setMsg(message);
        email.addTo(to);
        email.send();

    }

    @Override
    public void sendVerificationEmail(Long userId, String token) throws EmailException {

        String htmlMessage = "";
        try {
            final Charset UTF8_CHARSET = Charset.forName("UTF-8");
            htmlMessage = new String(Files.readAllBytes(Paths.get(getClass().getResource("/mailtemplate/verification.html").toURI())), UTF8_CHARSET);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }


        String toEmail = userService.getById(userId).getUserName();
        String verificationLink = String.format("%s/activate/%s", rootDomain, token);
        htmlMessage = htmlMessage.replace("@verificationLink", verificationLink);
        sendMail(verificationMail, toEmail, verificationSubject, htmlMessage);

    }

}
