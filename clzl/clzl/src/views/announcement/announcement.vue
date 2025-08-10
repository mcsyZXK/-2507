<template>
  <div class="announcement-container">
    <div class="header">
      <h2 class="title">公告页面</h2>
      <el-button type="primary" @click="refreshData">
        <el-icon><Refresh /></el-icon>
        <span>刷新</span>
      </el-button>
    </div>

    <div class="announcement-list">
      <div v-for="announcement in tableData" :key="announcement.id" class="announcement-item">
        <div class="announcement-main">
          <div class="announcement-header">
            <h3 class="announcement-title">{{ announcement.title }}</h3>
            <div class="announcement-meta">
              <span class="announcement-date">{{ formatDate(announcement.updatedAt) }}</span>
              <el-tag type="danger" effect="dark" class="like-tag">
                {{ announcement.likeCount || 0 }} 赞
              </el-tag>
            </div>
          </div>
          <div class="announcement-content">
            {{ announcement.message }}
          </div>
          <div class="announcement-actions">
            <el-button 
              type="danger" 
              size="small"
              @click="addPendingLike(announcement)"
              :loading="isSubmitting"
              @mouseleave="submitPendingLike(announcement)"
            >
              <el-icon><Star /></el-icon>
              <span>点赞</span>
            </el-button>
          </div>
        </div>

        <div class="replies-section">
          <h4 class="replies-title">
            回复 ({{ getRepliesForAnnouncement(announcement.id).length }})
          </h4>
          
          <div v-if="repliesLoading" class="loading-replies">
            <el-icon class="is-loading"><Loading /></el-icon>
            <span>加载回复中...</span>
          </div>
          
          <div v-else>
            <div v-if="getRepliesForAnnouncement(announcement.id).length > 0" class="replies-list">
              <div 
                v-for="reply in getRepliesForAnnouncement(announcement.id)" 
                :key="reply.id" 
                class="reply-item"
              >
                <div class="reply-header">
                  <span class="username">{{ reply.nickname }}</span>
                  <span class="timestamp">{{ formatReplyDate(reply.timestamp) }}</span>
                </div>
                <div class="reply-content">{{ reply.retext }}</div>
              </div>
            </div>
            <div v-else class="no-replies">
              暂无回复
            </div>
            
            <div class="add-reply-form">
              <el-input
                v-model="announcement.newReplyText"
                type="textarea"
                :rows="3"
                placeholder="输入回复内容"
              />
              <el-button 
                type="primary" 
                size="small" 
                @click="submitReply(announcement)"
                :loading="announcement.isSubmittingReply"
                style="margin-top: 10px;"
              >
                提交回复
              </el-button>
            </div>
          </div>
        </div>
      </div>

      <div v-if="!tableData.length && !loading" class="no-announcements">
        <el-empty description="暂无公告" :image-size="200">
          <el-button type="primary" @click="refreshData">重新加载</el-button>
        </el-empty>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { authInstance, publicInstance } from "@/util/request";
import { onMounted, ref } from 'vue';
import { 
  Refresh,
  Star,
  Loading
} from '@element-plus/icons-vue';
import { ElMessage } from 'element-plus'
import { useAuthStore } from "@/stores/token";
const token = useAuthStore()
const tableData = ref<Array<any>>([])
const allReplies = ref<Array<any>>([])
const loading = ref(false)
const repliesLoading = ref(false)
const error = ref(null)

// 格式化日期
const formatDate = (rawDate: string) => {
  if (!rawDate) return '未知日期';
  try {
    const date = new Date(rawDate);
    return date.toLocaleString('zh-CN', {
      year: 'numeric',
      month: '2-digit',
      day: '2-digit',
      hour: '2-digit',
      minute: '2-digit'
    });
  } catch (e) {
    console.error('日期格式错误:', rawDate, e);
    return rawDate;
  }
};

// 格式化回复日期
const formatReplyDate = (timestamp: string) => {
  if (!timestamp) return '未知时间';
  try {
    const date = new Date(timestamp);
    return date.toLocaleString('zh-CN', {
      year: 'numeric',
      month: '2-digit',
      day: '2-digit',
      hour: '2-digit',
      minute: '2-digit'
    });
  } catch (e) {
    console.error('回复日期格式错误:', timestamp, e);
    return timestamp;
  }
};

// 根据公告ID获取对应回复
const getRepliesForAnnouncement = (announcementId: number) => {
  return allReplies.value.filter(reply => reply.anId === announcementId);
};

