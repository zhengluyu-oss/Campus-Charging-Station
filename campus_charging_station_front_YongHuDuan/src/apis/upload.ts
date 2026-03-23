import request from "../utils/request.ts";
import axios from "axios";
import { useUserStore } from "../stores/user";

//上传图片（管理员）
export function uploadImage(rawFile: any) {
    return request({
        url: '/user-api/upload/image',
        method: 'post',
        data: {image: rawFile}
    })
}

//上传头像（用户）
export function uploadAvatar(imageFile: any) {
    const formData = new FormData();
    formData.append('image', imageFile);
    
    const token = useUserStore().getToken;
    
    return axios.post('/upload/image', formData, {
        headers: {
            'token': token || ''
        }
    });  
}

//上传视频
export function uploadVideo(rawFile: any) {
    return request({
        url: '/user-api/upload/video',
        method: 'post',
        data: {video: rawFile}
    })
}

//上传视频
export function uploadFile(rawFile: any) {
    return request({
        url: '/user-api/upload/file',
        method: 'post',
        data: {file: rawFile}
    })
}