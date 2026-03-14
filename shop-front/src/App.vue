<template>
  <div class="container">
    <h1 style="text-align: center; margin-top: 20px;">基于SpringBoot+Vue的在线购物平台</h1>
    
    <!-- 猜你喜欢区域 -->
    <el-divider content-position="left">猜你喜欢 (协同过滤推荐 - 用户ID: {{ currentUserId }})</el-divider>
    <el-row :gutter="20">
      <el-col :span="6" v-for="item in recommendList" :key="'r-'+item.id">
        <el-card shadow="hover" style="background-color: #f0f9eb;">
          <div style="color: #67C23A; font-weight: bold;">【推荐】</div>
          <span>{{ item.name }}</span>
          <div style="color: #f56c6c; margin-top: 10px;">¥{{ item.price }}</div>
        </el-card>
      </el-col>
    </el-row>

    <el-divider content-position="left">全部商品</el-divider>
    <el-row :gutter="20">
      <el-col :span="6" v-for="item in goodsList" :key="item.id">
        <el-card class="box-card" shadow="hover" style="margin-top: 20px;">
          <!-- 正确的v-slot写法，兼容Vue 2 -->
          <template v-slot:header>
            <div style="font-weight: bold;">{{ item.name }}</div>
          </template>
          <div>
            <p>{{ item.description }}</p>
            <p style="color: red; font-size: 18px;">¥{{ item.price }}</p>
            <el-button type="primary" style="width: 100%;">加入购物车</el-button>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'App',
  data() {
    return {
      currentUserId: 1,
      goodsList: [],
      recommendList: []
    }
  },
  mounted() {
    this.getList();
    this.getRecommend();
  },
  methods: {
    async getList() {
      try {
        const res = await this.$axios.get('http://localhost:8081/goods/list');
        if(res.data.code === 200) {
          this.goodsList = res.data.data;
        }
      } catch (e) {
        console.log('获取商品列表失败：', e);
      }
    },
    async getRecommend() {
      try {
        const res = await this.$axios.get(`http://localhost:8081/goods/recommend/${this.currentUserId}`);
        if(res.data.code === 200) {
          this.recommendList = res.data.data;
        }
      } catch (e) {
        console.log('获取推荐商品失败：', e);
      }
    }
  }
}
</script>

<style>
.container {
  width: 1200px;
  margin: 0 auto;
}
</style>