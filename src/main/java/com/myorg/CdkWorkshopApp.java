package com.myorg;

import java.io.IOException;

import software.amazon.awscdk.App;

public final class CdkWorkshopApp {
    public static void main(final String[] args) {
        App app = new App();

        try {
            CdkWorkshopStack loCdkWorkshopStack =  new CdkWorkshopStack(app, "CdkWorkshopStack");
            
            // create props for second stack
            // CdkContactFlowStackProps props = new CdkContactFlowStackProps(loCdkWorkshopStack.getConnect());
            CdkContactFlowStack loCdkContactFlowStack = new CdkContactFlowStack(app, "CdkContactFlowStack", null);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.exit(1);
        }

        app.synth();
    }
}
