package com.naman.subscription.workflow;

import com.naman.subscription.activities.SubscriptionActivities;
import com.naman.subscription.config.WorkflowOptionsBuilder;
import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Workflow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class SubscriptionWorkflowImpl implements SubscriptionWorkflow {

    private static final Logger logger = LoggerFactory.getLogger(SubscriptionWorkflowImpl.class);

    private final SubscriptionActivities activities = Workflow.newActivityStub(
            SubscriptionActivities.class,
            WorkflowOptionsBuilder.defaultActivityOptions() // Use the centralized activity options
    );

    private int billingPeriod = 0;
    private double chargeAmount = 10.0;

    @Override
    public void manageSubscription(String userId) {

        logger.info("Starting subscription workflow for user: {}", userId);

        activities.sendWelcomeEmail(userId);
        activities.startFreeTrail(userId);

        boolean trialCancelled = Workflow.await(Duration.ofDays(7), () -> Workflow.sideEffect(Boolean.class, () -> false));

        if (trialCancelled) {
            logger.info("Trial cancelled for user: {}", userId);
            activities.sendTrailCancellationEmail(userId);
            return;
        }

        while (billingPeriod < 36) {
            activities.chargeCustomer(userId, chargeAmount);
            billingPeriod++;
            Workflow.sleep(Duration.ofDays(30));

            boolean subscriptionCancelled = Workflow.sideEffect(Boolean.class, () -> false);
            if (subscriptionCancelled) {
                logger.info("Subscription cancelled during billing period for user: {}", userId);
                activities.sendSubscriptionCancellationEmail(userId);
                return;
            }
        }

        activities.sendSubscriptionEndedEmail(userId);
        logger.info("Subscription ended for user: {}", userId);
    }

}
