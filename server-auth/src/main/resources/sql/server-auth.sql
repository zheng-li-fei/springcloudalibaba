CREATE TABLE `users`
(
    `id`               bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `user_id`          varchar(32) COLLATE utf8mb4_bin NOT NULL COMMENT '用户id',
    `user_name`        varchar(16) COLLATE utf8mb4_bin          DEFAULT NULL COMMENT '用户名',
    `nike_name`        varchar(16) COLLATE utf8mb4_bin          DEFAULT NULL COMMENT '昵称',
    `phone`            varchar(16) COLLATE utf8mb4_bin          DEFAULT NULL COMMENT '手机号',
    `user_pwd`         varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT '用户密码',
    `platform_type`    int(2) NOT NULL COMMENT '平台类型: 10-商城, 20-工行',
    `last_login_ip`    varchar(32) COLLATE utf8mb4_bin          DEFAULT NULL COMMENT '上次登录ip',
    `last_login_time`  datetime                                 DEFAULT NULL COMMENT '上次登录时间',
    `create_time`      datetime                                 DEFAULT NULL COMMENT '创建时间',
    `create_user_id`   varchar(32) COLLATE utf8mb4_bin          DEFAULT NULL COMMENT '创建用户编号',
    `create_user_name` varchar(32) COLLATE utf8mb4_bin          DEFAULT NULL COMMENT '创建用户名',
    `update_time`      datetime                                 DEFAULT NULL COMMENT '修改时间',
    `update_user_id`   varchar(32) COLLATE utf8mb4_bin          DEFAULT NULL COMMENT '修改用户编号',
    `update_user_name` varchar(32) COLLATE utf8mb4_bin          DEFAULT NULL COMMENT '修改用户名',
    `version`          int(11) DEFAULT NULL COMMENT '版本号',
    `deleted`          bit(1)                          NOT NULL DEFAULT b'0' COMMENT '是否已删除',
    PRIMARY KEY (`id`),
    KEY `idx_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='用户信息';