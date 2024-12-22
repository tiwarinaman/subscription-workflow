package com.naman.subscription.config;

import io.temporal.activity.ActivityOptions;
import io.temporal.common.RetryOptions;

import java.time.Duration;

public class WorkflowOptionsBuilder {

    public static ActivityOptions defaultActivityOptions() {
        return ActivityOptions.newBuilder()
                .setStartToCloseTimeout(Duration.ofMinutes(1)) // Set activity execution timeout
                .setRetryOptions(
                        RetryOptions.newBuilder()
                                .setMaximumAttempts(3) // Retry 3 times
                                .setInitialInterval(Duration.ofSeconds(1)) // Initial retry interval
                                .setBackoffCoefficient(2.0) // Exponential backoff
                                .setMaximumInterval(Duration.ofMinutes(1)) // Max interval for retries
                                .build()
                )
                .build();
    }
}
