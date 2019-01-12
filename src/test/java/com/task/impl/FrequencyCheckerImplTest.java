package com.task.impl;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.task.FrequencyChecker;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;

class FrequencyCheckerImplTest {

  private FrequencyChecker frequencyChecker;

  @Test
  void givenMessagesPerMinute_whenSendMessagesWithAllowedRate_thenSucceed() {

    //Given
    int messagesPerMinute = 5000;
    long allowedTimeInterval = 60_000 / messagesPerMinute + 1;
    frequencyChecker = new FrequencyCheckerImpl(messagesPerMinute);

    //When
    IntStream.range(0, 100).forEach(message -> {
      assertTrue(frequencyChecker.isAllowed());
      try {
        Thread.sleep(allowedTimeInterval);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });
  }
}
