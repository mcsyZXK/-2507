import axios from 'axios';
import { ElMessage } from 'element-plus'
import { useAuthStore } from '@/stores/token.js'
const baseURL = '/api'
const instance = axios.create({ baseURL })
const authInstance = axios.create({ baseURL })
const publicInstance = axios.create({ baseURL })
authInstance.interceptors.request.use(
  (config) => {
    const tokenStore = useAuthStore()
    if (tokenStore.token) {
      config.headers.Authorization = `Bearer ${tokenStore.token}`
    }
    return config
  },
  (err) => {
    return Promise.reject(err)
  }
)
const setupResponseInterceptor = (instance) => {
  instance.interceptors.response.use(
    (response) => {
      return response.data
    },
    (error) => {
      ElMessage.error(error.response?.data?.message || '请求失败')
      return Promise.reject(error)
  })
}
setupResponseInterceptor(authInstance)
setupResponseInterceptor(publicInstance)
export { authInstance, publicInstance }
import router from '@/router'
instance.interceptors.response.use(
    result => {
        if(result.data.code===0){
            return result.data;
        }
        ElMessage.error(result.data.msg?result.data.msg:'服务异常')
        return Promise.reject(result.data)
    },
    err => {
        if(err.response.status===401){
            ElMessage.error('请先登录')
            router.push('/login')
        }else{
            ElMessage.error('服务异常')
        }
        return Promise.reject(err);
    }
)
export default instance;