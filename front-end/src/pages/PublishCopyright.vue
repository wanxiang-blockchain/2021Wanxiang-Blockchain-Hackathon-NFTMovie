<template>
  <div class="signUp-page">
    <div class="signUp">
      <div class="signUp-head">
        <span>发布版权</span>
      </div>
      <el-form :model="copyrightInfo" status-icon  ref="copyrightInfo" label-width="120px" class="demo-ruleForm">
        <div style="width:500px;height:45px;border:2px solid #DD700C; background-color: #DD700C;align-content: center">
          <el-form-item  label="选择电影" class="item">
            <el-select v-model="copyrightInfo.movieId" placeholder="请输入" style="width:100%" class="input">
              <el-option v-for="item in res" :key="item.movieId" :label="item.movieId" :value="item.movieId"></el-option>
            </el-select>
          </el-form-item>
        </div>
        <br>
        <div style="width:500px;height:45px;border:2px solid #DD700C; background-color: #DD700C;align-content: center">
          <el-form-item  label="选择版权类型" class="item">
            <el-select v-model="copyrightInfo.copyrightType" placeholder="请输入" style="width:100%" class="input">
              <el-option v-for="(item,index) in 3" :key="index" :label="index" :value="index"></el-option>
            </el-select>
          </el-form-item>
        </div>
        <br>
        <div style="width:500px;height:45px;border:2px solid #DD700C; background-color: #DD700C;align-content: center">
          <el-form-item  label="价格" class="item">
            <el-input  placeholder="请输入" v-model="copyrightInfo.price" class="input"></el-input>
          </el-form-item>
        </div>
        <br>
        <div style="width:500px;height:45px;border:2px solid #DD700C; background-color: #DD700C;align-content: center">
          <el-form-item  label="数量" class="item">
            <el-input v-model="copyrightInfo.quantity" placeholder="请输入" class="input"></el-input>
          </el-form-item>
        </div>
        <br>
        <!--        <el-form-item prop="birth" label="生日">-->
        <!--          <el-date-picker type="date" placeholder="选择日期" v-model="copyrightInfo.birth" style="width: 100%;"></el-date-picker>-->
        <!--        </el-form-item>-->
        <div style="width:500px;height:45px;border:2px solid #DD700C; background-color: #DD700C;align-content: center">
          <el-form-item  label="收益分成" class="item">
            <el-input  placeholder="请输入" v-model="copyrightInfo.share" class="input"></el-input>
          </el-form-item>
        </div>
        <br>
        <div class="login-btn">
          <el-button type="primary" @click="Publish" align="center">发布</el-button>
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
import { HttpManager } from '../api/index'

export default {
  name: 'SignUp-page',
  mixins: [mixin],
  components: {
    loginLogo
  },
  created () {
    axios.get('http://82.157.177.72:8081/movie-nft-server/movie/batch-get-movies?currentPage=1&pageSize=100')
      // then获取成功；response成功后的返回值（对象）
      .then(response => {
        this.res = response.data.pageInfo
        console.log(this.res)
      })
      // 获取失败
      .catch(error => {
        console.log(error)
        alert('网络错误，不能访问')
      })
  },
  data () {
    return {
      copyrightInfo: { // 注册
        movieId: '',
        copyrightType: '',
        price: '',
        quantity: '',
        share: '',
        recordNumber: ''
      },
      res: [],
      rules: rules
    }
  },
  methods: {
    async Publish () {
      let i = 0
      while (i < this.res.length) {
        if (this.copyrightInfo.movieId === this.res[i].movieId) {
          this.copyrightInfo.recordNumber = this.res[i].recordNumber
          break
        }
      }
      // const contractInfo = require('../../static/contractInfo.json')
      // const qtum = new Qtum('http://ang:qtum@localhost:8010', contractInfo)
      // // const qtum = new Qtum("http://test:test1234@152.136.130.81:13889", contractInfo);
      // const contract = qtum.contract('MovieNFT.sol')
      //
      // const tx = await contract.send(
      //   'publishMovie',
      //   ['0814bfacfac50c2b0ce0688e9fba219a17fd934e', 'baidu.com/1'],
      //   {senderAddress: 'qgtn7J8W6r6c2bGyD59adicoMcnrCBpeN5', amount: '0.00'})
      //
      // alert('Success! Tx id:' + tx.txid)

      axios.post(`http://82.157.177.72:8081/movie-nft-server/movie/upload-copyright?movieId=${this.copyrightInfo.movieId}&copyrightType=${this.copyrightInfo.copyrightType}&price=${this.copyrightInfo.price}&quantity=${this.copyrightInfo.quantity}&share=${this.copyrightInfo.share}&recordNumber=${this.copyrightInfo.recordNumber}`)
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
    // params.append('avator', '/img/user.jpg')
    // HttpManager.SignUp(params)
    //   .then(res => {
    //     console.log(res)
    //     if (res.code === 1) {
    //       _this.notify('注册成功', 'success')
    //       setTimeout(function () {
    //         _this.$router.push({path: '/'})
    //       }, 2000)
    //     } else {
    //       _this.notify('注册失败', 'error')
    //     }
    //   })
    //   .catch(err => {
    //     console.log(err)
    //   })
  },
  goback (index) {
    this.$router.go(index)
  }
}
</script>

<style lang="scss" scoped>
@import '../assets/css/sign-up.scss';
</style>
