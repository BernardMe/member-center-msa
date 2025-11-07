

--分享朋友圈领券活动
create table c_check_activity
(
    activity_id         INT(10) auto_increment comment '活动id'
        primary key,
    activity_name    VARCHAR(255)     null comment '活动名称',
    coupon_no_str    VARCHAR(255)     null comment '活动相关优惠券方案编号拼接串',
    bg_img_url       VARCHAR(255)     null comment '活动背景图片url',
    start_time       datetime         null comment '开始时间',
    end_time         datetime         null comment '结束时间',
    is_ocr           TINYINT(3)   not null comment '是否开启OCR识别(0否, 1是)',
    ocr_keyword      VARCHAR(255)     null comment 'OCR待验证关键字',
    status           TINYINT(3)   not null comment '状态(0禁用, 1正常)',
    is_delete        TINYINT(3)   not null comment '是否已删除(0正常, 1已删除)',
    create_time      datetime         null comment '创建时间',
    update_time      datetime         null comment '修改时间'
)
    comment '顾客登记活动表';


create table c_check_member_look
(
    phone          VARCHAR(50)                            not null comment '手机号'
        primary key,
    member_card_no VARCHAR(50)                            not null comment '会员卡号',
    activity_id    INT(10)                                null comment '活动id',
    createtime     timestamp     default CURRENT_TIMESTAMP null comment '创建时间'
)
    comment '顾客登记浏览记录表';

create index c_check_member_look_idx_phone
    on c_check_member_look (phone);


create table c_check_customer
(
    check_id              int auto_increment comment '登记id'
        primary     key,
    activity_id         INT(10)                    null comment '活动id',
    activity_name       VARCHAR(255)               null comment '活动名称',
    customer_name       varchar(255)               null comment '顾客名称',
    customer_phone      char(11)                   null comment '顾客手机号',
    member_card_no      varchar(50)                null comment '会员卡号',
    customer_address    varchar(255)               null comment '顾客家庭住址',
    choice_coupon_no    VARCHAR(50)                null comment '已选择的优惠券方案编码',
    screenshot_url      varchar(255)               null comment '分享截图url',
    submit_count        TINYINT(3)                 null comment '已提交次数',
    audit_status        TINYINT(3)   default 0 not null comment '审核状态(0:无需审核, 1:审核成功, 2:审核失败, 3:OCR识别中, 4:OCR识别成功, 5:OCR识别失败)',
    received            TINYINT(3)                 null comment '是否已领取(1:已领取, 0:未领取)',
    create_time         datetime                   null comment '创建时间',
    update_time         datetime                   null comment '修改时间',
    constraint c_check_customer_uni
        unique (activity_id, customer_phone, member_card_no)
)
    comment '顾客登记表';

create index c_check_customer_idx_card_no
    on c_check_customer (member_card_no);


create table c_heart_rate_report
(
    id			int auto_increment comment 'id'
           primary     key,
    member_card_no  VARCHAR(50)  null   comment '会员卡号',
    zxd_report_id   varchar(50)         comment '心电报告id',
    pdf_url         varchar(1500)       comment '心电PDF报告url',
    start_time      datetime            comment '开始记录时间',
    duration        int                 comment '测量时长(单位 毫秒)',
    type            int                 comment '报告类型(0-心律失常、1-hrv)',
    expires_time    datetime            comment 'PDF有效期截至时间',
    is_mine         tinyint             comment '我的报告',
    create_time     datetime            comment '创建时间',
    update_time     datetime     null   comment '修改时间',
    constraint c_heart_rate_report
        unique (member_card_no, zxd_report_id)
)
    comment '心率报告详情表';

create index c_heart_rate_report_idx_card_no
    on c_heart_rate_report (member_card_no);


create table c_heart_rate_data
(
    id			int auto_increment comment 'id'
           primary     key,
    member_card_no  VARCHAR(50)  null   comment '会员卡号',
    zxd_report_id   varchar(50)         comment '心电报告id',
    ori_data_url    varchar(255)        comment '原始心电数据OSS存储url',
    device_type     tinyint             comment '设备类型(0-4 、6-8 单导心电卡5、9 六导心电卡)',
    device_name     varchar(50)         comment '设备名称',
    device_sn       varchar(50)         comment '设备SN',
    platform        tinyint             comment '终端平台：0-安卓，1-iOS，2-微信小程序',
    lead_count      tinyint             comment '导联数，根据设备区分，如单导联设备为 1，三导联设备为 3，六导联设备为 6',
    duration        int                 comment '测量时长(单位 毫秒)',
    create_time     datetime            comment '创建时间',
    update_time     datetime     null   comment '修改时间'
)
    comment '原始心电数据表';

create index c_heart_rate_data_idx_card_no
    on c_heart_rate_data (member_card_no);


-- 二期
create table c_puzzle_cover
(
    cover_id              int auto_increment comment '封面id'
        primary     key,
    cover_name      varchar(255) null comment '封面名称',
    cover_url       varchar(255) null comment '封面url',
    status          tinyint      null comment '启用状态(1:已启用, 0:未启用)',
    create_time     datetime     null comment '创建时间',
    update_time     datetime     null comment '修改时间'
)
    comment '拼图封面表';


