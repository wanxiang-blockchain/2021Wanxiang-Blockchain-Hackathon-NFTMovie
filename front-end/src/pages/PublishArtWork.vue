<template>
  <div class="signUp-page">
    <div class="signUp">
      <div class="signUp-head">
        <span>发布艺术品</span>
      </div>
      <el-form :model="artworkInfo" status-icon  ref="copyrightInfo" label-width="120px" class="demo-ruleForm">
<!--        <div style="width:500px;height:45px;border:2px solid #DD700C; background-color: #DD700C;align-content: center">-->
<!--          <el-form-item prop="username" label="选择电影" class="item">-->
<!--            <el-select v-model-->
<!--                       artworkInfo.location" placeholder="请输入" style="width:100%" class="input">-->
<!--              <el-option v-for="item in cities" :key="item.value" :label="item.label" :value="item.value"></el-option>-->
<!--            </el-select>-->
<!--          </el-form-item>-->
<!--        </div>-->
        <div style="width:500px;height:45px;border:2px solid #DD700C; background-color: #DD700C;align-content: center">
          <el-form-item prop="password" label="艺术品名称" class="item">
            <el-input  placeholder="请输入" v-model="artworkInfo.artworkName" class="input"></el-input>
          </el-form-item>
        </div>
        <br>
        <div style="width:500px;height:45px;border:2px solid #DD700C; background-color: #DD700C;align-content: center">
          <el-form-item prop="phoneNum" label="数量" class="item">
            <el-input  placeholder="请输入" v-model="artworkInfo.quantity" class="input"></el-input>
          </el-form-item>
        </div>
        <br>
        <div style="width:500px;height:45px;border:2px solid #DD700C; background-color: #DD700C;align-content: center">
          <el-form-item prop="email" label="价格" class="item">
            <el-input v-model="artworkInfo.price" placeholder="请输入" class="input"></el-input>
          </el-form-item>
        </div>
        <br>
        <div style="width:500px;height:45px;border:2px solid #DD700C; background-color: #DD700C;align-content: center">
          <el-form-item prop="birth" label="发行日期" class="item">
            <el-date-picker type="date" placeholder="选择日期" v-model="artworkInfo.publishTime" class="input" style="width: 100%;"></el-date-picker>
          </el-form-item>
        </div>
        <br>
        <!--        <el-form-item prop="birth" label="生日">-->
        <!--          <el-date-picker type="date" placeholder="选择日期" v-model
        artworkInfo.birth" style="width: 100%;"></el-date-picker>-->
        <!--        </el-form-item>-->
        <div style="width:500px;height:45px;border:2px solid #DD700C; background-color: #DD700C;align-content: center">
          <el-form-item prop="introduction" label="简介" class="item">
            <el-input  placeholder="请输入" v-model="artworkInfo.intro" class="input"></el-input>
          </el-form-item>
        </div>
        <br>
        <div style="width:500px;height:45px;border:2px solid #DD700C; background-color: #DD700C;align-content: center">
          <el-form-item prop="introduction" label="艺术品状态" class="item">
            <el-input  placeholder="请输入" v-model="artworkInfo.artworkStatus" class="input"></el-input>
          </el-form-item>
        </div>
        <br>
        <div style="width:500px;height:150px;border:2px solid #DD700C; background-color: #DD700C;align-content: center">
          <el-form-item  label="封面" class="item" >
            <el-upload
              ref="upload"
              action="http://82.157.177.72:8082/movie-nft-storage/file/uopload-image"
              accept="image/png,image/gif,image/jpg,image/jpeg"
              list-type="picture-card"
              class="input"
              :limit="1"
              :on-success="handle_success"
            >
              <i class="el-icon-plus"></i>
            </el-upload>
            <!--            <el-input  placeholder="点击上传" v-model="uploadInfo.preview" class="input"></el-input>-->
          </el-form-item>
        </div>
        <br>
        <div class="login-btn">
          <el-button type="primary" @click="SignUp" align="center">发布</el-button>
        </div>
      </el-form>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import mixin from '../mixins'
import loginLogo from '../components/LoginLogo'
import { rules, cities } from '../assets/data/form'
import {Qtum} from 'qtumjs'

export default {
  name: 'SignUp-page',
  mixins: [mixin],
  components: {
    loginLogo
  },
  data () {
    return {
      artworkInfo: { // 注册
        artworkName: '',
        quantity: '',
        price: '',
        publishTime: '',
        intro: '',
        artworkStatus: '',
        cover: ''
      },
      rules: rules,
      cities: cities
    }
  },
  methods: {
    handle_success (res) {
      this.artworkInfo.cover = res
      console.log(res)
    },
    async SignUp () {
      // const contractInfo = require('../../static/contractInfo.json')
      // const qtum = new Qtum('http://ang:qtum@localhost:8010', contractInfo)
      // // const qtum = new Qtum("http://test:test1234@152.136.130.81:13889", contractInfo);
      // const contract = qtum.contract('MovieNFT.sol')

      // const tx = await contract.send(
      //   'publishMovie',
      //   ['0814bfacfac50c2b0ce0688e9fba219a17fd934e', 'baidu.com/1'],
      //   {senderAddress: 'qgtn7J8W6r6c2bGyD59adicoMcnrCBpeN5', amount: '0.00'})
      //
      // alert('Success! Tx id:' + tx.txid)

      let d = this.artworkInfo.publishTime
      let datetime = d.getFullYear().toString() + '-' + (d.getMonth() + 1).toString() + '-' + d.getDate().toString()
      axios.post(`http://82.157.177.72:8081/movie-nft-server/artwork/upload?artworkName=${this.artworkInfo.artworkName}&quantity=${this.artworkInfo.quantity}&price=${this.artworkInfo.price}&publishTime=${datetime}&intro=${this.artworkInfo.intro}&artworkStatus=${this.artworkInfo.artworkStatus}&cover=${this.artworkInfo.cover}`)
      // then获取成功；response成功后的返回值（对象）
        .then(response => {
          alert('上传成功')
          console.log(response)
        })
        // 获取失败
        .catch(error => {
          console.log(error)
          alert('网络错误，不能访问')
        })
    }
  },
  goback (index) {
    this.$router.go(index)
  }
}
</script>

<style lang="scss" scoped>
@import '../assets/css/sign-up.scss';
</style>
