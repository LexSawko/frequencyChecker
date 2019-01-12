package com.task.impl;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.task.FrequencyChecker;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;

class FrequencyCheckerImplTest {

  private FrequencyChecker frequencyChecker;

  @Test
  void givenMessagesPerMinute_whenSendMessagesWithAllowedRate_thenAllMessagesProcessed() {

    //Given
    int messagesPerMinute = 6500;
    double allowedTimeInterval = 60_000D / messagesPerMinute;
    frequencyChecker = new FrequencyCheckerImpl(messagesPerMinute);

    //When
    IntStream.range(0, 100).forEach(message -> {
      assertTrue(frequencyChecker.isAllowed());
      try {
        Thread.sleep((long) allowedTimeInterval + 1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });
  }
}