create table c_puzzle_piece
(
    piece_id              int auto_increment comment '图块id'
        primary     key,
    cover_id        int          null comment '封面id',
    piece_name      varchar(255) null comment '图块名称',
    piece_url       varchar(255) null comment '图块url',
    sort            tinyint      null comment '正确拼接排序',
    create_time     datetime     null comment '创建时间',
    update_time     datetime     null comment '修改时间',
    constraint c_puzzle_piece_uIdx
        unique (cover_id, sort)
)
    comment '拼图图块表';


create table c_match_material
(
    material_id              int auto_increment comment '素材id'
        primary     key,
    material_name    varchar(255) null comment '素材名称',
    record_num      int          null comment '参与人数',
    status          tinyint      null comment '启用状态(1:已启用, 0:未启用)',
    create_time     datetime     null comment '创建时间',
    update_time     datetime     null comment '修改时间'
)
    comment '连一连素材表';


create table c_match_goods
(
    goods_id              int auto_increment comment '商品id'
        primary     key,
    goods_name      varchar(255) null comment '商品名称',
    goods_img       varchar(255) null comment '商品图片url',
    create_time     datetime     null comment '创建时间',
    update_time     datetime     null comment '修改时间'
)
    comment '连一连商品表';


create table c_match_feature
(
    feature_id              int auto_increment comment '特色id'
        primary     key,
    feature_text    varchar(255) null comment '特色文本',
    question_type   tinyint      null comment '题目类型（1单选 2多选 3问答题）',
    create_time     datetime     null comment '创建时间',
    update_time     datetime     null comment '修改时间'
)
    comment '连一连特色表';


create table c_match_relation
(
    relation_id              int auto_increment comment '主键'
        primary     key,
    material_id     int          not null comment '素材id',
    goods_id        int          null comment '商品id',
    feature_id      int          null comment '特色id ',
    create_time     datetime     null comment '创建时间',
    update_time     datetime     null comment '修改时间'
)
    comment '素材关系表';

create index c_match_relation_idx_material_id
    on c_match_relation (material_id);


create table c_medical_equipment_category
(
    id			int auto_increment comment 'id'
           primary     key,
    category_code   char(20)                    comment '目录编码',
    category_name   varchar(50)                comment '目录名称',
    parent_code     CHAR(20)              null comment '父级编码',
    look_people_num int                        comment '查看人数',
    frequency       int                        comment '频次',
    status          tinyint                    comment '状态(0禁用, 1正常)',
    create_time     datetime                   comment '创建时间',
    update_time     datetime     null          comment '修改时间',
    constraint c_medical_equipment_category
        unique (category_code)
)
    comment '医疗器械目录表';

create index c_medical_equipment_category_category_code
    on c_medical_equipment_category (category_code);


create table c_medical_equipment_video
(
    id			int auto_increment comment '视频id'
           primary     key,
    equipment_id   int                         comment '器械id',
    goods_code      VARCHAR(50)  not null      comment '货号',
    thumbnail_image varchar(1500)              comment '缩略图url',
    video_url       varchar(1500)              comment '视频ossUrl',
    remark          varchar(255)               comment '备注说明',
    category_code   char(20)                   comment '目录编码',
    category_name   varchar(50)                comment '目录名称',
    click_num       int                        comment '点击次数',
    status          tinyint                    comment '状态(0禁用, 1正常)',
    create_time     datetime                   comment '创建时间',
    update_time     datetime     null          comment '修改时间',
    constraint c_medical_equipment_video
        unique (goods_code)
)
    comment '医疗器械视频表';

create index c_medical_equipment_video_idx_goods_code
    on c_medical_equipment_video (goods_code);


create table c_medical_equipment
(
    id			int auto_increment comment 'id'
           primary     key,
    goods_code      VARCHAR(50)  not null      comment '货号',
    equipment_name  varchar(50)                comment '品名',
    specification   varchar(255)               comment '规格',
    manufacturer    varchar(255)               comment '厂家',
    advantages      varchar(1500)              comment '产品优势',
    category_code   char(20)                    comment '目录编码',
    category_name   varchar(50)                comment '目录名称',
    main_image      varchar(1500)              comment '主图url',
    banner_images   VARCHAR(3000)         null comment '轮播图',
    detail_images   varchar(3000)              comment '详情图url',
    remark          varchar(255)               comment '备注说明',
    goods_price     bigint default -1 not null comment '单位厘',
    look_people_num int                        comment '查看人数',
    frequency       int                        comment '频次',
    status          tinyint                    comment '状态(0禁用, 1正常)',
    create_time     datetime                   comment '创建时间',
    update_time     datetime     null          comment '修改时间',
    constraint c_medical_equipment
        unique (goods_code)
)
    comment '医疗器械表';

create index c_medical_equipment_idx_goods_code
    on c_medical_equipment (goods_code);


create table c_wzsurvey_coupon_record
(
    id              int auto_increment comment '记录id'
        primary key,
    paper_id        int         null comment '试卷id',
    member_card_no  varchar(50) null comment '会员卡号',
    member_phone    char(11)    null comment '会员手机号',
    coupon_received tinyint(3)  null comment '是否已领取优惠券(1:已领取, 0:未领取)',
    create_date     datetime    null comment '创建时间'
)
    comment '问卷领券记录表';

create index c_wzsurvey_coupon_r_idx_member_card
    on c_wzsurvey_coupon_record (member_card_no);

