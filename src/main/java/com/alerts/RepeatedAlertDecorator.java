package com.alerts;

public class RepeatedAlertDecorator extends AlertDecorator {

    public RepeatedAlertDecorator(Alert decoratedAlert) {
        super(decoratedAlert);
    }

    @Override
    public String getCondition() {
        return decoratedAlert.getCondition() + " (Repeated Alert)";
    }
}