package io.swagger.petstore.common;

import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.ObjectAssert;


public class ResponseAssertion {

    public static String[] timeFieldsToIgnore = {
            "approveTime", "createTime", "expiredTime",
            "finishedAt", "startedAt", "vinHash", "revokeTime"
    };
    private final Response targetResponse;

    //todo try to create and use kotlin extension function to assert responses
    public ResponseAssertion(Response targetResponse) {
        this.targetResponse = targetResponse;
    }

    public ResponseAssertion statusCodeIsEqualTo(ResponseExpectMessages.StatusCode expectedStatusCode) {
        var statusCodeAssertionMessage = new ResponseExpectMessages(targetResponse)
                .expectedStatuesCode(expectedStatusCode);

        Assertions.assertThat(targetResponse.statusCode())
                .withFailMessage(statusCodeAssertionMessage)
                .isEqualTo(expectedStatusCode.code);
        return this;
    }

    /**
     * Assert possibility response body binding to target class
     *
     * @return Binded response body to target object
     */
    public <T> T bindAs(Class<T> expectedClass) {
        T convertedObject;
        try {
            convertedObject = targetResponse.as(expectedClass);
        } catch (Exception ex) {
            var assertionMessage = new ResponseExpectMessages(targetResponse)
                    .expectedResponseBodyClass(expectedClass);
            throw new AssertionError(assertionMessage);
        }
        return convertedObject;
    }

    public ObjectAssert<Object> objectTOAssert(Class expectedClass) {
        return Assertions.assertThat(bindAs(expectedClass));
    }

    public <T> T[] bindAsListOf(Class<T[]> expectedClass) {
        return bindAs(expectedClass);
    }

    public void responseIsEqualTo(Object expectedObject) {
        var objectUnderTest = bindAs(expectedObject.getClass());

        Assertions.assertThat(objectUnderTest).isEqualTo(expectedObject);
    }

    public void responseIsEqualToObjectIgnoringTimeFields(Object expectedObject) {
        var objectUnderTest = bindAs(expectedObject.getClass());

        Assertions.assertThat(objectUnderTest)
                .isEqualToIgnoringGivenFields(expectedObject, timeFieldsToIgnore);
    }

    public void responseIsEmpty() {
        Assertions.assertThat(targetResponse.asString()).isEmpty();
    }

    public void responseIsEmptyArray() {
        Assertions.assertThat(targetResponse.asString()).isEqualTo("[]");
    }

}
