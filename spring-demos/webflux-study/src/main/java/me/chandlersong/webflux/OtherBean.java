package me.chandlersong.webflux;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OtherBean {
    public OtherBean() {
        log.info("other bean has been loaded");
    }
}
