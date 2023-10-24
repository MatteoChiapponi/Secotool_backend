package com.group2.secotool_app.bussiness.facade.Impl;

import com.group2.secotool_app.bussiness.facade.IEmailFacade;
import com.group2.secotool_app.bussiness.service.IEmailService;
import com.group2.secotool_app.model.dto.request.ResendRegistrationEmailRequestDto;
import com.group2.secotool_app.model.dto.request.UserRegistrationRequestDto;
import com.group2.secotool_app.model.entity.Product;
import com.group2.secotool_app.model.entity.User;
import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.activation.FileDataSource;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMultipart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class EmailFacadeImpl implements IEmailFacade {

    private final IEmailService emailService;


    @Override
    public void singUpNotification(UserRegistrationRequestDto user) throws MessagingException {
        singUpNotification(new ResendRegistrationEmailRequestDto(user.firstName(),user.lastName(),user.username()));
    }

    @Override
    public void rentalNotification(Product product, User user, LocalDate startDate, LocalDate endDate, Double totalPrice) throws MessagingException {
        var subject = "¡Tu solicitud de alquiler esta confirmada!";
        var name = user.getFirstName().substring(0, 1).toUpperCase() + user.getFirstName().substring(1);
        String body = String.format(
                "<html>" +
                "<body>" +
                "<p style='margin: 0;'>¡Gracias por elegirnos, %s!</p>" +
                "<p style='font-weight: bold'>" +
                "El alquiler de la herramienta está confirmado. A continuación, te proporcionamos los detalles:" +
                "</p>" +
                "<ul style='list-style-type: none; margin: 35px 0; padding: 0;'>" +
                "<li>Herramienta: <b>%s</b></li>" +
                "<li>Desde: <b>%s</b></li>" +
                "<li>Hasta: <b>%s</b></li>" +
                "<li>Pago total: <b>$%s</b></li>" +
                "</ul>" +
                "<p style='margin: 0;'><img style='width: 135px;' src='https://0823grupo2proyectointegrador.s3.amazonaws.com/logo.png' alt='Logo de la empresa'></p>" +
                "<p style='margin: 0;'>Construye fácil y rápido con SecoTool.</p>" +
                "<p style='margin: 0;'>secotool@gmail.com</p>" +
                "</body>" +
                "</html>",name,product.getName(),startDate,endDate,totalPrice);

        //contenido del mensaje completo (html + imagenes que se utilizan dentro del html)
        MimeMultipart messageContent = new MimeMultipart("related");

        //html
        MimeBodyPart html = new MimeBodyPart();
        html.setContent(body, "text/html");

        // company logo
        MimeBodyPart image = new MimeBodyPart();
        DataSource logo = new FileDataSource("logo.png");
        image.setDataHandler(new DataHandler(logo));
        image.setHeader("Content-ID", "<image>");


        // se agrega el contenido al contenedor del mensaje
        messageContent.addBodyPart(html);
        //messageContent.addBodyPart(image);

        emailService.sendHtmlEmail(user.getUsername(), subject, body, messageContent);
    }

    @Override
    public void singUpNotification(ResendRegistrationEmailRequestDto registrationEmailRequestDto) throws MessagingException {
        var firstname = registrationEmailRequestDto.firstName().substring(0, 1).toUpperCase() + registrationEmailRequestDto.firstName().substring(1);
        var lastname = registrationEmailRequestDto.lastName().substring(0, 1).toUpperCase() + registrationEmailRequestDto.lastName().substring(1);
        var username = registrationEmailRequestDto.username();
        var subject = "¡regristro exitoso!";

        var body = "<html><body style='font-family: 'Poppins', sans-serif; background-color: rgb(234, 234, 234); display: flex; justify-content: center; margin: 0px;'><div style = display: flex; flex-direction: column; background-color: rgb(247, 249, 251); max-width: 100%; height: 100vh; justify-content: space-between; '><div> <div style=' height: 70px; background-color: rgb(61, 61, 61); display: flex; padding-left: 16px;'><img src='https://0823grupo2proyectointegrador.s3.amazonaws.com/logo-white.png' alt=' style='width: 400px; padding-left: 16px; max-width: 100%;' /></div><div style='padding-left: 16px; padding-right: 16px; padding-top: 32px; color: #3d3d3d;'><p style='margin-bottom: 0'>Hola <span>"+firstname +" "+ lastname +"</span>.</p><p style='margin-bottom: 0'>¡Tu cuenta en SecoTool fue creada exitosamente!</p><p>El email con el que creaste la cuenta fue <strong>"+username+"</strong>.</p><p style='margin-bottom: 0'>Puedes hacer clic<strong><a style='color: #6d6de1; text-decoration: underline;' href='http://0823grupo2proyectointegrador.s3-website-us-east-1.amazonaws.com/auth/login'> aquí</a></strong> para iniciar sesión y realizar el alquiler de las mejores herramientas que SecoTool tiene para ofrecerte.</p></div></div><div style='padding-top: 40px; padding-left: 16px; padding-bottom: 16px;'><img src='https://0823grupo2proyectointegrador.s3.amazonaws.com/logo.png' alt='Logo SecoTool' /><p style='font-size: 13px; margin: 0px; color: #939393'>Construye fácil y rápido</p><span style='font-size: 13px; color: #6d6de1'><a href='mailto:secotool@gmail.com'>secotool@gmail.com</a></span></div></div></body></html>";

        //contenido del mensaje completo (html + imagenes que se utilizan dentro del html)
        MimeMultipart messageContent = new MimeMultipart("related");

        //html
        MimeBodyPart html = new MimeBodyPart();
        html.setContent(body, "text/html");

        // company black logo
        MimeBodyPart image = new MimeBodyPart();
        DataSource logo = new FileDataSource("logo.png");
        image.setDataHandler(new DataHandler(logo));
        image.setHeader("Content-ID", "<logo>");

        // company white logo
        MimeBodyPart image2 = new MimeBodyPart();
        DataSource whiteLogo = new FileDataSource("logo-white.png");
        image2.setDataHandler(new DataHandler(whiteLogo));
        image2.setHeader("Content-ID", "<white-logo>");

        // se agrega el contenido al contenedor del mensaje
        messageContent.addBodyPart(html);
        // messageContent.addBodyPart(image);
        //messageContent.addBodyPart(image2);

        emailService.sendHtmlEmail(registrationEmailRequestDto.username(), subject,body, messageContent);
    }

}
