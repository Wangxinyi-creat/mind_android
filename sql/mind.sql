-- 分类
drop table if exists mind_category;
CREATE TABLE `mind_category`
(
    `id`          bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `title`       varchar(255)        NOT NULL COMMENT '标题',
    `pid`         bigint(20) unsigned NOT NULL COMMENT '父级ID',
    `tag`         varchar(255)                 DEFAULT NULL COMMENT '标签',
    `icon_url`    varchar(255)                 DEFAULT NULL COMMENT '图标链接',
    `sorted`      int(10) unsigned    NOT NULL DEFAULT '0' COMMENT '排序',
    `create_time` datetime            NOT NULL COMMENT '创建时间',
    `update_time` datetime                     DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='分类';
-- 幻灯片
drop table if exists mind_banner;
CREATE TABLE `mind_banner`
(
    `id`          bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `title`       varchar(255)                 DEFAULT NULL COMMENT '幻灯片标题',
    `picture_url` varchar(255)        NOT NULL COMMENT '图片地址',
    `sorted`      tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '排序',
    `status`      tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '是否展示',
    `create_time` datetime            NOT NULL COMMENT '创建时间',
    `update_time` datetime            NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='幻灯片表';

drop table if exists mind_article_comment;
CREATE TABLE `mind_article_comment`
(
    `id`          bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `article_id`  bigint(20) unsigned NOT NULL COMMENT '文章外键',
    `user_id`     bigint(20) unsigned Not NULL COMMENT '评论人外键',
    `content`     mediumtext COMMENT '评论内容',
    `create_time` datetime            NOT NULL COMMENT '创建时间',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='评论';

-- 文章表
CREATE TABLE mind_article
(
    `id`          BIGINT(20)   NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '文章ID',
    `category_id` BIGINT(20)   NOT NULL COMMENT '分类ID',
    `user_id`     BIGINT(20)   NULL COMMENT '用户ID',
    `title`       VARCHAR(60)  NOT NULL COMMENT '文章标题',
    `content`     TEXT         NULL COMMENT '文章内容',
    `isVideo`     INT(1)       NOT NULL DEFAULT 0 COMMENT '是否视频',
    `video_url`   VARCHAR(255) NULL     DEFAULT '' COMMENT '视频链接',
    `views`       INT(20)      NULL     DEFAULT 0 COMMENT '观看次数',
    `likes`       INT(20)      NULL     DEFAULT 0 COMMENT '点赞数',
    `comments`    INT(20)      NULL     DEFAULT 0 COMMENT '评论数',
    `anonymous`   INT(1)       NULL     DEFAULT 0 COMMENT '是否匿名',
    `create_by`   VARCHAR(64)  NULL     DEFAULT '' COMMENT '创建者',
    `create_time` DATETIME     NULL     DEFAULT NULL COMMENT '创建时间',
    `update_by`   VARCHAR(64)  NULL     DEFAULT '' COMMENT '更新者',
    `update_time` DATETIME     NULL     DEFAULT NULL COMMENT '更新时间'
)
    COMMENT ='文章表'
    COLLATE = 'utf8mb4_general_ci'
    ENGINE = InnoDB
;

-- 心晴信箱表
CREATE TABLE mind_message_inbox
(
    `message_id`      BIGINT(20)  NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '信件ID',
    `sender_id`       BIGINT(20)  NOT NULL COMMENT '发送者ID',
    `recipient_id`    BIGINT(20)  NOT NULL COMMENT '接收者ID',
    `message_content` TEXT        NOT NULL COMMENT '信件内容',
    `reply`           INT(1)      NOT NULL DEFAULT 0 COMMENT '是否回复',
    `anonymous`       INT(1)      NOT NULL DEFAULT 0 COMMENT '是否匿名',
    `create_by`       VARCHAR(64) NULL     DEFAULT '' COMMENT '创建者',
    `create_time`     DATETIME    NULL     DEFAULT NULL COMMENT '创建时间',
    `update_by`       VARCHAR(64) NULL     DEFAULT '' COMMENT '更新者',
    `update_time`     DATETIME    NULL     DEFAULT NULL COMMENT '更新时间'
)
    COMMENT ='心晴信箱表'
    COLLATE = 'utf8mb4_general_ci'
    ENGINE = InnoDB
;

CREATE TABLE mind_message
(
    `message_id`      BIGINT(20)  NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '消息ID',
    `sender_id`       BIGINT(20)  NOT NULL COMMENT '发送者ID',
    `recipient_id`    BIGINT(20)  NOT NULL COMMENT '接收者ID',
    `record_id`       BIGINT(20)  NOT NULL COMMENT '预约ID',
    `message_content` TEXT        NOT NULL COMMENT '信件内容',
    `create_by`       VARCHAR(64) NULL DEFAULT '' COMMENT '创建者',
    `create_time`     DATETIME    NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`       VARCHAR(64) NULL DEFAULT '' COMMENT '更新者',
    `update_time`     DATETIME    NULL DEFAULT NULL COMMENT '更新时间'
)
    COMMENT ='消息表'
    COLLATE = 'utf8mb4_general_ci'
    ENGINE = InnoDB
;

-- 心理测评表
CREATE TABLE mind_assessment
(
    `assessment_id`   BIGINT(20) PRIMARY KEY AUTO_INCREMENT COMMENT '测评表ID',
    `assessment_name` VARCHAR(255) NOT NULL COMMENT '测评表名称',
    `num`             BIGINT(20)   NOT NULL DEFAULT 0 COMMENT '已测人数',
    `create_by`       VARCHAR(64)  NULL     DEFAULT '' COMMENT '创建者',
    `create_time`     DATETIME     NULL     DEFAULT NULL COMMENT '创建时间',
    `update_by`       VARCHAR(64)  NULL     DEFAULT '' COMMENT '更新者',
    `update_time`     DATETIME     NULL     DEFAULT NULL COMMENT '更新时间'
)
    COMMENT ='心理测评表'
    COLLATE = 'utf8mb4_general_ci'
    ENGINE = InnoDB
;
-- 测试报告
CREATE TABLE mind_assessment_report
(
    `report_id`       BIGINT(20) PRIMARY KEY AUTO_INCREMENT COMMENT '报告ID',
    `user_id`   BIGINT(20)  NOT NULL COMMENT '用户ID',
    `assessment_id`   BIGINT(20)  NOT NULL COMMENT '测评表ID',
    `assessment_name` VARCHAR(64) NOT NULL COMMENT '测评表名称',
    `result`          VARCHAR(64) NOT NULL COMMENT '测评结果',
    `create_by`       VARCHAR(64) NULL DEFAULT '' COMMENT '创建者',
    `create_time`     DATETIME    NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`       VARCHAR(64) NULL DEFAULT '' COMMENT '更新者',
    `update_time`     DATETIME    NULL DEFAULT NULL COMMENT '更新时间'
)
    COMMENT ='测试报告'
    COLLATE = 'utf8mb4_general_ci'
    ENGINE = InnoDB
;


-- 校园心理活动表
CREATE TABLE mind_activity
(
    `activity_id`        BIGINT(20)  NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '活动ID',
    `activity_name`      VARCHAR(64) NOT NULL COMMENT '活动名称',
    `activity_time`      DATETIME    NOT NULL COMMENT '时间',
    `location`           VARCHAR(64) NOT NULL COMMENT '地点',
    `details`            TEXT        NOT NULL COMMENT '详情',
    `appointment_status` INT(1)      NOT NULL DEFAULT 0 COMMENT '预约状态',
    `capacity`           INT(3)      NOT NULL COMMENT '人数限制',
    `capacity_now`       INT(3)      NOT NULL DEFAULT 0 COMMENT '当前人数',
    `create_by`          VARCHAR(64) NULL     DEFAULT '' COMMENT '创建者',
    `create_time`        DATETIME    NULL     DEFAULT NULL COMMENT '创建时间',
    `update_by`          VARCHAR(64) NULL     DEFAULT '' COMMENT '更新者',
    `update_time`        DATETIME    NULL     DEFAULT NULL COMMENT '更新时间'
)
    COMMENT ='校园心理活动表'
    COLLATE = 'utf8mb4_general_ci'
    ENGINE = InnoDB
;


-- 活动预约记录表
CREATE TABLE mind_activity_record
(
    `record_id`   BIGINT(20) NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '记录ID',
    `user_id`     BIGINT(20) NOT NULL COMMENT '用户ID',
    `activity_id` BIGINT(20) COMMENT '活动ID'

) COMMENT ='活动预约记录表'
    COLLATE = 'utf8mb4_general_ci'
    ENGINE = InnoDB
;

-- 咨询师预约记录表
CREATE TABLE mind_counselor_record
(
    `record_id`          BIGINT(20)  NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '记录ID',
    `user_id`            BIGINT(20)  NOT NULL COMMENT '用户ID',
    `counselor_id`       BIGINT(20) COMMENT '咨询师ID',
    `appointment_time`   DATETIME    NOT NULL COMMENT '预约时间',
    `available_loc`      VARCHAR(64) NOT NULL COMMENT '预约地点',
    `name`               VARCHAR(64) NULL COMMENT '姓名',
    `college`            VARCHAR(64) NULL COMMENT '学院',
    `college_class`      VARCHAR(64) NULL COMMENT '班级',
    `phone`              VARCHAR(64) NULL COMMENT '联系方式',
    `appointment_status` INT(1)      NOT NULL DEFAULT 0 COMMENT '预约状态',
    `create_by`          VARCHAR(64) NULL     DEFAULT '' COMMENT '创建者',
    `create_time`        DATETIME    NULL     DEFAULT NULL COMMENT '创建时间',
    `update_by`          VARCHAR(64) NULL     DEFAULT '' COMMENT '更新者',
    `update_time`        DATETIME    NULL     DEFAULT NULL COMMENT '更新时间'
) COMMENT ='咨询师预约记录表'
    COLLATE = 'utf8mb4_general_ci'
    ENGINE = InnoDB
;

-- 咨询师表
CREATE TABLE mind_counselor
(
    `counselor_id`    BIGINT(20)   NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '咨询师ID',
    `user_id`         BIGINT(20)   NOT NULL COMMENT '用户ID',
    `counselor_name`  VARCHAR(64)  NOT NULL COMMENT '咨询师姓名',
    `counselor_level` INT(2)       NOT NULL DEFAULT '1' COMMENT '咨询师等级',
    `education`       VARCHAR(64)  NOT NULL COMMENT '资格证',
    `speciality`      VARCHAR(64)  NOT NULL COMMENT '擅长',
    `available_time1` VARCHAR(64)  NOT NULL COMMENT '上午',
    `available_time2` VARCHAR(64)  NOT NULL COMMENT '下午',
    `available_week`  VARCHAR(64)  NOT NULL COMMENT '可预约星期',
    `pic_url`         VARCHAR(255) NOT NULL COMMENT '头像',
    `create_by`       VARCHAR(64)  NULL     DEFAULT '' COMMENT '创建者',
    `create_time`     DATETIME     NULL     DEFAULT NULL COMMENT '创建时间',
    `update_by`       VARCHAR(64)  NULL     DEFAULT '' COMMENT '更新者',
    `update_time`     DATETIME     NULL     DEFAULT NULL COMMENT '更新时间'
)
    COMMENT ='咨询师表'
    COLLATE = 'utf8mb4_general_ci'
    ENGINE = InnoDB
;

