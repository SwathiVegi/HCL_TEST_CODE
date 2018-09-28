package com.springmvc.queue;

import javax.jms.JMSException;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

import com.springmvc.model.Employee;

 
@Component
public class MessageReceiver {
    static final Logger LOG = LoggerFactory.getLogger(MessageReceiver.class);
 
    private static final String EMP_RESPONSE_QUEUE = "emp-response-queue";
     
 
     
     
    @JmsListener(destination = EMP_RESPONSE_QUEUE)
    public void receiveMessage(final Message<Employee> message) throws JMSException {
        LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
        MessageHeaders headers =  message.getHeaders();
        LOG.info("Application : headers received : {}", headers);
         
        LOG.info("Application : response received : {}",message.getPayload());
        LOG.info("Perform some JMS Service call here");
         
        LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }
}