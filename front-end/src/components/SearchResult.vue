<template>
  <content-list
    :contentList="searchList"
    path="movie-detail"
  ></content-list>
</template>

<script>
import axios from 'axios'
import ContentList from './ContentList'

export default {
  name: 'searchresult',
  components: {
    ContentList
  },
  data () {
    return {
      searchList: '',
      keywords: ''
    }
  },
  created () {
    this.keywords = this.$route.params.keywords
    axios.get(`http://82.157.177.72:8081/movie-nft-server/movie/search?keyWord=%20${this.keywords}`)
      .then(response => {
        this.searchList = response.data.pageInfo
        console.log(response.data)
      })
  }
}
</script>

<style scoped>

</style>
