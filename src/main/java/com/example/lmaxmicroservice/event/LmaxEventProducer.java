package com.example.lmaxmicroservice.event;


import com.lmax.disruptor.RingBuffer;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import com.lmax.disruptor.EventTranslator;


@Component
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
        ringBuffer.publishEvent(eventTranslator);
    }
}