create index c_wzsurvey_coupon_record_idx_paper
    on c_wzsurvey_coupon_record (paper_id);

create index c_wzsurvey_coupon_record_idx_phone
    on c_wzsurvey_coupon_record (member_phone);



create table c_wzsurvey_record
(
    record_id     int auto_increment comment '记录id'
        primary key,
    member_card_no  VARCHAR(50)     null comment '会员卡号',
    paper_id      int               null comment '试卷id',
    question_id   int               null comment '题目id',
    question_type tinyint           null comment '题目类型（1单选 2多选 3问答题）',
    answer        varchar(255)      null comment '用户答案',
    user_phone    varchar(32)       null comment '用户手机号',
    user_name     varchar(50)       null comment '收件人姓名',
    address       varchar(255)      null comment '详细地址',
    create_date   datetime          null comment '创建时间'
)
    comment '问卷记录表';

create index c_survey_record_idx_member_card_no
    on c_wzsurvey_record (member_card_no);

create index c_wzsurvey_record_idx_paper_id
    on c_wzsurvey_record (paper_id);

create index c_wzsurvey_record_idx_phone
    on c_wzsurvey_record (user_phone);


create table c_survey_member_directed
(
    id        int(10) auto_increment comment 'id'
        primary key,
    paper_id         int          not null comment '问卷id',
    member_card_no   varchar(32)  null comment '会员卡号'
)
    comment '类教师节活动定向发送会员信息';

create index c_survey_member_directed_idx_paper
    on c_survey_member_directed (paper_id);

create index c_survey_member_directed_idx_card_no
    on c_survey_member_directed (member_card_no);


create table c_wzsurvey_paper
(
    paper_id              int auto_increment comment '试卷id'
        primary     key,
    paper_name          varchar(255)        null comment '问卷试卷名称',
    question_num        int                 null comment '总题数',
    record_num          int                 null comment '答题人数',
    status              tinyint             null comment '启用状态(1:已启用, 0:未启用)',
    sort                int                 not null comment '排序',
    start_time          DATETIME(19)        null comment '有效期开始时间',
    end_time            DATETIME(19)        null comment '有效期截至时间',
    source              tinyint default 1   null comment '来源(1:1V1快链)',
    small_program_code  VARCHAR(100)        null comment '小程序码',
    is_issue_coupon     tinyint(3)          null comment '是否发放优惠券(0否,1是)',
    coupon_no           varchar(50)         null comment '优惠券方案编码',
    is_directed         tinyint(3)          null comment '是否定向发送(0否,1是)',
    is_export_dely      tinyint(3)          null comment '是否导出地址(0否,1是)',
    foreword_text       varchar(2000)       null comment '前言',
    epilogue_text       varchar(2000)       null comment '结语',
    create_time         datetime            null comment '创建时间',
    update_time         datetime            null comment '修改时间'
)
    comment '问卷试卷表';


create table c_wzsurvey_question
(
    question_id              int auto_increment comment '题目id'
        primary     key,
    paper_id        int                 null comment '试卷id',
    question_text   varchar(255)        null comment '题目主干',
    question_type   tinyint             null comment '题目类型（1单选 2多选 3问答题）',
    answer          varchar(255)        null comment '答案',
    sort            int                 not null comment '排序',
    status          tinyint             null comment '启用状态(1:已启用, 0:未启用)',
    source          tinyint default 1   null comment '来源(1:1V1快链)',
    create_time     datetime            null comment '创建时间',
    update_time     datetime            null comment '修改时间'
)
    comment '问卷试卷题目表';

create index c_wzsurvey_question_idx_paper_id
    on c_wzsurvey_question (paper_id);


create table c_wzsurvey_option
(
    option_id              int auto_increment comment '主键'
        primary     key,
    question_id     int                 null comment '题目id',
    option_value    char(24)            null comment '选项值',
    option_text     varchar(255)        null comment '选项文本',
    status          tinyint             null comment '启用状态(1:已启用, 0:未启用)',
    source          tinyint default 1   null comment '来源(1:1V1快链)',
    create_time     datetime            null comment '创建时间',
    update_time     datetime            null comment '修改时间'
)
    comment '问卷题目选项表';

create index c_wzsurvey_option_idx_question_id
    on c_wzsurvey_option (question_id);


--教师节领券活动
create table member_teacher_activity
(
    activity_id         INT(10) auto_increment comment '活动id'
        primary key,
    activity_name    VARCHAR(255)     null comment '活动名称',
    coupon_no_str    VARCHAR(255)     null comment '活动相关优惠券方案编号拼接串',
    bg_img_url       VARCHAR(255)     null comment '活动背景图片url',
    start_time       datetime         null comment '开始时间',
    end_time         datetime         null comment '结束时间',
    is_ocr           TINYINT(3)   not null comment '是否开启OCR识别(0否, 1是)',
    status           TINYINT(3)   not null comment '状态(0禁用, 1正常)',
    is_delete        TINYINT(3)   not null comment '是否已删除(0正常, 1已删除)',
    create_time      datetime         null comment '创建时间',
    update_time      datetime         null comment '修改时间'
)
    comment '教师登记活动表';


