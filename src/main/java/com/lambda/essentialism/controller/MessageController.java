package com.lambda.essentialism.controller;

import com.lambda.essentialism.model.Message;
import com.lambda.essentialism.service.MessageService;

import io.swagger.annotations.ApiParam;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class MessageController {
  @Autowired
  private MessageService messageService;

  // GET MESSAGES

  // /api/messages
  @RequestMapping("/messages")
  @GetMapping(value = "", produces = { "application/json" })
  public ResponseEntity<?> listAllMessages() {
    List<Message> myMessages = messageService.findAll();
    return new ResponseEntity<>(myMessages, HttpStatus.OK);
  }

  // ADD NEW MESSAGE

  // POST /api/message
  @PostMapping(
    value = "/messages/new",
    consumes = { "application/json"
    },
    produces = { "application/json"
    }
  )
  public ResponseEntity<?> sendNewMessage(
    @RequestBody
    @Valid
    Message newMessage
  )
    throws
      URISyntaxException {
    newMessage = messageService.save(newMessage);
    HttpHeaders resHeaders = new HttpHeaders();
    URI newMsgURI = ServletUriComponentsBuilder.fromCurrentRequestUri(

    ).buildAndExpand(newMessage.getName()).toUri();

    resHeaders.setLocation(newMsgURI);

    return new ResponseEntity<>(null, resHeaders, HttpStatus.CREATED);
  }

  @GetMapping(value = "/messages/{msgId}", produces = { "application/json" })
  public ResponseEntity<?> getMessageById(
    @ApiParam(value = "msgId")
    @PathVariable
    Long msgId
  ) {
    Message msg = messageService.findMsgById(msgId);
    messageService.read(msgId);
    return new ResponseEntity<>(msg, HttpStatus.OK);
  }

}
