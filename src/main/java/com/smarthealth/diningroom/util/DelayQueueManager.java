package com.smarthealth.diningroom.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.DelayQueue;
@Slf4j
@Component
public class DelayQueueManager implements CommandLineRunner {
    private DelayQueue<DelayedElement> delayQueue = new DelayQueue();
    @Override
    public void run(String... args) throws Exception {
        new Thread(()->{
            while (true) {
                try {
                    System.out.println("延迟人物"+ delayQueue.take());
                } catch (InterruptedException e) {
                    continue;
                }
            }
        }).start();

    }

    public void put(Integer plateId) {
        DelayedElement element = new DelayedElement(10000,plateId);
        if(delayQueue.contains(element)){
            delayQueue.remove(element);
            log.info("有相同plateId移除");
        }
        delayQueue.put(element);


    }
}
