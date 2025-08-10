<script setup>
import { ref } from 'vue'
import { useAuthStore } from '@/stores/token.js'
import { ElCard, ElRow, ElCol } from 'element-plus'

const userInfoStore = useAuthStore()
const userInfo = ref({...userInfoStore.user})
</script>

<template>
  <el-card class="page-container">
    <template #header>
      <div class="header">
        <span>个人资料</span>
      </div>
    </template>
    <el-row>
      <el-col :span="12">
        <div class="info-display">
          <div class="info-item">
            <label class="info-label">登录名称：</label>
            <span class="info-content">{{ userInfo.username || '暂无' }}</span>
          </div>
          <div class="info-item">
            <label class="info-label">用户昵称：</label>
            <span class="info-content">{{ userInfo.nickname || '暂无' }}</span>
          </div>
          <div class="info-item">
            <label class="info-label">用户邮箱：</label>
            <span class="info-content">{{ userInfo.email || '暂无' }}</span>
          </div>
        </div>
      </el-col>
    </el-row>
  </el-card>
</template>

<style scoped>
.page-container {
  max-width: 800px;
  margin: 20px auto;
}

.header {
  font-size: 18px;
  font-weight: bold;
}

.info-display {
  padding: 0 20px;
}

.info-item {
  margin-bottom: 18px;
  font-size: 16px;
}

.info-label {
  display: inline-block;
  width: 100px;
  color: #666;
  font-weight: 500;
}

.info-content {
  color: #333;
}
</style>