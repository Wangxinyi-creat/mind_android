import request from '@/utils/request'

// 查询咨询师列表
export function listCounselor(query) {
  return request({
    url: '/mind/counselor/list',
    method: 'get',
    params: query
  })
}

// 查询咨询师详细
export function getCounselor(counselorId) {
  return request({
    url: '/mind/counselor/' + counselorId,
    method: 'get'
  })
}

// 查询咨询师详细
export function getCounselorByUserId() {
  return request({
    url: '/mind/counselor/getCounselorByUserId',
    method: 'get'
  })
}

// 新增咨询师
export function addCounselor(data) {
  return request({
    url: '/mind/counselor',
    method: 'post',
    data: data
  })
}

// 修改咨询师
export function updateCounselor(data) {
  return request({
    url: '/mind/counselor',
    method: 'put',
    data: data
  })
}

// 删除咨询师
export function delCounselor(counselorId) {
  return request({
    url: '/mind/counselor/' + counselorId,
    method: 'delete'
  })
}
