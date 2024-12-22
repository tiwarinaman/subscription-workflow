package com.naman.subscription.starter;

import com.naman.subscription.config.TemporalConfig;
import com.naman.subscription.workflow.SubscriptionWorkflow;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;

public class SubscriptionStarter {
    public static void main(String[] args) {

        // Connect to the Temporal service (default to localhost:7233)
        WorkflowServiceStubs service = WorkflowServiceStubs.newInstance();

        // Create a WorkflowClient
        WorkflowClient client = WorkflowClient.newInstance(service);

        // Define workflow options
        WorkflowOptions options = WorkflowOptions.newBuilder()
                .setTaskQueue("SubscriptionTaskQueue") // Task queue for the worker
                .setWorkflowId("subscription_workflow_id") // Unique workflow ID
                .build();

        // Create a Workflow Stub
        SubscriptionWorkflow workflow = client.newWorkflowStub(SubscriptionWorkflow.class, options);

        // Start the workflow
        System.out.println("Starting the subscription workflow...");
        workflow.manageSubscription("user-123");

        System.out.println("Workflow started. Workflow ID: subscription_workflow_id");
    }
}
