package com.example.demo.controller;

public class TaskResult<R> {
    private final TestResultType testResultType;
    private final R ReturnValue;
    private final String Reason="";

    public TaskResult(TestResultType testResultType, R returnValue, String reason) {
        super();
        this.testResultType = testResultType;
        ReturnValue = returnValue;
    }

    public TaskResult(TestResultType testResultType, R returnValue) {
        super();
        this.testResultType = testResultType;
        ReturnValue = returnValue;
    }

    public TestResultType getTestResultType() {
        return testResultType;
    }

    public R getReturnValue() {
        return ReturnValue;
    }

    public String getReason() {
        return Reason;
    }
}
