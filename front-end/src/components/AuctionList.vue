<template>
  <div class="content-list">
    <ul class="section-content" v-if="result.length">
      <li class="content-item" v-for="(item, index) in result.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize)" :key="index">
        <div class="kuo" @click="goAblum(item)">
          <img class="item-img" :src="url + item.cover" alt="">
          <div class="mask"  @click="goAblum(item)">
            <svg class="icon" aria-hidden="true">
              <use :xlink:href="BOFANG"></use>
            </svg>
          </div>
        </div>
        <p class="item-name">艺术品名称：{{item.artworkName}}</p>
        <p class="item-name">ID：{{item.artworkId}}</p>
        <p class="item-name">发行时间：{{item.publishTime}}</p>

        <button-style v-if="type" @onClick="buy" class="btn"
        btn="竞拍" :description= "'当前 ' + item.price + ' QTUM'" >
        </button-style>
      </li>
    </ul>
    <ul v-else>
      <li class="content-item" v-for="(item, index) in result
     .slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize)" :key="index">
        <div class="kuo" @click="goAblum(result)">
          <img class="item-img" :src="url+result.cover" alt="">
          <!-- class="caption"> 竞拍{{result.enddate}}结束</div-->
          <div class="mask"  @click="goAblum(result)">
            <svg class="icon" aria-hidden="true">
              <use :xlink:href="BOFANG"></use>
            </svg>
          </div>
        </div>
        <p class="item-name">{{result.artworkName}}</p>
        <p class="item-name">ID：{{result.artworkId}}</p>
        <p class="item-name">{{result.publishTime}}</p>

        <button-style @onClick="buy"
                      btn="竞拍" :description= "'当前 ' + auctionList.price + ' QTUM'" >
        </button-style>
      </li>
    </ul>
    <div class="pagination">
      <el-pagination
        align="right"
        @current-change="handleCurrentChange"
        background
        layout="total, prev, pager, next"
        :current-page="currentPage"
        :page-size="pageSize"
        :total="result.length">
      </el-pagination>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import Qtum from 'qtumjs'
import mixin from '../mixins'
import { ICON } from '../assets/icon/index'
import ButtonStyle from './ButtonStyle.vue'

export default {
  name: 'auction-list',
  mixins: [mixin],
  components: {
    ButtonStyle
  },
  created () {
    axios.get('http://82.157.177.72:8081/movie-nft-server/artwork/batch-get-artworks?currentPage=1&pageSize=100')
    // then获取成功；response成功后的返回值（对象）
      .then(response => {
        this.result = response.data.pageInfo
        this.loading = true
        console.log(this.result)
      })
    // 获取失败
      .catch(error => {
        console.log(error)
        alert('网络错误，不能访问')
      })
  },
  props: {
    auctionList: Array,
    path: String,
    type: Boolean
  },
  data () {
    return {
      BOFANG: ICON.BOFANG,
      url: 'http://82.157.177.72:8082/',
      currentPage: 1,
      pageSize: 6,
      result: ''
    }
  },
  methods: {
    onClick () {
      this.price = '当前' + this.price + 'qtum'
      this.$emit('onClick')
    },
    async buy () {
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
    goMovie (item) {
      this.$store.commit('setTempList', item)
      this.$router.push({path: `/${this.path}/${item.movieId}`})
    },
    handleCurrentChange (val) {
      this.currentPage = val
      console.log(this.auctionList.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize))
    }
  }
}
</script>

<style lang="scss" scoped>
@import '../assets/css/auction-list.scss';
@import '../assets/css/button-style.scss';
.input>>>.el-input__inner{
  margin-top: 2px;
  background-color: #262626;
  color: #DD700C;
  border-color: #DD700C;
}
</style>
