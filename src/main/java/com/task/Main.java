package com.task;

import com.task.impl.FrequencyCheckerImpl;

import java.util.stream.IntStream;

public class Main {

  private static final int MESSAGES_PER_MINUTE = 3000000;
  private static final int THREADS_AMOUNT = 3;
  private static final FrequencyChecker frequencyChecker
      = new FrequencyCheckerImpl(MESSAGES_PER_MINUTE);

  public static void main(String[] args) {
    IntStream.range(0, THREADS_AMOUNT).forEach(thread -> startAndSendMessages());
  }

  private static void startAndSendMessages() {
    new Thread(() -> {
      while (true) {
        if (frequencyChecker.isAllowed()) {
          System.out.printf("%s, message have been PROCESSED\n", Thread.currentThread().getName());
        }
      }
    }).start();
  }
}
