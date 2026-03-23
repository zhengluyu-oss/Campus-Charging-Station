/**
 * 充电桩相关常量定义
 */

/**
 * 充电桩状态枚举
 */
export enum ChargingStationStatus {
  INACTIVE = 0,  // 关闭/不可用
  ACTIVE = 1     // 开启/可用
}

/**
 * 充电桩状态文本映射
 */
export const CHARGING_STATION_STATUS_TEXT = {
  [ChargingStationStatus.INACTIVE]: '停用',
  [ChargingStationStatus.ACTIVE]: '启用'
} as const;

/**
 * 充电桩状态标签类型映射（用于Element Plus的el-tag组件）
 */
export const CHARGING_STATION_STATUS_TAG_TYPE = {
  [ChargingStationStatus.INACTIVE]: 'danger',
  [ChargingStationStatus.ACTIVE]: 'success'
} as const;