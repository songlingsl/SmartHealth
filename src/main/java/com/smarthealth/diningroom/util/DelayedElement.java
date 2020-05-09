package com.smarthealth.diningroom.util;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayedElement implements Delayed {
    private final long delay; //延迟时间
    private final long expire;  //到期时间
    private final Integer plateId;   //餐盘id
    private final long now; //创建时间

    public DelayedElement(long delay, Integer plateId) {
        this.delay = delay;
        this.plateId = plateId;
        expire = System.currentTimeMillis() + delay;    //到期时间 = 当前时间+延迟时间
        now = System.currentTimeMillis();
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.expire-System.currentTimeMillis(),TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        return (int) (this.getDelay(TimeUnit.MILLISECONDS) -o.getDelay(TimeUnit.MILLISECONDS));
    }

    @Override
    public boolean equals(Object obj) {
        final DelayedElement other = (DelayedElement) obj;
        if (this.plateId.equals(other.plateId)) {//餐盘id相同认为同一个消息，需要更新发送时间
            return true;
        }
        return false;
    }
}
