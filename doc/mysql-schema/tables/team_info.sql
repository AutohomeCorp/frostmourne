DROP TABLE IF EXISTS team_info;
CREATE TABLE IF NOT EXISTS team_info
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '自增主键',
    team_name     VARCHAR(200) NOT NULL COMMENT '团队名称',
    full_name     VARCHAR(200) NOT NULL COMMENT '全称，一般是中文名字',
    department_id BIGINT       NOT NULL COMMENT '部门ID',
    creator       VARCHAR(200) NOT NULL COMMENT '创建人',
    create_at     DATETIME     NOT NULL COMMENT '创建时间',
    modify_at     DATETIME     NOT NULL COMMENT '修改时间',
    modifier      VARCHAR(200) NOT NULL COMMENT '最后修改人'
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '团队信息';

ALTER TABLE team_info
    ADD INDEX idx_teamname (team_name);

CREATE UNIQUE INDEX uniq_teamname ON team_info (team_name);

INSERT INTO team_info(team_name, full_name, department_id, creator, create_at, modify_at, modifier)
VALUES ('default', '炒鸡赛亚人', 1, 'admin', now(), now(), 'admin');