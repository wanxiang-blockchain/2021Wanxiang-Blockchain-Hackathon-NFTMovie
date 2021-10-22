<!--电影详情页-->
<template>
  <div class="song-list-album">
    <div class="album-slide">
      <div class="album-img">
        <img :src="movies.picImg" alt="" />
      </div>
    </div>
    <div class="movie-info">
        <h1>《{{ movies.chineseName }}》汇编权 <span>竞拍 {{ movies.enddate }} 日截止</span></h1>

        <span>
          ID: {{ movies.movieId }}
          <br>
          发行日期：{{ movies.publishTime }}
          <br>
          剧情：{{ movies.plot }}
        </span>
      </div>
      <button-style @onClick="buy" lick= class="buy-btn"
        btn="购买" :description= "movies.price + ' QTUM'" >
      </button-style>

    <div class = "info">
      <h2>区块链信息</h2>
    <ul class="section-content">
      <li class="content-item" v-for="(value, name) in movies.bcinfo">
        {{name}}:{{value}}
      </li>
    </ul>
    </div>

    <div class = "info">
    <h2>成交历史</h2>
    <table id="secondTable">
      <thead>
        <tr>
          <th v-for="col in columns">{{col}}</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="row in movies.tradehist">
          <td v-for="col in columns">{{row[col]}}</td>
        </tr>
      </tbody>
    </table>
    </div>
  </div>
</template>

<script>
import {Qtum} from 'qtumjs'
import mixin from '../mixins'
import ButtonStyle from '../components/ButtonStyle'
import { mapGetters } from 'vuex'
import { HttpManager } from '../api/index'

export default {
  name: 'song-list-album',
  mixins: [mixin],
  components: {
    ButtonStyle
  },
  data () {
    return {
      songLists: [],
      movies: {},
      count: 0, // 点赞数
      songListId: '', // 歌单ID
      value3: 0,
      value5: 0,
      activeName: '视频' // 默认选择栏
    }
  },
  computed: {
    ...mapGetters([
      'tempList'
    ]),
    'columns': function columns () {
      if (this.movies.tradehist == null) {
        return []
      }
      if (this.movies.tradehist.length == 0) {
        return []
      }
      return Object.keys(this.movies.tradehist[0])
    }
  },

  created () {
    this.movies = this.tempList
    console.log(this.movies)
    this.getSongId() // 获取歌单里面的歌曲ID
    this.getRank(this.songListId) // 获取评分
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
    }
  }
}
</script>

<style lang="scss" scoped>
@import "../assets/css/auction-detail.scss";
</style>
