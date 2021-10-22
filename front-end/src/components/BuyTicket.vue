<template>
  <div class="signUp-page">
    <div class="signUp">
      <div class="signUp-head">
        <span>购买电影票</span>
      </div>
      <el-form :model="registerForm" status-icon :rules="rules" ref="registerForm" label-width="120px" class="demo-ruleForm">
        <div style="width:500px;height:45px;border:2px solid #DD700C; background-color: #DD700C;align-content: center">
          <el-form-item prop="username" label="选择影院" class="item">
            <el-select v-model="registerForm.location" placeholder="请输入" style="width:100%" class="input">
              <el-option v-for="item in cinemas" :key="item.value" :label="item.label" :value="item.value"></el-option>
            </el-select>
          </el-form-item>
        </div>
        <br>
        <div style="width:500px;height:45px;border:2px solid #DD700C; background-color: #DD700C;align-content: center">
          <el-form-item  label="选择电影" class="item">
            <el-select  v-model="registerForm.username" placeholder="请输入" style="width:100%" class="input">
              <el-option v-for="item in res" :key="item.chineseName" :label="item.chineseName" :value="item.chineseName"></el-option>
            </el-select>
          </el-form-item>
        </div>
        <br>
        <div style="width:500px;height:45px;border:2px solid #DD700C; background-color: #DD700C;align-content: center">
          <el-form-item prop="introduction" label="价格" class="item">
            <el-input  placeholder="请输入" v-model="registerForm.introduction" class="input"></el-input>
          </el-form-item>
        </div>
        <br>
        <div class="login-btn">
          <el-button type="primary" @click="buy" align="center">购买</el-button>
        </div>
      </el-form>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import Qtum from 'qtumjs'
import mixin from '../mixins'
import loginLogo from '../components/LoginLogo'
import { rules, cinemas } from '../assets/data/form'
import { HttpManager } from '../api/index'

export default {
  name: 'BuyTicket',
  mixins: [mixin],
  components: {
    loginLogo
  },
  created () {
    axios.get('http://82.157.177.72:8081/movie-nft-server/movie/batch-get-movies?currentPage=1&pageSize=100')
      // then获取成功；response成功后的返回值（对象）
      .then(response => {
        this.res = response.data.pageInfo
      })
      // 获取失败
      .catch(error => {
        console.log(error)
        alert('网络错误，不能访问')
      })
  },
  data () {
    return {
      registerForm: { // 注册
        username: '',
        password: '',
        sex: '',
        phoneNum: '',
        email: '',
        birth: '',
        introduction: '',
        location: ''
      },
      rules: rules,
      cinemas: cinemas,
      res: []
    }
  },
  methods: {
    async buy () {
      alert('购买成功')
      const contractInfo = require('../../static/contractInfo.json')
      const qtum = new Qtum('http://ang:qtum@localhost:8010', contractInfo)
      // const qtum = new Qtum("http://test:test1234@152.136.130.81:13889", contractInfo);
      const contract = qtum.contract('MovieNFT.sol')

      const tx = await contract.send(
        'publishMovie',
        ['0814bfacfac50c2b0ce0688e9fba219a17fd934e', 'baidu.com/1'],
        {senderAddress: 'qgtn7J8W6r6c2bGyD59adicoMcnrCBpeN5', amount: '0.00'})
      alert('Success! Tx id:' + tx.txid)
    },
    goback (index) {
      this.$router.go(index)
    }
  }
}
</script>

<style lang="scss" scoped>
@import '../assets/css/buyticket.scss';
</style>
