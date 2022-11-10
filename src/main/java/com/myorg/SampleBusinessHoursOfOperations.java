package com.myorg;

import java.util.List;

import software.amazon.awscdk.CfnTag;
import software.amazon.awscdk.services.connect.CfnHoursOfOperation;
import software.amazon.awscdk.services.connect.CfnInstance;
import software.amazon.awscdk.services.connect.CfnHoursOfOperation.HoursOfOperationConfigProperty;
import software.amazon.awscdk.services.connect.CfnHoursOfOperation.HoursOfOperationTimeSliceProperty;
import software.constructs.Construct;

public class SampleBusinessHoursOfOperations {

    public CfnHoursOfOperation hoursOfOperations = null;

    public SampleBusinessHoursOfOperations(Construct scope, String id, CfnInstance cfnInstance) {
        build(scope, id,cfnInstance);
    }

    private void build(Construct scope, String id, CfnInstance cfnInstance) {
        hoursOfOperations = CfnHoursOfOperation.Builder.create(scope, id)
                .config(List.of(HoursOfOperationConfigProperty.builder()
                        .day("MONDAY")
                        .endTime(HoursOfOperationTimeSliceProperty.builder()
                                .hours(17)
                                .minutes(00)
                                .build())
                        .startTime(HoursOfOperationTimeSliceProperty.builder()
                                .hours(8)
                                .minutes(00)
                                .build())
                        .build(),
                        HoursOfOperationConfigProperty.builder()
                                .day("TUESDAY")
                                .endTime(HoursOfOperationTimeSliceProperty.builder()
                                        .hours(17)
                                        .minutes(00)
                                        .build())
                                .startTime(HoursOfOperationTimeSliceProperty.builder()
                                        .hours(8)
                                        .minutes(00)
                                        .build())
                                .build(),
                        HoursOfOperationConfigProperty.builder()
                                .day("WEDNESDAY")
                                .endTime(HoursOfOperationTimeSliceProperty.builder()
                                        .hours(18)
                                        .minutes(00)
                                        .build())
                                .startTime(HoursOfOperationTimeSliceProperty.builder()
                                        .hours(8)
                                        .minutes(00)
                                        .build())
                                .build(),
                        HoursOfOperationConfigProperty.builder()
                                .day("THURSDAY")
                                .endTime(HoursOfOperationTimeSliceProperty.builder()
                                        .hours(18)
                                        .minutes(00)
                                        .build())
                                .startTime(HoursOfOperationTimeSliceProperty.builder()
                                        .hours(8)
                                        .minutes(00)
                                        .build())
                                .build(),
                        HoursOfOperationConfigProperty.builder()
                                .day("FRIDAY")
                                .endTime(HoursOfOperationTimeSliceProperty.builder()
                                        .hours(18)
                                        .minutes(00)
                                        .build())
                                .startTime(HoursOfOperationTimeSliceProperty.builder()
                                        .hours(8)
                                        .minutes(00)
                                        .build())
                                .build()))
                 .instanceArn(cfnInstance.getAtt("Arn").toString())
                //.instanceArn("arn:aws:connect:us-east-1:103485379488:instance/4a7b73e6-d96b-443f-93ef-3f07770ad65c")
                // .instanceArn("arn:aws:connect:::TestConnectInstance")
                .name("BusinessHours")
                .timeZone("Canada/Central")
                // the properties below are optional
                .description("hours of operation created using cdk")
                .tags(List.of(CfnTag.builder()
                .key("ContactCenterName")
                .value("Sample")
                        .build()))
                .build();
    }

    public CfnHoursOfOperation getHoursOfOperation(){
        return hoursOfOperations;
    }
}
