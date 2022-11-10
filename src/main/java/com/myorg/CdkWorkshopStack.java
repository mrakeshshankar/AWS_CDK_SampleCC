package com.myorg;

import java.io.IOException;

import software.amazon.awscdk.CfnOutput;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.services.connect.CfnContactFlow;
import software.amazon.awscdk.services.connect.CfnInstance;
import software.amazon.awscdk.services.connect.CfnPhoneNumber;
import software.constructs.Construct;;

public class CdkWorkshopStack extends Stack {

        private CfnInstance connect = null;

        public CfnInstance getConnect() {
                return connect;
        }

        public CdkWorkshopStack(final Construct parent, final String id) throws IOException {
                this(parent, id, null);
        }

        public CdkWorkshopStack(final Construct parent, final String id, final StackProps props) throws IOException {
                super(parent, id, props);

                // Create Connect Instace
                this.connect = new SampleConnectInstance(this, "SampleContactCenterInstance")
                                .getConnectInstance();

                // Create hours of operation
                SampleBusinessHoursOfOperations hop = new SampleBusinessHoursOfOperations(this, "SampleBusinessHoursOfOperation", connect);

                // List queues
                // new ListQueueCustomResource(this, "ListQueueCustomResource", connect, null);

                // create a reservation queue.
                QueueCustomResource queue = new QueueCustomResource(this, "QueueCustomResource", connect, hop.getHoursOfOperation());

                // Create Routing Profile
                new RoutingProfileCustomResource(this, "RoutingProfileCustomResource", connect, queue.getQueueID());

                // Add Connect flow to the intance
                CfnContactFlow cfnContactFlow = new SampleConnectFlow(this, "SampleConnectFlow", connect, "src/resources/flows/SampleFlowWithQueueReference.json", queue.getQueueArn()).getInstance();
                

                // Add Connect flow2 to the intance
                // CfnContactFlow cfnContactFlow1 = new SampleConnectFlow(this, "SampleConnectFlowLatest1", connect, "src/resources/flows/SampleFlow1.json", queue.getQueueArn()).getInstance();


                // Claim a phone number
                CfnPhoneNumber phoneNumber = new SampleContactCenterPhoneNumber(this, "SampleContactCenterPhoneNumber",
                                connect).getInstance();

                // Claim a phone number2
                CfnPhoneNumber phoneNumber1 = new SampleContactCenterPhoneNumber(this, "SampleContactCenterPhoneNumber1",
                                 connect).getInstance();


                // Associate Phone number with the Sample flow
                new PhoneAssociationCustomResource(this, "PhoneAssociationCustomResource",
                connect, cfnContactFlow, phoneNumber);

                // Associate Phone number with the Sample flow 2
                // new PhoneAssociationCustomResource(this, "PhoneAssociationCustomResource1",
                // connect, cfnContactFlow1, phoneNumber1);

                //CfnOutput.Builder.create(this,
                // "ConnectInstanceUrl").value(connect.getAtt("Url").toString()).build();
                

                CfnOutput.Builder.create(this, "phoneNumberOutput")
                                .value(phoneNumber.getAtt("Address").toString()).build();

                                // CfnOutput.Builder.create(this, "phoneNumberOutput1")
                                // .value(phoneNumber1.getAtt("Address").toString()).build();
                
        }

}
