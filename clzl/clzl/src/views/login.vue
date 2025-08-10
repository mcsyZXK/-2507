<template>
  <div class="login-background"></div>
  <div class="container-wrapper">
    <div class="container" :class="{ 'right-panel-active': showRegister }">
      <div class="container__form container--signup">
        <form @submit.prevent="handleRegister" class="form" :class="{'has-error': hasRegisterError}">
          <h2 class="form__title">注册</h2>
          <input v-model="registerForm.username" type="text" placeholder="用户名" class="input" 
                 :class="{'error': errors.username}" @blur="validateField('username')" required />
          <span v-if="errors.username" class="error-message">{{ errors.username }}</span>
          <input v-model="registerForm.email" type="email" placeholder="邮箱" class="input"
                 :class="{'error': errors.email}" @blur="validateField('email')" required />
          <span v-if="errors.email" class="error-message">{{ errors.email }}</span>
          <input v-model="registerForm.password" type="password" placeholder="密码" class="input"
                 :class="{'error': errors.password}" @blur="validateField('password')" required autocomplete="new-password">
          <span v-if="errors.password" class="error-message">{{ errors.password }}</span>
          <input v-model="registerForm.repassword" type="password" placeholder="重复密码" class="input"
                 :class="{'error': errors.repassword}" @blur="validateField('repassword')" required autocomplete="new-password">
          <span v-if="errors.repassword" class="error-message">{{ errors.repassword }}</span>
          <button type="submit" class="btn">注册</button>
        </form>
      </div>
      <div class="container__form container--signin">
        <form @submit.prevent="handleLogin" class="form">
          <h2 class="form__title">登录</h2>
          <input v-model="loginForm.username" placeholder="邮箱" class="input" required />
          <input v-model="loginForm.password" type="password" placeholder="密码" class="input" required autocomplete="current-password">
          <a href="#" @click.prevent="handleForgotPassword" class="link">忘记密码？</a>
          <button type="submit" class="btn">登录</button>
        </form>
      </div>
      <div class="container__overlay">
        <div class="overlay">
          <div class="overlay__panel overlay--left">
            <button @click="showRegister = false" class="btn">登录</button>
          </div>
          <div class="overlay__panel overlay--right">
            <button @click="showRegister = true" class="btn">注册</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script lang="ts" setup>
