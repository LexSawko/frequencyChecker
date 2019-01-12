package com.task.impl;

import com.task.FrequencyChecker;

import java.util.concurrent.atomic.AtomicLong;


public class FrequencyCheckerImpl implements FrequencyChecker {

  private final double MIN_ALLOWED_TIME_INTEVAL;  // minimum allowed time interval between messages in nanoseconds
  private AtomicLong lastMessageTs;

  public FrequencyCheckerImpl(int messagesPerMinute) {
    MIN_ALLOWED_TIME_INTEVAL = 60_000D / messagesPerMinute;
    lastMessageTs = new AtomicLong(0);
  }

  public boolean isAllowed() {
    long currentMessageTs = System.currentTimeMillis();
    return (currentMessageTs - lastMessageTs.getAndSet(currentMessageTs))
        >= MIN_ALLOWED_TIME_INTEVAL;
  }
}

