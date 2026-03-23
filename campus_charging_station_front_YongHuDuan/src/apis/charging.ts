import request from "../utils/request";

export interface ChargingStation {
    id: number;
    name: string;
    location: string;
    status: string;
    power: number;
    price: number;
    type: string;
    available: boolean;
}

export function getAllChargingStations() {
    return request({
        url: '/user-api/api/showAll',
        method: 'get'
    });
}

export function getChargingStationById(id: number) {
    return request({
        url: `/user-api/api/show/${id}`,
        method: 'get'
    });
}

export function getAvailableChargingStations() {
    return request({
        url: '/user-api/api/available',
        method: 'get'
    });
}