create table member_teacher_record
(
    record_id        int(10) auto_increment comment '记录id'
        primary key,
    activity_id      int          not null comment '活动id',
    type             tinyint      null comment '登记类型（0 教师节 1国庆 2铁路医保）',
    teacher_name     varchar(255) null comment '教师姓名',
    member_card_no   varchar(32)  null comment '会员卡号',
    cert_pic_url     varchar(255) null comment '教资证图片url',
    certificate_num  varchar(32)  null comment '教资证编号',
    choice_coupon_no varchar(50)  null comment '已选择的优惠券方案编码',
    received         tinyint(3)   null comment '是否已领取(1:已领取, 0:未领取)',
    receive_date     datetime     null comment '领取时间',
    store_code       varchar(36)  null comment '领取门店编号',
    store_name       varchar(36)  null comment '领取门店名称',
    teacher_phone    char(11)     null comment '教师手机号',
    home_address     varchar(255) null
)
    comment '教师资格证登记信息';

create index member_teacher_record_idx_act_id
    on member_teacher_record (activity_id);

create index member_teacher_record_idx_card_no
    on member_teacher_record (member_card_no);


create table plantation_survey_record
(
    record_id     int auto_increment comment '记录id'
        primary key,
    user_id       varchar(32)  null comment '答题用户id',
    open_id       varchar(32)  null comment '会员微信openId',
    paper_id      int          null comment '试卷id',
    question_id   int          null comment '题目id',
    question_type tinyint      null comment '题目类型（1单选 2多选 3问答题）',
    answer        varchar(255) null comment '用户答案',
    user_phone    varchar(32)  null comment '用户手机号',
    create_date   datetime     null comment '创建时间'
)
    comment '问卷记录表';

create index plantation_survey_record_idx_user_id
    on plantation_survey_record (user_id);


create table plantation_survey_paper
(
    paper_id              int auto_increment comment '试卷id'
        primary     key,
    paper_name      varchar(255) null comment '问卷试卷名称',
    question_num    int          null comment '总题数',
    record_num      int          null comment '答题人数',
    status          tinyint      null comment '启用状态(1:已启用, 0:未启用)',
    sort            int          not null comment '排序',
    create_time     datetime     null comment '创建时间',
    update_time     datetime     null comment '修改时间'
)
    comment '问卷试卷表';


create table plantation_survey_question
(
    question_id              int auto_increment comment '题目id'
        primary     key,
    paper_id        int          null comment '试卷id',
    question_text   varchar(255) null comment '题目主干',
    question_type   tinyint      null comment '题目类型（1单选 2多选 3问答题）',
    answer          varchar(255) null comment '答案',
    sort            int          not null comment '排序',
    status          tinyint      null comment '启用状态(1:已启用, 0:未启用)',
    create_time     datetime     null comment '创建时间',
    update_time     datetime     null comment '修改时间'
)
    comment '问卷试卷题目表';

create index plantation_wzsurvey_question_idx_paper_id
    on plantation_survey_question (paper_id);


create table plantation_survey_option
(
    option_id              int auto_increment comment '主键'
        primary     key,
    question_id     int          null comment '题目id',
    option_value    char(24)     null comment '选项值',
    option_text     varchar(255) null comment '选项文本',
    status          tinyint      null comment '启用状态(1:已启用, 0:未启用)',
    create_time     datetime     null comment '创建时间',
    update_time     datetime     null comment '修改时间'
)
    comment '问卷题目选项表';

create index plantation_survey_option_idx_question_id
    on plantation_survey_option (question_id);


-- 券桶
create table plat_coupon_base_info
(
    id                  int auto_increment comment 'ID'
        primary key,
    coupon_code         varchar(255)           null comment '编码',
    coupon_name         varchar(255)           null comment '名称',
    coupon_description  varchar(255)           null comment '描述',
    coupon_rules        varchar(255)           null comment '规则说明',
    creater_code        varchar(24)            null comment '创建人工号',
    creater_name        varchar(24)            null comment '创建人名称',
    create_time         datetime               null comment '创建时间',
    coupon_type_code    varchar(24)            null comment '类型编号',
    coupon_type_name    varchar(24)            null comment '类型名称',
    issue_begin_time    datetime               null comment '发放开始时间',
    issue_end_time      datetime               null comment '发放结束时间',
    use_begin_time      datetime               null comment '使用开始时间',
    use_end_time        datetime               null comment '使用结束时间',
    use_time_type_code  varchar(24)            null comment '使用时间类型编号',
    use_time_type_name  varchar(24)            null comment '使用时间类型名称',
    time_period_unit    varchar(8)             null comment '时间类型（使用时间类型为时间段时）',
    time_period_length  int                    null comment '时间数量（使用时间类型为时间段时）',
    condition_num       double                 null comment '条件数量',
    condition_type_code varchar(24)            null comment '条件类型编号',
    condition_type_name varchar(24)            null comment '条件类型名称',
    discounts_amount    double                 null comment '优惠金额',
    all_store_tag       varchar(2)             null comment '是否全门店可用',
    all_goods_tag       varchar(2)             null comment '是否全商品可用',
    issue_num_limit     int                    null comment '单人领取数量限制（-1不限制）',
    max_issue_num       int                    null comment '总领取次数',
    show_tag            varchar(2) default 'T' null comment '展示标记',
    delete_tag          varchar(2) default 'F' null comment '删除标记',
    coupon_pool_code    varchar(24)            null comment '券池编号',
    coupon_pool_name    varchar(24)            null comment '券池名称'
)
    comment '券桶优惠券基础信息表';


