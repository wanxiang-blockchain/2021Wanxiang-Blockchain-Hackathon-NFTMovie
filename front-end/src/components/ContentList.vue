<template>
  <div class="content-list">
    <ul class="section-content">
      <li class="content-item" v-for="(item, index) in contentList.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize)" :key="index">
        <div class="kuo" @click="goMovieDetail(item)">
          <img class="item-img" :src="url + item.preview" alt="">
          <div class="mask"  @click="goMovieDetail(item)">
            <svg class="icon" aria-hidden="true">
              <use :xlink:href="BOFANG"></use>
            </svg>
          </div>
        </div>
        <p class="item-name">{{item.chineseName}}</p>
        <p class="item-name">{{item.director}}</p>
        <p class="item-name">{{item.publishTime}}</p>
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
        :total="contentList.length">
      </el-pagination>
    </div>
  </div>
</template>

<script>
import mixin from '../mixins'
import { ICON } from '../assets/icon/index'
import ButtonStyle from '../components/ButtonStyle.vue'

export default {
  name: 'content-list',
  mixins: [mixin],
  components: {
    ButtonStyle
  },
  props: {
    contentList: Array,
    path: String
  },
  data () {
    return {
      BOFANG: ICON.BOFANG,
      url: 'http://82.157.177.72:8082/',
      currentPage: 1,
      pageSize: 6
    }
  },
  methods: {
    handleCurrentChange (val) {
      this.currentPage = val
    },
    goMovieDetail (item) {
      this.$store.commit('setTempList', item)
      this.$router.push({path: `/${this.path}/${item.movieId}`})
    }
  }
}
</script>

<style lang="scss" scoped>
@import '../assets/css/content-list.scss';
</style>
