import axios from 'axios';

const apiClient = axios.create({
  baseURL: '/user-api', // 使用代理路径 - 与vite.config.ts中的配置一致
  timeout: 10000,
  headers: {
    'Content-Type': 'application/json',
  },
});

// 确保请求不被缓存
apiClient.defaults.headers.common = {
  ...apiClient.defaults.headers.common,
  'Cache-Control': 'no-cache, no-store, must-revalidate',
  'Pragma': 'no-cache',
  'Expires': '0'
};

export default apiClient;