create table plat_coupon_issue_record
(
    id            int auto_increment comment '记录ID'
        primary key,
    coupon_id     int          null comment '优惠券ID',
    coupon_name   varchar(255) null comment '优惠券名称',
    vip_card_code varchar(16)  null comment '会员卡号',
    issue_time    datetime     null comment '发放时间'
)
    comment '券桶优惠券发放记录表';


-- 站内信设计

create table plat_plantation_notification
(
    id                  int  auto_increment comment '主键'
         primary key,
    manager_id          int              not null comment '创建人主键',
    title               varchar(128)     not null default 0 comment '公告标题',
    content             varchar(4000)    not null default 0 comment '公告内容',
    picture_url         varchar(1024)    null comment '公告图片url，多个英文逗号分隔',
    herb_id             int              not null default 0 comment '公告关联植株id',
    is_enable           TINYINT(3)       null comment '是否启用',
    create_time         datetime         null comment '创建时间',
    update_time         datetime         null comment '最后修改时间'
)
    comment ='站内信消息表';

create index plat_plantation_notification_idx_herb
    on plat_plantation_notification (herb_id);


create table plat_plantation_notification_log
(
    id                  int  auto_increment comment '主键'
        primary key,
    notification_id     int              not null default 0 comment '通知id',
    user_id             char(32)         null comment '用户id',
    open_id             varchar(32)      null comment '会员微信openId',
    vip_card_no         varchar(16)      null comment '用户会员卡号',
    rec_status          tinyint          not null default 1 comment '接受状态(1已读, 2删除)',
    create_time         datetime         null comment '创建时间',
    update_time         datetime         null comment '最后修改时间'
)
    comment ='站内信消息记录表';

create index plat_plantation_notifi_log_idx_user
    on plat_plantation_notification_log (user_id);

create index plat_plantation_notifi_log_vipcard
    on plat_plantation_notification_log (vip_card_no);

-- 一期
create table plat_plantation_config_global
(
    id                 int auto_increment not null comment '主键'
        primary key,
    config_code        varchar(32)       null comment '配置编码，不能重复，唯一确定一项配置',
    config_name        varchar(128)      null comment '配置名称',
    config_type        tinyint           null comment '配置类型(1:全局配置, 2:业务配置, 3:界面配置)',
    config_data_class  tinyint           null comment '配置数据类别(1:常规键值数据, 2:对象数据)',
    config_value       varchar(255)      not null comment '配置值',
    default_value      varchar(255)      null comment '配置的默认值',
    permission_enable  tinyint           null comment '是否要校验权限',
    permission_role_id int               null comment '权限所需角色id',
    is_delete          tinyint default 0 null comment '是否删除：0-否；1-是',
    manager_id         int               not null comment '创建人主键',
    create_time        datetime          null comment '创建时间',
    update_time        datetime          null comment '更新时间'
)
    comment '游戏全局设置';

create unique index plat_plantation_config_global_config_code_uindex
    on plat_plantation_config_global (config_code);


create table plat_plantation_config_data
(
    data_id            int auto_increment not null comment '主键'
        primary key,
    config_id          int               not null comment '配置id',
    data_code          varchar(255)       null comment '数据编码',
    data_name          varchar(255)      null comment '数据名称',
    data_value         varchar(255)      not null comment '数据值',
    date_sort          int               not null comment '数据排序'
)
    comment '配置对象数据表';

create index plat_plantation_config_data_config_id_index
    on plat_plantation_config_data (config_id);


create table plat_plantation_drip_record
(
    id          int auto_increment comment '主键'               
        primary key,
    task_id     int          null comment '任务id',
    drip_num    smallint(6)  null comment '水滴克数(含正负)',
    drip_direct tinyint      null comment '增减标记（1 增加 2减少）',
    water_name  varchar(100) null comment '浇水行为名称',
    user_id     varchar(32)  null comment '用户id',
    open_id     varchar(32)  null comment '会员微信openId',
    record_date datetime     null comment '记录时间'
)
    comment '水滴记录表';

create index plat_plantation_drip_record_user_id_index
    on plat_plantation_drip_record (user_id);


create table plat_plantation_gift_coupon
(
    gift_id         bigint auto_increment comment '礼品主键'
        primary key,
    gift_name       varchar(255)                        not null comment '礼品名称',
    main_image      varchar(255)                        null comment '礼品主图片',
    secondary_image varchar(255)                        null comment '礼品副图片',
    coupon_code     varchar(255)                        not null comment '兑换券ID',
    create_time     timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time     timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    delete_flag     tinyint   default 0                 null comment '删除标记（0 正常 1已删除）',
    delete_time     timestamp                           null comment '删除时间',
    create_by       int                                 null comment '创建人',
    update_by       int                                 null comment '更新人',
    gift_detail     longtext                            null comment '礼品描述',
    coupon_name     varchar(255)                        null comment '兑换券名称'
)
    comment '百草园礼品券表';


