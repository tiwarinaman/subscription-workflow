package com.naman.subscription.workflow;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface SubscriptionWorkflow {

    @WorkflowMethod
    void manageSubscription(String userId);

}
