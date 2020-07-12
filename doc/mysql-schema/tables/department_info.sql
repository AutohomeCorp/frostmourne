DROP TABLE IF EXISTS department_info;
CREATE TABLE IF NOT EXISTS department_info
(
    id              BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '自增主键',
    department_name VARCHAR(200) NOT NULL COMMENT '部门名称',
    full_name       VARCHAR(200) NOT NULL COMMENT '全称，一般是中文名字',
    creator         VARCHAR(200) NOT NULL COMMENT '创建人',
    create_at       DATETIME     NOT NULL COMMENT '创建时间',
    modify_at       DATETIME     NOT NULL COMMENT '修改时间',
    modifier        VARCHAR(200) NOT NULL COMMENT '最后修改人'
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '部门信息';

CREATE UNIQUE INDEX uniq_departmentname ON department_info (department_name);

INSERT INTO department_info(department_name, full_name, creator, create_at, modify_at, modifier)
VALUES ('default', '默认部门', 'admin', now(), now(), 'admin');