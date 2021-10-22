<template>
  <div class="home">
    <!--轮播图-->
    <el-carousel
      class="swiper-container"
      :interval="4000"
      type="card"
      height="280px"
    >
      <el-carousel-item v-for="(item, index) in swiperList" :key="index">
        <img :src="item.picImg" />
      </el-carousel-item>
    </el-carousel>
    <!--电影列表-->
    <div class="section">
      <content-list
        :contentList="result.pageInfo"
        path="movie-detail"
      ></content-list>
    </div>
  </div>
</template>

<script>
import ContentList from '../components/ContentList'
import { swiperList } from '../assets/data/swiper'
import { HttpManager } from '../api/index'
import axios from 'axios'

export default {
  name: 'home',
  components: {
    ContentList
  },
  data () {
    return {
      swiperList: swiperList, // 轮播图列表
      result: [],
      loading: false
    }
  },
  created () {
    axios.get('http://82.157.177.72:8081/movie-nft-server/movie/batch-get-movies?currentPage=1&pageSize=100')
      // then获取成功；response成功后的返回值（对象）
      .then(response => {
        this.result = response.data
        this.loading = true
      })
      // 获取失败
      .catch(error => {
        console.log(error)
        alert('网络错误，不能访问')
      })
  },
  methods: {
    getSongList () {
      HttpManager.getSongList()
        .then((res) => {
          this.songList = res.sort().slice(0, 10)
        })
        .catch((err) => {
          console.log(err)
        })
    }
  }
}
</script>

<style lang="scss" scoped>
@import "../assets/css/home.scss";
</style>
