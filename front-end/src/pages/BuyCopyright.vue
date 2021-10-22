<template>
  <div class="singer">

    <div class="section">
      <div class="section-title">电影列表</div>
      <copyright-list
        :copyrightMovies ="result.pageInfo"
        :type="true"
        path="BuyCopyright"
      ></copyright-list>
    </div>
  </div>
</template>

<script>
import CopyrightList from '../components/CopyrightList'
import axios from 'axios'

export default {
  name: 'buy-copyright',
  components: {
    CopyrightList
  },
  data () {
    return {
      activeName: '全部电影',
      pageSize: 15, // 页数
      currentPage: 1, // 当前页
      albumDatas: [],
      result: []
    }
  },
  computed: {
    // 计算当前表格中的数据
    data () {
      return this.albumDatas.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize)
    }
  },
  created () {
    axios.get('http://82.157.177.72:8081/movie-nft-server/movie/batch-get-movies?currentPage=1&pageSize=100')
      // then获取成功；response成功后的返回值（对象）
      .then(response => {
        this.result = response.data
        this.loading = true
        console.log(this.result)
      })
      // 获取失败
      .catch(error => {
        console.log(error)
        alert('网络错误，不能访问')
      })
  },
  methods: {
    // 获取当前页
    handleCurrentChange (val) {
      this.currentPage = val
    },
    handleChangeView (item) {
      this.activeName = item.name
      this.albumDatas = []
      if (item.name === '全部歌手') {
        this.getAllSinger()
      } else {
        this.getSingerSex(item.type)
      }
    }
  }
}
</script>

<style lang="scss" scoped>
@import '../assets/css/buycopyright.scss';
</style>
