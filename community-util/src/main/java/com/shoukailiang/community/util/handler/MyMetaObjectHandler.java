package com.shoukailiang.community.util.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author shoukailiang
 * @version 1.0
 * @date 2021/5/17 0:02
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        this.strictInsertFill(metaObject, "createDate", () -> LocalDateTime.now(), LocalDateTime.class); // 起始版本 3.3.3(推荐使用)
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        this.strictUpdateFill(metaObject, "updateDate", () -> LocalDateTime.now(), LocalDateTime.class); // 起始版本 3.3.3(推荐)

    }
}
