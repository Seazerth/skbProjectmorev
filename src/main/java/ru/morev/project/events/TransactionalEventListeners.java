package ru.morev.project.events;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.transaction.event.TransactionPhase;

@Slf4j
@Component
public class TransactionalEventListeners {

    @EventListener
    public void handleSyncEvent(Object event) {
        log.info("🟢 Обычный EventListener: событие = {}", event);
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleTransactionalEvent(Object event) {
        log.info("✅ TransactionalEventListener (после коммита): {}", event);
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void skipEvent(Object event) {
        log.info("⛔ TransactionalEventListener (до коммита): {}", event);
    }
}
