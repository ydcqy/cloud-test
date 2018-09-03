package com.ydcqy.cloud.services.talk;

import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.nio.channels.spi.SelectorProvider;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;

/**
 * @author xiaoyu
 */
@Slf4j
public class Test {

    public static void main(String[] args) throws Exception {
//        ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();
//        System.out.println(Arrays.asList(resourceResolver.getResources("mapper/*")).get(0).getURL());
//        \org\springframework\boot\logging\logback\defaults.xml
        InputStream systemResourceAsStream = ClassLoader.getSystemResourceAsStream("/");

        System.out.println(ClassLoader.getSystemResource("org/springframework/boot/logging/logback/defaults.xml"));
        System.out.println(Thread.currentThread().getContextClassLoader());
        System.out.println(SelectorProvider.provider());

        FileSystem fileSystem = FileSystems.getDefault();
        System.out.println(fileSystem.getClass());
        String separator = fileSystem.getSeparator();

        System.out.println(separator);
        System.out.println(fileSystem.getRootDirectories());
        Path path = Paths.get("E:/");
        System.out.println(path);

        WatchService watchService = FileSystems.getDefault().newWatchService();
        Paths.get("E:/").register(watchService
                , StandardWatchEventKinds.ENTRY_MODIFY
                , StandardWatchEventKinds.ENTRY_CREATE
//                , StandardWatchEventKinds.ENTRY_DELETE
//                , StandardWatchEventKinds.OVERFLOW
        );
        for (; ; ) {
            WatchKey watchKey = watchService.take();
            System.out.println("事件");
            List<WatchEvent<?>> watchEvents = watchKey.pollEvents();
            for (WatchEvent<?> watchEvent : watchEvents) {
                System.out.println(watchEvent.kind());
                System.out.println(watchEvent.context() );
            }
            watchKey.reset();
        }
    }

}
