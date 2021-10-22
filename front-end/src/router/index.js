import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

const constantRoutes = [
  {
    path: '*',
    redirect: '/404'
  },
  {
    path: '/404',
    component: () => import('@/pages/404.vue')
  },
  {
    path: '/',
    name: 'main',
    component: () => import('@/pages/Main'),
    children: [
      {
        path: '/login-in',
        name: 'login-in',
        component: () => import('@/pages/LoginIn')
      },
      {
        path: '/sign-up',
        name: 'sign-up',
        component: () => import('@/pages/SignUp')
      },
      {
        path: '/',
        name: 'home',
        component: () => import('@/pages/Home')
      },
      {
        path: '/song-list',
        name: 'song-list',
        component: () => import('@/pages/SongList')
      },
      {
        path: '/my-music',
        name: 'my-music',
        component: () => import('@/pages/MyMusic')
      },
      {
        path: '/movie-detail/:movieId/',
        name: 'movie-detail',
        component: () => import('@/pages/MovieDetail')
      },
      {
        path: '/singer',
        name: 'singer',
        component: () => import('@/pages/Singer')
      },
      {
        path: '/singer-album/:id',
        name: 'singer-album',
        component: () => import('@/pages/SingerAlbum')
      },
      {
        path: '/lyric/:id',
        name: 'lyric',
        component: () => import('@/pages/Lyric')
      },
      {
        path: '/search/:keywords',
        name: 'search',
        component: () => import('@/components/SearchResult')
      },
      {
        path: '/setting',
        name: 'setting',
        component: () => import('@/pages/Setting')
      },
      {
        path: '/uploadInfo',
        name: 'uploadInfo',
        component: () => import('@/pages/UploadInfo')
      },
      {
        path: '/publishCopyright',
        name: 'publishCopyright',
        component: () => import('@/pages/PublishCopyright')
      },
      {
        path: '/publishArtWork',
        name: 'publishArtWork',
        component: () => import('@/pages/PublishArtWork')
      },
      {
        path: '/BuyArtWork',
        name: 'BuyArtWork',
        component: () => import('@/pages/BuyArtWork')
      },
      {
        path: '/BuyCopyright',
        name: 'BuyCopyright',
        component: () => import('@/pages/BuyCopyright')
      },
      {
        path: '/BuyCopyright/:id',
        name: 'buycopyright-detail',
        component: () => import('@/pages/BuyCopyrightDetail')
      },
      {
        path: '/BuyArtwork/Auction/:id',
        name: 'auction-detail',
        component: () => import('@/pages/AuctionDetail')
      },
      {
        path: '/BuyArtwork/FixedPrice/:id',
        name: 'auction-detail',
        component: () => import('@/pages/FixedPriceDetail')
      },
      {
        path: '/MyInfo',
        name: 'myInfo',
        component: () => import('@/pages/MyInfo')
      }
    ]
  }
]

export default new Router({
  mode: 'history', // 去掉url中的#
  scrollBehavior: (o, from, savedPosition) => ({ x: 0, y: 0 }),
  routes: constantRoutes
})
