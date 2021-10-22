<template>
  <div class="content-list">
    <ul class="section-content" v-if="result.length">
      <li class="content-item" v-for="(item, index) in result.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize)" :key="index">
        <div class="kuo" @click="goAblum(item)">
          <img class="item-img" :src="url + copyrightMovies[index].preview" alt="">
            <div class="caption">{{item.recordNumber}}<br> {{item.copyrightType}} </div>

<!--          <div class="mask"  @click="goAblum(item)">-->
<!--            <svg class="icon" aria-hidden="true">-->
<!--              <use :xlink:href="BOFANG"></use>-->
<!--            </svg>-->
<!--          </div>-->
        </div>
        <p class="item-name">所剩数量：{{item.remainQuantity}}</p>
        <p class="item-name">收益分成：{{item.share}}</p>
        <p class="item-name">价格：{{item.price}}</p>
        <button-style v-if="type" @onClick="buy"
        btn="购买" :description= "item.price + ' QTUM'" >
        </button-style>
      </li>
    </ul>
    <ul v-else>
      <li class="content-item" v-for="(item, index) in result.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize)" :key="index">
        <div class="kuo" @click="goAblum(result)">
          <img class="item-img" :src="url+result.preview" alt="">
          <div class="caption">《{{result.recordNumber}}》<br> {{result.copyrightType}} </div>
          <div class="mask"  @click="goAblum(result)">
          </div>
        </div>
        <p class="item-name">所剩数量：{{result.remainQuantity}}</p>
        <p class="item-name">收益分成：{{result.share}}</p>
        <p class="item-name">价格：{{result.price}}</p>
        <button-style v-if="type" @onClick="buy"
                      btn="购买" :description= "result.price + ' QTUM'" >{{this.type}}
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
import mixin from '../mixins'
import { ICON } from '../assets/icon/index'
import ButtonStyle from '../components/ButtonStyle.vue'
import Qtum from 'qtumjs'

export default {
  name: 'copyright-list',
  mixins: [mixin],
  components: {
    ButtonStyle
  },
  async created () {
    axios.get('http://82.157.177.72:8081/movie-nft-server/movie/batch-get-copyright?currentPage=1&pageSize=100')
    // then获取成功；response成功后的返回值（对象）
      .then(response => {
        this.result = response.data.pageInfo
        this.loading = true
        // setTimeout(() => {
        //   for (let i = 0; i < this.result.length; i++) {
        //     console.log(this.result[i].movieId)
        //     axios.get(`http://82.157.177.72:8081/movie-nft-server/movie/movie-info?movieId=${this.result[i].movieId}`)
        //       .then(response => {
        //         this.previews[i] = response.data.preview
        //         console.log(this.previews[i])
        //       })
        //   }
        // }, 0)
      })
      // 获取失败
      .catch(error => {
        console.log(error)
        alert('网络错误，不能访问')
      })
    console.log(this.type)
  },
  props: {
    copyrightMovies: Array,
    path: String,
    type: Boolean
  },
  data () {
    return {
      url: 'http://82.157.177.72:8082/',
      currentPage: 1,
      pageSize: 3,
      result: '',
      previews: []
    }
  },
  methods: {
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
    handleCurrentChange (val) {
      this.currentPage = val
      console.log(this.copyrightMovies.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize))
    }
  }
}
</script>

<style lang="scss" scoped>
@import '../assets/css/copyright-list.scss';
</style>
