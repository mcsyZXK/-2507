<template>
    <el-container>
        <el-aside width="200px">
            <div class="el-aside__logo"></div>
            <el-menu active-text-color="#ffd04b" background-color="#232323" text-color="#fff" router>
                <el-menu-item index="/car">
                    <span>车辆租赁</span>
                </el-menu-item>
                <el-menu-item index="/announcement">
                    <span>公告栏</span>
                </el-menu-item>
                <el-menu-item index="/order">
                    <span>订单</span>
                </el-menu-item>
                <el-menu-item index="/user">
                    <span>個人資料</span>
                </el-menu-item>
            </el-menu>
        </el-aside>
        <el-container>
            <el-header v-if="!!tokenStore.isAuthenticated">
                <div>你好：<strong>{{ nickname }}</strong></div>
                <el-dropdown placement="bottom-end" @command="handleCommand">
                    <span class="el-dropdown__box">
                        <el-avatar :src="avatarUrl" />
                    </span>
                    <template #dropdown>
                        <el-dropdown-menu>
                            <el-dropdown-item command="user">基本资料</el-dropdown-item>
                            <el-dropdown-item command="logout">退出登录</el-dropdown-item>
                        </el-dropdown-menu>
                    </template>
                </el-dropdown>
            </el-header>
            <el-main class="test1">
                <router-view></router-view>
            </el-main>
            <el-footer>Mercedes ©2025 Created by MCSYZXK</el-footer>
        </el-container>
    </el-container>
</template>
<script setup>
import { useAuthStore } from '@/stores/token'
import { onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { ref, computed } from 'vue'
import avatar from '@/assets/img_12.jpg'
import { ElMessageBox,ElMessage } from 'element-plus'
import User from "@/views/user/user.vue";
const nickname = ref()
const tokenStore = useAuthStore()
tokenStore.$patch({
    user: tokenStore.user || {}
})
const router = useRouter()
const avatarUrl = computed(() => {
    return tokenStore.user?.pictureUrl || avatar;
});
const checkAuth = () => {
    return !!tokenStore.isAuthenticated 
}
const getNickname = () => {
    if (!checkAuth()) {
        nickname.value = '未登录'
    } else { nickname.value = tokenStore.user.nickname }
}
const handleCommand = (command) => {
    if (command === 'logout') {
        ElMessageBox.confirm(
            '您确认要退出吗?',
            {
                confirmButtonText: '确认',
                cancelButtonText: '取消',
                type: 'warning',
            }
        )
            .then(async () => {
                tokenStore.logout()

                ElMessage({
                    type: 'success',
                    message: '退出登录成功',
                })
            })
    } else {
        router.push('/user/' + command)
    }
}
onMounted(() => {
    getNickname()
})
</script>

<style scoped>
.home-container {
    min-height: 100vh;
    display: flex;
    flex-direction: column;
}

.header {
    background-color: #409EFF;
    color: white;
    padding: 1rem;
    display: flex;
    justify-content: space-between;
}

.main-content {
    flex: 1;
    padding: 20px;
}

.test1 {
    width: 850px;
}
</style>