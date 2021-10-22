<!--电影详情页-->
<template>
  <div class="song-list-album">
    <div class="album-slide">
      <div class="album-img">
        <img :src="url + currentMovie.preview" alt="" />
      </div>
      <div class="movie-info">
        <h1>{{ currentMovie.chineseName }}</h1>
        <span>
          导演：{{ currentMovie.director }}
          <br>
          发行日期：{{ currentMovie.publishTime }}
          <br>
          剧情：{{ currentMovie.plot }}
        </span>
      </div>
    </div>
    <ul class="song-list-header">
      <li
        v-for="(item, index) in songStyle"
        :key="index"
        :class="{ active: item.name === activeName }"
        @click="handleChangeView(item.name)"
      >
        {{ item.name }}
      </li>
    </ul>
    <Video :copyrightMovies ="movies"
                   path="MovieDetail" v-if="selected === 0"/>
    <CopyrightList :copyrightMovies ="movies"
                   :type="true"
                   path="BuyCopyright" v-if="selected === 1"/>
    <AuctionList
      :auctionList ="movies"
      :type="true"
      path="BuyArtwork/Auction" v-if="selected === 2"/>
<!--    <FixedList-->
<!--      :fixedList ="movies"-->
<!--      path="BuyArtwork/FixedPrice"-->
<!--      v-if="selected === 2"-->
<!--    ></FixedList>-->
    <BuyTicket v-if="selected === 3"/>
  </div>
</template>

<script>
import axios from 'axios'
import mixin from '../mixins'
import AlbumContent from '../components/AlbumContent'
import Comment from '../components/Comment'
import { songStyle } from '../assets/data/songList'
import { mapGetters } from 'vuex'
import { HttpManager } from '../api/index'
import AuctionList from '../components/AuctionList'
import CopyrightList from '../components/CopyrightList'
import FixedList from '../components/FixedList.vue'
import Video from '../components/Video'
import BuyTicket from '../components/BuyTicket'

export default {
  name: 'song-list-album',
  mixins: [mixin],
  components: {
    AlbumContent,
    Comment,
    AuctionList,
    CopyrightList,
    Video,
    BuyTicket,
    FixedList
  },
  data () {
    return {
      movies: '',
      count: 0, // 点赞数
      songListId: '', // 歌单ID
      value3: 0,
      value5: 0,
      activeName: '视频', // 默认选择栏
      songStyle: songStyle, // 歌手导航栏类别
      selected: 0,
      currentMovie: '',
      url: 'http://82.157.177.72:8082/'
    }
  },
  computed: {
    ...mapGetters([
      'loginIn', // 登录标识
      'tempList', // 单个歌单信息
      'listOfSongs', // 存放的音乐
      'userId', // 用户ID
      'avator' // 用户头像
    ])
  },
  mounted () {
    this.handleChangeView('视频')
  },
  created () {
    axios.get(`http://82.157.177.72:8081/movie-nft-server/movie/movie-info?movieId=${this.$route.params.movieId}`)
      .then(response => {
        this.currentMovie = response.data
      })
      .catch(error => {
        console.log(error)
        alert('网络错误，不能访问')
      })
    axios.get('http://82.157.177.72:8081/movie-nft-server/movie/batch-get-movies?currentPage=1&pageSize=100')
      // then获取成功；response成功后的返回值（对象）
      .then(response => {
        this.movies = response.data.pageInfo
        this.loading = true
      })
      // 获取失败
      .catch(error => {
        console.log(error)
        alert('网络错误，不能访问')
      })
    console.log(this.movies)
  },
  methods: {
    // 收集歌单里面的歌曲
    getSongId () {
      HttpManager.getListSongOfSongId(this.songListId)
        .then((res) => {
          // 获取歌单里的歌曲信息
          for (let item of res) {
            this.getSongList(item.songId)
          }
          this.$store.commit('setListOfSongs', this.songLists)
        })
        .catch((err) => {
          console.log(err)
        })
    },
    // 点击header 切换类别时
    handleChangeView: function (name) {
      this.activeName = name
      this.albumDatas = []
      switch (name) {
        case '链上版权':
          this.selected = 1
          break
        case '链上艺术品':
          this.selected = 2
          break
        case '购买电影票':
          this.selected = 3
          break
        case '视频':
          this.selected = 0
          break
      }
      console.log(this.selected)
    },

    // 获取单里的歌曲
    getSongList (id) {
      HttpManager.getSongOfId(id)
        .then((res) => {
          this.songLists.push(res[0])
        })
        .catch((err) => {
          console.log(err)
        })
    },
    // 获取评分
    getRank (id) {
      HttpManager.getRankOfSongListId(id)
        .then((res) => {
          this.value5 = res / 2
        })
        .catch((err) => {
          console.log(err)
        })
    },
    // 提交评分
    pushValue () {
      if (this.loginIn) {
        let params = new URLSearchParams()
        params.append('songListId', this.songListId)
        params.append('consumerId', this.userId)
        params.append('score', this.value3 * 2)
        HttpManager.setRank(params)
          .then((res) => {
            if (res.code === 1) {
              this.getRank(this.songListId)
              this.notify('评分成功', 'success')
            } else {
              this.notify('评分失败', 'error')
            }
          })
          .catch((err) => {
            console.log(err)
          })
      } else {
        this.value3 = null
        this.notify('请先登录', 'warning')
      }
    }
  }
}
</script>

<style lang="scss" scoped>
@import "../assets/css/movie-detail.scss";
</style>
