package com.email.controller;

import com.email.model.EmailRequest;
import com.email.model.EmailResponse;
import com.email.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
//becoz backend server is running on another port and frontend angular in running on another port
public class EmailController {
    @Autowired
    private EmailService emailService;
    @GetMapping("/welcome")
    public String welcome(){
        return "hello this is my email apio";
    }
    //api to send email
    @RequestMapping(value="/sendemail",method = RequestMethod.POST)
    public ResponseEntity<?> sendEmail(@RequestBody EmailRequest request){
        boolean result = this.emailService.sendEmailWithAttachment(request.getMessage(), request.getSubject(), request.getTo());
        if(result)
        {
            return ResponseEntity.ok(new EmailResponse("Email sent succesfully"));
        }
        else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new EmailResponse("Email is not sent "));

        }


    }

}
