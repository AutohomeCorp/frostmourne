// 格式化日期
const formatDate = function (date, format) {
  var o = {
    'M+': date.getMonth() + 1, // 月份
    'd+': date.getDate(), // 日
    'h+': date.getHours(), // 小时
    'm+': date.getMinutes(), // 分
    's+': date.getSeconds(), // 秒
    'q+': Math.floor((date.getMonth() + 3) / 3), // 季度
    S: date.getMilliseconds() // 毫秒
  }
  if (/(y+)/.test(format)) {
    format = format.replace(RegExp.$1, (date.getFullYear() + '').substr(4 - RegExp.$1.length))
  }
  for (var k in o) {
    if (new RegExp('(' + k + ')').test(format)) {
      format = format.replace(RegExp.$1, RegExp.$1.length === 1 ? o[k] : ('00' + o[k]).substr(('' + o[k]).length))
    }
  }
  return format
}

const getDateByStr = function (dateStr) {
  if (typeof dateStr !== 'string') {
    return null
  }
  if (dateStr.indexOf('T') > 0 || dateStr.indexOf('-') >= 0) {
    return getDateByJsonStr(dateStr)
  }
  var times = Date.parse(dateStr)
  return new Date(times)
}

// 处理2018-01-11T22:41:45 类型的JSON日期格式
const getDateByJsonStr = function (dateStr) {
  dateStr = dateStr.replace('T', ' ')
  if (dateStr.indexOf('.') > 0) {
    dateStr = dateStr.replace(/\.\d+/, '')
    // console.log(dateStr)
  }
  return new Date(dateStr.replace(/-/g, '/'))
}
// 格式话JSON日期
const formatJsonDate = function (date, format) {
  if (/1[0-9]{12}/.test(date)) {
    date = new Date(date)
  } else if (typeof date === 'string') {
    date = getDateByStr(date)
  }
  return formatDate(date, format)
}
// 返回
const datetimeFormat = function (value, formater) {
  if (!value) {
    return ''
  }
  return formatJsonDate(value, formater)
}

const formatDateText = (pass, current) => {
  const num = parseInt(pass)
  const date = new Date(num)
  const time = date.getTime()
  const _time = current - time
  if (_time / (60 * 60 * 24 * 365 * 1000) >= 1) {
    return '1年前'
  }
  if (_time / (60 * 60 * 24 * 30 * 1000) >= 6) {
    return '半年前'
  }
  if (_time / (60 * 60 * 24 * 30 * 1000) >= 1) {
    return parseInt(_time / (60 * 60 * 24 * 30 * 1000)) + '月前'
  }
  if (_time / (60 * 60 * 24 * 1000) >= 1) {
    return parseInt(_time / (60 * 60 * 24 * 1000)) + '天前'
  }
  if (_time / (60 * 60 * 1000) >= 1) {
    return parseInt(_time / (60 * 60 * 1000)) + '小时前'
  }
  if (_time / (60 * 1000) >= 1) {
    return parseInt(_time / (60 * 1000)) + '分钟前'
  }
  if (_time < 60 * 1000) {
    return '刚刚'
  }
}
const formatTimeText = dateTimeStrs => {
  const pass = parseDateTime(dateTimeStrs)
  if (isNaN(pass * 1)) {
    return dateTimeStrs
  } else {
    return formatDateText(pass, new Date().getTime())
  }
}

const parseDateTime = value => {
  const reg = /(\d{4})\D(\d{2})\D(\d{2})\D+(\d{2})\D(\d{2})\D(\d{2}).*/
  const result = value.match(reg)
  if (valueParseResult(result)) {
    let mils = new Date(result[1], result[2] - 1, result[3]).getTime()
    mils += result[4] * 3600 * 1000
    mils += result[5] * 60 * 1000
    mils += result[6] * 1000
    // console.log(new Date(mils).toFormat('yyyy-MM-dd hh:mm:ss'))
    return mils
  }
  return value
}
const valueParseResult = value => {
  if (value && value.length === 7) {
    return (value[1] + '').length === 4 && (value[2] + '').length === 2 && (value[3] + '').length === 2 && (value[4] + '').length === 2 && (value[5] + '').length === 2 && (value[6] + '').length === 2
  }
  return false
}

export { datetimeFormat, formatJsonDate, formatDateText, parseDateTime, valueParseResult, formatTimeText }

