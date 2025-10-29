package org.sopt.util;

import java.util.concurrent.atomic.AtomicLong;

public class IdGenerator {
    private static final AtomicLong sequence = new AtomicLong(1);

    public static long nextId() {
        return sequence.getAndIncrement();
    }
}
