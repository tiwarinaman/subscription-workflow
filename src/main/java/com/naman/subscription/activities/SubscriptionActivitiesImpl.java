package com.naman.subscription.activities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SubscriptionActivitiesImpl implements SubscriptionActivities {

    private static final Logger logger = LoggerFactory.getLogger(SubscriptionActivitiesImpl.class);


    @Override
    public void sendWelcomeEmail(String userId) {
        logger.info("Welcome email send to user {}", userId);
    }

    @Override
    public void startFreeTrail(String userId) {
        logger.info("Free trial started for user: {}", userId);
    }

    @Override
    public void sendTrailCancellationEmail(String userId) {
        logger.info("Trial cancellation email sent to user: {}", userId);
    }

    @Override
    public void chargeCustomer(String userId, double amount) {
        logger.info("Charged user: {} an amount of: {}", userId, amount);
    }

    @Override
    public void sendSubscriptionCancellationEmail(String userId) {
        logger.info("Subscription cancellation email sent to user: {}", userId);
    }

    @Override
    public void sendSubscriptionEndedEmail(String userId) {
        logger.info("Subscription ended email sent to user: {}", userId);
    }
}