create table plat_plantation_gift_coupon_record
(
    distribution_id bigint auto_increment comment '礼品发放主键'
        primary key,
    gift_id         bigint                              not null comment '礼品主键（礼品表关联）',
    herb_id         int                                 not null comment '种植中药材编号',
    plant_id        int                                 not null comment '植株编号',
    vip_card_no     varchar(16)                         null comment '用户会员卡号code',
    coupon_code     varchar(255)                        not null comment '兑换券ID',
    stage_id        int                                 null comment '阶段ID',
    redemption_time timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '兑换时间',
    redeemed        tinyint   default 0                 null comment '是否已兑换（0 未兑换 1 已兑换）默认0',
    create_time     timestamp default CURRENT_TIMESTAMP not null comment '创建时间（发放时间）',
    update_time     timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    delete_flag     tinyint   default 0                 null comment '删除标记（0 正常 1已删除）',
    delete_time     timestamp                           null comment '删除时间',
    create_by       varchar(100)                        null comment '创建人',
    update_by       varchar(100)                        null comment '更新人'
)
    comment '百草园礼品券记录表';

create index plat_plantation_gift_coupon_record_idx_createby
    on plat_plantation_gift_coupon_record (create_by);


create table plat_plantation_gift_integral
(
    integral_id    bigint auto_increment comment '礼品积分主键'
        primary key,
    integral_value double(10, 2)                       not null comment '礼品积分值',
    create_time    timestamp default CURRENT_TIMESTAMP null comment '创建时间',
    update_time    timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    delete_flag    tinyint   default 0                 null comment '删除标记（0 正常 1已删除）',
    delete_time    timestamp                           null comment '删除时间',
    create_by      int                                 null comment '创建人',
    update_by      int                                 null comment '更新人'
)
    comment '百草园礼品积分表';


create table plat_plantation_gift_integral_record
(
    record_id      bigint auto_increment comment '积分记录主键'
        primary key,
    integral_id    bigint                              not null comment '礼品积分id',
    integral_value double(10, 2)                       not null comment '礼品积分值',
    herb_id        int                                 not null comment '种植中药材编号',
    plant_id       int                                 not null comment '植株编号',
    stage_id       int                                 null comment '阶段ID',
    create_time    timestamp default CURRENT_TIMESTAMP not null comment '创建时间（发放时间）',
    update_time    timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    delete_flag    tinyint   default 0                 null comment '删除标记（0 正常 1已删除）',
    delete_time    timestamp                           null comment '删除时间',
    create_by      varchar(100)                        null comment '创建人',
    update_by      varchar(100)                        null comment '更新人'
)
    comment '百草园礼品积分记录表';

create index plat_plantation_gift_integral_record__idx_createby
    on plat_plantation_gift_integral_record (create_by);


create table plat_plantation_herb
(
    herb_id             int auto_increment comment '中药材id',
    herb_name           varchar(50)       not null comment '中药材名称',
    herb_img_url        varchar(255)      null comment '中药材列表图片URL',
    herb_img_detail_url varchar(255)      null comment '中药材详情图片URL',
    stage_num           tinyint           null comment '阶段数量',
    status              tinyint           null comment '启用状态(1:已启用, 0:未启用)',
    is_delete           tinyint default 0 null comment '是否已删除(1:是, 0:否)',
    herb_sort           int               not null comment '中药材类型种植顺序',
    create_time         datetime          null comment '创建时间',
    update_time         datetime          null comment '更新时间',
    herb_detail         longtext          null comment '中药材详情',
    primary key (herb_id, herb_sort)
)
    comment '中药材类型表';


create table plat_plantation_herb_stage
(
    stage_id            int auto_increment comment '阶段编号'
        primary key,
    herb_id             int               null comment '中药材类型编号',
    stage_name          varchar(20)       null comment '阶段名称',
    stage_image         varchar(255)      null comment '阶段图片',
    drip_capacity       smallint(6)       null comment '当前阶段水滴进度条阈值',
    water_time_capacity smallint(6)       null comment '当前阶段水滴进度条所需浇水次数',
    status              tinyint default 1 null comment '启用状态(1:已启用, 0:未启用)',
    is_delete           tinyint default 0 null comment '是否已删除(1:是, 0:否)',
    create_time         datetime          null comment '创建时间',
    update_time         datetime          null comment '更新时间',
    reward_id           bigint            null comment '奖励id',
    reward_type         tinyint default 2 not null comment '奖励类型（0 券 1积分 2无）',
    reward_name         varchar(255)      null comment '奖励名称/积分数量',
    stage_sort          int               null comment '阶段序号'
)
    comment '中药材植株阶段表';


create table plat_plantation_stage_words
(
    words_id            int auto_increment comment '话语id'
        primary key,
    stage_id            int               null comment '阶段编号',
    used_drip_num       smallint(6)       null comment '展示话语所需当前阶段已浇水克数',
    status              tinyint default 1 null comment '启用状态(1:已启用, 0:未启用)',
    create_time         datetime          null comment '创建时间',
    update_time         datetime          null comment '更新时间',
    words_text          varchar(255)      null comment '话语文本',
    words_sort          int               null comment '话语序号'
)
    comment '中药材植株阶段话语表';


create table plat_plantation_link
(
    link_id         int auto_increment comment '任务链接id'
        primary key,
    link_name       varchar(50)  null comment '链接名称',
    link_type       smallint(6)  null comment '连接类型(0 内部 1 外部)',
    param           varchar(255) null comment '参数信息',
    path            varchar(100) null comment '路径',
    main_module_id  int          null comment '首页模块主键',
    task_id         int               null comment '任务id',
    is_delete       tinyint default 0 null comment '是否已删除(1:是, 0:否)'
)
    comment '任务链接表';


