package com.example.lmaxmicroservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.example.lmaxmicroservice.event.LmaxEventFactory;
import com.example.lmaxmicroservice.event.LmaxEventHandler;
import com.example.lmaxmicroservice.event.Event;
import java.util.concurrent.Executors;
import com.lmax.disruptor.SleepingWaitStrategy;
import com.lmax.disruptor.dsl.ProducerType;


@Configuration
public class RingBufferManager {
    @Bean("ringBuffer")
    public RingBuffer<Event> EventRingBuffer(){
        LmaxEventFactory eventFactory = new LmaxEventFactory();
        int bufferSize = 1024;
        Disruptor<Event> disruptor = new Disruptor<>(eventFactory, bufferSize, Executors.newCachedThreadPool(), ProducerType.SINGLE, new SleepingWaitStrategy());
        disruptor.handleEventsWith(new LmaxEventHandler());
        RingBuffer<Event> ringBuffer = disruptor.start();
        return ringBuffer;
    }
}
