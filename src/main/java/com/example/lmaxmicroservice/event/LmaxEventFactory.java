package com.example.lmaxmicroservice.event;

import com.lmax.disruptor.EventFactory;

// Event factory
public class LmaxEventFactory implements EventFactory<Event> {

    @Override
    public Event newInstance() { return new Event(); }
}
