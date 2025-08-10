<template>
  <div class="order-container">
    <div class="header">
      <h2 class="title">订单详情</h2>
      <el-button type="primary" @click="refreshData">
        <el-icon><Refresh /></el-icon>
        <span>刷新数据</span>
      </el-button>
    </div>
    <el-card shadow="never" class="table-card">
      <el-table
        :data="tableData"
        style="width: 100%"
        v-loading="loading"
        :header-cell-style="{ background: '#f5f7fa' }"
        stripe
      >
        <el-table-column prop="orderId" label="订单号" width="120"/>
        <el-table-column prop="cheNum" label="车辆号" width="120"/>
        <el-table-column prop="status" label="狀態" width="120"/>
        <el-table-column prop="createdAt" label="请求日期" width="120"/>
        <el-table-column prop="requestDate" label="租赁日期" width="120"/>
        <!-- 操作列 -->
        <el-table-column label="操作" width="180" fixed="right" align="center">
          <template #default="{ row }">
            <el-button 
              v-if="row.status === statusMap.PENDING"
              type="primary" 
              size="small" 
              @click="handleReset(row)"
            >
              <el-icon><Edit /></el-icon>
              <span>撤销</span>
            </el-button>
            <el-tag v-else type="info" effect="plain">不可操作</el-tag>
          </template>
        </el-table-column>
      </el-table>

      <!-- 空状态 -->
      <el-empty
        v-if="!tableData.length && !loading"
        :description="!authStore.isAuthenticated ? '请先登录查看订单' : '暂无订单'"
        :image-size="200"
      >
        <el-button
        v-if="!authStore.isAuthenticated"
        type="primary"
        @click="goLogin"
      >
        点击登录
      </el-button>
      </el-empty>
    </el-card>
  </div>
</template>
<script lang="ts" setup>
import {authInstance,publicInstance} from '@/util/request.js'
import { onMounted,ref } from 'vue';
import { 
  Refresh,
  Edit
} from '@element-plus/icons-vue';
import { ElMessage, ElMessageBox } from 'element-plus'
import router from '@/router';
import { useAuthStore } from '@/stores/token';
const authStore = useAuthStore()
const tableData = ref([])
const loading = ref(false)
const error = ref(null)
const goLogin = () => {
  router.push('/login')
}
const statusMap = {
  PENDING: '等管理員处理',
  REJECTED: '已被租賃',
  APPROVED: '已批准',
  CANCELLED: '已取消'
}
const fetchOrders =async () =>{
  try{
  loading.value = true;
  const requestdata ={
    userId:authStore.getUserId()
  }
  const{data} = await authInstance.post('order/getByID',requestdata)
  tableData.value = (data||[]).map(item=>({
    orderId:item.orderId,
    cheNum:item.cheNum,
    createdAt:item.createdAt||'未知日期',
    status:statusMap[item.status],
    requestDate:item.requestDate||'等通知嗷'
  }))
  }catch (error) {
    console.error('获取订单列表失败:', error)
    if (error.response && error.response.status === 401) {
      ElMessage.error('请先登录');
      router.push('/login');
    }
    else{
      ElMessage.error('获取订单失败')
    }
  } finally {
    loading.value = false
  }
}
const refreshData = () => {
  fetchOrders()
}
const handleReset = (row: any) => {
  ElMessageBox.confirm(
    `确定撤銷訂單号为 ${row.orderId} 的訂單申請吗？`,
    '警告',
    {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning',
    }
  ).then(() => {
      const putdata = {
    orderId:row.orderId
  }
      authInstance.post('/order/reset',putdata)
      location.reload()
  })
}
onMounted(() => {
  authStore.initialize()
  if(authStore.isAuthenticated){
    fetchOrders()
  }
})
</script>

<style scoped>
.announcement-container {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
  font-family: Arial, sans-serif;
}

h2 {
  color: #333;
  text-align: center;
  margin-bottom: 20px;
}

.loading, .no-announcements, .error {
  text-align: center;
  padding: 20px;
  color: #666;
}

.error {
  color: #d9534f;
}

.announcement-item {
  background-color: #f9f9f9;
  border-left: 4px solid #42b983;
  padding: 15px;
  margin-bottom: 15px;
  border-radius: 0 4px 4px 0;
}

.announcement-title {
  color: #2c3e50;
  margin-top: 0;
  margin-bottom: 10px;
}

.announcement-message {
  color: #555;
  line-height: 1.6;
  margin-bottom: 10px;
}

.announcement-time {
  font-size: 0.9em;
  color: #888;
  text-align: right;
}
</style>