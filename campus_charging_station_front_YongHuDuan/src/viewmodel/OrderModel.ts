/**
 * 订单实体模型类
 * 与后端com.tjetc.entity.core.Orders实体类对应
 */
export interface Order {
  /**
   * 订单ID
   */
  id?: number;

  /**
   * 用户ID
   */
  userId: string;

  /**
   * 充电桩ID
   */
  stationId: string;

  /**
   * 开始时间
   */
  startTime: string;

  /**
   * 结束时间
   */
  endTime: string;

  /**
   * 持续时间（分钟）
   */
  durationMinutes: string;

  /**
   * 总金额
   */
  totalAmount: number;

  /**
   * 支付状态
   */
  paymentStatus: string; // 对应后端PaymentStatus枚举

  /**
   * 订单状态
   */
  orderStatus: string; // 对应后端OrderStatus枚举

  /**
   * 创建时间
   */
  createTime: string;
}

/**
 * 订单状态枚举
 */
export enum OrderStatus {
  PENDING = 'PENDING',           // 待处理
  CONFIRMED = 'CONFIRMED',       // 已确认
  COMPLETED = 'COMPLETED',       // 已完成
  CANCELLED = 'CANCELLED',       // 已取消
  REFUNDED = 'REFUNDED'          // 已退款
}

/**
 * 支付状态枚举
 */
export enum PaymentStatus {
  UNPAID = 'UNPAID',             // 未支付
  PAID = 'PAID',                 // 已支付
  FAILED = 'FAILED',             // 支付失败
  REFUNDING = 'REFUNDING',       // 退款中
  REFUNDED = 'REFUNDED'          // 已退款
}