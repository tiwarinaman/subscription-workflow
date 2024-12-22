package com.naman.subscription.starter;

import com.naman.subscription.activities.SubscriptionActivitiesImpl;
import com.naman.subscription.config.TemporalConfig;
import com.naman.subscription.workflow.SubscriptionWorkflowImpl;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;

public class SubscriptionWorkerFactory {
    public static void startWorker() {

        WorkerFactory factory = WorkerFactory.newInstance(TemporalConfig.getClient());
        Worker worker = factory.newWorker("subscriptionTaskQueue");

        worker.registerWorkflowImplementationTypes(SubscriptionWorkflowImpl.class);
        worker.registerActivitiesImplementations(new SubscriptionActivitiesImpl());

        factory.start();
    }
}
