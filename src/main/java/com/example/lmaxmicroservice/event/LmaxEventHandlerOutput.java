package com.example.lmaxmicroservice.event;

import com.lmax.disruptor.EventHandler;
import lombok.extern.slf4j.Slf4j;

// Output handler
@Slf4j
public class LmaxEventHandlerOutput implements EventHandler<Event> {

    @Override
    public void onEvent(Event event, long sequence, boolean endOfBatch) {
        try {
            log.info("Output message start");
            // Process the event and output the result
            log.info("Processed event value: " + event.getValue());
        } catch (Exception e) {
            log.error("Error processing event: " + e.getMessage(), e);
        }
        log.info("Output message finished");
    }
}
