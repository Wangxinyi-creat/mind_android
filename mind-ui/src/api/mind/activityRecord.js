import request from '@/utils/request'

// 查询活动预约记录列表
export function listActivityRecord(query) {
  return request({
    url: '/mind/activityRecord/list',
    method: 'get',
    params: query
  })
}

// 查询活动预约记录详细
export function getActivityRecord(recordId) {
  return request({
    url: '/mind/activityRecord/' + recordId,
    method: 'get'
  })
}
