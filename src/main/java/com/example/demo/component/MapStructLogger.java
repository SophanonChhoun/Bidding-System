package com.example.demo.component;

import org.mapstruct.TargetType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MapStructLogger {
    private final Logger logger = LoggerFactory.getLogger(MapStructLogger.class);

    public <T> T logMapping(Object source, @TargetType Class<T> targetType) {
        logger.info("Mapping {} to {}", source, targetType);
        return null;
    }
}