import { ref, reactive } from 'vue'
import { ElMessage } from 'element-plus'
import { publicInstance, authInstance } from '@/util/request'
import { useAuthStore } from '@/stores/token.js'
import { useRouter } from 'vue-router'
const router = useRouter()
const tokenStore = useAuthStore()
const showRegister = ref(false)
const hasRegisterError = ref(false)
const errors = reactive({
  username: '',
  email: '',
  password: '',
  repassword: ''
})
const validationRules = {
  username: (value: string) => {
    if (!value) return '请输入用户名'
    if (value.length < 2 || value.length > 16) return '用戶名應爲长度为2~16位非空字符'
    return ''
  },
  email: (value: string) => {
    if (!value) return '请输入邮箱'
    if (!/^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$/.test(value)) return '请输入有效的邮箱地址'
    return ''
  },
  password: (value: string) => {
    if (!value || value.trim() === '') return '请输入密码'
    return ''
  },
  repassword: (value: string) => {
    if (!value) return '请再次输入密码'
    if (value !== registerForm.value.password) return '两次输入的密码不一致'
    return ''
  }
}
const loginForm = ref({
  username: '',
  password: ''
})
const registerForm = ref({
  username: '',
  email: '',
  password: '',
  repassword: ''
})
const validateField = (field: keyof typeof errors) => {
  errors[field] = validationRules[field](registerForm.value[field])
  hasRegisterError.value = Object.values(errors).some(msg => msg !== '')
}
const validateForm = () => {
  let isValid = true
  Object.keys(validationRules).forEach(field => {
    errors[field] = validationRules[field](registerForm.value[field])
    if (errors[field]) isValid = false
  })
  hasRegisterError.value = !isValid
  return isValid
}
const handleRegister = async() => {
  if (!validateForm()) {
    ElMessage.warning('请正确填写所有字段')
    return
  }
  try {
    const params = new URLSearchParams()
    params.append('username', registerForm.value.username)
    params.append('email', registerForm.value.email)
    params.append('password', registerForm.value.password)
    const response = await publicInstance.post('/user/register', params)
    ElMessage.success('注册成功')
    location.reload()
  } catch (error) {
    ElMessage.error('注册失败')
    console.error(error)
  }
}
const handleLogin = async() => {
  try {
    const params = new URLSearchParams()
    params.append('emailOrUsername', loginForm.value.username)
    params.append('password', loginForm.value.password)
    const response = await publicInstance.post('/user/login', params)
    if (!response?.data?.token) {
      ElMessage.error('账户或密码错误')
      return
    }
    tokenStore.login(response.data)
    ElMessage.success('登录成功')
    router.push('/')
  } catch (error) {
    let errorMessage = '登录失败'
    if (error.response) {
      errorMessage = error.response.data.message || 
                    error.response.data.error || 
                    `错误状态码: ${error.response.status}`
    } else if (error.request) {
      errorMessage = '服务器无响应'
    } else {
      errorMessage = error.message || '未知错误'
    }
    ElMessage.error(errorMessage)
    console.error('登录错误:', error)
  }
}
const handleForgotPassword = () => {
  alert('请联系管理员: 15906869493')
}
</script>
<style scoped>
.error {
  border-color: #ff4d4f !important;
}
.error-message {
  color: #ff4d4f;
  font-size: 12px;
  display: block;
  margin-top: -8px;
  margin-bottom: 10px;
  text-align: left;
}
.has-error .btn {
  background: #f5f5f5;
  color: #999;
  cursor: not-allowed;
}
.has-error .btn:hover {
  transform: none;
  box-shadow: none;
}
.login-background {
  position: fixed;
  top: 0;
  left: 0;
  width: 100vw;
  height: 100vh;
  background: url("/src/assets/img_50.jpg") center/cover no-repeat fixed;
  z-index: -1; 
}
.container-wrapper {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  min-width: 175vh;
  padding: 20px;
}
.container {
  position: relative;
  width: 100%;
  max-width: 800px;
  height: 500px;
  background-color: rgba(255, 255, 255, 0.9);
  border-radius: 20px;
  box-shadow: 0 15px 30px rgba(0, 0, 0, 0.2);
  overflow: hidden;
  margin: 0 auto;
}
.container__form {
  position: absolute;
  top: 0;
  width: 50%;
  height: 100%;
  transition: all 0.6s ease-in-out;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 40px;
  box-sizing: border-box;
}
.container--signin {
  left: 0;
  z-index: 2;
}
.container--signup {
  left: 0;
  opacity: 0;
  z-index: 1;
}
.container.right-panel-active .container--signin {
  transform: translateX(100%);
  opacity: 0;
}
.container.right-panel-active .container--signup {
  transform: translateX(100%);
  opacity: 1;
  z-index: 5;
}
.container__overlay {
  position: absolute;
  top: 0;
  left: 50%;
  width: 50%;
  height: 100%;
  overflow: hidden;
  transition: transform 0.6s ease-in-out;
  z-index: 100;
}
.overlay {
  position: relative;
  left: -100%;
  height: 100%;
  width: 200%;
  background: linear-gradient(135deg, #0367a6 0%, #008997 100%);
  transition: transform 0.6s ease-in-out;
}
.container.right-panel-active .container__overlay {
  transform: translateX(-100%);
}
.container.right-panel-active .overlay {
  transform: translateX(50%);
}
.overlay__panel {
  position: absolute;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 50%;
  height: 100%;
  padding: 40px;
  box-sizing: border-box;
  text-align: center;
  transition: transform 0.6s ease-in-out;
}
.overlay--left {
  transform: translateX(-20%);
}
.overlay--right {
  right: 0;
  transform: translateX(0);
}
.container.right-panel-active .overlay--left {
  transform: translateX(0);
}
.container.right-panel-active .overlay--right {
  transform: translateX(20%);
}
.form {
  width: 100%;
  max-width: 320px;
}
.form__title {
  font-weight: 300;
  margin-bottom: 30px;
  color: #333;
  font-size: 24px;
}
.input {
  width: 100%;
  padding: 12px 15px;
  margin: 10px 0;
  border: 1px solid #ddd;
  border-radius: 4px;
  font-size: 14px;
  transition: border-color 0.3s;
}
.input:focus {
  border-color: #0367a6;
  outline: none;
}
.btn {
  background: linear-gradient(90deg, #0367a6 0%, #008997 100%);
  color: white;
  border: none;
  padding: 12px 30px;
  margin-top: 20px;
  border-radius: 20px;
  font-size: 14px;
  font-weight: bold;
  letter-spacing: 1px;
  text-transform: uppercase;
  cursor: pointer;
  transition: all 0.3s ease;
  width: 100%;
}
.btn:hover {
  transform: translateY(-2px);
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
}
.btn:active {
  transform: translateY(0);
}
.link {
  color: #666;
  font-size: 14px;
  margin: 15px 0;
  text-decoration: none;
  transition: color 0.3s;
}
.link:hover {
  color: #0367a6;
}
</style>