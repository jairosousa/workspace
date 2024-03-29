package com.jnsdev.springcompletablefeatureexceptionhandling.utils;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @Autor Jairo Nascimento
 * @Created 30/12/2022 - 17:01
 */
@Slf4j
public class SleepUtils {
    @SneakyThrows
    public static void loadingSimulator(int sec) {
        log.info("Sleeping for {} sec.", sec);
        String bar = " ".repeat(sec);
        for (int i = 0; i < sec; i++) {
            bar = bar.replaceFirst(" ", "=");
            log.info("[" + bar + "]");
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
