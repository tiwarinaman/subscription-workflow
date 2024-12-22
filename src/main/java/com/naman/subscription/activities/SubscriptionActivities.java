package com.naman.subscription.activities;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface SubscriptionActivities {

    @ActivityMethod
    void sendWelcomeEmail(String userId);

    @ActivityMethod
    void startFreeTrail(String userId);

    @ActivityMethod
    void sendTrailCancellationEmail(String userId);

    @ActivityMethod
    void chargeCustomer(String userId, double amount);

    @ActivityMethod
    void sendSubscriptionCancellationEmail(String userId);

    @ActivityMethod
    void sendSubscriptionEndedEmail(String userId);

}
