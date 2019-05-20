package com.lambda.essentialism.service;

import com.lambda.essentialism.model.Message;
import com.lambda.essentialism.repo.MessageRepo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "messageService")
@Transactional
public class MessageServiceImpl implements MessageService {
  @Autowired
  private MessageRepo messageRepo;

  @Override
  public List<Message> findAll() {
    List<Message> list = new ArrayList<>();
    messageRepo.findAll().iterator().forEachRemaining(list::add);
    return list;
  }

  @Override
  public Message save(Message message) {
    Message newMessage = new Message();

    newMessage.setName(message.getName());
    newMessage.setEmail(message.getEmail());
    newMessage.setBody(message.getBody());
    newMessage.setSeen();

    return messageRepo.save(newMessage);
  }

  @Override
  public Message findMsgById(long id) {
    return messageRepo.findById(id).orElseThrow(
      () -> new EntityNotFoundException("Message: " + id + " not found.")
    );
  }

  @Override
  public Message read(long id) {
    Message msg = findMsgById(id);
    msg.markAsRead();

    return messageRepo.save(msg);
  }

}

