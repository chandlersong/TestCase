package org.example.ai;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
public interface AiService {

    Integer PredictMnist(Integer req);

    String InfoGan(Integer number);
}
