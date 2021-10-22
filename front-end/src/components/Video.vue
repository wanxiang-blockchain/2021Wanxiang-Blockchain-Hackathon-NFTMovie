<template>
  <div class="content-list">
    <ul class="section-content" v-if="copyrightMovies.length">
      <li class="content-item" v-for="(item, index) in copyrightMovies" :key="index">
        <div class="kuo" @click="goAblum(item)">
          <img class="item-img" :src="url+ copyrightMovies[index].preview" alt="">
          <div class="mask"  @click="goAblum(item)">
            <svg class="icon" aria-hidden="true">
              <use :xlink:href="BOFANG"></use>
            </svg>
          </div>
        </div>
        <p class="item-name">所剩数量：{{2}}</p>
        <p class="item-name">收益分成：{{1}}</p>
        <p class="item-name">{{item.releasedate}}</p>
        <button-style @onClick="buy"
                      btn="打赏" >
        </button-style>
      </li>
    </ul>
    <ul v-else>
      <li class="content-item" >
        <div class="kuo" @click="goAblum(copyrightMovies)">
          <img class="item-img" :src="copyrightMovies.picImg" alt="">
          <div class="caption">《{{copyrightMovies.name}}》<br> 汇编权 </div>
          <div class="mask"  @click="goAblum(item)">
            <svg class="icon" aria-hidden="true">
              <use :xlink:href="BOFANG"></use>
            </svg>
          </div>
        </div>
        <p class="item-name">所剩数量：{{copyrightMovies.remaining}}</p>
        <p class="item-name">收益分成：{{copyrightMovies.shares}}</p>
        <p class="item-name">{{copyrightMovies.releasedate}}</p>
        <button-style @onClick="buy"
                      btn="打赏" :description= "copyrightMovies.price + ' QTUM'" >
        </button-style>
      </li>
    </ul>
  </div>
</template>

<script>
import {Qtum} from 'qtumjs'
import mixin from '../mixins'
import { ICON } from '../assets/icon/index'
import ButtonStyle from '../components/ButtonStyle.vue'

export default {
  name: 'video',
  mixins: [mixin],
  components: {
    ButtonStyle
  },
  props: {
    copyrightMovies: Array,
    path: String
  },
  data () {
    return {
      url: 'http://82.157.177.72:8082/'
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
    goMovie (item) {
      this.$store.commit('setTempList', item)
      this.$router.push({path: `/${this.path}/${item.id}`})
    }
  }
}
</script>

<style lang="scss" scoped>
@import '../assets/css/copyright-list.scss';
</style>
