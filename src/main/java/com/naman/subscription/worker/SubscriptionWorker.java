package com.naman.subscription.worker;

import com.naman.subscription.activities.SubscriptionActivitiesImpl;
import com.naman.subscription.workflow.SubscriptionWorkflowImpl;
import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;

public class SubscriptionWorker {
    public static void main(String[] args) {
        // Connect to the Temporal service (default to localhost:7233)
        WorkflowServiceStubs service = WorkflowServiceStubs.newInstance();

        // Create a WorkflowClient to communicate with the Temporal server
        WorkflowClient client = WorkflowClient.newInstance(service);

        // Create a WorkerFactory
        WorkerFactory factory = WorkerFactory.newInstance(client);

        // Create a Worker for the "SubscriptionTaskQueue"
        Worker worker = factory.newWorker("SubscriptionTaskQueue");

        // Register the Workflow implementation class
        worker.registerWorkflowImplementationTypes(SubscriptionWorkflowImpl.class);

        // Register the Activity implementation class
        worker.registerActivitiesImplementations(new SubscriptionActivitiesImpl());

        // Start the Worker
        factory.start();

        System.out.println("Worker started and listening to the task queue...");
    }
}
