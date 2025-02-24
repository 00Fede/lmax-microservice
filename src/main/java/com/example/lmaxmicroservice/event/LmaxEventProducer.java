package com.example.lmaxmicroservice.event;


import com.lmax.disruptor.RingBuffer;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import com.lmax.disruptor.EventTranslator;
import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
public class LmaxEventProducer {

    @Autowired
    RingBuffer<Event> ringBuffer;
    
    public synchronized void produce(int value){
        EventTranslator<Event> eventTranslator = new EventTranslator<Event>(){
            @Override
            public void translateTo(Event event, long sequence) {
                event.setValue(value);
            }
        };
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error("Thread was interrupted", e);
        }
        ringBuffer.publishEvent(eventTranslator);
    }
}