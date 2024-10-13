import request from '@/utils/request'

// 查询校园心理活动列表
export function listActivity(query) {
  return request({
    url: '/mind/activity/list',
    method: 'get',
    params: query
  })
}

// 查询校园心理活动详细
export function getActivity(activityId) {
  return request({
    url: '/mind/activity/' + activityId,
    method: 'get'
  })
}

// 新增校园心理活动
export function addActivity(data) {
  return request({
    url: '/mind/activity',
    method: 'post',
    data: data
  })
}

// 修改校园心理活动
export function updateActivity(data) {
  return request({
    url: '/mind/activity',
    method: 'put',
    data: data
  })
}

// 删除校园心理活动
export function delActivity(activityId) {
  return request({
    url: '/mind/activity/' + activityId,
    method: 'delete'
  })
}
