package io.fluxzero.training.meepleverse.common;

import io.fluxzero.common.MessageType;
import io.fluxzero.sdk.Fluxzero;
import io.fluxzero.sdk.common.exception.TechnicalException;
import io.fluxzero.sdk.tracking.Consumer;
import io.fluxzero.sdk.tracking.handling.HandleError;
import io.fluxzero.sdk.tracking.handling.Trigger;
import io.fluxzero.training.meepleverse.payments.api.ValidatePayment;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Component
@Consumer(name = "errorHandler", minIndex = 0)
public class ErrorHandler {

    @HandleError(allowedClasses = TechnicalException.class)
    public void handle(@Trigger(messageType = MessageType.COMMAND) ValidatePayment command) {
        Fluxzero.scheduleCommand(command, Duration.of(1, ChronoUnit.MINUTES));
    }
}
