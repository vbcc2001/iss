DROP TABLE IF EXISTS t_user_info;
CREATE TABLE t_user_info (
  id int(10) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  name varchar(100) DEFAULT NULL COMMENT '用户名称',
  sex varchar(100) DEFAULT NULL COMMENT '性别',
  age int(10) DEFAULT NULL COMMENT '年龄', 
  com varchar(100) DEFAULT NULL COMMENT '公司（医院）',
  dept varchar(100) DEFAULT NULL COMMENT '部门（科室）',
  post varchar(100) DEFAULT NULL COMMENT '岗位（职称）',
  tel varchar(100) DEFAULT NULL COMMENT '电话',
  email varchar(100) DEFAULT NULL COMMENT '电子邮箱',
  phone varchar(20) DEFAULT NULL COMMENT '手机号',
  user_type varchar(100) DEFAULT NULL COMMENT '用户类型',
  user_code varchar(100) DEFAULT NULL COMMENT '用户识别码可以是身份证、手机号等',
  weixin_open_id  varchar(100) DEFAULT NULL COMMENT '微信用户唯一标识符',  
  weixin_id varchar(30) NOT NULL COMMENT '微信公众账号ID',
  create_time  timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (id)
);
DROP TABLE IF EXISTS t_patient_info;
CREATE TABLE t_patient_info (
  id int(10) NOT NULL AUTO_INCREMENT,
  patient_code varchar(100) DEFAULT NULL COMMENT '患者识别码',   
  user_code varchar(100) DEFAULT NULL COMMENT '关联用户',  
  name varchar(100) DEFAULT NULL COMMENT '患者名称',
  sex varchar(100) DEFAULT NULL COMMENT '性别',
  age int(10) DEFAULT NULL COMMENT '年龄',
  address varchar(100) DEFAULT NULL COMMENT '地址',
  phone varchar(20) DEFAULT NULL COMMENT '电话',
  hospital varchar(100) DEFAULT NULL COMMENT '治疗医院',
  ill_date varchar(100) DEFAULT NULL COMMENT '受伤日期',
  ill_type varchar(100) DEFAULT NULL COMMENT '受伤类型',
  iss_score int(10) DEFAULT NULL COMMENT 'ISS评分',
  result varchar(100) DEFAULT NULL COMMENT '后续结果',
  create_time  timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (id)
) ;
