import elementZhLocale from 'element-ui/lib/locale/lang/zh-CN'// element-ui lang

export default {
  ...elementZhLocale,
  buttons: {
    search: '查询',
    add: '添加',
    run: '运行',
    export: '导出',
    edit: '编辑',
    delete: '删除',
    cancel: '取 消',
    confirm: '确 定'
  },
  route: {
    Dashboard: '首页',
    AlarmManager: '监控管理',
    DataQuery: '数据查询',
    DataManager: '数据管理',
    AccountManager: '账号管理',
    AlarmList: '监控列表',
    AlarmEdit: '监控编辑',
    ExecuteLog: '执行日志',
    MyMessage: '我的消息',
    MessageTemplate: '消息模板',
    Service: '服务信息',
    DataSource: '数据源',
    DataName: '数据名',
    Account: '账号信息',
    Team: '团队信息',
    Department: '部门信息'
  },
  login: {
    title: 'Frostmourne监控平台'
  },
  alarm: {
    list: {
      add: '添加报警',
      input_id: '输入id',
      input_name: '输入名称,支持模糊查询',
      input_status: '监控状态',
      input_service: '请选择服务',
      input_status_open: '开启',
      input_status_close: '关闭',

      header_alarm_name: '监控名称',
      header_alarm_type: '监控类型',
      header_is_open: '是否开启',
      header_last_execute_result: '最后执行结果',
      header_last_execute_time: '最后执行时间',
      header_owner_object: '所属对象',
      header_modifier: '最后修改人',
      header_last_modify_time: '最后修改时间',
      header_action: '操作'
    },
    edit: {
      label_basic: '基础信息',
      label_alarm_name: '监控名称',
      label_status_open: '开启',
      label_status_close: '关闭',
      label_service: '所属服务',
      label_risk: '风险等级',
      label_info: '提示',
      label_important: '重要',
      label_emergency: '紧急',
      label_crash: '我崩了',
      label_owner: '所属对象',
      label_owner_placeholder: '表示这个监控的归属对象标识',
      label_team: '团队',
      label_team_placeholder: '选择团队',
      label_description: '描述',
      label_data_config: '数据配置',
      label_data: '数据',
      label_aggregation_type: '聚合类型',
      label_pecentile: '百分比',
      label_percentile_placeholder: '例如: 90',
      label_aggregation_field: '聚合字段',
      label_bucket_type: '分桶类型',
      label_bucket_field: '分桶字段',

      input_service: '请选择服务'
    }
  },
  dashboard: {
    latest_30_day_alert_count: '最近30天报警次数',
    latest_30_day_message_count: '最近30天消息数',
    alarm_count: '监控数量',
    schedule_count: '调度次数',
    alert_count: '报警次数',
    message_count: '消息数量'
  }
}
