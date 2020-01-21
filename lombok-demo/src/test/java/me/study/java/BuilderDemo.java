package me.study.java;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import org.junit.Test;
import org.slf4j.Logger;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class BuilderDemo {


    private static final Logger logger = getLogger(BuilderDemo.class);

    @Test
    public void testBuilder() {

        BuilderEntry entry = BuilderEntry.builder().value("abc").name("bbb").name("ccc").build();
        logger.info("builder entry value:{}", entry.getValue());
        logger.info("builder entry names:{}", entry.getNames());
        logger.info("builder entry default value:{}", entry.getDefaultValue());


    }


}

@Builder
class BuilderEntry {


    @Builder.Default
    @Getter
    private String defaultValue = "default";

    @Getter
    private String value;

    @Singular
    @Getter
    private List<String> names;
}

