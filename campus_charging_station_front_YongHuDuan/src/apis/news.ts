import axios from 'axios';
import { useUserStore } from '../stores/user';

export interface NewsPublishData {
    title: string;
    content: string;
    categoryId: number;
    imageUrl: string;
    publishDate: string;
    newsCategory: string;
}

export function publishNews(data: NewsPublishData) {
    const token = useUserStore().getToken;
    
    return axios.post('/news/publish', data, {
        headers: {
            'Content-Type': 'application/json',
            'token': token || ''
        }
    });
}

export function getNewsList() {
    const token = useUserStore().getToken;
    
    return axios.get('/news/list', {
        headers: {
            'token': token || ''
        }
    });
}

export function getNewsByCategory(categoryId: number) {
    const token = useUserStore().getToken;
    
    return axios.get(`/news/category/${categoryId}`, {
        headers: {
            'token': token || ''
        }
    });
}
