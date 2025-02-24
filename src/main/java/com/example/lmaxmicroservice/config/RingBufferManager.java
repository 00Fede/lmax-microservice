package com.example.lmaxmicroservice.config;

import org.springframework.context.annotation.Configuration;

import org.springframework.context.annotation.Bean;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.example.lmaxmicroservice.event.LmaxEventFactory;
import com.example.lmaxmicroservice.event.LmaxEventHandler;
import com.example.lmaxmicroservice.event.Event;
import com.lmax.disruptor.SleepingWaitStrategy;
import com.lmax.disruptor.dsl.ProducerType;
import com.lmax.disruptor.util.DaemonThreadFactory;


@Configuration
public class RingBufferManager {
    @Bean("ringBuffer")
    public RingBuffer<Event> EventRingBuffer(){
        LmaxEventFactory eventFactory = new LmaxEventFactory();
        int bufferSize = 1024;
        int threadPoolSize = 200; // Define the size of the thread pool
        Disruptor<Event> disruptor = new Disruptor<>(eventFactory, bufferSize, DaemonThreadFactory.INSTANCE, ProducerType.SINGLE, new SleepingWaitStrategy());
        disruptor.handleEventsWith(new LmaxEventHandler());
        RingBuffer<Event> ringBuffer = disruptor.start();
        return ringBuffer;
    }
}
