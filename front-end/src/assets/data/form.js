// 匹配规则
const rules = {
  username: [
    { required: true, trigger: 'blur' }
  ],
  password: [
    { required: true, trigger: 'blur' }
  ],
  sex: [
    { required: true, message: '请选择性别', trigger: 'change' }
  ],
  phoneNum: [
    { essage: '请选择日期', trigger: 'blur' }
  ],
  email: [
    { message: '请输入邮箱地址', trigger: 'blur' },
    { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }
  ],
  birth: [
    { required: true, type: 'date', message: '请选择日期', trigger: 'change' }
  ],
  introduction: [
    { message: '请输入介绍', trigger: 'blur' }
  ],
  location: [
    { message: '请输入地区', trigger: 'change' }
  ]
}

// 地区选择
const cities = [{
  value: '中国',
  label: '中国'
}, {
  value: '美国',
  label: '美国'
}, {
  value: '中国香港',
  label: '中国香港'
}, {
  value: '中国台湾',
  label: '中国台湾'
}]
const cinemas = [{
  value: '万达',
  label: '万达'
}, {
  value: '橙天嘉禾',
  label: '橙天嘉禾'
}, {
  value: 'UME影城',
  label: 'UME影城'
}, {
  value: '金逸影城',
  label: '金逸影城'
}]

export {
  rules,
  cities,
  cinemas
}