create table plat_plantation_setting_page
(
    id                                int          not null comment '主键'
        primary key,
    share_text                        varchar(50)  null comment '分享显示文字',
    help_share_text                   varchar(50)  null comment '邀请助力分享显示文字',
    background_url                    varchar(255) null comment '游戏主页背景图片URL',
    theme_type                        smallint(6)  null comment '主题类型配置项',
    share_card_poster_url             varchar(255) null comment '横版微信分享卡片URL',
    share_poster_url                  varchar(255) null comment '竖版镂空分享海报URL',
    help_share_poster_url             varchar(255) null comment '邀请助力分享海报URL',
    use_default_card_poster_url       tinyint      null comment '是否使用默认分享卡片',
    use_default_share_poster_url      tinyint      null comment '是否使用默认竖版分享海报',
    use_default_help_share_poster_url tinyint      null comment '是否使用默认助力分享卡片',
    is_delete                         tinyint      null comment '是否已删除(1:是, 0:否)',
    manager_id                        int          not null comment '创建人主键',
    create_time                       datetime     null comment '创建时间',
    update_time                       datetime     null comment '更新时间'
)
    comment '页面配置表';


CREATE TABLE plat_plantation_sign_in
(
  `id`        bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id`  varchar(32) NULL COMMENT '签到用户id',
   open_id   varchar(32) not null comment '会员微信openId',
  `continue_days` int(3) NOT NULL DEFAULT '1' COMMENT '连续签到天数',
  `create_time` datetime NOT NULL COMMENT '创建日期，第一次签到日期',
  `update_time` datetime DEFAULT NULL COMMENT '更新日期, 最后签到日期',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `user_id_unique` (`user_id`) USING BTREE
)
    ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户签到表';

create index plat_plantation_sign_in_idx_open_id
    on plat_plantation_sign_in (open_id);


create table plat_plantation_task
(
    task_id          int auto_increment comment '任务id'
        primary key,
    task_type        tinyint           null comment '任务类型 1-一次性任务 2-多次可完成任务',
    task_class       tinyint(8)        NULL COMMENT '任务分组类型 1下单,2浏览,3领取券,4积分兑换,5问卷,6视频号,7邀请好友,8添加顾问,9连一连,10拼图',
    task_class_name  varchar(50)       null comment '任务分组类型名称',
    issue_type       tinyint default 2 NOT NULL COMMENT '发放方式 1=单次发放，2=完成发放',
    task_tips        varchar(50)       null comment '任务提示',
    time_drip_number tinyint           null comment '完成所需次数',
    award_drip       smallint(6)       null comment '奖励水滴数',
    icon_url         varchar(255)      null comment '图标url',
    show_icon        tinyint           null comment '是否显示图标（1 显示 0不显示）',
    show_task_name   varchar(50)       null comment '任务名称',
    sort             int               not null comment '排序',
    status           tinyint default 1 null comment '启用状态(1:已启用, 0:未启用)',
    is_delete        tinyint default 0 null comment '是否已删除(1:是, 0:否)',
    is_shortcut      tinyint default 0 null comment '是否快捷方式(1:是, 0:否)',
    create_time      datetime          null comment '创建时间',
    update_time      datetime          null comment '更新时间'
)
    comment '任务信息表';


create table plat_plantation_user_invitation
(
    invitation_id       char(19)    NOT NULL comment '邀请id'
        primary key,
    user_id             char(32)    NOT NULL comment '发起的用户id',
    open_id             varchar(32) null comment '会员微信openId',
    user_phone          char(11)    null comment '发起的用户手机号',
    user_task_id        int         null comment '用户任务id',
    create_time         datetime    null comment '创建时间',
    update_time         datetime    null comment '更新时间'
)
    comment '用户邀请记录表';

create index plat_plantation_user_invitation_idx_openid
    on plat_plantation_user_invitation (open_id, user_task_id);

create index plat_plantation_user_invitation_idx_userid
    on plat_plantation_user_invitation (user_id, user_task_id);


create table plat_plantation_user_assist
(
    assist_id           int auto_increment comment '助力id'
        primary key,
    invitation_id       char(19)    NOT NULL comment '邀请id',
    user_id             char(32)    NOT NULL comment '助力用户id',
    open_id             varchar(32) null comment '助力会员微信openId',
    user_phone          char(11)    null comment '助力用户手机号',
    inviter_user_id     char(32)    NOT NULL comment '发起的用户id',
    inviter_open_id     varchar(32) null comment '发起的会员微信openId',
    assist_count        smallint(6) null comment '助力次数',
    create_time         datetime    null comment '创建时间',
    update_time         datetime    null comment '更新时间'
)
    comment '用户助力表';

create index plat_plantation_user_assist_idx
    on plat_plantation_user_assist (invitation_id, user_id, inviter_user_id);

create index plat_plantation_user_assist_idx_ua
    on plat_plantation_user_assist (invitation_id, open_id, inviter_user_id);


create table plat_plantation_user_bottle
(
    bottle_id           int auto_increment comment '水壶id'
        primary key,
    user_id             char(32)    NULL comment '用户id',
    open_id             varchar(32) NOT null comment '会员微信openId',
    user_phone          char(11)    null comment '用户手机号',
    bottle_drip_total   smallint(6) null comment '水壶剩余水滴克数',
    create_time         datetime    null comment '创建时间',
    update_time         datetime    null comment '更新时间'
)
    comment '用户水壶表';

create index plat_plantation_user_bottle_idx_userid
    on plat_plantation_user_bottle (user_id);

create index plat_plantation_user_bottle_idx_openid
    on plat_plantation_user_bottle (open_id);


create table plat_plantation_user_plant
(
    plant_id            int auto_increment comment '植株id'
        primary key,
    user_id             char(32)    null comment '用户id',
    open_id             varchar(32) null comment '会员微信openId',
    user_phone          char(11)    null comment '用户手机号',
    herb_id             int         null comment '中药材类型编号',
    herb_name           varchar(50) null comment '中药材类型名称',
    curr_stage          int         null comment '当前成长阶段编号',
    plant_drip_num      smallint(6) null comment '该植株总浇水克数',
    used_drip_num       smallint(6) null comment '当前阶段已浇水克数',
    plant_state         smallint(6) null comment '植株状态(1:种植中,2:已收获)',
    create_time         datetime    null comment '植株创建时间',
    update_time         datetime    null comment '更新时间'
)
    comment '用户植株表';

create index plat_plantation_user_plant_idx_openid
    on plat_plantation_user_plant (open_id);

create index plat_plantation_user_plant_idx_userid
    on plat_plantation_user_plant (user_id);

create index plat_plantation_user_plant_idx_usr_p_state
    on plat_plantation_user_plant (user_id, plant_state);


create table plat_plantation_user_task
(
    user_task_id     int auto_increment comment '用户任务id'
        primary key,
    task_id          int               null comment '任务id',
    user_id          char(32)          null comment '用户id',
    open_id          varchar(32)       null comment '会员微信openId',
    task_tips        varchar(50)       null comment '任务提示',
    task_type        tinyint           null comment '任务类型 1-一次性任务 2-多次可完成任务',
    task_class       tinyint(8)        NOT NULL DEFAULT '1' COMMENT '任务分组类型,1下单,2浏览,3领取券,4积分兑换,5问卷,6视频号,7邀请好友,8添加顾问,9连一连,10拼图',
    status           tinyint(4)        NOT NULL DEFAULT '0' COMMENT '状态 0=待完成，1=部分完成，2=已完成未领取，3=已完成已领取',
    issue_type       tinyint(4)        NOT NULL DEFAULT '0' COMMENT '发放方式 1=单次发放，2=完成发放',
    time_drip_number tinyint           null comment '完成所需次数',
    complete_num     tinyint           null comment '已完成次数',
    award_drip       tinyint           null comment '奖励水滴数',
    is_delete        tinyint default 0 not null comment '是否已删除(1:是, 0:否)',
    create_time      datetime          null comment '创建时间',
    update_time      datetime          null comment '更新时间'
)
    comment '用户任务表';

create index plat_plantation_user_task__idx_tid_uid
    on plat_plantation_user_task (task_id, user_id);


create table plat_plantation_task_log
(
    task_log_id   int auto_increment comment '任务明细id'
        primary key,
    task_id          int          null comment '任务id',
    user_task_id     int          null comment '用户任务id',
    user_id          char(32)     null comment '用户id',
    open_id          varchar(32)  null comment '会员微信openId',
    award_drip       tinyint      null comment '奖励水滴数',
    bill_code        varchar(32)  null comment '订单编号',
    link_id          int          null comment '任务链接id',
    ticket_id        int          null comment '券主键',
    create_time      datetime     null comment '创建时间',
    update_time      datetime     null comment '更新时间'
)
    comment '用户任务明细表';

create index plantation_task_log__idx_user_task_id
    on plat_plantation_task_log (user_task_id);




CREATE TABLE mall_hot_search (
  id                 int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  keyword            varchar(100) NOT NULL COMMENT '搜索关键词',
  search_count       int(11) unsigned NOT NULL DEFAULT '0' COMMENT '搜索总次数',
  status             tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态：0-禁用，1-启用',
  created_time       datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  updated_time       datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (id),
  UNIQUE KEY uk_keyword (keyword),
  KEY idx_search_count (search_count),
  KEY idx_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商城热搜词总表';


CREATE TABLE mall_sku_info (
  sku_id        int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'SKU主键',
  sku_name      varchar(100) NOT NULL COMMENT 'SKU名称',
  sku_price     int(11) unsigned NOT NULL DEFAULT '0' COMMENT '搜索总次数',
  sku_price     int(11) unsigned NOT NULL DEFAULT '0' COMMENT '搜索总次数',
  sku_price     int(11) unsigned NOT NULL DEFAULT '0' COMMENT '搜索总次数',
  sku_price     int(11) unsigned NOT NULL DEFAULT '0' COMMENT '搜索总次数',
  sku_price     int(11) unsigned NOT NULL DEFAULT '0' COMMENT '搜索总次数',
  spec          int(11) unsigned NOT NULL DEFAULT '0' COMMENT '搜索总次数',
  spu_id        int(11) unsigned NOT NULL DEFAULT '0' COMMENT '',
  status        tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态：0-禁用，1-启用',
  created_time  datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  updated_time  datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (id),
  UNIQUE KEY uk_keyword (keyword),
  KEY idx_search_count (search_count),
  KEY idx_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商城热搜词总表';

