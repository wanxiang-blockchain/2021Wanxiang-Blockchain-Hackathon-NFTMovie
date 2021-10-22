<template>
  <div>
    <the-header/>
    <router-view class="music-content"/>
    <scroll-top/>
  </div>
</template>

<script>
import ScrollTop from '../components/ScrollTop'
import SongAudio from '../components/SongAudio'
import TheHeader from '../components/TheHeader'
import TheFooter from '../components/TheFooter'
import TheAside from '../components/TheAside'

export default {
  components: {
    ScrollTop,
    SongAudio,
    TheHeader,
    TheFooter,
    TheAside
  }
}
</script>

<style lang="scss" scoped>
@import '../assets/css/main.scss';
</style>