const fetchAnnouncements = async () => {
  try {
    loading.value = true;
    
    const [announcementsRes, repliesRes] = await Promise.all([
      publicInstance.get('/announcement/getAll'),
      publicInstance.get('/reply/getall')
    ]);
    
    tableData.value = (announcementsRes.data || []).map((announcement: any) => ({
      ...announcement,
      newReplyText: '',
      isSubmittingReply: false
    }));
    
    allReplies.value = repliesRes.data || [];
  } catch (err) {
    error.value = err;
    ElMessage.error('获取数据失败');
    console.error('获取数据失败:', err);
  } finally {
    loading.value = false;
  }
};

const submitReply = async (announcement: any) => {
  if (!announcement.newReplyText.trim()) {
    ElMessage.warning('回复内容不能为空');
    return;
  }

  try {
    announcement.isSubmittingReply = true;
    const response = await authInstance.post('/reply/insert', {
      anId: announcement.id,
      retext: announcement.newReplyText,
      nickname: token.user.nickname, 
      timestamp: new Date().toISOString()
    });
    
    if (response.data) {
      ElMessage.success('回复成功');
      location.reload()
    } else {
      ElMessage.error('回复失败');
    }
  } catch (err) {
    ElMessage.error('回复失败');
    console.error('回复失败:', err);
  } finally {
    announcement.isSubmittingReply = false;
  }
};

const refreshData = () => {
  fetchAnnouncements();
};

const pendingLikes = ref<Record<number, number>>({});
const isSubmitting = ref(false);

const addPendingLike = (announcement: any) => {
  if (!pendingLikes.value[announcement.id]) {
    pendingLikes.value[announcement.id] = 0;
  }
  pendingLikes.value[announcement.id]++;
  announcement.likeCount++;
};

const submitPendingLike = async (announcement: any) => {
  if (!pendingLikes.value[announcement.id]) return;
  
  isSubmitting.value = true;
  try {
    await authInstance.post('/announcement/like', {
      id: announcement.id,
      count: pendingLikes.value[announcement.id]
    });
    pendingLikes.value[announcement.id] = 0;
  } catch (err) {
    announcement.likeCount -= pendingLikes.value[announcement.id];
    ElMessage.error('点赞提交失败');
  } finally {
    isSubmitting.value = false;
  }
};

onMounted(() => {
  fetchAnnouncements();
});
</script>

<style scoped>
.announcement-container {
  max-width: 1000px;
  margin: 0 auto;
  padding: 20px;
  font-family: Arial, sans-serif;
}

.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

.title {
  color: #333;
  margin: 0;
}

.announcement-list {
  display: flex;
  flex-direction: column;
  gap: 24px;
}

.announcement-item {
  background: #fff;
  border-radius: 8px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  overflow: hidden;
}

.announcement-main {
  padding: 20px;
  border-bottom: 1px solid #eee;
}

.announcement-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 10px;
}

.announcement-title {
  margin: 0;
  font-size: 18px;
  color: #333;
}

.announcement-meta {
  display: flex;
  align-items: center;
  gap: 10px;
}

.announcement-date {
  color: #999;
  font-size: 14px;
}

.like-tag {
  margin-left: 10px;
}

.announcement-content {
  color: #555;
  line-height: 1.6;
  margin-bottom: 15px;
  white-space: pre-wrap;
}

.announcement-actions {
  display: flex;
  justify-content: flex-end;
}

.replies-section {
  padding: 20px;
  background-color: #f9f9f9;
}

.replies-title {
  margin-top: 0;
  margin-bottom: 15px;
  font-size: 16px;
  color: #666;
}

.replies-list {
  display: flex;
  flex-direction: column;
  gap: 15px;
  margin-bottom: 20px;
}

.reply-item {
  background: #fff;
  padding: 15px;
  border-radius: 4px;
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
}

.reply-header {
  display: flex;
  justify-content: space-between;
  margin-bottom: 8px;
  font-size: 14px;
}

.username {
  font-weight: bold;
  color: #409EFF;
}

.timestamp {
  color: #999;
}

.reply-content {
  color: #333;
  line-height: 1.5;
}

.loading-replies {
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 20px;
  color: #666;
}

.loading-replies .el-icon {
  margin-right: 5px;
  animation: rotating 2s linear infinite;
}

.no-replies {
  text-align: center;
  padding: 20px;
  color: #999;
}

.add-reply-form {
  margin-top: 20px;
}

.no-announcements {
  text-align: center;
  padding: 40px 0;
}

@keyframes rotating {
  from {
    transform: rotate(0deg);
  }
  to {
    transform: rotate(360deg);
  }
}
</style>