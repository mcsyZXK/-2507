import { defineStore } from 'pinia'
import { ref,computed } from 'vue'
import router from '@/router'
import { authInstance } from '@/util/request'
export const useAuthStore = defineStore('auth', () => {
    const token = ref(null) 
    const user = ref(null)
    const login = (loginResponse) => {
        try {
            if (!loginResponse?.token) {
                throw new Error('无效的登录响应: token缺失')
            }
            token.value = loginResponse.token
            user.value = loginResponse.user || null
            localStorage.setItem('token', loginResponse.token)
            if (loginResponse.user) {
                localStorage.setItem('user', JSON.stringify(loginResponse.user))
            }
            return true
        } catch (error) {
            console.error('登录存储失败:', error)
            return false
        }
    }
    const logout = async() => {
        try {
            const response = await authInstance.post(
                '/user/logout', 
                null,
                {
                    headers: {
                        'Authorization': `Bearer ${token.value}`,
                        'Content-Type': 'application/json'
                    }
                }
            );
            console.log('登出成功', response);
        } catch (error) {
            console.error('登出失敗', error.response);
        } finally {
            token.value = null;
            user.value = null;
            
            ['token', 'user'].forEach(key => {
                localStorage.removeItem(key);
                sessionStorage.removeItem(key);
            });
            
            window.location.reload();
        }
    }
    // 4. 初始化方法（安全增强版）
    const initialize = () => {
        try {
            // Token处理
            const rawToken = localStorage.getItem('token')
            token.value = rawToken ? rawToken : null  
            
            // User处理
            const rawUser = localStorage.getItem('user')
            user.value = rawUser ? JSON.parse(rawUser) : null
        } catch (error) {
            console.error('初始化失败:', error)
            logout() // 自动清理数据
        }
    }

    // 5. 认证检查
    const isAuthenticated = computed(() => {
        return !!token.value  
    })

    // 6. 管理员检查（安全访问）
    const isAdmin = computed(() => {
        return user.value?.role === 'ADMIN'
    })

    // 7. 获取用户ID（安全访问）
    const getUserId = () => {
        return user.value?.id || null
    }

    return {
        token,
        user,
        login,
        logout,
        initialize,
        isAuthenticated,
        isAdmin,
        getUserId
    }
}, {
    persist: {
        key: 'auth-store',  // 指定存储键名
        paths: ['token', 'user']  // 明确指定要持久化的字段
    }
})