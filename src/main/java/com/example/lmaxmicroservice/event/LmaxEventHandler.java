package com.example.lmaxmicroservice.event;

import com.lmax.disruptor.EventHandler;
import lombok.extern.slf4j.Slf4j;

// Business logic handler
@Slf4j
public class LmaxEventHandler implements EventHandler<Event> {

    @Override
    public void onEvent(Event event, long sequence, boolean endOfBatch) {
        try{
            log.info("consume message start");
            event.setValue(event.getValue() * 2); // Dummy operation
            log.info("the message is: " + event.getValue());
        } catch (Exception e) {
            log.info("Error: " + e.getMessage());
            e.printStackTrace();
            
        }
        log.info("consume message finished");
    }
}
