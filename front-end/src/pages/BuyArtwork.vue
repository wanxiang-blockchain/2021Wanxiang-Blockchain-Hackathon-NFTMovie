<template>
  <div class="singer">

    <div class="section">
<!--      <div class="section-title">拍卖中</div>-->
<!--      <auction-list-->
<!--        :auctionList ="movieList.pageInfo"-->
<!--        path="BuyArtwork/Auction"-->
<!--      ></auction-list>-->

      <div class="section-title">定价销售</div>
      <fixed-list
        :fixedList ="fixedartworks.pageInfo"
        path="BuyArtwork/FixedPrice"
      ></fixed-list>

    </div>
  </div>
</template>

<script>
import AuctionList from '../components/AuctionList'
import FixedList from '../components/FixedList'
import { HttpManager } from '../api/index'
import axios from 'axios'
export default {
  name: 'buy-artwork',
  components: {
    AuctionList,
    FixedList
  },
  data () {
    return {
      activeName: '全部电影',
      pageSize: 15, // 页数
      currentPage: 1, // 当前页
      albumDatas: [],
      movieList: [], // 歌单列表
      fixedartworks: []
    }
  },
  computed: {
    // 计算当前表格中的数据
    data () {
      return this.albumDatas.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize)
    }
  },
  created () {
    axios.get('http://82.157.177.72:8081/movie-nft-server/artwork/batch-get-artworks?currentPage=1&pageSize=100')
      // then获取成功；response成功后的返回值（对象）
      .then(response => {
        this.movieList = response.data
        this.fixedartworks = response.data
        this.loading = true
        console.log(this.movieList)
      })
      // 获取失败
      .catch(error => {
        console.log(error)
        alert('网络错误，不能访问')
      })
  },
  methods: {
    // 获取当前页

  }
}
</script>

<style lang="scss" scoped>
@import '../assets/css/buy-artwork.scss';
</style>
