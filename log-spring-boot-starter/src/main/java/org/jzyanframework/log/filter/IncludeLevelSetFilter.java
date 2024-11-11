package org.jzyanframework.log.filter;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.AbstractMatcherFilter;
import ch.qos.logback.core.spi.FilterReply;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 日志级别过滤器
 * </p>
 *
 * @author jzyan
 * @since 2023-02-23
 */
public class IncludeLevelSetFilter extends AbstractMatcherFilter<ILoggingEvent> {
    private String levels;

    private Set<Level> levelSet;

    @Override
    public FilterReply decide(ILoggingEvent event) {
        return levelSet.contains(event.getLevel()) ? onMatch : onMismatch;
    }

    public void setLevels(String levels) {
        this.levels = levels;
        this.levelSet = Arrays.stream(levels.split(","))
                .map(item -> Level.toLevel(item, Level.INFO)).collect(Collectors.toSet());
    }

    @Override
    public void start() {
        if (Objects.nonNull(this.levels)) {
            super.start();
        }
    }

}
