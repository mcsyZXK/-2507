<template>
  <div class="car-container">
    <div class="header">
      <h2 class="title">阿卡丽的黑心商店</h2>
      <el-button type="primary" @click="refreshData">
        <el-icon><Refresh /></el-icon>
        <span>刷新数据</span>
      </el-button>
    </div>
    <el-card shadow="never" class="table-card">
      <el-table
        :data="tableData.filter(item => item.isZl != true)"
        v-loading="loading"
        :header-cell-style="{ background: '#f5f7fa' }"
        stripe
      >
        <el-table-column label="车辆图片" width="180" align="center">
          <template #default="{ row }">
            <el-image
              class="car-image"
              :src="row.pictureUrl"
              :preview-src-list="[row.pictureUrl]"
              fit="cover"
              hide-on-click-modal
            >
              <template #error>
                <div class="image-error">
                  <el-icon><Picture /></el-icon>
                  <span>图片加载失败</span>
                </div>
              </template>
            </el-image>
          </template>
        </el-table-column>
        <el-table-column prop="xinXi" label="品牌" width="120" sortable />
        <el-table-column prop="price" label="价格" width="120">
        </el-table-column>
        <el-table-column prop="cheNum" label="车牌" width="120">
        </el-table-column>
        <el-table-column label="操作" width="180" fixed="right" align="center">
          <template #default="{ row }">
            <el-button type="success" size="small" @click="handleZl(row)">
              <el-icon><Share /></el-icon>
              <span>租赁</span>
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-empty
        v-if="!tableData.length && !loading"
        description="暂无车辆数据"
        :image-size="200"
      >
        <el-button type="primary" @click="refreshData">重新加载</el-button>
      </el-empty>
    </el-card>
  </div>
</template>

<script lang="ts" setup>
import { ref, onMounted } from 'vue'
import { 
  Refresh,
  Picture,
  Share
} from '@element-plus/icons-vue'
import {authInstance,publicInstance} from '@/util/request.js'
import { ElMessage, ElMessageBox } from 'element-plus'
const tableData = ref([])
const loading = ref(false)
const defaultcar = '/src/assets/img_85.png'
import { useAuthStore } from '@/stores/token'
import { useRoute,useRouter } from 'vue-router'
const tokenStore = useAuthStore()  
const router = useRouter()
const fetchCarData = async () => {
  try {
    loading.value = true
    const { data } = await publicInstance.get('car/getAll')
    tableData.value = (data || []).map(item => ({
      isZl:item.isZl,
      cheNum: item.cheNum || '无车牌号',
      xinXi: item.xinXi || '无信息',
      price: item.price || '0',
      pictureUrl: item.pictureUrl 
        ? `/src/assets/${item.pictureUrl}`
        : defaultcar
    }))
  } catch (error) {
    ElMessage.error('获取车辆列表失败')
  } finally {
    loading.value = false
  }
}

// 刷新数据(刷新頁面)
const refreshData = () => {
  location.reload()
  fetchCarData()
}

const checkAuth = () => {
  return !!tokenStore.isAuthenticated // 如果token存在则返回true
}
// 租赁操作
const handleZl = (row: any) => {
  if(!checkAuth()){
    router.push({
      path:'/login',
    })
  return;
  }

  ElMessageBox.confirm(
    `确定租赁车牌号为 ${row.cheNum} 的车辆吗？`,
    '警告',
    {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning',
    }
  ).then(async () => {
    const requestdata ={
      cheNum:row.cheNum,
      userId:tokenStore.getUserId()
    }
    const response = await authInstance.post('/order/zl',requestdata)
    ElMessage.success('租賃成功')
    location.reload()
  })
}

// 挂載時渲染
onMounted(() => {
  fetchCarData()
})
</script>

<style scoped lang="scss">
.car-container {
  padding: 20px;
  background-color: #f5f7fa;
  min-height: calc(100vh - 60px);
 
  .header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 20px;

    .title {
      color: #303133;
      font-size: 18px;
      font-weight: 500;
    }
  }

  .table-card {
    border-radius: 8px;
    border: none;
    box-shadow: 0 1px 4px rgba(0, 0, 0, 0.08);
  }

  :deep(.el-table) {
    width: 100%; 
  }

  .car-image {
    width: 150px;
    height: 100px;
    border-radius: 4px;
    transition: transform 0.3s;

    &:hover {
      transform: scale(1.05);
    }
  }

  .image-error {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    height: 100%;
    color: #909399;
    font-size: 12px;

    .el-icon {
      font-size: 24px;
      margin-bottom: 5px;
    }
  }

  :deep(.el-table) {
    .el-table__row {
      transition: background-color 0.3s;

      &:hover {
        background-color: #f5f7fa !important;
      }
    }
  }
}
</style>